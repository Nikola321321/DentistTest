package com.example.dentisttest.person;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assistant_table")
public class Assistant {

    @PrimaryKey(autoGenerate = true)
    private int assistantId;

    private String firstName;

    private String lastName;

    private String address;

    private int toDentist;

    public Assistant(String firstName, String lastName, String address, int toDentist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.toDentist = toDentist;
        this.address = address;
    }

    public int getAssistantId() {
        return assistantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getToDentist() {
        return toDentist;
    }

    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
