package com.korovesys.camino.model;

import java.util.List;

public class Flow {

    private String name;
    private List<Block> blocks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
