package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Mock
    private Logger logger;

    @Test
    void shouldFilterBoard() {
        //given
        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards =
                List.of(new TrelloBoard("1", "test", trelloLists));
        //when
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        assertEquals(0, validatedBoards.size());
    }
    @Test
    void shouldNotFilterBoard() {
        //given
        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards =
                List.of(new TrelloBoard("1", "board name", trelloLists));
        //when
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        assertEquals(1, validatedBoards.size());
    }
    @Test
    void validateEmptyBoard() {
        //given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        //when
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        assertEquals(0, validatedBoards.size());
    }
}
