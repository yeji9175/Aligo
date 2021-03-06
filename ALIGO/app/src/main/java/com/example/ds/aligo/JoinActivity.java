package com.example.ds.aligo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    EditText name, birth, id, password, passwordCheck, phone;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_join);

        pref = getSharedPreferences("user", MODE_PRIVATE);
        editor = pref.edit();

        name = (EditText)findViewById(R.id.name);
        birth = (EditText)findViewById(R.id.birth);
        id = (EditText)findViewById(R.id.id);
        password = (EditText)findViewById(R.id.password);
        passwordCheck = (EditText)findViewById(R.id.passwordCheck);
        phone = (EditText)findViewById(R.id.phone);
    }

    public void onJoinCheckButton(View view) {
        String idCheck = pref.getString("아이디", "null");

        if(name.getText().toString().equals("")||birth.getText().toString().equals("")||id.getText().toString().equals("")||
                password.getText().toString().equals("")||passwordCheck.getText().toString().equals("")||phone.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "입력되지 않은 정보가 있습니다.",Toast.LENGTH_LONG).show();
        }

        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(id.getText().toString()).matches())
            Toast.makeText(getApplicationContext(),"이메일 형식이 아닙니다",Toast.LENGTH_SHORT).show();

        else if(!Pattern.matches("^[A-Za-z0-9]*.{6,20}$", password.getText().toString()))
            Toast.makeText(getApplicationContext(),"비밀번호형식이 맞지 않습니다..",Toast.LENGTH_SHORT).show();

        else if(!(password.getText().toString().equals(passwordCheck.getText().toString())))
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();

        else if(!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phone.getText().toString()))
            Toast.makeText(getApplicationContext(),"올바른 핸드폰 번호가 아닙니다.",Toast.LENGTH_SHORT).show();

        else if (id.getText().toString().equals(idCheck))
            Toast.makeText(getApplicationContext(), "이미 회원가입이 되어있습니다.", Toast.LENGTH_LONG).show();

        else {
            editor.putString("이름", name.getText().toString());
            editor.putString("생년월일", birth.getText().toString());
            editor.putString("아이디", id.getText().toString());
            editor.putString("비밀번호", password.getText().toString());
            editor.putString("핸드폰", phone.getText().toString());
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
