package com.example.makerenew;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class PrivacyHealthActivity extends AppCompatActivity {
    String W = null; //몸무게
    String H = null; //키
    String D = null;  //당뇨유형
    String HD = null; //혈당강화제
    String I = null;  //안슐린
    String M = null;  //처방약
    String GL = null;  //GLP 주사
    String G1 = null;  //목표혈당 1
    String G2 = null;   //목표혈당 2
    int F=0; //식사량 숫자 1,2,3 으로 저장 1이 적음 2가 보통 3이 많음
    int E=0; //운동량

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_health);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText weight = (EditText)findViewById(R.id.weight);
        EditText height = (EditText)findViewById(R.id.height);
        Spinner 당뇨유형 = (Spinner)findViewById(R.id.당뇨유형);
        Spinner 혈당강화제 = (Spinner)findViewById(R.id.혈당강화제);
        Spinner 인슐린 = (Spinner)findViewById(R.id.인슐린);
        EditText 처방약 = (EditText)findViewById(R.id.처방약);
        Spinner GLP = (Spinner)findViewById(R.id.GLP);
        EditText 목표혈당 = (EditText)findViewById(R.id.목표혈당);
        EditText 목표혈당2 = (EditText)findViewById(R.id.목표혈당2);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);

        View.OnClickListener listener = new View.OnClickListener() //입력 버튼 누르면 입력된 데이터 각 변수에 저장
        {
            @Override
            public void onClick(View v) {
                W = weight.getText().toString();
                H = height.getText().toString();
                D = 당뇨유형.getSelectedItem().toString();
                HD = 혈당강화제.getSelectedItem().toString();
                I = 인슐린.getSelectedItem().toString();
                M = 처방약.getText().toString();
                GL = GLP.getSelectedItem().toString();
                G1= 목표혈당.getText().toString();
                G2 = 목표혈당2.getText().toString();

            }
        };

        // 입력 버튼 클릭 시 이동
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrivacyHealthActivity.class); // 클래스 변경 필요 @@@@@@@@@@@@@
                startActivity(intent);
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // 식사량
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rgBtn1:
                        F=1;
                        break;
                    case R.id.rgBtn2:
                        F=2;
                        break;
                    case R.id.rgBtn3:
                        F=3;
                        break;
                }
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // 운동량
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rgBtn2_1:
                        F=1;
                        break;
                    case R.id.rgBtn2_2:
                        F=2;
                        break;
                    case R.id.rgBtn2_3:
                        F=3;
                        break;
                }
            }
        });

    }
}