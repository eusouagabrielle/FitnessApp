package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.FileDocument;
import com.example.fitnessapp.repository.FileRepository;
import com.example.fitnessapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Objects;

@Service
public class fileServiceImpl implements FileService {
    private FileRepository document;

    @Autowired
    public fileServiceImpl(FileRepository document) {
        this.document = document;
    }

    @Override
    public FileDocument uploadFileDocument(MultipartFile file) throws IOException {
        MediaType uploadType = MediaType.parseMediaType(file.getContentType());
        String correctMediaType = "application/pdf";

        if (!correctMediaType.equals(uploadType.toString())){
            throw new InvalidPropertiesFormatException("Wrong file type, PDF required");
        }else {
            String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(name);
            fileDocument.setDocFile(file.getBytes());
            document.save(fileDocument);
            return fileDocument;
        }
    }

    @Override
    public ResponseEntity<byte[]> fileDownload(String fileName, HttpServletRequest request) {
        FileDocument doc = document.findByFileName(fileName);
        MediaType contentType = MediaType.APPLICATION_PDF;

        String mimeType = request.getServletContext().getMimeType(doc.getFileName());
        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;fileName=" + doc.getFileName()).body(doc.getDocFile());
    }
}
