package com.bezkoder.spring.files.excel.controller;

import com.bezkoder.spring.files.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@Order(0)
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final String FILE_NAME = "C:/Code/GitRepositories/spring-boot-upload-excel-files/src/main/resources/data.xlsx";

    @Autowired
    ExcelService fileService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            fileService.saveFile(excelFile);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
