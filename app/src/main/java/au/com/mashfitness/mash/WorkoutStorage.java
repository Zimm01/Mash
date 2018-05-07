package au.com.mashfitness.mash;

import android.media.Image;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutStorage {

    // Position and comparable name of our Hydrate Image in the array
    private static int positionOfHydrateEntry = 0;
    private static String nameOfHydrateEntry = "Hydrate";

    String name, description, image;
    private int j = 0;

    // Number of Exercises used in total
    private  int numberOfExercises = 0;

    // Map list Setup
    private List<Map<String, String>> myMap = new ArrayList<Map<String, String>>();
    private List<String> exercise = new ArrayList<String>();

    private String[] nameList = new String[]{
            nameOfHydrateEntry,
            "Burpee",
            "Plyometric Lunge",
            "Plank Hold",
            "Push Up Jacks",
            "Push Up Twists",
            "Russian Twists",
            "Strict Sit Ups",
            "Squats",
            "Plank Rollers"};


    private String[] descriptionString = new String[]{
            "Not An Exercise",
            "Cardio",
            "Cardio",
            "Core",
            "Endurance",
            "Endurance",
            "Core",
            "Core",
            "Endurance",
            "Core"};

    // THIS ARRAY STORES THE REFERENCE INTEGER TO THE IMAGE IN DRAWABLE
    // IT WILL NEED TO BE CONVERTED BACK TO AN INT TO BE USED
    private String[] imageString = new String[]{
            Integer.toString(R.drawable.app_ex_static_hydrate),
            Integer.toString(R.drawable.app_ex_static_burpee),
            Integer.toString(R.drawable.app_ex_static_lunge),
            Integer.toString(R.drawable.app_ex_static_plank),
            Integer.toString(R.drawable.app_ex_static_pushup),
            Integer.toString(R.drawable.app_ex_static_pushuptwist),
            Integer.toString(R.drawable.app_ex_static_russian),
            Integer.toString(R.drawable.app_ex_static_situp),
            Integer.toString(R.drawable.app_ex_static_squat),
            Integer.toString(R.drawable.app_logo)};

    public void setUp() {

        numberOfExercises = nameList.length;

        for (int i = 0; i < numberOfExercises; i++) {
            HashMap<String, String> mMap = new HashMap<String, String>();
            mMap.put("name", nameList[i]);
            mMap.put("description", descriptionString[i]);
            mMap.put("image", imageString[i]);
            myMap.add(mMap);


        }
        Log.d("My Map Array", String.valueOf(myMap));
    }

    // Function to ensure the first name in the list is "Hydrate"
    public boolean isHydtareEntry1()
    {
        String fistEntryName = getName(0);

        if(nameOfHydrateEntry.equals(fistEntryName))
        {
            return true;
        }

        return false;
    }

    public  int getNumberOfExercises()
    {
        return numberOfExercises;
    }

    public String getName(int exerciseType) {
        for (Map<String, String> map : myMap) {
            if (j == exerciseType) {
                this.name = map.get("name");
                exercise.add(this.name);
                j = 0;
                return this.name;
            } else {
                j++;
            }
    }
        j = 0;
        return "Select Exercise";
    }

    public String getDescription(int exerciseType) {
        for (Map<String, String> map : myMap) {
            if (j == exerciseType) {
                this.description = map.get("description");
                exercise.add(this.description);
                j = 0;
                return this.description;
            } else {
                j++;
            }
        }
        j = 0;
        return "Select Exercise";
    }

    public String getImage(int exerciseType) {
        for (Map<String, String> map : myMap) {
            if (j == exerciseType) {
                this.image = map.get("image");
                exercise.add(this.image);
                j = 0;
                return this.image;
            } else {
                j++;
            }
        }
        j = 0;
        return "Select Exercise";
    }




}
