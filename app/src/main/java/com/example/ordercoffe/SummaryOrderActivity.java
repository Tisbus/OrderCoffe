package com.example.ordercoffe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryOrderActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_order);
        textView = findViewById(R.id.textViewSummary);
        Intent intent = getIntent();
        if(intent.hasExtra("order")){
            textView.setText(intent.getStringExtra("order"));
        }else{
            Intent goBack = new Intent(this, LoginActivity.class);
            startActivity(goBack);
        }

    }
}