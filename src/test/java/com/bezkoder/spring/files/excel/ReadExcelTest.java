package com.bezkoder.spring.files.excel;


import com.bezkoder.spring.files.excel.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class ReadExcelTest {

    static String path = "src/test/resources";

    static File resource_directory = new File(path);
    static String path_to_resources = resource_directory.getAbsolutePath();

    private static final String FILE_NAME_TUTORIAL = path_to_resources + "/data.xlsx";
    public static final String FILE_NAME_COMPETENT = path_to_resources + "/CompetentNL_core_hard_soft_skills_v0.3_edited.xlsx";

    @Autowired
    ExcelService fileService;

    @Value("${foo}")
    String foo;

    @Test
    public void ReadExcelAndPersistToDatabase_ThenReadExcelAndCompareToDatabase() {
        log.info("Data insertions on test started");
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_TUTORIAL));
            fileService.saveFile(excelFile);
        } catch (FileNotFoundException e) {
            Assertions.fail("Exception" + e);
        }
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_COMPETENT));
            fileService.excelToDatabase(excelFile);
            log.info("Data insertions on test complete.");
        } catch (FileNotFoundException e) {
            Assertions.fail("Exception" + e);
        }
        try {
            log.info("Comparison on test started.");
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_COMPETENT));
            boolean test = fileService.compareExcelToDatabase(excelFile);
            log.info("Comparison from the unit test complete. Excel sheet contents and the database are the same: " + test);
            assertThat(test).isEqualTo(true);
        } catch (FileNotFoundException e) {
            Assertions.fail("Exception" + e);
        }
    }

    @Test
    public void testProperties() {
        System.out.println(foo);
        assertThat(foo).isEqualTo("bar");
    }
}