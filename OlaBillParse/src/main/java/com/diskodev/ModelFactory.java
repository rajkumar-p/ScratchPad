package com.diskodev;

import com.model.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    public static Credentials makeCredential(String partnerUserID,
                                             String partnerUserSecret) {
        Credentials credentials = new Credentials();

        credentials.setPartnerUserID(partnerUserID);
        credentials.setPartnerUserSecret(partnerUserSecret);

        return credentials;
    }

    public static Report makeReport(String title) {
        Report report = new Report();

        report.setTitle(title);

        return report;
    }

    public static Expense makeExpense(String date,
                                      String currency,
                                      String merchant,
                                      int amount,
                                      String category,
                                      String comment) {
        Expense expense = new Expense();

        expense.setDate(date);
        expense.setCurrency(currency);
        expense.setMerchant(merchant);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setComment(comment);

        return expense;
    }

    public static List<Expense> makeExpenses(List<OlaInvoice> olaInvoices) {
        List<Expense> expenses = new ArrayList<>();
        for (OlaInvoice olaInvoice : olaInvoices) {
            System.out.println(olaInvoice);

            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String expenseDate = olaInvoice.getInvoiceDateTime().format(dateTimeFormatter);
            int expenseAmount = (int) olaInvoice.getInvoiceAmount() * 100;
            String fileName = olaInvoice.getInvoiceFileName();

            expenses.add(ModelFactory.makeExpense(expenseDate, Constants.expenseCurrency,
                    Constants.expenseMerchant, expenseAmount, Constants.expenseCategory,
                    fileName));
        }

        return expenses;
    }

    public static InputSettings makeInputSettings(String type,
                                                  String policyID,
                                                  String employeeEmail,
                                                  Report report,
                                                  List<Expense> expenses) {
        InputSettings inputSettings = new InputSettings();

        inputSettings.setType(type);
        inputSettings.setPolicyID(policyID);
        inputSettings.setEmployeeEmail(employeeEmail);
        inputSettings.setReport(report);
        inputSettings.setExpenses(expenses);

        return inputSettings;
    }

    public static RequestJobDescription
        makeRequestJobDescription(String type,
                                  Credentials credentials,
                                  InputSettings inputSettings) {
        RequestJobDescription requestJobDescription =
                new RequestJobDescription();

        requestJobDescription.setType(type);
        requestJobDescription.setCredentials(credentials);
        requestJobDescription.setInputSettings(inputSettings);

        return requestJobDescription;
    }
}
