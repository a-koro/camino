package com.korovesys.camino.model;

import java.util.HashMap;
import java.util.Map;

public class FlowContext {

    private final Map<String, Flow> flows;

    public FlowContext() {
        flows = new HashMap<>();
    }

    public Map<String, Flow> getFlows() {
        return flows;
    }
}
