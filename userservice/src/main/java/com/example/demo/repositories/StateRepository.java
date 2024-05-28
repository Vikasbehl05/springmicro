package com.example.demo.repositories;

import com.example.demo.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository  extends JpaRepository<State, Long> {
    Optional<State> findByStateName(String stateName);
}
