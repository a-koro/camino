package com.korovesys.camino.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.korovesys.camino.model.Block;
import com.korovesys.camino.model.BlockType;
import com.korovesys.camino.model.Condition;
import com.korovesys.camino.model.FlowContext;

public class FlowExecutor extends AbstractFlowExecutor {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FlowContext flowContext;

    @Override
    public void execute(String flowName, HashMap<String, Object> params) {
        List<Block> blocks = flowContext.getFlows().get(flowName).getBlocks();

        Block initialBlock = blocks.stream()
                .filter(b -> b.getType().equals(BlockType.START))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No initial block found"));

        Block block = blocks.stream()
                .filter(b -> b.getId().equals(initialBlock.getNextId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No block found"));

        while (!block.getType().equals(BlockType.END)) {
            String nextBlockId;

            if (block.getType().equals(BlockType.INTERSECTION)) {
                Optional<Condition> condition = block.getConditions().stream()
                        .filter(c -> !c.getDefaultCondition())
                        .filter(c -> (Boolean) MVEL.eval(c.getExpression(), params))
                        .findFirst();

                if (condition.isEmpty())
                    condition = block.getConditions().stream()
                            .filter(Condition::getDefaultCondition)
                            .findFirst();

                nextBlockId = condition.orElseThrow(() -> new RuntimeException("No matching condition")).getNextId();
            } else {
                nextBlockId = block.getNextId();
            }

            if (block.getType().equals(BlockType.ACTION))
                ((AbstractActionHandler) applicationContext.getBean(block.getAction())).execute();

            block = blocks.stream()
                    .filter(b -> b.getId().equals(nextBlockId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No block found"));
        }
    }
}
