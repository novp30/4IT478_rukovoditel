package edu.afts.rukovoditel.testframework.constants;

public enum TaskStatus {

    NEW("New"),
    OPEN("Open"),
    WAITING("Waiting"),
    CLOSED("Closed"),
    CANCELED("Canceled"),
    DONE("Done"),
    PAID("Paid");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
