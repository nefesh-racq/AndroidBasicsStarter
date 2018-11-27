package com.example.nova.program1;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by nova on 04-05-18.
 */

public class MultiTouchTest extends Activity implements View.OnTouchListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;
    float[] x = new float[10];
    float[] y = new float[10];
    boolean[] touched = new boolean[10];
    int[] id = new int[10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("toca la pantalla (multiples dedos)");
        textView.setOnTouchListener(this);
        setContentView(textView);

        for (int i = 0; i < 10; i++) {
            id[i] = -1;
        }

        updateTextView();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (motionEvent.getActionIndex() & MotionEvent.ACTION_POINTER_ID_MASK) >>
                MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerCount = motionEvent.getPointerCount();

        for (int i = 0; i < 10; i++) {
            if (i >= pointerCount) {
                touched[i] = false;
                id[i] = -1;
                continue;
            }

            if (motionEvent.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
                // si se trata de un evento up / down / cancel / out, enmascare el id para ver si debemos procesar para este punto de contacto
                continue;
            }

            int pointerId = motionEvent.getPointerId(i);

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touched[i] = true;
                    id[i] = pointerId;
                    x[i] = (int)motionEvent.getX(i);
                    y[i] = (int)motionEvent.getY(i);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                    touched[i] = false;
                    id[i] = -1;
                    x[i] = (int)motionEvent.getX(i);
                    y[i] = (int)motionEvent.getY(i);
                    break;

                case MotionEvent.ACTION_MOVE:
                    touched[i] = true;
                    id[i] = pointerId;
                    x[i] = (int)motionEvent.getX(i);
                    y[i] = (int)motionEvent.getY(i);
                    break;
            }
        }

        updateTextView();

        return true;
    }

    private void updateTextView() {
        builder.setLength(0);

        builder.append("toque la pantalla con mas de un dedo\n");

        for (int i = 0; i < 10; i++) {
            builder.append(touched[i]);
            builder.append(", ");
            builder.append(id[i]);
            builder.append(", ");
            builder.append(x[i]);
            builder.append(", ");
            builder.append(y[i]);
            builder.append('\n');
        }

        textView.setText(builder.toString());
    }
}
