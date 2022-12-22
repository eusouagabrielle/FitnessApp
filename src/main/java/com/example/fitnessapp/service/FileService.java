package com.example.fitnessapp.service;

import com.example.fitnessapp.model.FileDocument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileService {
    FileDocument uploadFileDocument(MultipartFile file) throws IOException;
    ResponseEntity<byte[]> fileDownload(String fileName, HttpServletRequest request);
}
