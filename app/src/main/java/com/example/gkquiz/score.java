package com.example.gkquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class score extends AppCompatActivity {
private Button ok;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent =getIntent();
        TextView TextScore=(TextView) findViewById(R.id.score);
         int marks=intent.getIntExtra(ParkQuiz.EXTRA_MARKS,0);
         int question=intent.getIntExtra(ParkQuiz.EXTRA_QUESTION,0);
         TextScore.setText("  SCORE \n "  +marks+" / "+question);

         ok=findViewById(R.id.ok);
         ok.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(score.this,MainActivity.class);

                 startActivity(intent);
                 finish();

             }
         });
    }
}
