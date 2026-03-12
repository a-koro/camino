package com.korovesys.camino.executor;

import java.util.HashMap;

public abstract class AbstractActionHandler {

    public abstract void execute(HashMap<String, Object> ctx);
}
