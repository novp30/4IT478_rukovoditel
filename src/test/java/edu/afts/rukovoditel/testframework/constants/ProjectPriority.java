package edu.afts.rukovoditel.testframework.constants;

public enum ProjectPriority {

    URGENT("Urgent"),
    HIGH("High");

    private final String value;

    ProjectPriority(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
