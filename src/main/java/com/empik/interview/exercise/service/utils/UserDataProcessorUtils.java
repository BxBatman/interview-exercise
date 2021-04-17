package com.empik.interview.exercise.service.utils;

import com.empik.interview.exercise.converter.UserDataApiToUserDataConverter;
import com.empik.interview.exercise.dto.UserDataDto;
import com.empik.interview.exercise.dto.UserDataDtoApi;
import com.empik.interview.exercise.exception.UserDataProcessorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static com.empik.interview.exercise.exception.Tokens.API_GET_REQUEST_ERROR;
import static com.empik.interview.exercise.exception.Tokens.USER_DATA_NOT_FOUND;

@Component
@Slf4j
public class UserDataProcessorUtils {

    private final String API_GITHUB_URL;
    private final RestTemplate restTemplate;

    public UserDataProcessorUtils(@Value("${api.github.url}") String API_GITHUB_URL, RestTemplate restTemplate) {
        this.API_GITHUB_URL = API_GITHUB_URL;
        this.restTemplate = restTemplate;
    }

    public double calculate(int followers, int publicRepos) {
        if (followers > 0) {
            return 6 * 1.0 / followers * (2 + publicRepos);
        } else {
            return 2 + publicRepos;
        }
    }

    public UserDataDtoApi getUserDataDtoApi(String login) throws UserDataProcessorException {
        ResponseEntity<UserDataDtoApi> apiData;
        try {
            apiData = restTemplate.exchange(API_GITHUB_URL + login, HttpMethod.GET, null, UserDataDtoApi.class);
        } catch (HttpStatusCodeException e) {
            log.error(e.getMessage(),e.getCause());
            throw new UserDataProcessorException(API_GET_REQUEST_ERROR);
        }
        UserDataDtoApi userDataApi = apiData.getBody();

        if (userDataApi == null) {
            throw new UserDataProcessorException(USER_DATA_NOT_FOUND);
        }

        return userDataApi;

    }

    public UserDataDto makeCalculationsAndConvertDto(UserDataDtoApi userDataApi) {
        userDataApi.setCalculations(calculate(userDataApi.getFollowers(), userDataApi.getPublicRepos()));
        return UserDataApiToUserDataConverter.convert(userDataApi);
    }
}
