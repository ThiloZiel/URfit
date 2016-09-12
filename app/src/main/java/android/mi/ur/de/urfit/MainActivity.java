package android.mi.ur.de.urfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{


    Button startButton;
    Button ergebnisButton;
    Button hilfeButton;
    Button profilButton;

    Intent startIntent;
    Intent ergebnisIntent;
    Intent hilfeIntent;
    Intent profileIntent;

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
