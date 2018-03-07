package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

   @Value("${trello.app.username}")
   private String trelloUserName;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<List<TrelloBoardDto>> getTrelloBoards() {

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildURL(), TrelloBoardDto[].class);
        List<TrelloBoardDto> listBoardsResponse = Arrays.asList(boardsResponse);

        return Optional.of(listBoardsResponse);
    }


        private URI buildURL(){
        URI url = UriComponentsBuilder.fromHttpUrl((trelloApiEndpoint)+"members/katarzynawojcik13/boards")
                //.queryParam("username",trelloUserName)
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
        return url;
    }
}