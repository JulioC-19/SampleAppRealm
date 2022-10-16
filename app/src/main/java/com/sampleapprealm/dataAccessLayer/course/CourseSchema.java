package com.sampleapprealm.dataAccessLayer.course;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CourseSchema extends RealmObject {
    // on below line we are creating our variables
    // and with are using primary key for our id.
    @PrimaryKey
    private String id;
    private String courseName;
    private String courseDescription;
    private String currentWeek;
    private String courseDuration;

    // List of realm objects

    // on below line we are
    // creating an empty constructor.
    public CourseSchema() {
    }

    // below line we are
    // creating getter and setters.
    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public String getId() {
        return id;
    }

    public void setId(long id) {
        this.id = String.valueOf(id);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }
}
