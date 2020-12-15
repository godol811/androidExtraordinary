package com.android.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String emailOk ="123@gmail.com"; //기존 데이터 베이스 1
    String passwordOk="1234"; //기존 데이터 베이스 2
    String inputEmail = "";
    String inputPassword = "";
    EditText Email;
    EditText Password;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = findViewById(R.id.textEmail);
        Password = findViewById(R.id.textPassword);
        loginButton = findViewById(R.id.loginButton);


        // 1.값을 가져온다 - 검사 (123@gmail.com) /1234
        // 2.클릭을 가져온다
        // 3.1번의 값을 다음 액티비티로 넘긴다.
        loginButton.setClickable(false);

        //text필드를 채울때 반응 하는 내용
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("하하", s +"," +count);
                if(s != null){ //s값이 있을때의 상황을 고려해서 안전장치로 s는 null이 아닐 때를 할수 있도록 한다.
                    inputEmail = s.toString();
                    loginButton.setEnabled(validation()); //true or not
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override//아다리가 맞으면 로그인 가능하게
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null) {
                    inputPassword = s.toString();
                    loginButton.setEnabled(validation()); //true or not
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });






//        loginButton.setClickable(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = findViewById(R.id.textEmail);
                Password = findViewById(R.id.textPassword);

                String textEmail =       Email.getText().toString();
                String textPassword = Password.getText().toString();

                Intent intent = new Intent(MainActivity.this,loginActivity.class); //메인 액티비티에서 loginActivity로 intent 보내기
                intent.putExtra("email",textEmail);
                intent.putExtra("password",textPassword);
                startActivity(intent);

            }
        });

    }


    public boolean validation(){
        Log.d("GODOL",inputEmail + "/" + inputPassword + "/" +(inputEmail.equals(emailOk)) + "/" + (inputPassword.equals(passwordOk)));
        return inputEmail.equals(emailOk) && inputPassword.equals(passwordOk);

    }
}