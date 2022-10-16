package com.sampleapprealm.dataAccessLayer.university;

import com.sampleapprealm.dataAccessLayer.student.StudentSchema;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class UniversitySchema extends RealmObject {

    @PrimaryKey
    @Required
    private String id;
    @Required
    private String name;
    @Required
    private Integer founded;
    private RealmList<StudentSchema> students;


    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }
}
