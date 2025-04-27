package com.korovesys.camino.validation;

import com.korovesys.camino.model.Block;
import com.korovesys.camino.model.BlockType;
import com.korovesys.camino.model.Condition;
import com.korovesys.camino.model.Flow;

public class FlowValidator {

    public static void isValid(Flow flow) {

        flow.getBlocks().stream()
                .filter(b -> b.getType() == null || b.getId() == null)
                .findFirst()
                .ifPresent(block -> {
                    throw new RuntimeException();
                });

        flow.getBlocks().stream()
                .filter(b -> BlockType.ACTION.equals(b.getType()) || BlockType.INTERSECTION.equals(b.getType()))
                .filter(b -> b.getName() == null)
                .findFirst()
                .ifPresent(block -> {
                    throw new RuntimeException();
                });

        flow.getBlocks().stream()
                .filter(b -> BlockType.START.equals(b.getType()) || BlockType.ACTION.equals(b.getType()))
                .filter(b -> b.getNextId() == null)
                .findFirst()
                .ifPresent(block -> {
                    throw new RuntimeException();
                });

        long distinctIds = flow.getBlocks().stream()
                .map(Block::getId)
                .distinct()
                .count();

        int blocksCount = flow.getBlocks().size();

        if (blocksCount != distinctIds)
            throw new RuntimeException();

        flow.getBlocks().stream()
                .filter(b -> b.getType().equals(BlockType.INTERSECTION))
                .forEach(b -> {
                    b.getConditions().stream()
                            .filter(c -> c.getName() == null || c.getNextId() == null)
                            .findFirst()
                            .ifPresent(c -> {
                                throw new RuntimeException();
                            });
                    b.getConditions().stream()
                            .filter(c -> c.getDefaultCondition() == null && c.getExpression() == null)
                            .findFirst()
                            .ifPresent(c -> {
                                throw new RuntimeException();
                            });

                    long defaultConditionCount = b.getConditions().stream()
                            .filter(Condition::getDefaultCondition)
                            .count();

                    if (defaultConditionCount > 1)
                        throw new RuntimeException();
                });
    }
}
