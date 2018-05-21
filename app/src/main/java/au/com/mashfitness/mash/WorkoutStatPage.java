package au.com.mashfitness.mash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutStatPage extends AppCompatActivity {

    private WorkoutStorage exercises = new WorkoutStorage();
    private String customList = "";
    private String timeOnString = "";
    private String timeOffString = "";
    int cardioCount = 0;
    int enduranceCount = 0;
    int coreCount = 0;
    List<String> finalCutomList = new ArrayList<>();
    int sizeOfList = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutcompletesummary);

        exercises.setUp();
        Bundle extras = getIntent().getExtras();
        customList = extras.getString("customList");
        timeOffString = extras.getString("timeOff");
        timeOnString = extras.getString("timeOn");

        ImageButton saveWorkout = (ImageButton) findViewById(R.id.save);

        for (String item : customList.split(",")) {
            sizeOfList++;
            if(exercises.getDescription(Integer.parseInt(item)).equalsIgnoreCase("core")){
                coreCount++;
            }else if(exercises.getDescription(Integer.parseInt(item)).equalsIgnoreCase("endurance")){
                enduranceCount++;
            }else if(exercises.getDescription(Integer.parseInt(item)).equalsIgnoreCase("cardio")){
                cardioCount++;
            }
        }


        TextView cardio = (TextView) findViewById(R.id.cardioNum);
        cardio.setText(Integer.toString((cardioCount-1)));
        TextView endurance = (TextView) findViewById(R.id.enduranceNum);
        endurance.setText(Integer.toString(enduranceCount));
        TextView core = (TextView) findViewById(R.id.coreNum);
        core.setText(Integer.toString(coreCount));

        TextView sets = (TextView) findViewById(R.id.setNum);
        sets.setText(Integer.toString(sizeOfList));
        TextView timeOn = (TextView) findViewById(R.id.timeOnNum);
        timeOn.setText(timeOnString);
        TextView timeOff = (TextView) findViewById(R.id.TimeOffNum);
        timeOff.setText(timeOffString);




    }
}
