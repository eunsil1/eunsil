package com.example.imggallery.repository;

import com.example.imggallery.domain.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryImageRepository extends JpaRepository<GalleryImage, Long> {
    List<GalleryImage> findAllByOrderByCreatedAtDesc();
}
