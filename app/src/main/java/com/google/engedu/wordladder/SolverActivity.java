package com.google.engedu.wordladder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.engedu.worldladder.R;

import java.util.ArrayList;

public class SolverActivity extends AppCompatActivity {


    TextView start,end;
    Button solve;
    LinearLayout linearLayout;
    ArrayList<String> path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_solver);

        linearLayout = (LinearLayout)findViewById(R.id.linear);
        start = (TextView)findViewById(R.id.startTextView);
        end = (TextView)findViewById(R.id.endTextView);
        solve = (Button)findViewById(R.id.solveButton);

        path = getIntent().getStringArrayListExtra("path");
        start.setText(path.get(0));
        end.setText(path.get(path.size()-1));

        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout2 = new LinearLayout(SolverActivity.this);
                linearLayout2.setOrientation(LinearLayout.VERTICAL);
                for( int i = 1; i < path.size()-1; i++ )
                {
                    TextView textView = new TextView(SolverActivity.this);
                    textView.setText(path.get(i));
                    linearLayout2.addView(textView);
                }
                linearLayout.addView(linearLayout2);
            }
        });




    }
}
