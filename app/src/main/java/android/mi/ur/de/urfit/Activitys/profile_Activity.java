package android.mi.ur.de.urfit.Activitys;

import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        initUI();
    }

    private void initUI(){
        initProfilButton();
    }

    private void updateList() {
        //tasks.clear();
        //tasks.addAll(db.getAllToDoItems());
        //tasks_adapter.notifyDataSetChanged();
    }

    private void addProfil(String age, String gender, String size, String weight, String stepLength) {
        //Date dueDate = getDateFromString(date);
        //GregorianCalendar cal = new GregorianCalendar();
        //cal.setTime(dueDate);

        //ToDoItem newTask = new ToDoItem(task, cal.get(Calendar.DAY_OF_MONTH),
        //        cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

        //db.insertToDoItem(newTask);
        updateList();
    }

    private void initProfilButton() {
        Button addProfilButton = (Button) findViewById(R.id.profil_save);
        addProfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ageEdit = (EditText) findViewById(R.id.editText_age);
                EditText genderEdit = (EditText) findViewById(R.id.editText_gender);
                EditText sizeEdit = (EditText) findViewById(R.id.editText_size);
                EditText weightEdit = (EditText) findViewById(R.id.editText_weight);
                EditText stepLengthEdit = (EditText) findViewById(R.id.editText_steplength);
                String age = ageEdit.getText().toString();
                String gender = genderEdit.getText().toString();
                String size = sizeEdit.getText().toString();
                String weight = weightEdit.getText().toString();
                String stepLength = stepLengthEdit.getText().toString();

                if (stepLength.equals("")) {
                } else {
                    ageEdit.setText("");
                    genderEdit.setText("");
                    sizeEdit.setText("");
                    weightEdit.setText("");
                    stepLengthEdit.setText("");

                    addProfil(age,gender,size,weight,stepLength);
                }
            }
        });
    }
}
