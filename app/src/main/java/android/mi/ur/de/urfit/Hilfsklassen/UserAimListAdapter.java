package android.mi.ur.de.urfit.Hilfsklassen;

import android.content.Context;
import android.mi.ur.de.urfit.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class UserAimListAdapter extends ArrayAdapter<UserAim> {

    private ArrayList<UserAim> userAimsList;
    private Context context;

    public UserAimListAdapter(Context context, ArrayList<UserAim> listItems) {
        super(context, R.layout.user_aim_list_item, listItems);

        this.context = context;
        this.userAimsList = listItems;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.user_aim_list_item, null);

        }

        UserAim item = userAimsList.get(position);

        if (item != null) {
            TextView steps = (TextView) v.findViewById(R.id.item_steps);
            TextView titel = (TextView) v.findViewById(R.id.item_Titel);

            steps.setText(item.getSteps());
            titel.setText(item.getTitel());
        }

        return v;
    }
}
