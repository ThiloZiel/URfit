package android.mi.ur.de.urfit.Activitys;

import android.content.Intent;
import android.mi.ur.de.urfit.Hilfsklassen.URFitItem;
import android.mi.ur.de.urfit.Hilfsklassen.URFitListAdapter;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ergebnis_activity extends AppCompatActivity{

    private ListView OverAll;
    private ArrayList<URFitItem> items = new ArrayList<>();
    private URFitListAdapter adapter;
    private ImageButton addButton;
    private Intent addButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnis_activity);
        initUI();
    }

    private void initUI() {
        initList();
        initListAdapter();
        initTastList();
        initButton();
    }

    private void initButton() {
        addButton = (ImageButton) findViewById(R.id.ergebnisAddButton);
        addButtonIntent = new Intent(ergebnis_activity.this, manuellesTracking_activity.class);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addButtonIntent);
            }
        });
    }

    private void initTastList() {
        updateList();
    }

    private void initListAdapter() {
        adapter = new URFitListAdapter(this, items);
        OverAll.setAdapter(adapter);
    }

    private void initList() {
        OverAll = (ListView) findViewById(R.id.overAllView);
        OverAll.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                removeTaskAtPosition(position);
                return false;
            }
        });
    }

    private void removeTaskAtPosition(int position) {
        if (items.get(position) == null) {
        } else {
            MainActivity.dataSource.removeURFitItem(items.get(position));
            updateList();
        }
    }

    private void updateList() {
        items.clear();
        items.addAll(MainActivity.dataSource.getAllURFitItems());
        adapter.notifyDataSetChanged();
    }
}
