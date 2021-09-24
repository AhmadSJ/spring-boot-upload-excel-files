package com.bezkoder.spring.files.excel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private boolean published;

  public Tutorial() {

  }

  public Tutorial(String title, String description, boolean published) {
    this.title = title;
    this.description = description;
    this.published = published;
  }

}
