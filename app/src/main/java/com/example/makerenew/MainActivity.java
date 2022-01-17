package com.example.makerenew;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView.CommaTokenizer;

import com.example.makerenew.R;

import java.io.NotActiveException;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String[] items = { "쇠고기 장조림", "메추리알 장조림", "공깃밥", "계란후라이",
                "배추김치", "미역국","쇠고기 뭇국","짜장밥","장어 덮밥" };
//자동완성기능이 완벽하게 구현되지는 않음 ('장'만 입력하면 장조림과 장어덮밥이 모두 뜨지만 '국'을 입력해도 미역국과 뭇국이 출력되지않음)
        //(띄어쓰기 맨글자만 인식하는 것 같음)
        AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, items);
        auto.setAdapter(adapter);





    }
}