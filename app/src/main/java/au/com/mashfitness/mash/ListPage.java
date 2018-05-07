package au.com.mashfitness.mash;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ListPage extends AppCompatActivity {



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
    WorkoutStorage exercises = new WorkoutStorage();


    String sortName = "";
    String sortDescription = "";
    String sortImage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_setup);

        Button beginButton = (Button) findViewById(R.id.begin_workout_button);

        //Getting user value from home screen for 'sets'
        Intent intent = getIntent();
        Integer setsAmount = intent.getIntExtra("sets",5);

        //Button for starting workout
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
        //Enter transition code for Begin workout button

            }
        });


        exercises.setUp();

        for(int i = 0;i<setsAmount;i++) {
            if(i==0) {
                sortName = exercises.getName(i);
                sortDescription = exercises.getDescription(i);
            }else{
                sortName = sortName+","+exercises.getName(i);
                sortDescription = sortDescription+","+exercises.getDescription(i);
            }
        }

        String[] nameArray = sortName.split(",");
        String[] infoArray = sortDescription.split(",");
        Log.d("Exercise Name", sortName);

        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.listViewID);
        listView.setAdapter(whatever);



    }
}
