package com.diskodev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.model.*;
import org.apache.commons.cli.ParseException;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * OlaBillParse
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        try {
            Cli cli = new Cli();
            cli.parseArgs(args);

            if (cli.hasOption("help")) {
                cli.printHelpAndExit();
            }

            String invoicesDirectory = cli.getOptionValue("invoicesDir");
            String excelTemplateFileName = cli.getOptionValue("invoiceTemplate");
            String invoiceMonth = cli.getOptionValue("invoiceMonth");

            if (Utils.isNull(invoicesDirectory) ||
                    Utils.isNull(excelTemplateFileName) ||
                    Utils.isNull(invoiceMonth)) {
                cli.printHelpAndExit();
            }

            List<OlaInvoice> olaInvoices =
                    OlaInvoiceExtractor.getInvoiceForEachFile(invoicesDirectory);
            olaInvoices.sort(Comparator.comparing(OlaInvoice::getInvoiceDateTime));

            double sum = olaInvoices.stream()
                    .mapToDouble(OlaInvoice::getInvoiceAmount)
                    .sum();

            System.out.println("Total Sum : " + sum);

            ExcelInvoiceCreator.createExcelWithInvoiceDetails(invoicesDirectory,
                    excelTemplateFileName,
                    invoiceMonth,
                    olaInvoices);

            System.out.println("Created invoice file.");

            if (cli.hasOption("createExpensifyReport")) {
                String reportName = String.format("%s - %s",
                        "Cab Reimbursement for office Commute", invoiceMonth);

                String partnerUserIDFromEnv = System.getenv("PARTNER_USER_ID");
                String partnerUserSecretFromEnv = System.getenv("PARTNER_USER_SECRET");
                String policyIDFromEnv = System.getenv("POLICY_ID");
                String employeeEmailFromEnv = System.getenv("EMPLOYEE_EMAIL");

                System.out.println(partnerUserIDFromEnv);
                System.out.println(partnerUserSecretFromEnv);
                System.out.println(policyIDFromEnv);
                System.out.println(employeeEmailFromEnv);

                if (Utils.isNull(partnerUserIDFromEnv) ||
                        Utils.isNull(partnerUserSecretFromEnv) ||
                        Utils.isNull(policyIDFromEnv) ||
                        Utils.isNull(employeeEmailFromEnv)) {
                    System.out.println(String.format("%s, %s, %s, %s environment vars needs to be set",
                            "PARTNER_USER_ID", "PARTNER_USER_SECRET",
                            "POLICY_ID", "EMPLOYEE_EMAIL"));
                    return;
                }

                Credentials credentials = ModelFactory.makeCredential(partnerUserIDFromEnv,
                        partnerUserSecretFromEnv);
                Report report = ModelFactory.makeReport(reportName);
                List<Expense> expenses = ModelFactory.makeExpenses(olaInvoices);
                InputSettings inputSettings = ModelFactory.makeInputSettings(Constants.reportType,
                        policyIDFromEnv, employeeEmailFromEnv, report,
                        expenses);
                RequestJobDescription requestJobDescription =
                        ModelFactory.makeRequestJobDescription(Constants.requestJobDescriptionType,
                                credentials, inputSettings);

                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String requestJobDescriptionStr = ow.writeValueAsString(requestJobDescription);
                String inputPayload = String.format("%s=%s",
                        "requestJobDescription", requestJobDescriptionStr);

                System.out.println(inputPayload);
                String url = Constants.apiURL;
                HttpPost postReq = new HttpPost(url);

                List<NameValuePair> nvps = new ArrayList<>();
                nvps.add(new BasicNameValuePair("requestJobDescription", requestJobDescriptionStr));
                postReq.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(postReq);

                System.out.println(response);
                System.out.println(response.getEntity().getContent());

                client.close();
            }
        } catch (JsonProcessingException|
                UnsupportedEncodingException|
                ClientProtocolException|
                ParseException e) {
            Utils.printExceptionMessage(e);
            System.exit(1);
        } catch (IOException e) {
            Utils.printExceptionMessage(e);
            System.exit(1);
        }
    }
}
