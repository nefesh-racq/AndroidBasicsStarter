package com.example.nova.program1;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nova on 04-05-18.
 */

public class BitmapTest extends Activity {

    class RenderView extends View {
        Bitmap img565;
        Bitmap img4444;
        Rect dst = new Rect();

        public RenderView(Context context) {
            super(context);

            try {
                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open("imgrgb565.png");
                img565 = BitmapFactory.decodeStream(inputStream);

                inputStream.close();
                Log.d("BitmapText", "imgrg888.png format: " + img565.getConfig());

                inputStream = assetManager.open("imgargb4444.png");

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                img4444 = BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();
                Log.d("bitmapText ", "imgargb888.png: " + img4444.getConfig());
            }
            catch (IOException e) {
                //
            }
            finally {
                //
            }
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(0, 0, 0);
            dst.set(50, 50, 350, 350);
            canvas.drawBitmap(img565, null, dst, null);
            canvas.drawBitmap(img4444, 100, 100, null);
            invalidate();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new RenderView(this));
    }
}
