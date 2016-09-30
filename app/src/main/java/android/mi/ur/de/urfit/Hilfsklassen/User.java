package android.mi.ur.de.urfit.Hilfsklassen;

/**
 * Created by Thilo on 30.09.16.
 */

public class User {

    private String name;
    private String Gender;
    private String stepLength;

    public User (String _name, Boolean _gender, String _stepLength){
        name = _name;
        Gender = isMale(_gender);
        stepLength = _stepLength;

    }

    private String isMale(Boolean _gender) {
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
}
