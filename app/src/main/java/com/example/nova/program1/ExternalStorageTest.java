package com.example.nova.program1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by nova on 04-05-18.
 */

public class ExternalStorageTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);

        String state = Environment.getExternalStorageState();

        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            textView.setText("no existe una memoria externa");
        }
        else {
            File externalDir = Environment.getExternalStorageDirectory();
            File textFile = new File(externalDir.getAbsolutePath() + File.separator + "mitexto.txt");

            try {
                writeTextFile(textFile, "esto es un test, jajaja");
                String text = readTextFile(textFile);
                textView.setText(text);

                if (!textFile.delete()) {
                    textView.setText("no se puede remover temporalmente el arhivo");
                }
            }
            catch (IOException e) {
                textView.setText("ocurrio algun problema! " + e.getMessage());
            }
        }
    }

    public void writeTextFile(File file, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(text);
        writer.close();
    }

    public String readTextFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append('\n');
        }

        reader.close();

        return builder.toString();
    }
}
