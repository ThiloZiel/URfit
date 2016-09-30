package android.mi.ur.de.urfit.Hilfsklassen;


public class Calculator {

    private final static int FAST_JOGGING_KCAL_PER_HOUR = 840;
    private final static int SLOW_JOGGING_KCAL_PER_HOUR = 530;
    private final static double MAX_SLOW_KM_H = 7;
    private double distance;
    private double time;
    private double pause;


    public void setValues(double distance, double time, double pause) {
        this.distance = distance;
        this.time = time;
        this.pause = pause;
    }

    private double calculateSpeed() {
        return (distance) / ((time - pause) / 60);
    }


    public double calculateKcal() {
        double speed = calculateSpeed();
        double kcal;
        if (speed > MAX_SLOW_KM_H) {
            kcal = FAST_JOGGING_KCAL_PER_HOUR * (time / 60);
        } else {
            kcal = SLOW_JOGGING_KCAL_PER_HOUR * (time / 60);
        }
        return kcal;
    }


}
