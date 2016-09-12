package android.mi.ur.de.urfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button startButton = (Button) findViewById(R.id.startButton);
    Button ergebnisButton = (Button) findViewById(R.id.ergebnisButton);
    Button hilfeButton = (Button) findViewById(R.id.hilfeButton);
    Button einstellungsButton = (Button) findViewById(R.id.einstellungsButton);
    Button profilButton = (Button) findViewById(R.id.profilButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
