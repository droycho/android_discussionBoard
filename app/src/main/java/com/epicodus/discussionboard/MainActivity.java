package com.epicodus.discussionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mNewQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewQuestionButton = (Button) findViewById(R.id.newQuestionButton);
            mNewQuestionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                    startActivity(intent);

                }
            });
    }
}
