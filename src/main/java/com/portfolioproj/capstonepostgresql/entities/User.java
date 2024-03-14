package com.portfolioproj.capstonepostgresql.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String fullname;

    public User() {

    }

    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Consultations> consultations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SupplyList> supplyList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Pay> pay;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
