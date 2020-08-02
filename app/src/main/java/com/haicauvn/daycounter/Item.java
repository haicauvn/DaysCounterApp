package com.haicauvn.daycounter;

import java.util.Date;

public class Item {
    private String name;
    private Date date;
    private String Description;

    public Item(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Item(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
