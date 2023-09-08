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
                Log.i("onCreate()", "Switching to Main Activity.");

                Intent i = new Intent(Second.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}