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

    // The position of the "Hydrate" entry in the list
    private static int positionOfFistRealWorkout = 1;

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
    private ListView listView;
    private WorkoutStorage exercises = new WorkoutStorage();


    private String sortName = "";
    private String sortDescription = "";
    private String sortImage = "";

    // The number of exercises we currently have in the Storage Array
    private  int numberOfExercises = 0;

    // Our "Return Home" call
    private Intent returnHome = new Intent(ListPage.this, HomeActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_setup);

        // Instantiate our list of exercises
        exercises.setUp();

        // Number of exercises = Storage Array - 1 (We do not want the first value!)
        numberOfExercises = exercises.getNumberOfExercises();

        try {


            // We must ensure the "Hydrate" entry is number 1 on the list
            if (! exercises.isHydtareEntry1()) {
                throw new Exception("h");
            }
        }
        catch (Exception e){

            Log.e("ListPage","You Must Set the 'Hydrate' item to the first Postion on the Workout Storage List!!");
            startActivity(returnHome);
        }

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


        for(int i = positionOfFistRealWorkout; i < setsAmount + 1;i++) {
            if(i== positionOfFistRealWorkout) {
                sortName = exercises.getName(i);
                sortDescription = exercises.getDescription(i);
            }else{
                sortName += "," + exercises.getName(i);
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
