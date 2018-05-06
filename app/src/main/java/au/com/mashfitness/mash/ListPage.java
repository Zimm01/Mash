package au.com.mashfitness.mash;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ListPage extends AppCompatActivity {

    String[] nameArray = {"Burpees","Butterfly situps","Lunge Walk","Plank Walk","Plyometric lunges","Pulse Squats",
            "Pushup Jacks","Pushup Twist","Russain twist","Star Jumps"};

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
            "Endurance"
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
            R.drawable.ic_launcher_background
    };

    private boolean isReady = false;
    ListView listView;

    Button beginButton = (Button) findViewById(R.id.begin_workout_button);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_setup);


        beginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Enter code to transition to workout page

                
            }
        });



        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.listViewID);
        listView.setAdapter(whatever);


    }
}
