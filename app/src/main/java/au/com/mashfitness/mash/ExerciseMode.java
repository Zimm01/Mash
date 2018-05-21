package au.com.mashfitness.mash;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.ProgressBar;

public class ExerciseMode extends AppCompatActivity {

    Thread newThread;
    private boolean timerIsOn = false;
    private boolean updateExercise = false;
    private boolean updateTimer = false;
    private boolean isCancelled = false;
    static int interval;
    static Timer timer;
    static Timer progressTimer;
    private String timeOnString;
    private String timeOffString;
    private int counter = 1;


    private ProgressBar progress;


    ImageView exerciseImage;
    TextView workoutName;


    private WorkoutStorage exercises = new WorkoutStorage();

    private String customList = "";

    List<Integer> finalCutomList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_mode);
        Bundle extras = getIntent().getExtras();
        customList = extras.getString("customList");
        exercises.setUp();
        timeOffString = extras.getString("timeOff");
        timeOnString = extras.getString("timeOn");



        TextView setsText = (TextView) findViewById(R.id.infoTextviewID);
        TextView timeOnText = (TextView) findViewById(R.id.textView);
        TextView timeOffText = (TextView) findViewById(R.id.textView4);
        exerciseImage = (ImageView) findViewById(R.id.exercise_image);
        workoutName = (TextView) findViewById(R.id.textView5);
        progress = findViewById(R.id.progressBar2);


        //turns time on string to an int
        String[] timeOnValues = timeOnString.split(":");
        int min = Integer.parseInt(timeOnValues[0]);
        int sec = Integer.parseInt(timeOnValues[1]);
        int timeOn = (min * 60) + sec;

        //turns time off string into an int
        String[] timeOffValue = timeOffString.split(":");
        int sencondMin = Integer.parseInt(timeOffValue[0]);
        int secondSec = Integer.parseInt(timeOffValue[1]);
        int timeOff = (sencondMin * 60) + secondSec;


        for (String item : customList.split(",")) {
            finalCutomList.add(Integer.parseInt(item));
        }
        setsText.setText("Sets: "+ Integer.toString(finalCutomList.size()-1));
        setsText.setTextColor(Color.WHITE);
        timeOnText.setText("Time On: "+timeOnString);
        timeOnText.setTextColor(Color.WHITE);
        timeOffText.setText("Time Off: "+timeOffString);
        timeOffText.setTextColor(Color.WHITE);

        ImageButton cancelbutton = (ImageButton) findViewById(R.id.imageButton2);

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCancelled = true;
                timer.cancel();
                progressTimer.cancel();
                finish();

            }
        });


        //prepares initial setup
        updateExercise(timeOn, timeOff);
        changeTime(timeOn, timeOff);


    }


    //Custom timer, this takes in a time (period) and itll run 'run()' after the 'period' has ended
    private void newTimer(final int timeOn, final int timeOff, int period) {
        int delay = 1000;
        //final int period = 1000 * timeOn;
        timer = new Timer();
        interval = 1;
        timer.schedule(new TimerTask() {
            public void run() {
                if (isCancelled == false) {
                    newThread = new Thread(new ThreadingClass());
                    //starts the workout
                    newThread.run();
                    changeTime(timeOn, timeOff);
                    Log.d("Running new timer", "now");
                    progressTimer.cancel();
                    updateExercise(timeOn, timeOff);
                }
            }
        }, period, delay);

    }

    //this function prepares the custom timer and sends it a time to run for.
    private void changeTime(int timeOn, int timeOff) {

        if(isCancelled == false) {
            if (timerIsOn == true) {
                timer.cancel();
            }

            if (updateTimer == false) {
                newTimer(timeOn, timeOff, timeOn * 1000);
                updateTimer = true;
            } else {
                newTimer(timeOn, timeOff, timeOff * 1000);
                updateTimer = false;
            }
            timerIsOn = true;
        }
    }




    //this is for changing data on the page after each time interval
    public void updateExercise(int timeOn, int timeOff){

        if(counter == finalCutomList.size()){
            //Code for page transition to show work out stats
            Log.d("End of workout", "yes");
            timer.cancel();
            toStatPage();

        }else {
            if (updateExercise == false) {
                exerciseImage.setImageResource(Integer.parseInt(exercises.getImage(finalCutomList.get(counter))));
                workoutName.setText(exercises.getName(finalCutomList.get(counter)));
                startProgressbar(timeOn,timeOff,true);
                counter += 1;
                updateExercise = true;
            } else {
                exerciseImage.setImageResource(Integer.parseInt(exercises.getImage(finalCutomList.get(0))));
                workoutName.setText(exercises.getName(finalCutomList.get(0)));
                startProgressbar(timeOn,timeOff,false);
                updateExercise = false;
            }
        }
    }


    public void toStatPage() {
        Intent goToActivityBIntent = new Intent(ExerciseMode.this, WorkoutStatPage.class);
        startActivity(goToActivityBIntent);

    }


    public void startProgressbar(final int timeOn, final int timeOff, boolean isTimeOn){


        if(isTimeOn == true){
            progress.setMax(timeOn-1);
        }else{
            progress.setMax(timeOff-1);
        }
        int period = 1000;
        int delay = 0;
        //final int period = 1000 * timeOn;
        progressTimer = new Timer();
        interval = 0;
        progressTimer.schedule(new TimerTask() {
            public void run() {

              progress.setProgress(interval);
              interval++;


            }
        }, delay, period);




    }




}
