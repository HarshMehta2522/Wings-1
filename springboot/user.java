package com.fresco.ecommerce.models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class User {

  @Id
  @Column
  private Integer userId;

  @Column
  private String username;

  @Column
  private String password;

  // Implement Roles
  @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
  @JoinTable(
    name = "User_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  public User() {
    super();
  }

  public User(String username, String password, Set<Role> roles) {
    super();
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return (
      "User [userId=" +
      userId +
      ", username=" +
      username +
      ", password=" +
      password +
      ", roles=" +
      roles +
      "]"
    );
  }
}
