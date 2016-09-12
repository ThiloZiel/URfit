package android.mi.ur.de.urfit;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class URFitListAdapter extends ArrayAdapter<URFitItem>{
    private ArrayList<URFitItem> taskList;
    private Context context;

    public URFitListAdapter(Context context, ArrayList<URFitItem> listItems) {
        super(context, R.layout.listitem, listItems);

        this.context = context;
        this.taskList = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listitem, null);

        }

        URFitItem task = taskList.get(position);

        if (task != null) {
            TextView steps = (TextView) v.findViewById(R.id.item_steps);
            TextView calories = (TextView) v.findViewById(R.id.item_calories);
            TextView date = (TextView) v.findViewById(R.id.item_date);

            steps.setText(task.getSteps());
            steps.setText(task.getCalories());
            date.setText(task.getFormattedDate());
        }

        return v;
    }
}
