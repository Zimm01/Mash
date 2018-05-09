package au.com.mashfitness.mash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import au.com.mashfitness.mash.list_pages.CustomListPage;
import au.com.mashfitness.mash.list_pages.ListPage;

public class HomeActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Setup Buttons for home screen Activity
        Button mashButton= (Button) findViewById(R.id.mash_button);
        Button customButton= (Button) findViewById(R.id.custom_button);
        final EditText sets = (EditText)findViewById(R.id.input_sets);


        // ONCLICK Event for MASH BUTTON
        customButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                //Intent myIntent = new Intent(getBaseContext(),   ListPage.class);
                Intent toCustomWorkout = new Intent(HomeActivity.this, CustomListPage.class);
                String value= sets.getText().toString();
                int finalValue=Integer.parseInt(value);
                toCustomWorkout.putExtra("sets", finalValue);

                startActivity(toCustomWorkout);

            }
        });

        // ONCLICK Event for CUSTOM WORKOUT BUTTON
        mashButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                //Intent myIntent = new Intent(getBaseContext(),   ListPage.class);
                Intent toCustomWorkout = new Intent(HomeActivity.this, ListPage.class);
                String value= sets.getText().toString();
                int finalValue=Integer.parseInt(value);
                toCustomWorkout.putExtra("sets", finalValue);

                startActivity(toCustomWorkout);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
