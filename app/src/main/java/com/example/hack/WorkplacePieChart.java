package com.example.hack;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;


class WorkplacePieChart extends AppCompatActivity {


    static int  childCount = 0;
    ArrayList<Integer> typeCount;
    //String[] category = {"Bribery", "Blackmail", "Harassment","Theft"};

    com.github.mikephil.charting.charts.PieChart pieChart;
    DatabaseReference ref;

    static HashMap<String, Float> crimeCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplace_pie_chart);

        pieChart =  findViewById(R.id.WorkPieChart);

        crimeCount = new HashMap<String, Float>();

        countNumber();

        typeCount = new ArrayList<>();

    }

    private void countNumber() {

        //getting type 1  type 1 - workplace, type 2- domestic. type 3- student
        ref = FirebaseDatabase.getInstance().getReference().child("Type1");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    childCount = (int) snapshot.getChildrenCount();
                Log.i("pie", String.valueOf(childCount));

                retrieveData();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void retrieveData() {

        Log.i("count:", String.valueOf(childCount));

        //getting type 1  type 1 - workplace, type 2- domestic. type 3- student


        for(int i = 1; i <= childCount; i++)
        {
            ref = FirebaseDatabase.getInstance().getReference().child("Type1").child(Integer.toString(i));

            final int finalI = i;
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String str = snapshot.child("sub_category").getValue().toString();
                    Float val;
                    Log.i("Type of crime: ", str);
                    if(crimeCount.containsKey(str))
                    {
                        val = crimeCount.get(str);
                        crimeCount.put(str, (val+1));
                    }
                    else {
                        crimeCount.put(str, 1f);
                    }

                    Log.i("crimeCount"+String.valueOf(finalI), String.valueOf(crimeCount.size()));

                    pieChartFunc();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void pieChartFunc() {


        ArrayList<PieEntry> types = new ArrayList<PieEntry>();

        Log.i("crime size", String.valueOf(crimeCount.size()));


        Iterator crimeItr = crimeCount.entrySet().iterator();

        while (crimeItr.hasNext()) {
            HashMap.Entry mapElement = (HashMap.Entry)crimeItr.next();
            Float value = ((Float)mapElement.getValue()), mul = 100.0f;
            value = (value / (float)(childCount)) *mul;
            Log.i("pieEntry",mapElement.getKey() + " : " + value);

            types.add(new PieEntry(value, mapElement.getKey()));

        }



        PieDataSet dataSet = new PieDataSet(types, "Types Of Crimes");

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColors(Collections.singletonList(Color.BLACK));
        dataSet.setValueTypeface(Typeface.SANS_SERIF);
        dataSet.setValueTextSize(16f);

        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieData.setDrawValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(true);
        pieChart.setCenterText("Workplace Complaints");
        pieChart.setAlpha(0.8f);
        pieChart.animateXY(1000,1000);



    }
}
