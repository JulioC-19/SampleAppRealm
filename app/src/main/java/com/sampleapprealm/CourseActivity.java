package com.sampleapprealm;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.sampleapprealm.dataAccessLayer.course.CourseSchema;
import org.bson.types.ObjectId;
import io.realm.Realm;

public class CourseActivity extends AppCompatActivity {
    // creating variables for our edit text
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt, courseTracksEdt;
    private Realm realm;

    // creating a strings for storing
    // our values from edittext fields.
    private String courseName, courseDuration, courseDescription, courseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        // initializing our edittext and buttons
        realm = Realm.getDefaultInstance();
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);

        // creating variable for button
        Button submitCourseBtn = findViewById(R.id.idBtnAddCourse);
        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting data from edittext fields.
                courseName = courseNameEdt.getText().toString();
                courseDescription = courseDescriptionEdt.getText().toString();
                courseDuration = courseDurationEdt.getText().toString();
                courseTracks = courseTracksEdt.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(courseName)) {
                    courseNameEdt.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(courseDescription)) {
                    courseDescriptionEdt.setError("Please enter Course Description");
                } else if (TextUtils.isEmpty(courseDuration)) {
                    courseDurationEdt.setError("Please enter Course Duration");
                } else if (TextUtils.isEmpty(courseTracks)) {
                    courseTracksEdt.setError("Please enter Course Tracks");
                } else {
                    // calling method to add data to Realm database..
                    addDataToDatabase(courseName, courseDescription, courseDuration, courseTracks);
                    Toast.makeText(CourseActivity.this, "Course added to database", Toast.LENGTH_SHORT).show();
                    courseNameEdt.setText("");
                    courseDescriptionEdt.setText("");
                    courseDurationEdt.setText("");
                    courseTracksEdt.setText("");
                }
            }

        });
    }

    private void addDataToDatabase(String courseName, String courseDescription, String courseDuration, String courseTracks) {

        /* This is an old way of performing transactions, not too safe
        realm.beginTransaction();
        CourseSchema course = realm.createObject(CourseSchema.class, String.valueOf(new ObjectId()));
        course.setCourseDescription(courseDescription);
        course.setCourseName(courseName);
        course.setCourseDuration(courseDuration);
        course.setCourseTracks(courseTracks);
        realm.commitTransaction();
        */

        // Writing to realm must be an asynchronous operation
        // Something is off about this method, or rather, can't fully understand it yet.
        realm.executeTransactionAsync(realm -> {
            CourseSchema course = realm.createObject(CourseSchema.class, String.valueOf(new ObjectId()));
            course.setCourseDescription(courseDescription);
            course.setCourseName(courseName);
            course.setCourseDuration(courseDuration);
            course.setCurrentWeek(courseTracks);

        }, () -> { // Lambda expression
            /* success actions */
            Log.i("Success", "New object added to realm!");
            realm.close(); // Not sure if this the correct place to close the realm instance
        }, error -> {
            /* failure actions */
            Log.e("Error", "Something went wrong! " + error);
        });
    }
}
