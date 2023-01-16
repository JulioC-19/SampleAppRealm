package com.sampleapprealm;

import android.util.Log;

import com.sampleapprealm.dataAccessLayer.student.StudentSchema;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {

    private String[] arraySchema = {"CourseSchema","StudentSchema","UniversitySchema"};

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        Long version = oldVersion;
        Log.i("old version", String.valueOf(version));
        Log.i("New version", String.valueOf(newVersion));

        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();
        Log.i("Schema", String.valueOf(schema.getAll()));

        // Changes from version 0 to 1: Adding inState field to StudentSchema.
        // All properties will be initialized with the default value "".
        // Adding a field to an existing table
        if (version == 0L) {
            schema.get("StudentSchema")
                    .addField("inState", Boolean.class);
            version++;
        }

        // Changes from version 1 to 2: Renaming field courseTracks to currentWeek
        if (version == 1L) {
            schema.get("CourseSchema").renameField("courseTracks", "currentWeek");
            version++;
        }

        // Now to handle adding a new realm, if fields are Objects of primitive class
        // Include the field required tab
        if (version == 2L){

            schema.create("UniversitySchema").addField("id", String.class, FieldAttribute.PRIMARY_KEY, FieldAttribute.REQUIRED)
                    .addField("name", String.class, FieldAttribute.REQUIRED)
                    .addField("founded", Integer.class, FieldAttribute.REQUIRED);

            version++;
        }

        // Adding an existing realm list field to another realm
        if(version == 3L){
            schema.get("UniversitySchema")
                    .addRealmListField("students", schema.get("StudentSchema"));
            version ++;
        }
    }
}
