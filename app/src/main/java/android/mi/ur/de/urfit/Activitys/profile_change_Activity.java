package android.mi.ur.de.urfit.Activitys;

import android.content.Intent;
import android.mi.ur.de.urfit.Hilfsklassen.User;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;

public class profile_change_Activity extends AppCompatActivity {

    Intent backIntent;

    private EditText nameEdit;
    private Switch genderEdit;
    private EditText stepLengthEdit;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change);
        initUI();
    }

    private void deleteUsers() {
        ArrayList<User> users = MainActivity.dataSource.getAllUser();
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i);
            MainActivity.dataSource.removeUser(users.get(i));
        }
    }

    private void initUI() {
        initProfilButton();
    }


    private void initProfilButton() {
        Button addProfilButton = (Button) findViewById(R.id.profil_save);
        backIntent = new Intent(profile_change_Activity.this, Profile_start_activity.class);
        addProfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUsers();
                initEditText();
                insert();
                startActivity(backIntent);
            }
        });
    }

    private void insert() {
        String name = nameEdit.getText().toString();
        Boolean gender = genderEdit.isChecked();
        String stepLength = stepLengthEdit.getText().toString();

        MainActivity.dataSource.insertUser(new User(name, gender, stepLength, "1"));
    }

    private void initEditText() {
        nameEdit = (EditText) findViewById(R.id.editText_name);
        genderEdit = (Switch) findViewById(R.id.edit_gender);
        genderEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genderEdit.isChecked()) {
                    genderEdit.setText("Männlich");
                } else {
                    genderEdit.setText("Weiblich");
                }
            }
        });
        genderEdit.setTextOn("männlich");
        genderEdit.setTextOff("weiblich");
        stepLengthEdit = (EditText) findViewById(R.id.editText_steplength);
    }
}
