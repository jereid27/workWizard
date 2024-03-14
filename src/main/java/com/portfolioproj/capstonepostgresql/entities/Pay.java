package com.portfolioproj.capstonepostgresql.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="pay")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;

    private int amount;

    private int artistPercentage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Pay() {
    }

    public Pay(Long id, LocalDate date, int amount, int artistPercentage) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.artistPercentage = artistPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getArtistPercentage() {
        return artistPercentage;
    }

    public void setArtistPercentage(int artistPercentage) {
        this.artistPercentage = artistPercentage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
