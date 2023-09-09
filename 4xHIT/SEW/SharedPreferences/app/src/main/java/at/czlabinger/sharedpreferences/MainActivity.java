package at.czlabinger.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getApplication().getSharedPreferences("Name", 0);
        String load = pref.getString("name", null);

        EditText te = (EditText) findViewById(R.id.text);

        if(load == null) {
           load = getString(R.string.defaultValue);
        }

        te.setText(load);

        Button save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Editor ed = pref.edit();
                ed.putString("name", te.getText().toString());
                ed.apply();
            }
        });

        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Editor ed = pref.edit();
                ed.remove("name");
                te.setText("");
                ed.apply();
            }
        });

    }
}