package com.epicodus.discussionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SuccessActivity extends AppCompatActivity {
    private Button mSuccessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        mSuccessButton = (Button) findViewById(R.id.returnButton);
        mSuccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SuccessActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
