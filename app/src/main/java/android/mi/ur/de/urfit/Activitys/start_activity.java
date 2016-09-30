package android.mi.ur.de.urfit.Activitys;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.mi.ur.de.urfit.Hilfsklassen.Calculator;
import android.mi.ur.de.urfit.Hilfsklassen.URFitItem;
import android.mi.ur.de.urfit.Hilfsklassen.User;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class start_activity extends AppCompatActivity implements SensorEventListener {

    // zum rauslöschen

    // Quelle timer: http://www.tippscom.de/wie-du-eine-stoppuhr-app-in-android-programmierst/


    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mStepDetectorSensor;


    private TextView stepView;
    private TextView timeView;
    private Button stopButton;

    private Intent stopButtonIntent;

    private double distance;
    private int steps;
    private double time;
    private double kCal;
    private URFitItem nextURFitItem;

    public static final long SLEEPTIME = 10;
    private boolean running;
    private Thread refreshThread;

    private Calculator calc;
    private Calendar cal;
    private int mYear;
    private int mMonth;
    private int mDay;

    private NumberFormat n;

    private User user;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        initNumberFormat();
        initUI();
        initSensors();
        initTimeCounter();
        initCalculator();
        initCalendar();

    }

    private void initNumberFormat() {
        n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(2);
    }

    /*
    initCalendar()
    * initialisiert den Kalender
    * holt Jahr,Monat,Tag und speichert diese in den zugehörigen Variablen
     */
    private void initCalendar() {
        cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
    }

    private void initCalculator() {
        calc = new Calculator();
    }

    /*
    initTimeCounter() setzt die Anfangswerte für den Timer und startet den Timer sobald die Activity aufgerufen wird
     */

    private void initTimeCounter() {
        running = true;
        time = 0;
        initThread();
    }

    /*
    initSensors() stellt die nötigen Sensoren bereit
     */

    private void initSensors() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    private void initUI() {
        initStopButton();
        initTextViews();

    }

    /*
    initTextViews initalisiert die einzelnen TextViews
     */

    private void initTextViews() {
        stepView = (TextView) findViewById(R.id.number_Steps_View);
        timeView = (TextView) findViewById(R.id.time_View);
    }

    /*
    StopButton kehrt zur MainActivity zurück
     */

    private void initStopButton() {
        stopButton = (Button) findViewById(R.id.addActivityButton);
        stopButtonIntent = new Intent(start_activity.this, MainActivity.class);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(stopButtonIntent);
            }
        });

    }

    /*
    calc Values berechnet die benötigten Werte für das nächste URFit Item

    wird bei verwendung des Stop buttons ausgelöst um die endgültigen Werte zu verwenden
     */

    private void calcValues() {
        UsersfromDatabase();
        if (steps == 0) {
            distance = 0;
            kCal = 0;
        } else {
            //strecke in KM
            distance = (steps * Double.parseDouble(user.getStepLength())) / 1000;
            //werte für Calculator setzten (Entfernung,Zeit,Pausen)
            calc.setValues(distance, time, 0);
            kCal = calc.calculateKcal();
        }
    }

    private void UsersfromDatabase() {
        users = MainActivity.dataSource.getAllUser();
        if (users.size() != 0) {
            user = users.get(0);
        } else {
            user = new User("Max Mustermann", true, "74", "1");
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        steps = -1;

        if (values.length > 0) {
            steps = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            stepView.setText("Bisher gelaufene Schritte: " + steps);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
        calcValues();
        nextURFitItem = new URFitItem("" + steps, n.format(kCal), mDay, mMonth, mYear);
        MainActivity.dataSource.insertURFitItem(nextURFitItem);
    }

    /*
    initThread
    zählt die Sekunden, indem es immer 0.01 Sekunden zuzählt und dann genau diese Zeit wartet
     */

    public void initThread() {
        refreshThread = new Thread(new Runnable() {
            public void run() {
                while (running) {
                    time = time + 0.01;
                    try {
                        Thread.sleep(SLEEPTIME);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    runOnUiThread(new Runnable() {
                        public void run() {
                            timeView.setText(getString(R.string.time_string, String.format("%.2f", time)));
                        }
                    });

                }
            }
        });
        refreshThread.start();
    }
}
