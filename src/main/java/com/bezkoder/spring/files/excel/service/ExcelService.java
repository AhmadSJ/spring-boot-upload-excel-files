package com.bezkoder.spring.files.excel.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bezkoder.spring.files.excel.model.Beroep;
import com.bezkoder.spring.files.excel.model.HardSkill;
import com.bezkoder.spring.files.excel.model.SoftSkill;
import com.bezkoder.spring.files.excel.repository.BeroepRepository;
import com.bezkoder.spring.files.excel.repository.HardSkillRepository;
import com.bezkoder.spring.files.excel.repository.SoftSkillRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.excel.helper.ExcelHelper;
import com.bezkoder.spring.files.excel.model.Tutorial;
import com.bezkoder.spring.files.excel.repository.TutorialRepository;

@Service
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

  public HardSkill rowToHardSkill(Iterator<Cell> cellsInRow) {
    HardSkill hardskill = new HardSkill();
    int cellIdx = 0;
    while (cellsInRow.hasNext()){
      Cell currentCell = cellsInRow.next();
      switch (cellIdx) {
        case 0:
          hardskill.setCode_5e_laag((int) currentCell.getNumericCellValue());
          break;
        case 1:
          hardskill.setOmschrijving_5e_laag(currentCell.getStringCellValue());
          break;
        case 2:
          hardskill.setSkillCode(currentCell.getStringCellValue());
          break;
        case 3:
          hardskill.setEssentieelOptioneel(currentCell.getStringCellValue());
          break;
        default:
          break;
      }
      cellIdx++;
    }
    return hardskill;
  }

  public SoftSkill rowToSoftSkill(Iterator<Cell> cellsInRow) {
    SoftSkill softSkill = new SoftSkill();
    int cellIdx = 0;
    while (cellsInRow.hasNext()){
      Cell currentCell = cellsInRow.next();
      switch (cellIdx) {
        case 0:
          softSkill.setCode_5e_laag((int) currentCell.getNumericCellValue());
          break;
        case 1:
          softSkill.setOmschrijving_5e_laag(currentCell.getStringCellValue());
          break;
        case 2:
          softSkill.setSkillCode(currentCell.getStringCellValue());
          break;
        case 3:
          softSkill.setSkillOmschrijving(currentCell.getStringCellValue());
          break;
        case 4:
          softSkill.setEssentieelOptioneel(currentCell.getStringCellValue());
          break;
        default:
          break;
      }
      cellIdx++;
    }
    return softSkill;
  }

  public Beroep rowToBeroep(Iterator<Cell> cellsInRow) {
    Beroep beroep = new Beroep();
    int cellIdx = 0;
    while (cellsInRow.hasNext()){
      Cell currentCell = cellsInRow.next();
      switch (cellIdx) {
        case 0:
          beroep.setBeroepsCode((int) currentCell.getNumericCellValue());
          break;
        case 1:
          beroep.setOmschrijvingBeroep(currentCell.getStringCellValue());
          break;
        case 2:
          beroep.setBeroepType(currentCell.getStringCellValue());
          break;
        case 3:
          beroep.setBeroepStatus(currentCell.getStringCellValue());
          break;
        case 4:
          beroep.setCode_5e_laag((int) currentCell.getNumericCellValue());
          break;
        case 5:
          beroep.setBeroepen_5e_laag(currentCell.getStringCellValue());
          break;
        case 6:
          beroep.setIsco_code_UG((int) currentCell.getNumericCellValue());
          break;
        case 7:
          beroep.setNl_unit_group_4e_laag(currentCell.getStringCellValue());
          break;
        case 8:
          beroep.setIsco_code_mig((int) currentCell.getNumericCellValue());
          break;
        case 9:
          beroep.setNl_minor_group_3e_laag(currentCell.getStringCellValue());
          break;
        case 10:
          beroep.setIsco_code_sub_mg((int) currentCell.getNumericCellValue());
          break;
        case 11:
          beroep.setIsco_nl_sub_major_group_2e_laag(currentCell.getStringCellValue());
          break;
        case 12:
          beroep.setIsco_code_mg((int) currentCell.getNumericCellValue());
          break;
        case 13:
          beroep.setIsco_nl_major_group_1e_laag(currentCell.getStringCellValue());
        default:
          break;
      }//switch
      cellIdx++;
    }//while
    return beroep;
  }

  public void excelToEntities(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);
      List<HardSkill> hardSkills = new ArrayList<HardSkill>();
      List<SoftSkill> softSkills = new ArrayList<SoftSkill>();
      List<Beroep> beroepen = new ArrayList<Beroep>();

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
              hardSkills.add(rowToHardSkill(cellsInRow));
              break;
            case "CNL_soft_skills_v0_3":
              softSkills.add(rowToSoftSkill(cellsInRow));
              break;
            case "CNL_beroepen_ISCO_21.1":
              beroepen.add(rowToBeroep(cellsInRow));
              break;
          }//switch
        }//while

      }//for
      hardSkillRepository.saveAll(hardSkills);
      softSkillRepository.saveAll(softSkills);
      beroepRepository.saveAll(beroepen);
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Cannot save the file");
    }
  }

}
