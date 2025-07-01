package com.codegym.thuchanh2.repository;

import com.codegym.thuchanh2.model.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISmartphoneRepository extends JpaRepository<Smartphone, Long> {
}
