package com.korovesys.camino.model;

public class Condition {

    private String name;
    private String expression;
    private boolean defaultCondition;
    private String nextId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Boolean getDefaultCondition() {
        return defaultCondition;
    }

    public void setDefaultCondition(Boolean defaultCondition) {
        this.defaultCondition = defaultCondition;
    }

    public String getNextId() {
        return nextId;
    }

    public void setNextId(String nextId) {
        this.nextId = nextId;
    }
}
