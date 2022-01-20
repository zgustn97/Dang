package com.example.makerenew;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView.CommaTokenizer;
import android.widget.TextView;
import android.widget.Toast;


import com.example.makerenew.R;

import java.io.NotActiveException;


public class FoodSelectActivity extends AppCompatActivity {

    String foodname = null;//장바구니에 들어갈 음식의 이름을 넣기위한 변수
    String selectedF1 = null; // 최종 결정된 음식의 이름을 저장하는 변수
    String selectedF2 = null;
    String selectedF3 = null;
    String selectedF4 = null;
    String selectedF5 = null;
    String selectedF6 = null;

    int num =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_select);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button CF1 = (Button) findViewById(R.id.CF1);
        Button CF2 = (Button) findViewById(R.id.CF2);
        Button CF3 = (Button) findViewById(R.id.CF3);
        Button CF4 = (Button) findViewById(R.id.CF4);
        Button CF5 = (Button) findViewById(R.id.CF5);
        Button CF6 = (Button) findViewById(R.id.CF6);

        Button RF1 = (Button) findViewById(R.id.RF1);
        Button RF2 = (Button) findViewById(R.id.RF2);
        Button RF3 = (Button) findViewById(R.id.RF3);
        Button RF4 = (Button) findViewById(R.id.RF4);
        Button RF5 = (Button) findViewById(R.id.RF5);
        Button RF6 = (Button) findViewById(R.id.RF6);

        String[] items = {"쇠고기 장조림", "메추리알 장조림", "공깃밥", "계란후라이",
                "배추김치", "미역국", "쇠고기 뭇국", "짜장밥", "장어 덮밥"};


        //자동완성기능이 완벽하게 구현되지는 않음 ('장'만 입력하면 장조림과 장어덮밥이 모두 뜨지만 '국'을 입력해도 미역국과 뭇국이 출력되지않음)
        //(띄어쓰기 맨글자만 인식하는 것 같음)
        AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, items);
        auto.setAdapter(adapter);
        OnItemClickListener clickListener = new OnItemClickListener() //검색해서 음식 선택한 경우
        {

            public void onItemClick(AdapterView<?> adapterView, View clickedView, int position, long id)
            {
                num++;
                foodname = ((TextView)clickedView).getText().toString();
                Toast.makeText(getApplicationContext(), foodname, Toast.LENGTH_SHORT).show(); //음식 선택시 토스트 메세지 (삭제 가능)
                switch (num) {
                    case 1:
                        CF1.setText(foodname);
                        CF1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        CF2.setText(foodname);
                        CF2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        CF3.setText(foodname);
                        CF3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        CF4.setText(foodname);
                        CF4.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        CF5.setText(foodname);
                        CF5.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        CF6.setText(foodname);
                        CF6.setVisibility(View.VISIBLE);
                        break;
                    default: //음식을 6개 모두 선택 했을 시
                        Toast.makeText(getApplicationContext(), "음식 선택을 완료하였습니다.", Toast.LENGTH_SHORT).show();
                }

            }

        };
        auto.setOnItemClickListener(clickListener);

        View.OnClickListener listener = new View.OnClickListener() //추천음식을 선택한 경우
        {
            @Override
            public void onClick(View v) {
                num++;
                Button b = (Button)v;
                foodname = b.getText().toString();
                Toast.makeText(getApplicationContext(), foodname, Toast.LENGTH_SHORT).show(); //음식 선택시 토스트 메세지 (삭제 가능)
                switch (num) {
                    case 1:
                        CF1.setText(foodname);
                        CF1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        CF2.setText(foodname);
                        CF2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        CF3.setText(foodname);
                        CF3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        CF4.setText(foodname);
                        CF4.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        CF5.setText(foodname);
                        CF5.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        CF6.setText(foodname);
                        CF6.setVisibility(View.VISIBLE);
                        break;
                    default: //음식을 6개 모두 선택 했을 시
                        Toast.makeText(getApplicationContext(), "음식 선택을 완료하였습니다.", Toast.LENGTH_SHORT).show();
                }

            }

        };
        RF1.setOnClickListener(listener);
        RF2.setOnClickListener(listener);
        RF3.setOnClickListener(listener);
        RF4.setOnClickListener(listener);
        RF5.setOnClickListener(listener);
        RF6.setOnClickListener(listener);

        View.OnClickListener listenerD = new View.OnClickListener() //장바구니 음식 삭제
        {
            @Override
            public void onClick(View v) {
                num--;
                Toast.makeText(getApplicationContext(), "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                Button b = (Button)v;
                foodname = " ";
                b.setText(foodname);
                b.setVisibility(View.INVISIBLE);

                switch (v.getId()) {
                    case R.id.CF1:
                        if(num>=1){
                            foodname = CF2.getText().toString();
                            CF1.setText(foodname);
                            CF1.setVisibility(View.VISIBLE);
                            CF2.setVisibility(View.INVISIBLE);

                        }
                    case R.id.CF2:
                        if(num>=2){
                            foodname = CF3.getText().toString();
                            CF2.setText(foodname);
                            CF2.setVisibility(View.VISIBLE);
                            CF3.setVisibility(View.INVISIBLE);
                        }
                    case R.id.CF3:
                        if(num>=3){foodname = CF4.getText().toString();
                            CF3.setText(foodname);
                            CF3.setVisibility(View.VISIBLE);
                            CF4.setVisibility(View.INVISIBLE);
                        }
                    case R.id.CF4:
                        if(num>=4){
                            foodname = CF5.getText().toString();
                            CF4.setText(foodname);
                            CF4.setVisibility(View.VISIBLE);
                            CF5.setVisibility(View.INVISIBLE);
                        }
                    case R.id.CF5:
                        if(num>=5){
                            foodname = CF6.getText().toString();
                            CF5.setText(foodname);
                            CF5.setVisibility(View.VISIBLE);
                            CF6.setVisibility(View.INVISIBLE);
                        }
                    case R.id.CF6: break;
                    default: //음식을 6개 모두 선택 했을 시
                        Toast.makeText(getApplicationContext(), "음식을 모두 삭제 하였습니다", Toast.LENGTH_SHORT).show();
                }

            }

        };
        CF1.setOnClickListener(listenerD);
        CF2.setOnClickListener(listenerD);
        CF3.setOnClickListener(listenerD);
        CF4.setOnClickListener(listenerD);
        CF5.setOnClickListener(listenerD);
        CF6.setOnClickListener(listenerD);

        View.OnClickListener listenerF = new View.OnClickListener() // 다음 버튼 클릭시 음직 저장 && 추천음식 변경
        {
            @Override
            public void onClick(View v) {
                selectedF1 = CF1.getText().toString();
                selectedF2 = CF2.getText().toString();
                selectedF3 = CF3.getText().toString();
                selectedF4 = CF4.getText().toString();
                selectedF5 = CF5.getText().toString();
                selectedF6 = CF6.getText().toString();

                RF1.setText(selectedF1);
                RF2.setText(selectedF2);
                RF3.setText(selectedF3);
                RF4.setText(selectedF4);
                RF5.setText(selectedF5);
                RF6.setText(selectedF6);
            }
        };

        Button daum = (Button) findViewById(R.id.daum);
        daum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 여기에 DB 저장하는 기능 구현하기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                finish();
            }
        });

        // 뒤로가기 버튼 구현
        ImageButton button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}