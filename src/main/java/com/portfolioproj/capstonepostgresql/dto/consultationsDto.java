package com.portfolioproj.capstonepostgresql.dto;

import jakarta.persistence.Lob;

import java.time.LocalDate;

public class consultationsDto {

    private Long id;
    private String name;
    private LocalDate date;
    private String notes;

    @Lob
    private byte[] data;

    public consultationsDto(Long id, String name, LocalDate date, String notes, byte[] data) {

        this.id = id;
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.data = data;
    }

    public consultationsDto() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
