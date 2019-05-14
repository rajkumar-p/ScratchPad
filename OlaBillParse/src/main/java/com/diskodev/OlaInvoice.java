package com.diskodev;

import java.time.LocalDateTime;

public class OlaInvoice {
    private double invoiceAmount;
    private String invoiceID;
    private LocalDateTime invoiceDateTime;
    private String invoiceFileName;

    public OlaInvoice(double invoiceAmount, String invoiceID,
                      LocalDateTime invoiceDateTime, String invoiceFileName) {
        this.invoiceAmount = invoiceAmount;
        this.invoiceID = invoiceID;
        this.invoiceDateTime = invoiceDateTime;
        this.invoiceFileName = invoiceFileName;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public LocalDateTime getInvoiceDateTime() {
        return invoiceDateTime;
    }

    public void setInvoiceDateTime(LocalDateTime invoiceDateTime) {
        this.invoiceDateTime = invoiceDateTime;
    }

    public String toString() {
        return String.format("<%s, %s, %s, %s>",
                this.getInvoiceDateTime(), this.getInvoiceID(),
                this.getInvoiceAmount(), this.getInvoiceFileName());
    }

    public String getInvoiceFileName() {
        return invoiceFileName;
    }

    public void setInvoiceFileName(String invoiceFileName) {
        this.invoiceFileName = invoiceFileName;
    }
}
