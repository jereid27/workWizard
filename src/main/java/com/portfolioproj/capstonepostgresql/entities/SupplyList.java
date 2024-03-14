package com.portfolioproj.capstonepostgresql.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "supply_lists")
public class SupplyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplyName;
    @Min(value=0, message = "Quantity must be more than 0.")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SupplyList() {
    }

    public SupplyList(String supplyName, int quantity, User user) {
        this.supplyName = supplyName;
        this.quantity = quantity;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
