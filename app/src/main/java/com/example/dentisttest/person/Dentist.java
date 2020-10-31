package com.example.dentisttest.person;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dentist_table")
public class Dentist {

    @PrimaryKey(autoGenerate = true)
    private int dentistId;

    private String firstName;

    private String lastName;

    private String address;


    public Dentist(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getName() {
        return firstName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
