package at.czlabinger.androidtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView o = (TextView) findViewById(R.id.showInput);
        Intent i = getIntent();
        String input = i.getStringExtra("input");

        o.setText(input);

        Button prev_button = (Button) findViewById(R.id.prev_button);

        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Second.this, MainActivity.class);
                startActivity(i);
            }
        });
        Log.i("onCreate()", "Second Activity created.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart()", "Second Activity started.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onCreate()", "Second Activity resumed.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onCreate()", "Second Activity paused.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onCreate()", "Second Activity stopped.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onCreate()", "Second Activity destroyed.");
    }
}