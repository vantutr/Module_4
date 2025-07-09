package com.codegym.baitap1.repository;

import com.codegym.baitap1.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findByLocationIgnoreCase(String location);
}

