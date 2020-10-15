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

public class WorkplaceComplaintList extends AppCompatActivity {
    RecyclerView Complaints;
    workplaceAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplace_complaint_list);
        Complaints = findViewById(R.id.showWorkplaceData);
        Complaints.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Type1"), model.class)
                        .build();
        adapter = new workplaceAdapterClass(options);
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
        String name, age, email, pin, add, sub_category, prob, comp, other;

        model(){

        }

        public model(String name, String age, String email, String  pin, String add, String sub_category, String prob, String comp, String other) {
            this.name = name;
            this.email = email;
            this.pin = pin;
            this.add = add;
            this.sub_category = sub_category;
            this.prob = prob;
            this.comp = comp;
            this.other = other;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public void setOther(String other) {
            this.other = other;
        }

        public String getOther() {
            return other;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getAdd() {
            return add;
        }

        public void setAddress(String add) {
            this.add = add;
        }

        public String getProb() {
            return prob;
        }

        public void setProb(String email) {
            this.prob = prob;
        }

        public String getComp() {
            return comp;
        }

        public void setComp(String comp) {
            this.comp = comp;
        }
    }
}