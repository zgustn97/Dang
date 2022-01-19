package com.example.makerenew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FoodValueActivity extends AppCompatActivity {
    ProgressBar progressBar; // 전체 칼로리 수치 바
    TextView total_food_cal;
    TextView max_food_cal;

    // DB 에서 불러온 값을 여기에 저장해 주세요 @@@@@@@@@@@@@@@@@@@@@@@@@@
    int totalCal = 0;
    int maxCal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_value);

        // 뒤로가기 버튼 구현
        ImageButton button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        progressBar = findViewById(R.id.progress_bar);
        total_food_cal = findViewById(R.id.total_food_cal);
        max_food_cal = findViewById(R.id.max_food_cal);

        // 최초 DB의 값을 부르는 위치
        totalCal = 5000;
        maxCal = 14000;

        setValues();
    }

    // progress 및 textView 변경 함수
    // DB를 불러올 때 이 함수를 1회 실행하면 됩니다.
    public void setValues() {
        progressBar.setMax(maxCal);
        progressBar.setProgress(totalCal);
        total_food_cal.setText(Integer.toString(totalCal));
        max_food_cal.setText(Integer.toString(maxCal));
    }
}