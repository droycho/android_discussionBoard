package com.epicodus.discussionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.newQuestionButton) Button mNewQuestionButton;
    @Bind(R.id.questionEditText) EditText mQuestionEditText;

    private DatabaseReference mInputtedQuestionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mInputtedQuestionReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_QUESTION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mNewQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = mQuestionEditText.getText().toString();
                saveQuestionToFirebase(question);

                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                intent.putExtra("question", question);
                startActivity(intent);
            }
            public void saveQuestionToFirebase(String question) {
                mInputtedQuestionReference.push().setValue(question);
            }
        });
    }
}
