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
        viewholder.name.setText(model.getName());
        viewholder.age.setText(String.valueOf(model.getAge()));
        viewholder.email.setText(String.valueOf(model.getEmail()));
        viewholder.pin.setText(String.valueOf(model.getPin()));
        viewholder.institution.setText(String.valueOf(model.getInstitution()));
        viewholder.address.setText(String.valueOf(model.getAdd()));
        viewholder.sub_category.setText(String.valueOf(model.getSub_Category()));
        viewholder.description.setText(String.valueOf(model.getDescription()));
        viewholder.contact.setText(String.valueOf(model.getContact()));
    }


    @NonNull
    @Override
    public studentAdapterClass.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workplacesingleview,parent,false);
        return new viewholder(view);
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView name, age, pin, address, description, sub_category, email, institution, contact;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            email= itemView.findViewById(R.id.email);
            pin = itemView.findViewById(R.id.pincode);
            sub_category = itemView.findViewById(R.id.sub_category);
            description = itemView.findViewById(R.id.description);
            address = itemView.findViewById(R.id.add);
            institution = itemView.findViewById(R.id.institution);
        }
    }
}
