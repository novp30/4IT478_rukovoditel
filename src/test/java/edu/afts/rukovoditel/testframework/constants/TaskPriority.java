package edu.afts.rukovoditel.testframework.constants;

public enum TaskPriority {

    URGENT("Urgent"),
    HIGH("High"),
    MEDIUM("Medium");

    private final String value;

    TaskPriority(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
