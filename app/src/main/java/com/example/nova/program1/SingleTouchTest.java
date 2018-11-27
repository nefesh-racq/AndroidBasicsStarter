package com.example.nova.program1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by nova on 04-05-18.
 */

public class SingleTouchTest extends Activity implements View.OnTouchListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("toca la pantalla (toque simple)");
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        builder.setLength(0);

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: builder.append("down"); break;
            case MotionEvent.ACTION_MOVE: builder.append("move"); break;
            case MotionEvent.ACTION_CANCEL: builder.append("cancel"); break;
            case MotionEvent.ACTION_UP: builder.append("up"); break;
        }

        builder.append(motionEvent.getX());
        builder.append(", ");
        builder.append(motionEvent.getY());

        String text = builder.toString();

        Log.d("touchTest",  text);
        textView.setText(text);

        return true;
    }
}
