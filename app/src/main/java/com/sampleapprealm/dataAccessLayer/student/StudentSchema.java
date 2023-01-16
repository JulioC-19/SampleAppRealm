package com.sampleapprealm.dataAccessLayer.student;

import com.sampleapprealm.dataAccessLayer.course.CourseSchema;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class StudentSchema extends RealmObject{

    // on below line we are creating our variables
    // and with are using primary key for our id.
    @PrimaryKey
    private String id;
    private String firstName;
    private String lastName;
    private String enrollmentPeriod;
    private Boolean inState;

    private RealmList<CourseSchema> courses;

    public String getId() {
        return id;
    }

    public void setId(long id) {
        this.id = String.valueOf(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEnrollmentPeriod() {
        return enrollmentPeriod;
    }

    public void setEnrollmentPeriod(String enrollmentPeriod) {
        this.enrollmentPeriod = enrollmentPeriod;
    }

    public RealmList<CourseSchema> getCourses() {
        return courses;
    }

    public void setCourses(RealmList<CourseSchema> courses) {
        this.courses = courses;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInState() {
        return inState;
    }

    public void setInState(Boolean inState) {
        this.inState = inState;
    }

}
