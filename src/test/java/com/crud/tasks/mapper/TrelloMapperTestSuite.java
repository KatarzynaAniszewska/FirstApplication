package com.crud.tasks.mapper;
import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    TrelloList trelloList = new TrelloList("1", "ListToTest", true);
    TrelloListDto trelloListDto = new TrelloListDto("1" ,"ListDtoToTest", false);
    List<TrelloList> trelloLists = new ArrayList<>();
    List<TrelloBoard> trelloBoards = new ArrayList<>();
    List<TrelloListDto> trelloListsDto = new ArrayList<>();
    List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
    TrelloCard trelloCard = new TrelloCard("TestCardName", "testCardDescription", "testCardPos", "1");
    TrelloCardDto trelloCardDto = new TrelloCardDto("TestCardDtoName", "testCardDtoDescription",
            "testCardDtoPos", "1Dto");

    @Before
    public void init() {
        trelloLists.add(trelloList);
        trelloListsDto.add(trelloListDto);
        trelloBoards.add(new TrelloBoard("2", "boardToTest", trelloLists));
        trelloBoardsDto.add(new TrelloBoardDto("1", "boardDtoTest", trelloListsDto));
    }
    @Test
    public void mapToBoardsTest() {
        //When
        trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        Assert.assertEquals(1, trelloBoards.size());
        Assert.assertEquals("boardDtoTest", trelloBoards.get(0).getName());
    }
    @Test
    public void mapToBoardsDtoTest() {
        //When
        trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals(1, trelloBoardsDto.size());
        Assert.assertEquals("boardToTest", trelloBoardsDto.get(0).getName());
    }
    @Test
    public void mapToListTest() {

        //When
        trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        Assert.assertEquals(1, trelloLists.size());
        Assert.assertEquals("ListDtoToTest", trelloLists.get(0).getName());
    }
    @Test
    public void mapToListDtoTest() {
        //When
        trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(1, trelloListsDto.size());
        Assert.assertEquals("ListToTest", trelloListsDto.get(0).getName());
    }
    @Test
    public void mapToCardTest() {
        //When
        trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("TestCardDtoName", trelloCard.getName());
    }
    @Test
    public void mapToCardDtoTest() {
        //When
        trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("TestCardName", trelloCard.getName());
    }
}


