package com.example.myrecycleview;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    List<Title> list;
    Context context;

    private OnItemClickListener onItemClickListener;

    public MainAdapter(List<Title> list, Context context, OnItemClickListener onItemClickListener){
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_recycle,parent,false);
        return new ViewHolder(view);
    }
    public void addApplication(Title title){
        list.add(title);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, final int position) {
        holder.onBind(list.get(position));

        holder.textMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(context,holder.textMenu);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menuSave:
                                Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();
                                break;

                            case R.id.menuDelete:
                                list.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;

                        }

                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtLastName;
        TextView txtAge;
        TextView txtGroup;
        ImageView imageView;
        TextView textMenu;
        Title title1;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtGroup = itemView.findViewById(R.id.txtGroup);
            imageView = itemView.findViewById(R.id.img);
            textMenu = itemView.findViewById(R.id.txtOptionMenu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    title1 = list.get(getLayoutPosition());
                    onItemClickListener.onItemClickListener(title1);
                }
            });

        }
        public void onBind(Title title){
            this.title1 = title;
            txtName.setText(title.name);
            txtLastName.setText(title.lastName);
            txtAge.setText(title.age);
            txtGroup.setText(title.group);
            imageView.setImageURI(Uri.parse(title.imageView));
        }
    }
    public interface OnItemClickListener{
        void onItemClickListener(Title title);
    }
}
