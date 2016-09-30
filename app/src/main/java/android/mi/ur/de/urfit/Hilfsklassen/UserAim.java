package android.mi.ur.de.urfit.Hilfsklassen;

public class UserAim {

    private String titel;
    private String steps;
    private int reached;

    public UserAim(String titel, String steps, Boolean reached) {

        this.titel = titel;
        this.steps = steps + "";
        this.reached = isReached(reached);
    }

    private int isReached(Boolean reached) {
        if (reached) {
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

    public int getReached() {
        return reached;
    }
}
