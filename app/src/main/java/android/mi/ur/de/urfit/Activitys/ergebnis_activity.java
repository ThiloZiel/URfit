package android.mi.ur.de.urfit.Activitys;

import android.mi.ur.de.urfit.Hilfsklassen.URFitItem;
import android.mi.ur.de.urfit.Hilfsklassen.URFitListAdapter;
import android.mi.ur.de.urfit.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ergebnis_activity extends AppCompatActivity implements View.OnClickListener {

    ListView OverAll;
    ArrayList<URFitItem> items = new ArrayList<>();
    URFitListAdapter adapter;

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

    @Override
    public void onClick(View v) {

    }

    private void removeTaskAtPosition(int position) {
        if (items.get(position) == null) {
        } else {
            MainActivity.dataSource.removeItem(items.get(position));
            updateList();
        }
    }

    private void updateList() {
        items.clear();
        items.addAll(MainActivity.dataSource.getAllURFitItems());
        adapter.notifyDataSetChanged();
    }
}
