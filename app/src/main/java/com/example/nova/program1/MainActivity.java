/*
*
* program1 andridbasicstarter muestra los controles basicos
* toques de pantalla, musica graficos, color horientacion pantalla,
*
* */
package com.example.nova.program1;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String teststable[] = {"LifeCycleTest", "SingleTouchTest", "MultiTouchTest",
    "KeyTest", "AccelerometerTest", "AssetsTest", "ExternalStorageTest",
    "SoundPoolTest", "MediaPlayerTest", "FullScreenTest", "RenderViewTest",
    "ShapeTest", "BitmapTest", "FontTest", "SurfaceViewTest"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, teststable));
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        String testName = teststable[position];

        try {
            Class classs = Class.forName("com.example.nova.program1." + testName);
            Intent intent = new Intent(this, classs);
            startActivity(intent);
        }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }
}
