package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.client.TrelloClient;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloSeviceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    SimpleEmailService simpleEmailService;
    @Mock
    Mail mail;

    @Test
    public void fetchTrelloBoardTestSuite() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals("test",trelloBoardDtoList.get(0).getName());
    }
    @Test
    public void createdTrelloCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TrelloCardDto_name",
                "TrelloCardDto_description","TrelloCardDto_pos","TrelloCardDto_listId");
        TrelloCard trelloCard = new TrelloCard("TrelloCard_name",
                "TrelloCard_description","TrelloCard_pos","TrelloCard_listId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("CreatedCard_id",
            "CreatedCard_name","CreatedCard_url");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDtoTest = trelloService.createdTrelloCard(trelloCardDto);

        assertEquals("CreatedCard_name",createdTrelloCardDtoTest.getName());
    }
}
