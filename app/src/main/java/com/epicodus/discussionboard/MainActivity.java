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

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mNewQuestionButton;
    private EditText mQuestionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String value = extras.getString("questions");
        }
        setContentView(R.layout.activity_main);

        mQuestionEditText = (EditText) findViewById(R.id.questionEditText);
        mNewQuestionButton = (Button) findViewById(R.id.newQuestionButton);
        mNewQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = mQuestionEditText.getText().toString();
                Log.d(TAG, question);
                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                intent.putExtra("question", question);
                startActivity(intent);

            }
        });
    }
}
