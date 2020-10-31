package com.example.dentisttest.person;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table")
public class Patient {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int patientId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    private String address;

    private int patientDentistId;



    public Patient(@NonNull String firstName, @NonNull String lastName, String address, int patientDentistId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patientDentistId = patientDentistId;
        this.address = address;
    }

    public int getPatientId() {
        return patientId;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public int getPatientDentistId() {
        return patientDentistId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public void setPatientDentistId(int patientDentistId) {
        this.patientDentistId = patientDentistId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
