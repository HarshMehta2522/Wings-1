package com.fresco.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Category {

  @Id
  @Column
  private Integer categoryId;

  @Column
  private String categoryName;

  public Category() {
    super();
  }

  public Category(String categoryName) {
    super();
    this.categoryName = categoryName;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public String toString() {
    return (
      "Category [categoryId=" +
 N     categoryId +
      ", categoryName=" +
      categoryName +
      "]"
    );
  }
}
