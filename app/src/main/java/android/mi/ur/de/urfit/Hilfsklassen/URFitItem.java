package android.mi.ur.de.urfit.Hilfsklassen;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class URFitItem implements Comparable<URFitItem> {
    // zum rauslöschen
    private String steps;
    private String calories;
    private GregorianCalendar cal;

    public URFitItem(String steps, String calories, int day, int month, int year) {
        this.steps = steps;
        this.calories = calories;
        cal = new GregorianCalendar(year, month, day);
    }

    public String getCalories() {
        return calories;
    }

    public String getSteps() {
        return steps;
    }

    public String getFormattedDate() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);

        return dateFormat.format(cal.getTime());
    }

    private Date getDueDate() {
        return cal.getTime();
    }

    @Override
    public int compareTo(URFitItem another) {
        return getDueDate().compareTo(another.getDueDate());
    }
}
