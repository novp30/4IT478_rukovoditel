package edu.afts.rukovoditel.testframework.constants;

public enum ProjectStatus {

    NEW("New"),
    OPEN("Open"),
    WAITING("Waiting"),
    CLOSED("Closed"),
    CANCELED("Canceled");

    private final String value;

    ProjectStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
