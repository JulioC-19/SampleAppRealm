package com.sampleapprealm;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sampleapprealm.dataAccessLayer.course.CourseSchema;
import com.sampleapprealm.dataAccessLayer.student.StudentSchema;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class StudentActivity extends AppCompatActivity {

    private EditText studentIdEdit, firstNameEdit, lastNameEdit, enrollmentPeriodEdit;
    private Realm realm;
    private String studentId, firstName, lastName, enrollmentPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // initializing our edittext and buttons
        realm = Realm.getDefaultInstance();
        studentIdEdit = findViewById(R.id.studentId);
        firstNameEdit = findViewById(R.id.addStudentFirstName);
        lastNameEdit = findViewById(R.id.addStudentLastName);
        enrollmentPeriodEdit = findViewById(R.id.enrollmentPeriod);

        // creating variable for button
        Button submitCourseBtn = findViewById(R.id.btnAddStudent);

        submitCourseBtn.setOnClickListener(v -> {

            // getting data from edittext fields.
            studentId = studentIdEdit.getText().toString();
            firstName = firstNameEdit.getText().toString();
            lastName = lastNameEdit.getText().toString();
            enrollmentPeriod = enrollmentPeriodEdit.getText().toString();

            // validating the text fields if empty or not.
            if (TextUtils.isEmpty(studentId)) {
                studentIdEdit.setError("Please enter id");
            } else if (TextUtils.isEmpty(firstName)) {
                firstNameEdit.setError("Please enter first name");
            } else if (TextUtils.isEmpty(lastName)) {
                lastNameEdit.setError("Please enter Course Duration");
            } else if (TextUtils.isEmpty(enrollmentPeriod)) {
                enrollmentPeriodEdit.setError("Please enter Course Tracks");
            } else {
                // calling method to add data to Realm database..
                addDataToDatabase(studentId, firstName, lastName, enrollmentPeriod);
                Toast.makeText(StudentActivity.this, "Student added to database", Toast.LENGTH_SHORT).show();
                studentIdEdit.setText("");
                firstNameEdit.setText("");
                lastNameEdit.setText("");
                enrollmentPeriodEdit.setText("");
            }
        });
    }

    private void addDataToDatabase(String studentId, String firstName, String lastName, String enrollmentPeriod) {
        // Writing to realm must be an asynchronous operation
        // Something is off about this method, or rather, can't fully understand it yet.
        realm.executeTransactionAsync(realm -> {
            StudentSchema student = realm.createObject(StudentSchema.class, String.valueOf(studentId));
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEnrollmentPeriod(enrollmentPeriod);

        }, () -> { // Lambda expression
            /* success actions */
            Log.i("Success", "New student object added to realm!");
            //realm.close(); // Not sure if this the correct place to close the realm instance
        }, error -> {
            /* failure actions */
            Log.e("Error", "Something went wrong! " + error);
        });

    }
}