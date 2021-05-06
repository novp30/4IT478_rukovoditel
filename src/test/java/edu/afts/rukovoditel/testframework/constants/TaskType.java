package edu.afts.rukovoditel.testframework.constants;

public enum TaskType {

    IDEA("Idea"),
    TASK("Task"),
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
