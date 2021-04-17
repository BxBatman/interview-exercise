package com.empik.interview.exercise.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REQUEST_COUNTER")
public class RequestCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "REQUEST_COUNT")
    private int requestCount;
}
