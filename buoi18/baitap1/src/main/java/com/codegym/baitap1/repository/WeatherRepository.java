package com.codegym.baitap1.repository;

import com.codegym.baitap1.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Weather, String> {
}
