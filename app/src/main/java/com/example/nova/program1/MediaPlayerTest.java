package com.example.nova.program1;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by nova on 04-05-18.
 */

public class MediaPlayerTest extends Activity {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();

        try {
            AssetManager assetmanager = getAssets();
            AssetFileDescriptor descriptor = assetmanager.openFd("efectos/musica.ogg");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),
                    descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        }
        catch (IOException e) {
            textView.setText("no se puede cargar el archivo de musica, " + e.getMessage());
            mediaPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mediaPlayer != null)
            mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.pause();

            if (isFinishing()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }
}
