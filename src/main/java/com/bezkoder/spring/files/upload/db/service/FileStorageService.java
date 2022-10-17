package com.bezkoder.spring.files.upload.db.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.upload.db.model.FileDB;
import com.bezkoder.spring.files.upload.db.repository.FileDBRepository;

@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;
        
  public FileDB store(MultipartFile file,String client,String country,Date date) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName, client,country, file.getContentType(), file.getBytes(),date);

    return fileDBRepository.save(FileDB);
  }    

  public FileDB getFile(int id) {
    return fileDBRepository.findById(id).get();
  }
              
  public List<FileDB> getAllFiles() {
    return fileDBRepository.findAll();
    
  }
  
  public Stream<FileDB> getAllFile() {
	    return fileDBRepository.findAll().stream();
	    
	  }
}