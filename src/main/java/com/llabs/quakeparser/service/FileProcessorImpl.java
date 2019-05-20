package com.llabs.quakeparser.service;

import com.llabs.quakeparser.web.model.GameViewModel;
import com.llabs.quakeparser.web.model.KillViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component("fileProcessor")
public class FileProcessorImpl {

    private static Logger logger = LoggerFactory.getLogger(FileProcessorImpl.class);

    @Autowired
    private ILineProcessor fileLineProcessor;

    @Autowired
    private IGameService gameService;

    @Bean
    public CommandLineRunner fillDatabase(ApplicationContext ctx) {

        Path file;

        List<GameViewModel> games = new ArrayList<>();

        try (Stream<Path> path = Files.list(Paths.get("requirements"))){
            Stream<Path> paths = path.filter(p1 -> p1.getFileName().toString().matches(".*\\.log"));
            logger.info("paths: " + paths);
            if (paths != null && (file = paths.findFirst().get()) != null ) {
                logger.info("File: " + file);

                List<String> lines = Files.readAllLines(file);

                GameViewModel game = null;
                KillViewModel kill = null;
                for (String line : lines) {
                    String time;
                    if ((time = fileLineProcessor.getStart(line)) != null) {
                        game = new GameViewModel();
                        game.setStart(time);
                    } else if ((kill = fileLineProcessor.getKill(line)) != null) {
                        game.getKills().add(kill);
                    } else if ((time = fileLineProcessor.getFinish(line)) != null) {
                        game.setFinish(time);
                        games.add(game);
                        game = null;
                    }
                }
            }

            gameService.create(games);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

        return null;
    }

}
