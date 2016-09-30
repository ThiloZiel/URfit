package android.mi.ur.de.urfit.Hilfsklassen;

/**
 * Created by Thilo on 30.09.16.
 */

public class UserAim {

    private String titel;
    private String steps;
    private String calories;
    private int reached;

    public UserAim(String titel, String steps, String calories, Boolean reached){

        this.titel = titel;
        this.steps = steps+"";
        this.calories = calories+"";
        this.reached = isReached(reached);
    }

    private int isReached(Boolean reached) {
        if (reached){
            return 1;
        } else {
            return 0;
        }

    }

    public String getTitel() {
        return titel;
    }

    public String getSteps() {
        return steps;
    }

    public String getCalories() {
        return calories;
    }

    public int getReached() {
        return reached;
    }
}
