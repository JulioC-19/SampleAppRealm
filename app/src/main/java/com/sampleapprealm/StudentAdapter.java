package com.sampleapprealm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sampleapprealm.dataAccessLayer.student.StudentSchema;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    // variable for our array list and context
    private List<StudentSchema> studentList;
    private Context context;

    // This is the constructor
    public StudentAdapter(List<StudentSchema> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        StudentSchema modal = studentList.get(position);
        holder.firstName.setText(modal.getFirstName());
        holder.lastName.setText(modal.getLastName());
        holder.enrollment.setText(modal.getEnrollmentPeriod());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private TextView firstName, lastName, enrollment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            enrollment = itemView.findViewById(R.id.enrollment);
        }
    }
}
