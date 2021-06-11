package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldNotCreateTrelloCard() {
        //given
        TrelloCardDto cardDto = new TrelloCardDto("name", "desc", "pos1", "1");
        when(trelloClient.createNewCard(cardDto)).thenReturn(null);
        //when
        CreatedTrelloCardDto newCard = trelloService.createdTrelloCard(cardDto);
        //then
        assertNull(newCard);
    }
    @Test
    void shouldCreateTrelloCard() {
        //given
        TrelloCardDto cardDto = new TrelloCardDto("name", "desc", "pos1", "1");
        CreatedTrelloCardDto createdCardDto =
                new CreatedTrelloCardDto("1", "name", "http://test.com");

        when(trelloClient.createNewCard(cardDto)).thenReturn(createdCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test_admin@test.com");
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .subject("test")
                .message("Test message")
                .toCc("test22@test.com")
                .build();

        //when
        CreatedTrelloCardDto newCard = trelloService.createdTrelloCard(cardDto);
        //then
        assertEquals("1", newCard.getId());
        assertEquals("name", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }
}
