package com.example.nova.program1;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by nova on 04-05-18.
 */

public class SoundPoolTest extends Activity implements View.OnTouchListener {
    SoundPool soundPool;
    int explosionId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setOnTouchListener(this);
        setContentView(textView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("efectos/efect0.ogg");
            explosionId = soundPool.load(descriptor, 1);
        }
        catch (IOException e) {
            textView.setText("no se puede cargar el efecto desde asset, " + e.getMessage());
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (explosionId != -1)
                soundPool.play(explosionId, 1, 1, 0, 0, 1);
        }

        return true;
    }
}
