package android.mi.ur.de.urfit.Activitys;

import android.content.Intent;
import android.mi.ur.de.urfit.Hilfsklassen.User;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile_start_activity extends AppCompatActivity {

    TextView nameView;
    TextView levelView;
    TextView genderView;
    TextView stepLengthView;

    Button changeProfile;

    Intent changeButtonIntent;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_start_activity);
        //initUi();
        initButton();
    }

    private void initUi() {
        initUser();
        initTextView();
        initButton();

    }

    private void initButton() {
        changeProfile = (Button) findViewById(R.id.changeProfileButton);
        changeButtonIntent =  new Intent(Profile_start_activity.this,profile_change_Activity.class);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(changeButtonIntent);
            }
        });
    }

    private void initUser() {
        ArrayList<User> users = MainActivity.dataSource.getAllUser();
        user = users.get(0);
    }

    private void initTextView() {
        nameView = (TextView) findViewById(R.id.profile_name_view);
        nameView.setText(user.getName());
        levelView = (TextView) findViewById(R.id.profile_level_view);
        levelView.setText(user.getLevel());
        genderView = (TextView) findViewById(R.id.profile_gender_view);
        genderView.setText(user.getGender());
        stepLengthView = (TextView) findViewById(R.id.profile_step_length_view);
        stepLengthView.setText(user.getStepLength());
    }
}
