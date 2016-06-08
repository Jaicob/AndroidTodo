package com.jaicob.simpletodo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Jaicob on 6/7/16.
 */

@Table(name = "Tasks")
public class Task extends Model {
    @Column(name = "Description")
    public String description;

    @Column(name = "Duedate")
    public Date dueDate;

    @Column(name = "Recurring")
    public Boolean recurring;

    public Task(){
        super();
    }

    public Task(String description, Date dueDate, Boolean recurring) {
        super();
        this.description = description;
        this.dueDate = dueDate;
        this.recurring = recurring;
    }

    public static List<Task> all(){
        return new Select().from(Task.class).execute();
    }
}
