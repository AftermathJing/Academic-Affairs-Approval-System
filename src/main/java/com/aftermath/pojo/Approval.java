package com.aftermath.pojo;

public class Approval {
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private Short credits;
    private String semester;
    private Short beginYear;
    private String courseNotes;
    private Boolean status;
    private String chooseNotes;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Short getCredits() {
        return credits;
    }

    public void setCredits(Short credits) {
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

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getChooseNotes() {
        return chooseNotes;
    }

    public void setChooseNotes(String chooseNotes) {
        this.chooseNotes = chooseNotes;
    }
}
