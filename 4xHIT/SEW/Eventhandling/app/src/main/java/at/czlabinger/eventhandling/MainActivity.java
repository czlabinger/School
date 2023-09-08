package at.czlabinger.eventhandling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    String msg = "";
    LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
        img.setTag("Image");
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);

                Log.i(null, "a");

                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);

                v.startDragAndDrop(dragData, shadowBuilder, null, 0);
                return true;
            }
        });


    }
}