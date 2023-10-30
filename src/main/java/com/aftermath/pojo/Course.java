package com.aftermath.pojo;

public class Course {
    private String id;
    private String name;
    private Double credits;
    private String semester;
    private Short beginYear;
    private String notes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Short getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(Short beginYear) {
        this.beginYear = beginYear;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
