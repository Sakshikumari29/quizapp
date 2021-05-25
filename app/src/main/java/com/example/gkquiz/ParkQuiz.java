package com.example.gkquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ParkQuiz extends AppCompatActivity {
    private int totalQuestion=7;
public static final String EXTRA_MARKS ="com.example.gkquiz.EXTRA_MARKS";
    public static final String EXTRA_QUESTION ="com.example.gkquiz.EXTRA_QUESTION";

    private TextView question_count,question;
    private LinearLayout optionContainer;
    private FloatingActionButton bookmark;
    private int count=0;
    private Button next;
    private int marks=0;
    static private String[] choiceList = new String[]{"Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chhattisgarh",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jammu and Kashmir",
                "Jharkhand",
                "Karnataka",
                "Kerala",
                "Madhya Pradesh",
                "Maharashtra",
                "Manipur",
                "Meghalaya",
                "Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Rajasthan",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttarakhand",
                "Uttar Pradesh",
                "West Bengal",
                "Andaman and Nicobar Islands",
                "Chandigarh",
                "Dadra and Nagar Haveli",
                "Daman and Diu",
                "Delhi",
                "Lakshadweep",
                "Puducherry"
        };
    static private HashMap<String, String> questionAnswer = new HashMap<String, String>();
       public ParkQuiz(){
           questionAnswer.put("Where is Anamudi Shola National Park located", "Kerala");
           questionAnswer.put("Where is Anshi  National Park located", "Karnataka");
           questionAnswer.put("Where is Balphakram National Park located", "Meghalaya");
           questionAnswer.put("Where isBandhavgarh National Park located", "Madhya Pradesh");
           questionAnswer.put("Where is Bandipur  National Park located", "Karnataka");
           questionAnswer.put("Where is Bannerghatta National Park located", "Karnataka");
           questionAnswer.put("Where is Betla National Park located", "Jharkhand");
           questionAnswer.put("Where is Bhitarkanika National Park located", "Odisha");
           questionAnswer.put("Where is Bison  National Park located", "Tripura");
           questionAnswer.put("Where is BlackBuck Shola National Park located", "Gujarat");


       }

    private List<QuestionsModel> list;
    private Object randomquestionAnswer;
    private  int position=0;
    private String q;
    private int que;
    static private ArrayList<Integer> randomquestion = new ArrayList<Integer>();
    static private ArrayList<Integer> randomoption = new ArrayList<Integer>();
private int option2,option3,option4;
private String option1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_quiz);
        //////////////
        question_count=findViewById(R.id.question_count);
        question=findViewById(R.id.question);
        bookmark= findViewById(R.id.bookmark);
        next=findViewById(R.id.next);
        optionContainer=findViewById(R.id.optionContainer);
        ////////////////
       list = new ArrayList<>();
        for(int i=0;i<totalQuestion;i++) {


            Set<String> keySet = questionAnswer.keySet();

            List<String> keyList = new ArrayList<>(keySet);

            int size = keyList.size();
            do {
             que = new Random().nextInt(size);
             q = keyList.get(que);
            }while(randomquestion.contains(que));
            randomquestion.add(que);

             option1 = questionAnswer.get(q);
           randomoption.add(Arrays.asList(choiceList).indexOf(option1));

            Random r = new Random();
            do {
                 option2 = r.nextInt(36);
            }while(randomoption.contains(option2));
            randomoption.add(option2);
            do {
                option3 = r.nextInt(36);
            }while(randomoption.contains(option3));
            randomoption.add(option3);
            do {
                option4 = r.nextInt(36);
            }while(randomoption.contains(option4));
            randomoption.add(option4);
           // String[] choices = {option1, choiceList[option2], choiceList[option3], choiceList[option4]};

            list.add(new QuestionsModel(q, choiceList[option2],option1,choiceList[option3],choiceList[option4], option1));  //All Questions(15) will get added here in list
        }
        for(int i=0;i<4;i++){
            optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkAnswer((Button)  v);
                }
            });
        }
        playAnim(question,0,list.get(position).getQuestion());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setEnabled(false);
                next.setAlpha((float) 0.7);
                enableOption((true));
                position++;
                if(position==list.size()){
                    Intent intent=new Intent(ParkQuiz.this,score.class);
                    intent.putExtra(EXTRA_MARKS,marks);
                    intent.putExtra(EXTRA_QUESTION,totalQuestion);
                    startActivity(intent);
                    finish();
                    return;
                }
                count=0;
                playAnim(question,0,list.get(position).getQuestion());

            }
        });
    }

    private void playAnim(final View view, final int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).
                setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value==0 && count<4) {
                    String option = "";
                    if(count==0){
                        option=list.get(position).getOption1();
                    }else if(count==1){
                        option=list.get(position).getOption2();
                    }else if(count==2){
                        option=list.get(position).getOption3();
                    }else if(count==3){
                        option=list.get(position).getOption4();
                    }

                    playAnim(optionContainer.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    ((TextView) view).setText(data);
                    question_count.setText(position+1+"/"+list.size());

                }
                catch(ClassCastException e){
                    ((Button) view).setText(data);

                }
                view.setTag(data);
                if(value==0){
                    playAnim(view,1,data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void checkAnswer(Button selectedOption){
        enableOption(false);
        next.setEnabled(true);
        next.setAlpha(1);
        if(selectedOption.getText().toString().equals((list.get(position).getCorrectAnswer()))){
            //correct
            marks++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        }else{
            //incorrect
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFCC0000")));
            Button correctoption;
            correctoption = (Button)optionContainer.findViewWithTag(list.get(position).getCorrectAnswer());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        }
    }

    private void enableOption(boolean enable) {
        for(int i=0;i<4;i++){
            optionContainer.getChildAt(i).setEnabled(enable);
            if(enable==true){
                optionContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));

            }
        }
    }

}
