package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity // tells your app that a table should be created in your database that has the below fields
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @NotNull
    @Size(min = 4)
    private String taskName;

//    @NotNull
//    @Size(min = 3)
//    public String dueDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-YY")
    private Date dueDate;

    @NotNull
    @Size(min = 1)
    public String priority;

    @NotNull
    @Size(min = 10)
    public String description;

    public boolean isCompleted;

    public Todo() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }
}
