package com.empik.interview.exercise.repository;

import com.empik.interview.exercise.entity.RequestCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCounterRepository extends JpaRepository<RequestCounter,Long> {
    RequestCounter getByLogin(String login);
}
