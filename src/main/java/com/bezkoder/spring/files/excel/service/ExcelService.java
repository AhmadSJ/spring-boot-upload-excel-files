package com.bezkoder.spring.files.excel.service;

import com.bezkoder.spring.files.excel.dto.BeroepDto;
import com.bezkoder.spring.files.excel.dto.HardSkillDto;
import com.bezkoder.spring.files.excel.dto.SoftSkillDto;
import com.bezkoder.spring.files.excel.dto.mapper.BeroepMapper;
import com.bezkoder.spring.files.excel.dto.mapper.HardSkillMapper;
import com.bezkoder.spring.files.excel.dto.mapper.SoftSkillMapper;
import com.bezkoder.spring.files.excel.helper.ExcelHelper;
import com.bezkoder.spring.files.excel.model.HardSkill;
import com.bezkoder.spring.files.excel.model.Tutorial;
import com.bezkoder.spring.files.excel.repository.BeroepRepository;
import com.bezkoder.spring.files.excel.repository.HardSkillRepository;
import com.bezkoder.spring.files.excel.repository.SoftSkillRepository;
import com.bezkoder.spring.files.excel.repository.TutorialRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ExcelService {

  @Autowired
  TutorialRepository repository;

  @Autowired
  private HardSkillRepository hardSkillRepository;

  @Autowired
  private SoftSkillRepository softSkillRepository;

  @Autowired
  private BeroepRepository beroepRepository;

  static final String[] workbooks_competent = {"CNL_hard_skills_v0_3", "CNL_soft_skills_v0_3", "CNL_beroepen_ISCO_21.1"};

  public void save(MultipartFile file) {
    try {
      List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public void saveFile(InputStream file) {
    List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file);
    repository.saveAll(tutorials);
  }

  public ByteArrayInputStream load() {
    List<Tutorial> tutorials = repository.findAll();

    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
    return in;
  }

  public List<Tutorial> getAllTutorials() {
    return repository.findAll();
  }

  public void excelToDatabase(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);
      List<HardSkillDto> hardSkillsDtos = new ArrayList<>();
      List<SoftSkillDto> softSkillsDtos = new ArrayList<>();
      List<BeroepDto> beroepDtos = new ArrayList<>();

      for(String sheet_name : workbooks_competent){
        Sheet sheet = workbook.getSheet(sheet_name);
        Iterator<Row> rows = sheet.iterator();
        int rowNumber = 0;
        while(rows.hasNext()) {
          Row currentRow = rows.next();

          //skip header
          if (rowNumber ==0) {
            rowNumber++;
            continue;
          }

          Iterator<Cell> cellsInRow = currentRow.iterator();

          switch (sheet_name) {
            case "CNL_hard_skills_v0_3":
              hardSkillsDtos.add(ExcelHelper.rowToHardSkillDto(cellsInRow));
              break;
            case "CNL_soft_skills_v0_3":
              softSkillsDtos.add(ExcelHelper.rowToSoftSkillDto(cellsInRow));
              break;
            case "CNL_beroepen_ISCO_21.1":
              beroepDtos.add(ExcelHelper.rowToBeroepDto(cellsInRow));
              break;
          }//switch
        }//while

      }//for
      hardSkillRepository.saveAll(HardSkillMapper.INSTANCE.getListOfHardSkills(hardSkillsDtos));
      softSkillRepository.saveAll(SoftSkillMapper.INSTANCE.getListOfSoftSkills(softSkillsDtos));
      beroepRepository.saveAll(BeroepMapper.INSTANCE.getListOfBeroepen(beroepDtos));
      workbook.close();
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Cannot save the file");
    }
  }

  public boolean testcompare(InputStream is) {
    List<HardSkill> hardskills = hardSkillRepository.findAll(Sort.by("id").ascending());
    for(int i=0; i<11; i++) {
      System.out.println(hardskills.get(i));
    }
    return true;
  }

  public boolean compareExcelToDatabase(InputStream is) {
    List<HardSkillDto> hardSkillDtosFromDatabase = HardSkillMapper.INSTANCE.getListOfHardSkillDtos(
            hardSkillRepository.findAll(Sort.by("id").ascending()));
    List<SoftSkillDto> softSkillDtosFromDatabase = SoftSkillMapper.INSTANCE.getListOfSoftSkillDtos(
            softSkillRepository.findAll(Sort.by("id").ascending()));
    List<BeroepDto> beroepDtosFromDatabase = BeroepMapper.INSTANCE.getListOfBeroepDtos(
            beroepRepository.findAll(Sort.by("id").ascending()));

    try {
      Workbook workbook = new XSSFWorkbook(is);
      List<HardSkillDto> hardSkillDtos = new ArrayList<>();
      List<SoftSkillDto> softSkillDtos = new ArrayList<>();
      List<BeroepDto> beroepDtos = new ArrayList<>();

      for(String sheet_name : workbooks_competent){
        Sheet sheet = workbook.getSheet(sheet_name);
        Iterator<Row> rows = sheet.iterator();
        int rowNumber = 0;
        while(rows.hasNext()) {
          Row currentRow = rows.next();

          //skip header
          if (rowNumber ==0) {
            rowNumber++;
            continue;
          }

          Iterator<Cell> cellsInRow = currentRow.iterator();

          switch (sheet_name) {
            case "CNL_hard_skills_v0_3":
              hardSkillDtos.add(ExcelHelper.rowToHardSkillDto(cellsInRow));
              break;
            case "CNL_soft_skills_v0_3":
              softSkillDtos.add(ExcelHelper.rowToSoftSkillDto(cellsInRow));
              break;
            case "CNL_beroepen_ISCO_21.1":
              beroepDtos.add(ExcelHelper.rowToBeroepDto(cellsInRow));
              break;
          }//switch
        }//while
      }//for
      for(int i =0; i<hardSkillDtos.size(); i++) {
        try {
          boolean t = !hardSkillDtos.get(i).equalsItself(hardSkillDtosFromDatabase.get(i));
          if(t){
            System.out.println("Hard Skills are not equal");
            return false;
          }
        } catch (IndexOutOfBoundsException e){
          e.printStackTrace();
          log.info("INDEX OUT OF BOUNDS MUFA");
          return false;
        }
      }

      for(int i =0; i<softSkillDtos.size(); i++) {
        try {
          boolean t = !softSkillDtos.get(i).equalsItself(softSkillDtosFromDatabase.get(i));
          if(t){
            log.info("Soft Skills are not equal");
            return false;
          }
        } catch (IndexOutOfBoundsException e){
          e.printStackTrace();
          log.info("INDEX OUT OF BOUNDS MUFA");
          return false;
        }
      }

      for(int i =0; i<beroepDtos.size(); i++) {
        try {
          boolean t = !beroepDtos.get(i).equalsItself(beroepDtosFromDatabase.get(i));
          if(t){
            log.info("Beroepen are not equal");
            return false;
          }
        } catch (IndexOutOfBoundsException e){
          e.printStackTrace();
          log.info("INDEX OUT OF BOUNDS MUFA");
          return false;
        }
      }
    workbook.close();
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      log.info("Cannot save the file");
    }
    log.info("Excel sheet contents are the same as the Database.");
  return true;
  }

}
