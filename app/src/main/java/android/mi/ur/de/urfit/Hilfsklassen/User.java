package android.mi.ur.de.urfit.Hilfsklassen;

/**
 * Created by Thilo on 30.09.16.
 */

public class User {

    private String name;
    private String Gender;
    private String stepLength;
    private String level;

    public User (String _name, Boolean _gender, String _stepLength, String _level){
        name = _name;
        Gender = isMale(_gender);
        stepLength = _stepLength;
        level = _level;

    }

    public String isMale(Boolean _gender) {
        if (_gender){
            return "male";
        } else {
            return "female";
        }

    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return Gender;
    }

    public String getStepLength() {
        return stepLength;
    }

    public String getLevel() {
        return level;
    }

    public void nextLevel(){
        int nextLevel = Integer.parseInt(level);
        nextLevel++;
        level = nextLevel+"";
    }
}
