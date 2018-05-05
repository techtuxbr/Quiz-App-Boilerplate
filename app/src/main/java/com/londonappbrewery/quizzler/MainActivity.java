package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:
    Button trueButton;
    Button falseButton;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;
    TextView mScoreTextView;
    int mIndex = 0;
    int mScore = 0;
    int mQuestion;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT_RATE = (int)Math.ceil(100.0/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        }else{
            mScore = 0;
            mIndex = 0;
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mScoreTextView = findViewById(R.id.score);

        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestion);

        updateQuestion();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


    }

    private void updateQuestion(){
        mIndex = (mIndex+1) % mQuestionBank.length;
        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT_RATE);
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game over");
            alert.setCancelable(false);
            alert.setMessage("The games is finished you score is "+mScore);
            alert.setPositiveButton("Close the app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
    }

    private void checkAnswer(boolean userAnswer){
        boolean rightAnswer = mQuestionBank[mIndex].isAnswer();
        if(userAnswer == rightAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey",mScore);
        outState.putInt("IndexKey",mIndex);

    }

}
