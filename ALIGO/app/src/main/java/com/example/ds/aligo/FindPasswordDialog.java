package com.example.ds.aligo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FindPasswordDialog extends Dialog {
    Button cancel, ok;
    Context context;

    EditText findId, findName, findPhone;

    public FindPasswordDialog(Context context){
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_findpassword);

        final Dialog dialog = this;

        cancel = (Button)findViewById(R.id.cancel);
        ok = (Button)findViewById(R.id.ok);

        findId = (EditText) findViewById(R.id.find_id);
        findName= (EditText )findViewById(R.id.find_name);
        findPhone = (EditText) findViewById(R.id.find_phone);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이메일로 임시번호 보내는 다이얼로그
                SharedPreferences pref = context.getSharedPreferences("user",context.MODE_PRIVATE);
                String logId = pref.getString("아이디",null);
                String phone = pref.getString("핸드폰",null);
                String name = pref.getString("이름",null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("이메일로 임시비밀번호를 보내드렸습니다.");
                alertDialogBuilder.setPositiveButton("확인", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        dialog2.dismiss();
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();

                if(logId.equals(findId.getText().toString())&&phone.equals(findPhone.getText().toString())&&name.equals(findName.getText().toString())){
                    alertDialog.show();
                }
                else{
                    Toast.makeText(context,"정보가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }
}