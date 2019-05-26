package com.llabs.quakeparser.processor;

import com.llabs.quakeparser.service.FileService;
import com.llabs.quakeparser.service.IGameService;
import com.llabs.quakeparser.service.ILineProcessor;
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
import java.util.ArrayList;
import java.util.List;

@Component("fileProcessor")
public class FileProcessorImpl {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessorImpl.class);

    @Autowired
    private ILineProcessor fileLineProcessor;

    @Autowired
    private IGameService gameService;

    @Autowired
    private FileService fileService;

    @Bean
    public CommandLineRunner fillDatabase(ApplicationContext ctx) {

        final String logFolder = "requirements";
        Path file = fileService.getFile(logFolder);
        try {
            if (file != null) {
                List<String> lines = Files.readAllLines(file);
                List<GameViewModel> games = new ArrayList<>();

                GameViewModel game = null;
                KillViewModel kill = null;
                for (String line : lines) {
                    String time;
                    if ((time = fileLineProcessor.getStart(line)) != null) {
                        game = new GameViewModel();
                        game.setStart(time);
                    } else if ((kill = fileLineProcessor.getKill(line)) != null) {
                        game.getKills().add(kill);
                    } else if ((time = fileLineProcessor.getFinish(line)) != null ||
                            (game != null && line.contains("------------"))) {
                        game.setFinish(time);
                        games.add(game);
                        game = null;
                    }
                }
                gameService.create(games);
            } else {
                logger.warn("Não há arquivos na pasta");
            }

        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

        return null;
    }

}
