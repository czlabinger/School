package at.czlabinger.androidtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.input);
                String input = et.getText().toString();

                Intent i = new Intent(MainActivity.this, Second.class);
                i.putExtra("input", input);
                startActivity(i);
            }
        });

        Log.i("onCreate()", "Main Activity created.");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart()", "Main Activity started.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onCreate()", "Main Activity resumed.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onCreate()", "Main Activity paused.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onCreate()", "Main Activity stopped.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onCreate()", "Main Activity destroyed.");
    }
}