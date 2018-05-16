package au.com.mashfitness.mash;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;



public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "workouts.db";
    private static final String TABLE_WORKOUTS =  "workouts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "workoutName";
    private static final String COLUMN_TIME_ON = "timeOn";
    private static final String COLUMN_TIME_OFF = "timeOff";
    private static final String COLUMN_EXERCISE_LIST = "exerciseList";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT %s TEXT %s TEXT %s TEXT %s TEXT );",
                TABLE_WORKOUTS,
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_TIME_ON,
                COLUMN_TIME_OFF,
                COLUMN_EXERCISE_LIST);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);
        onCreate(db);
    }

    public void addWorkout(WorkoutsObject workout){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, workout.get_name());
        values.put(COLUMN_TIME_ON, workout.get_timeOn());
        values.put(COLUMN_TIME_OFF, workout.get_timeOff());
        values.put(COLUMN_EXERCISE_LIST, workout.get_exercisesList());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WORKOUTS, null, values);
        db.close();
    }

    public void deleteWorkout(String workoutName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORKOUTS + " WHERE " + COLUMN_NAME + "=\"" + workoutName + "\";");
    }

    public String dataBaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = (String.format("SELECT * FROM %sWHERE 1", TABLE_WORKOUTS));

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("workoutName"))!= null){
                dbString += c.getString(c.getColumnIndex("workoutName"));
                dbString +="\n";
            }

        }
        db.close();
        return dbString;
    }
}
