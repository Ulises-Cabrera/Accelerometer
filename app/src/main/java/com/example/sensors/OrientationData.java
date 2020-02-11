package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class OrientationData implements SensorEventListener {

    private SensorManager manager;
    private Sensor accelerometer;
    private final float[] accelerometerReading = new float[3];

    OrientationData(Context context) {
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        assert manager != null;
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    void register() {
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    void pause() {
        manager.unregisterListener(this);
    }

    void onResume() {
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.length);
        }
    }

    String getAngle() {
        double angle = Math.atan2(accelerometerReading[1], accelerometerReading[0]) * 57.3;
        return "El angulo es: " + angle;
    }
}
