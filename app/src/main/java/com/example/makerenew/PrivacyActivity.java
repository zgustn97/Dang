package com.example.makerenew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class PrivacyActivity extends AppCompatActivity {

    String N = null; //이름
    String NM = null; //닉네임
    String B = null;  //생년월일
    String P = null; //전화번호

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText nameText = (EditText)findViewById(R.id.nameText);
        EditText nicknameText = (EditText)findViewById(R.id.nicknameText);
        EditText birthText = (EditText)findViewById(R.id.birthText);
        EditText phoneText = (EditText)findViewById(R.id.phoneText);
        View.OnClickListener listener = new View.OnClickListener() //입력 버튼 누르면 입력된 데이터 각 변수에 저장
        {
            @Override
            public void onClick(View v) {
                N = nameText.getText().toString();
                NM = nicknameText.getText().toString();
                B = birthText.getText().toString();
                P = phoneText.getText().toString();
            }
        };

        // PrivacyHealthActivity 로 이동
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrivacyHealthActivity.class);
                startActivity(intent);
            }
        });
    }
}
