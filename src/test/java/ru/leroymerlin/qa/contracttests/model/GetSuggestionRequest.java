package ru.leroymerlin.qa.contracttests.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetSuggestionRequest {
    private String keyPhrase;
    private Integer size;
}
