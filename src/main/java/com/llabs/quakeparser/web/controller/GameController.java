package com.llabs.quakeparser.web.controller;

import com.llabs.quakeparser.service.IGameService;
import com.llabs.quakeparser.web.model.GameStatisticsViewModel;
import com.llabs.quakeparser.web.model.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/games",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GameController {

    private static final String NOT_FOUND_MESSAGE = "Game Not Found";

    @Autowired
    private IGameService gameService;

    /**
     * Lists all game entities and corresponding kills
     */
    @GetMapping("/entities")
    public ResponseEntity<List<GameViewModel>> getEntities() {
        List<GameViewModel> games = gameService.list();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Lists statistics of all games
     */
    @GetMapping("/")
    public ResponseEntity<List<GameStatisticsViewModel>> getStatistics() {
        List<GameStatisticsViewModel> games = gameService.getStatistics();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Shows statistics of a specific game
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameStatisticsViewModel> getGameStatistics(@PathVariable Integer id) {
        GameStatisticsViewModel game = gameService.getById(id);
        if (game == null) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
