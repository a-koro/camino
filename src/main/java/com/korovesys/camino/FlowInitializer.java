package com.korovesys.camino;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korovesys.camino.model.Flow;
import com.korovesys.camino.model.FlowContext;

import static com.korovesys.camino.validation.FlowValidator.isValid;

public class FlowInitializer {

    @Value("${camino.path:src/main/resources/camino}")
    private String path;

    @Autowired
    private FlowContext flowContext;

    @PostConstruct
    public void init() {
        find(Path.of(path), "json").forEachRemaining(file -> {
            String fileContent = "";
            try {
                fileContent = FileUtils.readFileToString(file, "UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                Flow flow = objectMapper.readValue(fileContent, Flow.class);
                isValid(flow);
                flowContext.getFlows().put(flow.getName(), flow);
                System.out.println("Initializing flow with name: " + flow.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Iterator<File> find(Path startPath, String extension) {
        if (!extension.startsWith(".")) {
            extension = "." + extension;
        }
        return FileUtils.iterateFiles(
                startPath.toFile(),
                WildcardFileFilter.builder().setWildcards("*" + extension).get(),
                TrueFileFilter.INSTANCE);
    }
}
