package com.example.myrecycleview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityDatePerson extends AppCompatActivity {
    public static int REQUEST_CODE =100;
    private EditText etName;
    private EditText etLastName;
    private EditText etAge;
    private EditText etGroup;
    private ImageView imageView;
    private Uri imageDate;
    public static String KEY = "key";
    Title title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_person);
        init();
        Intent intent = getIntent();
        title = (Title) intent.getSerializableExtra(MainActivity.SOME_ITEM);
    }
    private void init(){
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etAge = findViewById(R.id.etAge);
        etGroup = findViewById(R.id.etGroup);
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Choose image"), 200);
    }

    public void sendResult(View view) {
        String titleName = etName.getText().toString();
        String titleLastname = etLastName.getText().toString();
        String titleAge = etAge.getText().toString();
        String titleGroup = etGroup.getText().toString();
        String image = imageDate.toString();

        Intent intent = new Intent();
        Title title1 = new Title();
        title1.setName(titleName);
        title1.setLastName(titleLastname);
        title1.setAge(titleAge);
        title1.setGroup(titleGroup);
        title1.setImageView(image);
        intent.putExtra(KEY,title1);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == RESULT_OK){
            if(data != null){
        imageDate = data.getData();
        imageView.setImageURI(imageDate);}}
    }
    public void setTitle(Title title){
        this.title = title;
        if (title != null) {
            etName.setText(title.getName());
        etLastName.setText(title.getLastName());
        etAge.setText(title.getAge());
        etGroup.setText(title.getGroup());
        imageView.setImageURI(Uri.parse(title.getImageView()));}
    }
}