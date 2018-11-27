package com.example.nova.program1;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by nova on 04-05-18.
 */

public class AccelerometerTest extends Activity implements SensorEventListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);

        setContentView(textView);
        SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
            textView.setText("no esta instalado un acelerometro..");
        }
        else {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);

            if (!manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)) {
                textView.setText("no puede registrarse el sensor en el escuchador");
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        builder.setLength(0);
        builder.append("prueba del acelerometro:");
        builder.append("\nx: ");
        builder.append(sensorEvent.values[0]);
        builder.append("\ny: ");
        builder.append(sensorEvent.values[1]);
        builder.append("\nz: ");
        builder.append(sensorEvent.values[2]);
        textView.setText(builder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
