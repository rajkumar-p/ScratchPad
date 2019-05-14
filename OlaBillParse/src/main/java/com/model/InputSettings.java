package com.model;

import java.util.List;

public class InputSettings {
    String type;
    String policyID;
    Report report;
    String employeeEmail;

    List<Expense> expenses;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
