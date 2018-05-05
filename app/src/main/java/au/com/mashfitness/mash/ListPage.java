package au.com.mashfitness.mash;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ListPage extends AppCompatActivity {

    String[] nameArray = {"Burpees","Butterfly situps","Lunge Walk","Plank Walk","Plyometric lunges","Pulse Squats",
            "Pushup Jacks","Pushup Twist","Russain twist","10 Star Jumps/2 Burpees"};



    String[] infoArray = {
            "Endurance",
            "Core",
            "Endurance",
            "Core",
            "Cardio",
            "Endurance",
            "Endurance",
            "Endurance",
            "Core",
            "Cardio,Endurance"
    };

    Integer[] imageArray = {R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    };

    View backgroundView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_setup);
        backgroundView = (View) findViewById(R.id.view2);
        backgroundView.setBackgroundColor(Color.parseColor("#ee6565"));
        listView = (ListView) findViewById(R.id.listViewID);
        listView.setBackgroundColor(Color.GRAY);



        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.listViewID);
        listView.setAdapter(whatever);
    }
}