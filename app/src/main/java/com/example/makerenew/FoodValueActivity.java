package com.example.makerenew;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class FoodValueActivity extends AppCompatActivity {
    ProgressBar progressBar; // 전체 칼로리 수치 바
    TextView total_food_cal;
    TextView max_food_cal;
    // 곡률, 어육류, 채소, 지방, 우유, 과일 순
    TextView district1;
    TextView district2;
    TextView district3;
    TextView district4;
    TextView district5;
    TextView district6;

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

        // 리사이클러 뷰 생성
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        FoodAdapter adapter = new FoodAdapter();

        adapter.addItem(new Food("아침"));
        adapter.addItem(new Food("점심"));
        adapter.addItem(new Food("저녁"));
        adapter.addItem(new Food("간식"));

        Food morning = adapter.getItem(0);
        Food lunch = adapter.getItem(1);
        Food dinner = adapter.getItem(2);
        Food snack = adapter.getItem(3);

        // 각 변수에 값 입력 이런식으로 사용하시면 됩니다 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // 최초 DB의 값을 부르는 위치
        // ----- 각 시간대별 데이터 영역 ------
        morning.setMeal_cal(1000);

        recyclerView.setAdapter(adapter);

        // ----- total 영역 ------
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