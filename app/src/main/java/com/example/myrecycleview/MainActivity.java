package com.example.myrecycleview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    public RecyclerView recyclerView;
    public List<Title> list;
    public MainAdapter adapter;
    Button btnOpen;
    Title title;
    Title title2;
    public  static  String SOME_ITEM = "someItem";
    public static  String IMAGE = "image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter.OnItemClickListener onItemClickListener = new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Title title1) {
                title1 = title;
                Toast.makeText(MainActivity.this, "user "+ title1.getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, ActivityDatePerson.class);
                i.putExtra(SOME_ITEM, title1);
                i.putExtra(IMAGE, title1.getImageView());
                startActivity(i);
            }
        };
        list = new ArrayList<>();
        adapter = new MainAdapter(list,this, onItemClickListener);
        recyclerView.setAdapter(adapter);
        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivityDatePerson.class);
                startActivityForResult(i, ActivityDatePerson.REQUEST_CODE);
            }
        });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ActivityDatePerson.REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null) {
                title = (Title) data.getSerializableExtra(ActivityDatePerson.KEY);
            }
            adapter.addApplication(title);
        }
    }
}