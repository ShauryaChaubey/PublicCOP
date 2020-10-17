package com.example.hack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class studentAdapterClass extends FirebaseRecyclerAdapter<StudentComplaintList.model, studentAdapterClass.viewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public studentAdapterClass(@NonNull FirebaseRecyclerOptions<StudentComplaintList.model> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull studentAdapterClass.viewholder viewholder, int i, @NonNull StudentComplaintList.model model) {
        viewholder.name.setText("Name: " + model.getName());
        viewholder.age.setText("Age: " + String.valueOf(model.getAge()));
        viewholder.email.setText("Email: " + String.valueOf(model.getEmail()));
        viewholder.pincode.setText("Pin: " + String.valueOf(model.getPincode()));
        viewholder.institution.setText("Institution: " + String.valueOf(model.getInstitution()));
        viewholder.address.setText("Address: " + String.valueOf(model.getAddress()));
        viewholder.type.setText("Crime Type: " + (CharSequence) model.getType());
        viewholder.description.setText("Crime Description: " + String.valueOf(model.getDescription()));
        viewholder.phone.setText("Phone No.: " + String.valueOf(model.getPhone()));
    }


    @NonNull
    @Override
    public studentAdapterClass.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentsingleview,parent,false);
        return new viewholder(view);
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView name, age, pincode, address, description, type, email, institution, phone;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            phone = itemView.findViewById(R.id.phone);
            email= itemView.findViewById(R.id.email);
            pincode = itemView.findViewById(R.id.pincode);
            type = itemView.findViewById(R.id.type);
            description = itemView.findViewById(R.id.prob);
            address = itemView.findViewById(R.id.address);
            institution = itemView.findViewById(R.id.institution);
        }
    }
}
