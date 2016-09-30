package android.mi.ur.de.urfit.Activitys;

import android.mi.ur.de.urfit.Hilfsklassen.UserAim;
import android.mi.ur.de.urfit.Hilfsklassen.UserAimListAdapter;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class aims_activity extends AppCompatActivity {

    private EditText editAimTitel;
    private EditText editAimSteps;
    private Button addAim;
    private ListView aimListView;
    private ArrayList<UserAim> userAims = new ArrayList<>();
    private UserAimListAdapter adapter;

    private String steps;
    private String titel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aims_activity);
        initUI();
    }

    /*
    initUI()
    behinhaltet die Methoden um die einzelnen UI Elemente zu initialisieren
     */

    private void initUI() {
        initList();
        initListAdapter();
        initItemList();
        initAdd();

    }

    // behinhaltet die initialisierung der editTexte und des Buttons
    private void initAdd() {
        initEdit();
        initButton();
    }

    // initialisiert beim Aufrufen die EditTexte und speichert die Eingaben in passenden Variablen
    private void initEdit() {
        editAimTitel = (EditText) findViewById(R.id.edit_aims_titel);
        editAimSteps = (EditText) findViewById(R.id.edit_aims_steps);

        titel = editAimTitel.getText().toString();
        steps = editAimSteps.getText().toString();
    }

    /*
    * initialisiert den Button
    * registriert einen OnClickListener auf diesem.
    * wenn der Button geklickt wird wird das UserAim element in der Datenbank gespeichert und die Methode UpdateList aufgerufen
     */
    private void initButton() {
        addAim = (Button) findViewById(R.id.add_aim_button);
        addAim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataSource.insertUserAim(new UserAim(titel, steps, false));
                updateList();
            }
        });

    }

    // ruft die Methode updateList() auf
    private void initItemList() {
        updateList();
    }


    // initialisiert den ListAdapter
    private void initListAdapter() {
        adapter = new UserAimListAdapter(this, userAims);
        aimListView.setAdapter(adapter);
    }

    /*
    * initialisiert den ListView
    * registriert einen OnItemLongClickListener auf diesem
    * durch langes klicken eines Elements kann dieses durch Aufruf der removeTaskAtPosition(int position) aus dem ListView gelöscht werden
     */
    private void initList() {
        aimListView = (ListView) findViewById(R.id.aims_list_view);
        aimListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                removeTaskAtPosition(position);
                return false;
            }
        });

    }

    /*
    * erwartet die position des zu löschenden UserAim Elements als int Wert
    * löscht das an der Position zu findende UserAim Element aus der Datenbank
     */

    private void removeTaskAtPosition(int position) {
        if (userAims.get(position) == null) {
        } else {
            MainActivity.dataSource.removeUserAim(userAims.get(position));
            updateList();
        }
    }

    /*
    löscht alle Elemente aus userAims um diese mit den neuen UserAim Elementen zu aktualisieren
     */
    private void updateList() {
        userAims.clear();
        userAims.addAll(MainActivity.dataSource.getAllUserAims());
        adapter.notifyDataSetChanged();
    }
}
