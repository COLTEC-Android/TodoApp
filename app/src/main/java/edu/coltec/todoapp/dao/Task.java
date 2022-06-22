package edu.coltec.todoapp.dao;

public class Task {

    private Integer id;
    private String name;
    private String description;


    public Task(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public boolean isValid() {
        if (this.name == null || this.name.equals(""))
            return false;
        if (this.description == null || this.description.equals(""))
            return false;

        return true;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
