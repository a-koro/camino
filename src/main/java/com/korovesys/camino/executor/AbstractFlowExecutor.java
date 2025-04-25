package com.korovesys.camino.executor;

import java.util.HashMap;

public abstract class AbstractFlowExecutor {

    public abstract void execute(String flowName, HashMap<String, Object> ctx);
}
