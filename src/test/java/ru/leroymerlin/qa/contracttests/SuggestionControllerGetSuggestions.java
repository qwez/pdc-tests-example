package ru.leroymerlin.qa.contracttests;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.leroymerlin.qa.contracttests.model.GetSuggestionRequest;
import ru.leroymerlin.qa.contracttests.utils.*;

import java.util.List;
import java.util.Map;

public class SuggestionControllerGetSuggestions {

    private final String SWAGGER_SPEC_URL = "https://aapps-emb.lmru.adeo.com/opus/api/search-suggestions/v2/api-docs";
    private final String API_BASE_URL = "https://aapps-emb.lmru.adeo.com/opus/api/search-suggestions";
    private final String GET_SUGGESTIONS_PATH = "/suggestions";

    private RestTemplate restTemplate;

    private OpenApiValidationClientHttpRequestInterceptor validationInterceptor() {
        return new OpenApiValidationClientHttpRequestInterceptor(
//                OpenApiInteractionValidator.createForSpecificationUrl(SWAGGER_SPEC_URL).build()
                OpenApiInteractionValidator.createFor("ru/leroymerlin/qa/contracttests/swagger.json").build()
        );
    }

    private <T> HttpEntity<T> getRequest(T body) {
        return new HttpEntity<>(
                body,
                CollectionUtils.toMultiValueMap(Map.of("Content-Type", List.of("application/json")))
        );
    }

    @BeforeClass
    public void setUpRestTemplate() {
        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory(API_BASE_URL)
        );
        restTemplate.setRequestFactory(RestUtils.getRestFactory());
        restTemplate.setInterceptors(List.of(validationInterceptor()));
    }

    @Test
    public void test() {
        var requestBody = new GetSuggestionRequest("inspire", 10);
        final ResponseEntity<String> response = restTemplate.exchange(
                GET_SUGGESTIONS_PATH, HttpMethod.POST, getRequest(requestBody), String.class
        );
        System.out.println(response.getBody());
    }
}
