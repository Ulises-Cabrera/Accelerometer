package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private OrientationData orientationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientationData = new OrientationData(this);
        orientationData.register();
        text = findViewById(R.id.txt);

        findViewById(R.id.btn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        text.setText(orientationData.getAngle());
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        orientationData.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        orientationData.pause();
    }


}
