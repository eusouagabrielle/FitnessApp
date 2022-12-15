package com.example.fitnessapp.repository;

import com.example.fitnessapp.model.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileDocument, Long> {
    FileDocument findByFileName(String fileName);
}
