package com.example.hack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentComplaintList extends AppCompatActivity {
    RecyclerView Complaints;
    studentAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_complaint_list);
        Complaints = findViewById(R.id.showStudentData);
        Complaints.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Type3"), model.class)
                        .build();
        adapter = new studentAdapterClass(options);
        Complaints.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static class model {
        String name,description,age,type, email, pin, add, sub_category, contact, institution;

        model(){

        }

        public model(String name, String contact, String description, String age, String add, String email, String pin, String institution) {
            this.name = name;
            this.description = description;
            this.email = email;
            this.pin = pin;
            this.add = add;
            this.age = age;
            this.sub_category = sub_category;
            this.institution = institution;
            this.contact = contact;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSub_Category() {
            return sub_category;
        }

        public void setSub_Category(String sub_category) {
            this.sub_category = sub_category;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getContact() {
            return contact;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String prob) {
            this.pin = pin;
        }

        public String getAdd() {
            return add;
        }

        public void setAddress(String add) {
            this.add = add;
        }

        public String getInstitution() {
            return institution;
        }

        public void setInstitution(String institution) {
            this.institution = institution;
        }
    }
}