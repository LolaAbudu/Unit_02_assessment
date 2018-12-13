package org.pursuit.unit_02_assessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    private String textViewResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewResult = textView.getText().toString();
        if(savedInstanceState !=null){
            textView.setText(textViewResult);
        }

        Intent intent = getIntent();
        final String secondResult = intent.getStringExtra(MainActivity.RESULT);
        textView.setText(secondResult);
    }



}
