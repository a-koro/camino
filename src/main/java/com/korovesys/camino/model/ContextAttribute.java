package com.korovesys.camino.model;

public enum ContextAttribute {

    FLOW_NAME("flowName"),
    GUID("guid");

    public final String name;

    ContextAttribute(String name) {
        this.name = name;
    }
}
