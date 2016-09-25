package android.mi.ur.de.urfit.Activitys;

import android.content.Context;
import android.content.Intent;
import android.mi.ur.de.urfit.Hilfsklassen.Database;
import android.mi.ur.de.urfit.Hilfsklassen.URFitItem;
import android.mi.ur.de.urfit.Hilfsklassen.URFitListAdapter;
import android.mi.ur.de.urfit.R;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Button startButton;
    private Button ergebnisButton;
    private Button hilfeButton;
    private Button profilButton;

    private Intent startIntent;
    private Intent ergebnisIntent;
    private Intent hilfeIntent;
    private Intent profileIntent;

    //Database

    private Database dataSource;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        dataSource = new Database(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();

    }


    private void initUI() {
        initStartButton();
        initErgebnisButton();
        initHilfeButton();
        initProfileButton();


    }

    private void initProfileButton() {
        profilButton = (Button) findViewById(R.id.profilButton);
        profileIntent = new Intent(MainActivity.this,profile_Activity.class);
        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    private void initHilfeButton() {
        hilfeButton = (Button) findViewById(R.id.hilfeButton);
        hilfeIntent = new Intent(MainActivity.this,hilfe_activity.class);
        hilfeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(hilfeIntent);
            }
        });
    }

    private void initErgebnisButton() {
        ergebnisIntent = new Intent(MainActivity.this,ergebnis_activity.class);
        ergebnisButton = (Button) findViewById(R.id.ergebnisButton);
        ergebnisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ergebnisIntent);
            }
        });
    }

    private void initStartButton() {
        startButton = (Button) findViewById(R.id.startButton);
        startIntent = new Intent(MainActivity.this,start_activity.class);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startIntent);
            }
        });
    }
}
