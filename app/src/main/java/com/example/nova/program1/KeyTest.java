package com.example.nova.program1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by nova on 04-05-18.
 */

public class KeyTest extends Activity implements View.OnKeyListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);

        textView.setText("presiones una tecla (si tiene alguna en el telefono)");
        textView.setOnKeyListener(this);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();

        setContentView(textView);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        builder.setLength(0);

        switch (keyEvent.getAction()) {
            case KeyEvent.ACTION_DOWN:
                builder.append("down, ");
                break;
            case KeyEvent.ACTION_UP:
                builder.append("up, ");
                break;
        }

        builder.append(keyEvent.getKeyCode());
        builder.append(", ");
        builder.append((char)keyEvent.getUnicodeChar());

        String text = builder.toString();

        Log.d("keyTest", text);
        textView.setText(text);

        return keyEvent.getKeyCode() != keyEvent.KEYCODE_BACK;
    }
}
