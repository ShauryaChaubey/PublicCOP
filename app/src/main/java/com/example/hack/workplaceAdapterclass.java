package com.example.hack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class workplaceAdapterclass extends FirebaseRecyclerAdapter<WorkplaceComplaintList.model, workplaceAdapterclass.viewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public workplaceAdapterclass(@NonNull FirebaseRecyclerOptions<WorkplaceComplaintList.model> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull workplaceAdapterclass.viewholder viewholder, int i, @NonNull WorkplaceComplaintList.model model) {
        viewholder.name.setText(model.getName());
        viewholder.age.setText(String.valueOf(model.getAge()));
        viewholder.emailId.setText(String.valueOf(model.getEmail()));
        viewholder.pin.setText(String.valueOf(model.getPin()));
        viewholder.prob.setText(String.valueOf(model.getProb()));
        viewholder.add.setText(String.valueOf(model.getAdd()));
        viewholder.sub_category.setText(String.valueOf(model.getSub_Category()));
        viewholder.comp.setText(String.valueOf(model.getComp()));
    }


    @NonNull
    @Override
    public workplaceAdapterclass.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workplacesingleview,parent,false);
        return new viewholder(view);
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView name, age, pin, add, prob, sub_category, emailId, comp;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            emailId= itemView.findViewById(R.id.email);
            pin = itemView.findViewById(R.id.pincode);
            sub_category = itemView.findViewById(R.id.sub_category);
            comp = itemView.findViewById(R.id.comp);
            add = itemView.findViewById(R.id.add);
            prob = itemView.findViewById(R.id.prob);
        }
    }
}
