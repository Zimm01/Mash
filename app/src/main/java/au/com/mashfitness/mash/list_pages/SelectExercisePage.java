package au.com.mashfitness.mash.list_pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.Random;

import au.com.mashfitness.mash.CustomListAdapter;
import au.com.mashfitness.mash.HomeActivity;
import au.com.mashfitness.mash.R;
import au.com.mashfitness.mash.WorkoutStorage;

public class SelectExercisePage extends AppCompatActivity {

    // The position of the "Hydrate" entry in the list
    private static int positionOfFistRealWorkout = 1;

    private ListView listView;
    private WorkoutStorage exercises = new WorkoutStorage();

    private String sortName = "";
    private String sortDescription = "";
    private String sortImage = "";
    private int prevPagePosition;

    // The number of exercises we currently have in the Storage Array
    private  int numberOfExercises = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_setup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select a Workout");

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

            Intent returnHome = new Intent(this, HomeActivity.class);

            Log.e("ListPage","You Must Set the 'Hydrate' item to the first Postion on the Workout Storage List!!");
            startActivity(returnHome);
        }

        Button beginButton = (Button) findViewById(R.id.begin_workout_button);


        //Getting user value from home screen for 'sets'
        Bundle extras = getIntent().getExtras();
        this.prevPagePosition = extras.getInt("position");


        //Button for starting workout
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //Enter transition code for Begin workout button

            }
        });


        // The setsAmount +1 may cause an exception, requires more testing!
        for(int i = positionOfFistRealWorkout; i < (numberOfExercises-1);i++) {
            //-1 because nameList.length has the hydrate in it

            if(i== positionOfFistRealWorkout) {
                sortName = exercises.getName(i);
                sortDescription = exercises.getDescription(i);
                sortImage = exercises.getImage(i);
            }else{
                sortName += "," + exercises.getName(i);
                sortDescription = sortDescription+","+exercises.getDescription(i);
                sortImage = sortImage+","+exercises.getImage(i);
            }
        }

        String[] nameArray = sortName.split(",");
        String[] infoArray = sortDescription.split(",");
        String[] imageArray = sortImage.split(",");


        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.listViewID);
        listView.setAdapter(whatever);
        returnSelectedItem();

    }

    public void returnSelectedItem(){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                Intent goToActivityBIntent = new Intent(SelectExercisePage.this, CustomListPage.class);
                goToActivityBIntent.putExtra("selected_exercise", position);
                goToActivityBIntent.putExtra("prevSelectedItem", prevPagePosition);
                goToActivityBIntent.putExtra("secondSetup", true);
                Log.d("SELECPREVPAGEPOSSY", Integer.toString(prevPagePosition));
                startActivity(goToActivityBIntent);
                onSupportNavigateUp();
            }
        });

    }
//function for back button
    public boolean onSupportNavigateUp(){
        this.finish();
        return true;
    }


}
