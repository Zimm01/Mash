package au.com.mashfitness.mash;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutStorage {

    String name, description, image;
    private int j = 0;
    private String[] nameList = new String[]{"Burpees","Butterfly Situps","Lunge Walk","Plank Walk","Plyometric Lunges","Pulse Squats",
            "Pushup Jacks","Pushup Twist","Russain Twist","Star Jumps"};


    private String[] descriptionString = new String[]{"Endurance", "Core", "Endurance", "Core", "Cardio", "Endurance", "Endurance",
            "Endurance", "Core", "Endurance"};


    private String[] imageString = new String[]{"uirbviurbv", "uwiebiru", "irbviurbv","uirbviurbv", "uwiebiru", "irbviurbv",
            "uirbviurbv", "uwiebiru", "irbviurbv","jrv birjv"};


    private List<Map<String, String>> myMap = new ArrayList<Map<String, String>>();

    private List<String> exercise = new ArrayList<String>();

    public void setUp() {


        for (int i = 0; i < 10; i++) {
            HashMap<String, String> mMap = new HashMap<String, String>();
            mMap.put("name", nameList[i]);
            mMap.put("description", descriptionString[i]);
            mMap.put("image", imageString[i]);
            myMap.add(mMap);


        }
        Log.d("My Map Array", String.valueOf(myMap));
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
