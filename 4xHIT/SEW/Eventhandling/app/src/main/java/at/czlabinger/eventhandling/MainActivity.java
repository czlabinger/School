package at.czlabinger.eventhandling;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int xDelta, yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                Log.i("a", String.valueOf(x));

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        view.setX(x);
                        view.setY(y);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setX(x);
                        view.setY(y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.setX(x);
                        view.setY(y);
                        break;
                }
                return true;
            }
        });
    }
}
