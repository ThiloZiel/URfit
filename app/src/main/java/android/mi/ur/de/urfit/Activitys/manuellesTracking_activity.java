package android.mi.ur.de.urfit.Activitys;

import android.content.Intent;
import android.mi.ur.de.urfit.Hilfsklassen.Calculator;
import android.mi.ur.de.urfit.Hilfsklassen.URFitItem;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class manuellesTracking_activity extends AppCompatActivity {

    // zum rauslöschen

    private EditText distanceIntput;
    private EditText timeInput;
    private EditText dateInput;

    double distance;
    double time;
    String date;

    int day;
    int month;
    int year;

    private Button addButton;
    private Calculator calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuelles_tracking_activity);
        initUi();
        initCalculator();

    }

    private void initCalculator() {
        calc = new Calculator();
    }

    private void initUi() {
        initButton();

    }

    private void initButton() {
        addButton = (Button) findViewById(R.id.addActivityButton);
        final Intent backIntent =  new Intent(manuellesTracking_activity.this,ergebnis_activity.class);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEditText();
                distance = Double.parseDouble(distanceIntput.getText().toString());
                time = Double.parseDouble(timeInput.getText().toString());
                date = dateInput.getText().toString();
                calc.setValues(distance,time,0);
                double calories = calc.calculateKcal();
                calcDate();
                URFitItem NextUrFitItem = new URFitItem(distance/74+1000+"", calories+"", day, month, year);
                MainActivity.dataSource.insertURFitItem(NextUrFitItem);

                startActivity(backIntent);
            }
        });
    }

    private void calcDate() {
        day = Integer.parseInt(date.substring(0,1));
        month = Integer.parseInt(date.substring(3,4));
        year = Integer.parseInt(date.substring(5,6));

    }

    private void initEditText() {
        distanceIntput = (EditText) findViewById(R.id.editText_distance);
        timeInput = (EditText) findViewById(R.id.editText_time_spent);
        dateInput = (EditText) findViewById(R.id.editText_activity_time);

    }

}
