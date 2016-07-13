package com.epicodus.discussionboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    final ArrayList<String> questions = new ArrayList<>();
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.newQuestionButton) Button mNewQuestionButton;
    @Bind(R.id.questionEditText) EditText mQuestionEditText;
    @Bind(R.id.questionListView) ListView mQuestionListView;
    private DatabaseReference mInputtedQuestionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mInputtedQuestionReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_QUESTION);

        mInputtedQuestionReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d("onDataChange", "called");
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    Question question = questionSnapshot.getValue(Question.class);
                    Log.d("Questions updated", "question: " + question);
                    questions.add(question.getQuestion());
                }
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, questions);
                mQuestionListView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String question = intent.getStringExtra("question");

        mNewQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = mQuestionEditText.getText().toString();
                saveQuestionToFirebase(question);

                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                intent.putExtra("question", question);
                startActivity(intent);
            }
        });

        final ListView questions = (ListView) findViewById(R.id.questionListView);

        questions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String question = (String) (questions.getItemAtPosition(i));
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("question", question);
                startActivity(intent);
            }
        });
    }

    public void saveQuestionToFirebase(String question) {
        Question questionObject = new Question(question);
        DatabaseReference ref = mInputtedQuestionReference.push();
        String pushId = ref.getKey();
        questionObject.setPushId(pushId);
        ref.setValue(questionObject);
    }
}
