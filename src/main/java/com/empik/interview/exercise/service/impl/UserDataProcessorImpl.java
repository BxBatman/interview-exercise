package com.empik.interview.exercise.service.impl;

import com.empik.interview.exercise.dto.UserDataDto;
import com.empik.interview.exercise.dto.UserDataDtoApi;
import com.empik.interview.exercise.entity.RequestCounter;
import com.empik.interview.exercise.exception.UserDataProcessorException;
import com.empik.interview.exercise.repository.RequestCounterRepository;
import com.empik.interview.exercise.service.inf.UserDataProcessor;
import com.empik.interview.exercise.service.utils.UserDataProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDataProcessorImpl implements UserDataProcessor {

    private final RequestCounterRepository requestCounterRepository;
    private final UserDataProcessorUtils userDataProcessorUtils;

    public UserDataProcessorImpl(RequestCounterRepository requestCounterRepository, UserDataProcessorUtils userDataProcessorUtils) {
        this.requestCounterRepository = requestCounterRepository;
        this.userDataProcessorUtils = userDataProcessorUtils;
    }

    @Override
    public UserDataDto processData(String login) throws UserDataProcessorException {
        UserDataDtoApi userDataApi = userDataProcessorUtils.getUserDataDtoApi(login);
        saveWithIncrement(userDataApi.getLogin());
        return userDataProcessorUtils.makeCalculationsAndConvertDto(userDataApi);
    }

    private void saveWithIncrement(String login) {
        RequestCounter requestCounter = requestCounterRepository.getByLogin(login);
        if (requestCounter == null) {
            requestCounter = RequestCounter
                    .builder()
                    .requestCount(1)
                    .login(login)
                    .build();
        } else {
            int requestCount = requestCounter.getRequestCount();
            requestCount++;
            requestCounter.setRequestCount(requestCount);
        }
        requestCounterRepository.save(requestCounter);
    }
}
