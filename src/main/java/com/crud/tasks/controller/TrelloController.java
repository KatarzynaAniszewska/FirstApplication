package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;

import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
//po utworzeniu TrelloFacade, TrelloService został podmieniony na TrelloFacade
    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards= trelloFacade.fetchTrelloBoards();

        /*trelloBoards.stream()
                .filter(i->i.getId()!= null)
                .filter(n->n.getName()!=null)
                .filter(k->k.getName().startsWith("Kod"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
        });*/
        return trelloBoards;
    }
   /* @RequestMapping (method = RequestMethod.POST,value = "createTrelloCard")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }*/
    @RequestMapping (method = RequestMethod.POST,value = "createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createCard(trelloCardDto);

        // System.out.println(trelloCard.getTrelloBadgesDto().getAttachmentsByTypeDto().getTrelloDto().getBoard());
        //System.out.println(trelloCard.getTrelloBadgesDto().getAttachmentsByTypeDto().getTrelloDto().getCard());

    }

}
