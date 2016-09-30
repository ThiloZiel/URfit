package android.mi.ur.de.urfit.Hilfsklassen;

/**
 * Created by Thilo on 30.09.16.
 */

public class UserAim {

    private String titel;
    private double steps;
    private double calories;
    private Boolean reached;

    public UserAim(String titel, double steps, double calories, Boolean reached){

        this.titel = titel;
        this.steps = steps;
        this.calories = calories;
        this.reached = reached;
    }

    public String getTitel() {
        return titel;
    }

    public double getSteps() {
        return steps;
    }

    public double getCalories() {
        return calories;
    }

    public Boolean getReached() {
        return reached;
    }
}
