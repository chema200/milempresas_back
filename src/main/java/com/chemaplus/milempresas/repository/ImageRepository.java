package com.chemaplus.milempresas.repository;

import java.util.Optional;

import com.chemaplus.milempresas.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
