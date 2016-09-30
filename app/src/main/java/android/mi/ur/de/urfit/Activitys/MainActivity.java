package android.mi.ur.de.urfit.Activitys;

import android.content.Intent;
import android.mi.ur.de.urfit.Hilfsklassen.Database;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button ergebnisButton;
    private Button aimButton;
    private Button profilButton;

    private Intent startIntent;
    private Intent ergebnisIntent;
    private Intent aimIntent;
    private Intent profileIntent;

    //Database

    public static Database dataSource;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        dataSource = new Database(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initUI() {
        initStartButton();
        initErgebnisButton();
        initAimsButton();
        initProfileButton();


    }

    private void initProfileButton() {
        profilButton = (Button) findViewById(R.id.profilButton);
        profileIntent = new Intent(MainActivity.this, Profile_start_activity.class);
        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    private void initAimsButton() {
        aimButton = (Button) findViewById(R.id.AimsButton);
        aimIntent = new Intent(MainActivity.this, aims_activity.class);
        aimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(aimIntent);
            }
        });
    }

    private void initErgebnisButton() {
        ergebnisIntent = new Intent(MainActivity.this, ergebnis_activity.class);
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
        startIntent = new Intent(MainActivity.this, start_activity.class);
        startIntent.putExtra("Database", String.valueOf(dataSource));
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startIntent);
            }
        });
    }

    protected void onDestroy() {
        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
        super.onStop();
    }
}
