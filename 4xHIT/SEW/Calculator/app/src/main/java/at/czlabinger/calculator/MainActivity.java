package at.czlabinger.calculator;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.darkGreen, this.getTheme()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();


        addSaveLoadListeners();
        addOutputListener();
        if(!addTextListeners()) return;

        Button calc = (Button) findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(checkValues(0) && checkValues(1))) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Please enter two valid integers!",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                int selected = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
                TextView output = (TextView) findViewById(R.id.output);

                if(((RadioButton) findViewById(selected)) == null) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Please select an operator!",Toast.LENGTH_SHORT);
                    toast.show();

                    return;
                }

                int val1 = Integer.parseInt(((TextView) findViewById(R.id.firstInput)).getText().toString());
                int val2 = Integer.parseInt(((TextView) findViewById(R.id.secondInput)).getText().toString());

                switch (((RadioButton) findViewById(selected)).getText().toString()) {
                    case "+":
                        output.setText(String.valueOf(val1 + val2));
                        break;
                    case "-":
                        output.setText(String.valueOf(val1 - val2));
                        break;
                    case "*":
                        output.setText(String.valueOf(val1 * val2));
                        break;
                    case "/":
                        if(val2 == 0) {
                            Toast toast = Toast.makeText(getApplicationContext(),"Can't divide by 0!",Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }
                        output.setText(String.valueOf(val1 / val2));
                        break;
                }
            }
        });
    }

    private boolean addTextListeners() {
        EditText first = (EditText) findViewById(R.id.firstInput);
        first.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!checkValues(0)) return;
                int val1 = Integer.parseInt(((TextView) findViewById(R.id.firstInput)).getText().toString());

                if(val1 < 0) {
                    ((TextView) findViewById(R.id.firstInput)).setTextColor(getResources().getColor(R.color.red, null));
                } else {
                    ((TextView) findViewById(R.id.firstInput)).setTextColor(getResources().getColor(R.color.black, null));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        EditText second = (EditText) findViewById(R.id.secondInput);
        second.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!checkValues(1)) return;
                int val2 = Integer.parseInt(((TextView) findViewById(R.id.secondInput)).getText().toString());

                if(val2 < 0) {
                    ((TextView) findViewById(R.id.secondInput)).setTextColor(getResources().getColor(R.color.red, null));
                } else {
                    ((TextView) findViewById(R.id.secondInput)).setTextColor(getResources().getColor(R.color.black, null));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        return true;
    }

    private boolean checkValues(int i) {
        if(i == 0) {
            String val1Str = ((TextView) findViewById(R.id.firstInput)).getText().toString();

            if(val1Str.equals("")) return false;

            try {
                Integer.parseInt(val1Str);
            } catch (NumberFormatException e ) {
                return false;
            }
            return true;
        } else {

            String val2Str = ((TextView) findViewById(R.id.secondInput)).getText().toString();

            if(val2Str.equals("")) return false;

            try {
                Integer.parseInt(val2Str);
            } catch (NumberFormatException e ) {
                return false;
            }
            return true;
        }
    }

    private void addOutputListener() {
        TextView output = (TextView) findViewById(R.id.output);
        output.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    output.setText(getResources().getString(R.string.zero));
                }
                return MainActivity.super.onTouchEvent(event);
            }
        });
    }

    private void addSaveLoadListeners() {
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(v -> {
            SharedPreferences pref = getApplication().getSharedPreferences("operation", 0);
            SharedPreferences.Editor ed = pref.edit();
            int selected = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
            ed.putInt("operation", selected);
            ed.apply();
        });

        Button load = (Button) findViewById(R.id.load);
        load.setOnClickListener(v -> {
            SharedPreferences pref = getApplication().getSharedPreferences("operation", 0);
            int loadInt = pref.getInt("operation", 0);

            ((RadioButton) findViewById(loadInt)).setChecked(true);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.reset) {
            reset();
        } else if(item.getItemId() == R.id.about) {
            switchToAbout();
        }

        return true;
    }

    private void reset() {
        ((EditText) findViewById(R.id.firstInput)).setText("");
        ((EditText) findViewById(R.id.secondInput)).setText("");

        try {
            int selected = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
            ((RadioButton) findViewById(selected)).setChecked(false);
        } catch (NullPointerException e){}

        ((TextView) findViewById(R.id.output)).setText("");

        SharedPreferences pref = getApplication().getSharedPreferences("operation", 0);
        SharedPreferences.Editor ed = pref.edit();
        ed.remove("operation");
        ed.apply();
    }

    private void switchToAbout() {
        Intent i = new Intent(MainActivity.this, About.class);
        i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }
}