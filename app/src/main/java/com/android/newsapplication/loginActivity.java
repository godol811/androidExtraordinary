package com.android.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

        Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView TextView_get;

        TextView_get = findViewById(R.id.TextView_get);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras(); //값 가져온 부분

        String textEmail =  bundle.getString("email"); //email 키 받기
        String textPassword = bundle.getString("password"); //password 키 받기
        btn = findViewById(R.id.pros);

        TextView_get.setText(textEmail + " / "+ textPassword);//setText 해서 올리기


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,NewsActivity.class);
                startActivity(intent);

            }
        });
    }


}