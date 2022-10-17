package com.bezkoder.spring.files.upload.db.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bezkoder.spring.files.upload.db.service.FileStorageService;
import com.bezkoder.spring.files.upload.db.message.ResponseFile;
import com.bezkoder.spring.files.upload.db.message.ResponseMessage;
import com.bezkoder.spring.files.upload.db.model.FileDB;

@Controller
@CrossOrigin("http://localhost:4200")
public class FileController {

  @Autowired
  private FileStorageService storageService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("client") String client,@RequestParam("country") String country,@RequestParam("date") Date date) {
    String message = "";
    try {
      storageService.store(file,client,country,date);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/filesStream")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFile().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("files/")
          .path(""+dbFile.getId())
          .toUriString();  
      BufferedReader reader;
      
      StringBuilder resultStringBuilder = new StringBuilder();
      try {
			reader = new BufferedReader(new FileReader(
					"C:/Users/pc/Downloads/"+dbFile.getName()));
			String line;
			
	        while ((line = reader.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
      } catch (IOException e) {
    	  e.printStackTrace();
	}
     
      return new ResponseFile(dbFile.getName(), 
    		  fileDownloadUri, 
    		  dbFile.getType(), 
    		  dbFile.getData().length, 
    		  dbFile.getClient(), 
    		  dbFile.getCountry(),
    		  resultStringBuilder.toString())
    		  ;}).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable int id) {
    FileDB fileDB = storageService.getFile(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }
  
  @GetMapping("/files")
  public ResponseEntity<List<FileDB>> getAllFiles(){
	  List<FileDB> files = storageService.getAllFiles();
	  return new ResponseEntity<List<FileDB>>(files, HttpStatus.OK);
  }
}
