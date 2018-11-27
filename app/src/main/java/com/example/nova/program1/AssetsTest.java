package com.example.nova.program1;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nova on 04-05-18.
 */

public class AssetsTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);

        AssetManager assetManager = getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open("texts/mitexto.txt");
            String text = loadTextFile(inputStream);
            textView.setText(text);
        }
        catch (IOException e) {
            textView.setText("no se puede cargar el archivo, " + e.getMessage());
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    textView.setText("no se puede cerrar el archivo, " + e.getMessage());
                }
            }
        }

    }

    private String loadTextFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bytesStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len = 0;

        while ((len = inputStream.read(bytes)) > 0) {
            bytesStream.write(bytes, 0, len);
        }

        return new String(bytesStream.toByteArray(), "UTF8");
    }

}
