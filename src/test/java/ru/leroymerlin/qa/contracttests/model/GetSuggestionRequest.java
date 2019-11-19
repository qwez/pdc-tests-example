package ru.leroymerlin.qa.contracttests.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.leroymerlin.qa.contracttests.SuggestionControllerGetSuggestions;

@AllArgsConstructor
@Getter
public class GetSuggestionRequest {
    private String keyPhrase;
    private Integer size;
}
