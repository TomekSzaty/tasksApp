package com.crud.tasks.config;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    void testTrelloConfigGetters() {
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloToken  = trelloConfig.getTrelloToken();

        //Then
        assertEquals("https://api.trello.com/1",trelloApiEndpoint);
        assertEquals("830be811092be8c53a53e95b27ad84ce",trelloAppKey);
        assertEquals("32fbe791f365bc654fe3675be32fe83c0b093c0c53d1b2a3d6bb47ef18686c16",trelloToken);

    }
}
