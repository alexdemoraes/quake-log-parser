package com.llabs.quakeparser.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service("fileService")
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    public Path getFile(String folder) {
        Path file = null;
        try (Stream<Path> path = Files.list(Paths.get(folder))) {
                Stream<Path> paths = path.filter(p1 -> p1.getFileName().toString().matches(".*\\.log"));
                logger.info("paths: " + paths);
            if (paths != null) {
                file = paths.findFirst().get();
            }
        } catch (IOException ioe) {
            logger.error("Error looking for .log files in [" + folder + "]");
        }
        return file;
    }

}
