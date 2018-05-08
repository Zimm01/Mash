package au.com.mashfitness.mash;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.*;

import java.util.*;

import static au.com.mashfitness.mash.R.layout.list_view_setup;
import static java.lang.String.format;
import static java.lang.System.*;

public class CustomListAdapter extends ArrayAdapter {


    private final ListPage context;

    private final String[] nameArray;

    private final String[] infoArray;



    private final String[] tempImgArray;



    public CustomListAdapter(ListPage context, String[] nameArrayParam, String[] infoArrayParam, String[] tempImgArray){

        super(context,R.layout.list_view_setup,nameArrayParam);
        this.context = context;
        this.tempImgArray = tempImgArray;
        this.nameArray = nameArrayParam;
        this.infoArray = infoArrayParam;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell_setup, null, true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.textViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextviewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1ID);

        nameTextField.setText(nameArray[position]);
        infoTextField.setText(infoArray[position]);
        Log.e("ListPage", String.valueOf(tempImgArray));

        //Turning the string value to Int for getting exercise image
        int img = Integer.parseInt(String.valueOf(tempImgArray[position]));
        imageView.setImageResource(img);
        return rowView;
    };


}
