package com.empik.interview.exercise.service.inf;

import com.empik.interview.exercise.dto.UserDataDto;
import com.empik.interview.exercise.exception.UserDataProcessorException;

public interface UserDataProcessor {
    UserDataDto processData(String login) throws UserDataProcessorException;
}
