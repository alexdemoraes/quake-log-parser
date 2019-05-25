package com.llabs.quakeparser.controller;

import com.llabs.quakeparser.service.IGameService;
import com.llabs.quakeparser.Utils;
import com.llabs.quakeparser.web.controller.GameController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    private Utils utils;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IGameService service;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void init() {
        utils = new Utils();
    }

    @Test
    public void listGameEntitiesSuccess() throws Exception {
        given(service.list()).
                willReturn(utils.gamesModelList2());

        mvc.perform( get("/games/entities").
                contentType(contentType)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(
                        jsonPath("$[1].kills[1].player").
                                value(utils.WORLD_PLAYER));
    }


    @Test
    public void listGameStatisticsSuccess() throws Exception {
        given(service.getStatistics()).
                willReturn(utils.gameStatisticsModelList2());


        mvc.perform( get("/games").
                contentType(contentType)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(
                        jsonPath("$[1].kills[1].name").
                                value(utils.FRED_PLAYER)).
                andExpect(
                        jsonPath("$[1].kills[1].count").
                                value(5L)).
                andExpect(
                        jsonPath("$[1].total_kills").
                                value(20L));

    }

    @Test
    public void listSpecificGameStatisticsSuccess() throws Exception {
        given(service.getStatistics(1)).
                willReturn(utils.gameStatisticsViewModel1());


        mvc.perform( get("/games/1").
                contentType(contentType)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(
                        jsonPath("$.kills[0].name").
                                value(utils.JASON_PLAYER)).
                andExpect(
                        jsonPath("$.kills[0].count").
                                value(5L)).
                andExpect(
                        jsonPath("$.total_kills").
                                value(10L));

    }

    @Test
    public void listSpecificGameStatisticsNotFound() throws Exception {
        given(service.getStatistics(314)).
                willReturn(null);

        mvc.perform( get("/games/314").
                contentType(contentType)).
                andDo(print()).
                andExpect(status().isNotFound());

    }

}
