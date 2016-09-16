package android.mi.ur.de.urfit.Activitys;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class start_activity extends AppCompatActivity implements SensorEventListener {


    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mStepDetectorSensor;

    private int value;

    private TextView stepView;
    private TextView dailyAimView;
    private TextView mainAimView;
    private Button stopButton;

    private Intent stopButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        initUI();
        initSensors();

    }

    private void initSensors() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    private void initUI() {
        initStopButton();
        initTextViews();

    }

    private void initTextViews() {
        stepView = (TextView) findViewById(R.id.number_Steps_View);
        dailyAimView = (TextView) findViewById(R.id.persöhnliches_Tages_Ziel_View);
        mainAimView = (TextView) findViewById(R.id.persöhnliches_Hauptziel);
    }

    private void initStopButton() {
        stopButton = (Button) findViewById(R.id.stop_Button);
        stopButtonIntent = new Intent(start_activity.this,MainActivity.class);
        stopButtonIntent.putExtra("Schritte",value);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(stopButtonIntent);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        value = -1;

        if (values.length > 0){
            value = (int) values[0];
        }

        if(sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
            stepView.setText("Bisher gelaufene Schritte: " + value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume(){
        super.onResume();

        mSensorManager.registerListener(this,mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this,mStepDetectorSensor,SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onStop(){
        super.onStop();
        mSensorManager.unregisterListener(this,mStepCounterSensor);
        mSensorManager.unregisterListener(this,mStepDetectorSensor);
    }
}
