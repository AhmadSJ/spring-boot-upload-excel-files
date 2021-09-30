package com.bezkoder.spring.files.excel.controller;

import com.bezkoder.spring.files.excel.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
@Order(0)
@Slf4j
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    static String path = "src/test/resources";

    static File resource_directory = new File(path);
    static String path_to_resources = resource_directory.getAbsolutePath();

    private static final String FILE_NAME_TUTORIAL = path_to_resources + "/data.xlsx";
    public static final String FILE_NAME_COMPETENT = path_to_resources + "/CompetentNL_core_hard_soft_skills_v0.3_edited.xlsx";

    @Autowired
    ExcelService fileService;

    public void insert_data_and_check() {
        log.info("Data insertions on start-up started.");
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_TUTORIAL));
            fileService.saveFile(excelFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_COMPETENT));
            fileService.excelToDatabase(excelFile);
            log.info("Data insertions on start-up complete.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            log.info("Comparison on start-up started.");
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_COMPETENT));
            log.info("Comparison on start-up complete. Excel file is equal to the Database: " + fileService.compareExcelToDatabase(excelFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        insert_data_and_check();
    }
}
