package com.example.firenotes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesViewHolder> {

    Context context;
    DBHandler db;
    List<NotesModel> modelList;
    public NotesListAdapter(Context context, List<NotesModel> list) {
        this.context = context;
        modelList = list;
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.note_list_layout,parent,false);
        return (new NotesViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        NotesModel model = modelList.get(position);
        holder.title.setText(model.getTitle());
        holder.subTitle.setText(model.getBody());
        int itemPosition = holder.getAdapterPosition();
        Log.d("tag", "onBindViewHolder: "+itemPosition);
        holder.deleteItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBHandler(context);
                db.deleteNote(model.getId());
                Toast.makeText(context, "Deleting item with title "+holder.title.getText(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });

    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener , View.OnLongClickListener {

        TextView title,subTitle,tvDateTime;
        ImageButton deleteItemBtn;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_list_tv);
            subTitle = itemView.findViewById(R.id.sub_title_list_tv);
            deleteItemBtn = itemView.findViewById(R.id.delete_list_button);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);




        }


        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
//            Toast.makeText(context, "Clicked on item no."+position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,showParticularNotes.class);
            intent.putExtra("id",modelList.get(position).getId());
            intent.putExtra("TITLE",modelList.get(position).getTitle());
            intent.putExtra("BODY",modelList.get(position).getBody());
            context.startActivity(intent);


        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(context, "You long clicked on item"+this.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
