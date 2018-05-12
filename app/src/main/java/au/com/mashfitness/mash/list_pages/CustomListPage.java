package au.com.mashfitness.mash.list_pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;

import au.com.mashfitness.mash.CustomListAdapter;
import au.com.mashfitness.mash.ExerciseMode;
import au.com.mashfitness.mash.HomeActivity;
import au.com.mashfitness.mash.R;
import au.com.mashfitness.mash.WorkoutStorage;

public class CustomListPage extends AppCompatActivity {

    // The position of the "Hydrate" entry in the list
    private static int positionOfFistRealWorkout = 1;

    private ListView listView;
    private WorkoutStorage exercises = new WorkoutStorage();
    private int setsAmount = 0;
    private String sortName = "";
    private String sortDescription = "";
    private String sortImage = "";
    private int selectedExercise; //Value collected after user selects a workout to add to custom workout
    private int itemSelected; //number of cell selected to add a workout too
    // The number of exercises we currently have in the Storage Array
    private  int numberOfExercises = 0;

    List<Integer> finalCutomList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_setup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //For backbutton
        getSupportActionBar().setTitle("Your Workout"); //For activity title

        Button beginButton = (Button) findViewById(R.id.begin_workout_button);

        // Instantiate our list of exercises
        exercises.setUp();

        // Number of exercises = Storage Array - 1 (We do not want the first value!)
        numberOfExercises = exercises.getNumberOfExercises();

        Intent intent = getIntent();
        setsAmount = intent.getIntExtra("sets",5);

//This is for setting up the initial array for list cells
        for(int i = 0;i<setsAmount+1;i++){
            if(i==0){
                this.finalCutomList.add(0);
            }else {
                this.finalCutomList.add(-1);
            }
        }

        try {
            // We must ensure the "Hydrate" entry is number 1 on the list
            if (!exercises.isHydtareEntry1()) {
                throw new Exception("h");
            }
        } catch (Exception e) {
            Intent returnHome = new Intent(this, HomeActivity.class);
            startActivity(returnHome);
        }

        //Getting values from selectWorkoutPage
        Bundle extras = getIntent().getExtras();
        //if statement checking if the Select exercise list was just close, if that is true then this will run
        if(extras.getBoolean("secondSetup")==true) {
            itemSelected = (extras.getInt("prevSelectedItem"))+1;
            selectedExercise = (extras.getInt("selected_exercise")) + 1;
            String customList = String.valueOf(extras.get("customList"));
            finalCutomList.clear();
            for(String item: customList.split(",")){
                finalCutomList.add(Integer.parseInt(item));
            }
            addToFinalList(itemSelected,selectedExercise);
            Log.d("Updated List", String.valueOf(finalCutomList));
        }

        //Button for starting workout
        beginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if(checkIfReady()){
                    //Code for button transition
                    Intent toWorkoutMode = new Intent(CustomListPage.this, ExerciseMode.class);
                    startActivity(toWorkoutMode);
                }else{
                    return;
                }

            }
        });

        //Setting up the cells values
        newListView();

            String[] nameArray = sortName.split(",");
            String[] infoArray = sortDescription.split(",");
            String[] imageArray = sortImage.split(",");

            CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
            listView = (ListView) findViewById(R.id.listViewID);
            listView.setAdapter(whatever);

            checkIfReady();

//When an item on the list view is selected, this method is called
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //Intent myIntent = new Intent(getBaseContext(),   ListPage.class);
                Intent toCustomWorkout = new Intent(CustomListPage.this, SelectExercisePage.class);
                toCustomWorkout.putExtra("position",position);
                toCustomWorkout.putExtra("customList", makeString());
                startActivity(toCustomWorkout);
            }
        });
        //End of onCreate method...
    }


    public void newListView(){
// This whole for loop just looks at the array of selected exercises and puts the correct name, info and image to the correct cell...
        for(int item = positionOfFistRealWorkout;item<setsAmount+1;item++){
            if(finalCutomList.get(item) != -1){
                if (item == positionOfFistRealWorkout) {
                        sortName = exercises.getName(finalCutomList.get(item));
                        sortDescription = exercises.getDescription(finalCutomList.get(item));
                        sortImage = exercises.getImage(finalCutomList.get(item));
                } else {
                        sortName += ","+exercises.getName(finalCutomList.get(item));
                        sortDescription += ","+exercises.getDescription(finalCutomList.get(item));
                        sortImage += ","+exercises.getImage(finalCutomList.get(item));
                    }
            }else {
                if(item == positionOfFistRealWorkout) {
                    sortName = "Empty";
                    sortDescription = "Select Workout";
                    sortImage = exercises.getImage(0);
                }else{
                    sortName += "," + "Empty";
                    sortDescription = sortDescription + "," + "Select Workout";
                    sortImage = sortImage + "," + exercises.getImage(0);
                    }
                }
            }
    }


    //Function for back button
    public boolean onSupportNavigateUp(){
        startActivity(new Intent(CustomListPage.this, HomeActivity.class));
        finish();
        return true;
    }


//This function just replaces the old value in the list with the new selected exercise
    public void addToFinalList(int prevPosition, int newItem){
        for (int i = 0; i<finalCutomList.size();i++) {
            if(i==prevPosition) {
                finalCutomList.remove(prevPosition);
                finalCutomList.add(prevPosition,newItem);
            }
        }
    }

//This function just turns our custom list into a string so we can easily send it through activities.
    public String makeString(){
        String customListString = "";
        for (int i = 0; i<finalCutomList.size();i++){
            if(i == 0){
                customListString = String.valueOf(finalCutomList.get(i));
            }else{
                customListString += ","+String.valueOf(finalCutomList.get(i));
            }
        }
        return customListString;
    }

    //Checks of all the workout cells are filled with a workout
    public boolean checkIfReady() {
        if (finalCutomList.contains(-1)) {
            return false;
        } else {
            return true;
        }
    }
}
