package com.bezkoder.spring.files.excel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.excel.helper.ExcelHelper;
import com.bezkoder.spring.files.excel.message.ResponseMessage;
import com.bezkoder.spring.files.excel.model.Tutorial;
import com.bezkoder.spring.files.excel.service.ExcelService;

import static com.bezkoder.spring.files.excel.controller.MyApplicationListener.FILE_NAME_COMPETENT;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/excel")
@Slf4j
public class ExcelController {

  @Autowired
  ExcelService fileService;

  private static final String FILE_NAME_TUTORIAL =
          "C:/Code/GitRepositories/spring-boot-upload-excel-files/src/main/resources/data.xlsx";
  public static final String FILE_NAME_COMPETENT =
          "C:/Code/GitRepositories/spring-boot-upload-excel-files/src/main/resources/CompetentNL_core_hard_soft_skills_v0.3_edited.xlsx";


  @GetMapping("/upload")
  public ResponseEntity<String> compare(){
    try {
      FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_COMPETENT));
      log.info("The excel sheet is equal to the database: " + fileService.compareExcelToDatabase(excelFile));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.OK).body("comparison finished");
  }


  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (ExcelHelper.hasExcelFormat(file)) {
      try {
        fileService.save(file);
        System.out.println("saved the file");
        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        System.out.println(e);
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }

    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials() {
    try {
      List<Tutorial> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/download")
  public ResponseEntity<Resource> getFile() {
    String filename = "tutorials.xlsx";
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(file);
  }

}
