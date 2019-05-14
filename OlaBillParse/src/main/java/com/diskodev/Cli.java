package com.diskodev;

import org.apache.commons.cli.*;

public class Cli {
    Options options;
    CommandLine cmd;

    public Cli() {
        this.initCliArgs();
    }

    public void initCliArgs() {
        this.addAppOptions();
    }

    public void addAppOptions() {
        this.options = new Options();
        this.options.addOption(new Option("h", "help",
                false, "Prints help info."));
        this.options.addOption(new Option("invoicesDir", "invoicesDir",
                true, "Directory where the invoices are present."));
        this.options.addOption(new Option("invoiceTemplate", "invoiceTemplate",
                true, "Template from which to generate the final invoice."));
        this.options.addOption(new Option("invoiceMonth", "invoiceMonth",
                true, "<month yyyy> of the final invoice."));
        this.options.addOption(new Option("cer", "createExpensifyReport",
                false, "Create Expensify report."));
    }

    public void parseArgs(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        cmd = parser.parse(this.getOptions(), args);
    }

    public Options getOptions() {
        return this.options;
    }

    public boolean hasOption(String opt) {
        return cmd.hasOption(opt);
    }

    public String getOptionValue(String opt) {
        return cmd.getOptionValue(opt);
    }

    public void printHelp() {
        String command = String.format("%s %s=%s %s=%s %s=%s",
                "OlaBillParse",
                "--invoiceMonth", "monthStr",
                "--invoicesDir", "invoiceDirLoc",
                "--invoiceTemplate", "invoiceTemplateLoc");
        HelpFormatter help = new HelpFormatter();
        help.printHelp(command, this.getOptions());
    }

    public void printHelpAndExit() {
        printHelp();
        System.exit(0);
    }
}
