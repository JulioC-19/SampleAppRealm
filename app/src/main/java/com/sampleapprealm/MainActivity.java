package com.sampleapprealm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sampleapprealm.dataAccessLayer.student.StudentSchema;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    List<StudentSchema> studentList;

    // creating variables for realm,
    // recycler view, adapter and our list.
    private Realm realm;
    private RecyclerView studentRecyclerView;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on below lines we are initializing our variables.
        studentRecyclerView = findViewById(R.id.idStudentList);
        realm = Realm.getDefaultInstance();
        studentList = new ArrayList<>();

        // calling a method to load
        // our recycler view with data.
        prepareRecyclerView();

        FloatingActionButton addStudentFab = findViewById(R.id.addStudentFAB);
        addStudentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void prepareRecyclerView() {
        // on below line we are getting data from realm database in our list.
        studentList = realm.where(StudentSchema.class).findAll();
        Log.i("studentList", String.valueOf(studentList));
        // on below line we are adding our list to our adapter class.
        studentAdapter = new StudentAdapter(studentList, this);
        // on below line we are setting layout manager to our recycler view.
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // at last we are setting adapter to our recycler view.
        studentRecyclerView.setAdapter(studentAdapter);
    }
}
