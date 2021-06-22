package com.example.firenotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.MyViewHolder> {
    Context context;
    DBHandler handler;
    List<PasswordModel> models;
    public PasswordAdapter(Context context, List<PasswordModel> models) {
        this.context = context;
        this.models = models;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pass_layout,parent,false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PasswordModel model = models.get(position);
        holder.title.setText(model.getTitle());
        holder.pass.setText(model.getPassword());
        holder.CopyImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("password",model.getPassword());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, "Clicked on "+model.getId(), Toast.LENGTH_SHORT).show();

            }
        });
        holder.deleteImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler = new DBHandler(context);
                handler.DeletePassword(model.getId());
//                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context,PassGenManager.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,pass;
        ImageButton deleteImgBtn;
        ImageButton CopyImgBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv_pass);
            pass = itemView.findViewById(R.id.password_tv_pass);
            CopyImgBtn = itemView.findViewById(R.id.copy_pass_imgBtn);
            deleteImgBtn = itemView.findViewById(R.id.delete_pass_imgBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
//            starting UpdatePassword activity and passing the id, title and password.
            Intent intent = new Intent(context,UpdatePassword.class);
            PasswordModel model = models.get(position);
            intent.putExtra("id",model.getId());
            intent.putExtra("title",model.getTitle());
            intent.putExtra("pass",model.getPassword());
            context.startActivity(intent);
        }
    }
}
