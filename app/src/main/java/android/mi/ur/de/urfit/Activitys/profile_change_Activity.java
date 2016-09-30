package android.mi.ur.de.urfit.Activitys;

import android.mi.ur.de.urfit.Hilfsklassen.User;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class profile_change_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change);
        initUI();
    }

    private void initUI(){
        initProfilButton();
    }


    private void initProfilButton() {
        Button addProfilButton = (Button) findViewById(R.id.profil_save);
        addProfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEdit = (EditText) findViewById(R.id.editText_name);
                Switch genderEdit = (Switch) findViewById(R.id.edit_gender);
                genderEdit.setTextOn("männlich");
                genderEdit.setTextOff("weiblich");
                EditText stepLengthEdit = (EditText) findViewById(R.id.editText_steplength);
                String name = nameEdit.getText().toString();
                Boolean gender = genderEdit.isChecked();
                String stepLength = stepLengthEdit.getText().toString();

                MainActivity.dataSource.insertUser(new User(name,gender,stepLength,"1"));

            }
        });
    }
}
