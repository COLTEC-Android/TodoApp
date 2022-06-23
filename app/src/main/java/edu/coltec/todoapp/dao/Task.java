package edu.coltec.todoapp.dao;

public class Task {

    private Integer id;
    private String name;
    private String description;
    private Category category;


    public Task(Integer id, String name, String description, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Task(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }


    public boolean isValid() {
        if (this.name == null || this.name.equals(""))
            return false;
        if (this.description == null || this.description.equals(""))
            return false;
        if (!this.category.isValid())
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
