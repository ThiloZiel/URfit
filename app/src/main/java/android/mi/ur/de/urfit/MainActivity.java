package android.mi.ur.de.urfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button startButton = (Button) findViewById(R.id.startButton);
    Button ergebnisButton = (Button) findViewById(R.id.ergebnisButton);
    Button hilfeButton = (Button) findViewById(R.id.hilfeButton);
    Button settingsButton = (Button) findViewById(R.id.einstellungsButton);
    Button profilButton = (Button) findViewById(R.id.profilButton);

    Intent startIntent = new Intent(MainActivity.this,start_activity.class);
    Intent ergebnisIntent = new Intent(MainActivity.this,ergebnis_activity.class);
    Intent hilfeIntent = new Intent(MainActivity.this,hilfe_activity.class);
    Intent settingsIntent = new Intent(MainActivity.this,SettingsActivity.class);
    Intent profileIntent = new Intent(MainActivity.this,profile_Activity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();

    }

    private void initButtons() {
        initStartButton();
        initErgebnisButton();
        initHilfeButton();
        initSettingsButton();
        initProfileButton();


    }

    private void initProfileButton() {
        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    private void initSettingsButton() {
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
            }
        });
    }

    private void initHilfeButton() {
        hilfeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(hilfeIntent);
            }
        });
    }

    private void initErgebnisButton() {
        ergebnisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ergebnisIntent);
            }
        });
    }

    private void initStartButton() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startIntent);
            }
        });
    }
}
