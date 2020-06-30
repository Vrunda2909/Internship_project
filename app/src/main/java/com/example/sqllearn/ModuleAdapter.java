package com.example.sqllearn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleHolder> {

    Context context;
    String[] title;
    String[]htmlFile;

    public ModuleAdapter(Context context, String[] title, String[] htmlfile) {
        this.context = context;
        this.title = title;
        this.htmlFile = htmlfile;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list,parent,false);
        return new ModuleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {
        final String htmlfile = htmlFile[position];

        holder.textView.setText(title[position]);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudyActivity.class);
                intent.putExtra("file",htmlfile);
                context.startActivity(intent);
                ((ModuleOneActivity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class ModuleHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView textView;
        public ModuleHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear);
            textView = itemView.findViewById(R.id.title);
        }
    }


}
