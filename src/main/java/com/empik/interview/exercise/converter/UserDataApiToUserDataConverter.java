package com.empik.interview.exercise.converter;

import com.empik.interview.exercise.dto.UserDataDtoApi;
import com.empik.interview.exercise.dto.UserDataDto;

public class UserDataApiToUserDataConverter {
    public static UserDataDto convert(UserDataDtoApi userDataApi) {
        return UserDataDto.builder()
                .id(userDataApi.getId())
                .login(userDataApi.getLogin())
                .name(userDataApi.getName())
                .type(userDataApi.getType())
                .avatarUrl(userDataApi.getAvatarUrl())
                .createdAt(userDataApi.getCreatedAt())
                .calculations(userDataApi.getCalculations()).build();
    }
}
