package com.example.ejemplo1listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ForeachActivity extends AppCompatActivity {

    protected TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreach);

        textView = findViewById(R.id.textView);
        
        String[] arraycito = {"Julian", "Miguel", "Pablo"};
        String arraycitoS = "";

        for (int i = 0; i < arraycito.length; i++) {
            arraycitoS += arraycito[i] + "\n ----------------------- \n";
        }

        textView.setText(arraycitoS);
    }
}