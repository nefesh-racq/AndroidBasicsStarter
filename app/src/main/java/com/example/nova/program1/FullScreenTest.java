package com.example.nova.program1;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by nova on 04-05-18.
 */

public class FullScreenTest extends SingleTouchTest {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
    }
}
