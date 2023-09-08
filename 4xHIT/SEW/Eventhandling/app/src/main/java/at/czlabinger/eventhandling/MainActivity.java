package at.czlabinger.eventhandling;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            float dx = event.getX() - x;
            float dy = event.getY() - y;

            imageView.setX(imageView.getX() + dx);
            imageView.setY((imageView.getY() + dy));

            x = event.getX();
            y = event.getY();
        }

        return super.onTouchEvent(event);
    }

}