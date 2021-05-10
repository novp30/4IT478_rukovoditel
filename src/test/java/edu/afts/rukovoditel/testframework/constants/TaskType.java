package edu.afts.rukovoditel.testframework.constants;

public enum TaskType {

    TASK("Task"),
    IDEA("Idea"),
    CHANGE("Change"),
    BUG("Bug");

    private final String value;

    TaskType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
