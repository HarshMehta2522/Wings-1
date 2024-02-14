package com.fresco.ecommerce.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Implement User Roles
@Entity
@Table
public class Role {

  @Id
  @Column
  private Integer roleId;

  @Column
  private String role;

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Role(Integer roleId, String role) {
    super();
    this.roleId = roleId;
    this.role = role;
  }

  public Role() {
    super();
  }

  @Override
  public String toString() {
    return "Role [roleId=" + roleId + ", role=" + role + "]";
  }
}
