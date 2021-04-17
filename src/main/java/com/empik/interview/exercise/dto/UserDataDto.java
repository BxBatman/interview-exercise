package com.empik.interview.exercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class UserDataDto implements Serializable {
    private long id;
    private String login;
    private String name;
    private String type;
    @JsonProperty(value = "avatar_url")
    private String avatarUrl;
    @JsonProperty(value = "created_at")
    private String createdAt;
    private double calculations;
}
