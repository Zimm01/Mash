package au.com.mashfitness.mash;

public class WorkoutsObject {


    private int _id;
    private String _WorkoutName;
    private String _timeOn;
    private String _timeOff;
    private String _exercisesList;

    public WorkoutsObject(){

    }

    public WorkoutsObject(String name, String timeOn, String timeOff, String exercisesList) {
        this._WorkoutName = name;
        this._timeOn = timeOn;
        this._timeOff = timeOff;
        this._exercisesList = exercisesList;
    }



    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._WorkoutName = _name;
    }

    public void set_timeOn(String _timeOn) {
        this._timeOn = _timeOn;
    }

    public void set_timeOff(String _timeOff) {
        this._timeOff = _timeOff;
    }

    public void set_exercisesList(String _exercisesList) {
        this._exercisesList = _exercisesList;
    }




    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _WorkoutName;
    }

    public String get_timeOn() {
        return _timeOn;
    }

    public String get_timeOff() {
        return _timeOff;
    }

    public String get_exercisesList() {
        return _exercisesList;
    }
}
