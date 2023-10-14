package csevent;
import java.util.Calendar;
/**
 * A Date object class that implements the Comparable Interface
 * for the Event calendar to use.
 *
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since September 18, 2023
 */
public class Date implements Comparable<Date>{
    /**
     * A field describing the year of the date
     */
    private int year;

    /**
     * A field describing the day of the date
     */
    private int day;

    /**
     * A field describing the month of the date
     */
    private int month;

    /**
     * A constant defined to be the first month of the year
     */
    private final int JANUARY = 1;

    /**
     * A constant defined to be the second month of the year
     */
    private final int FEBRUARY = 2;

    /**
     * A constant defined to be the third month of the year
     */
    private final int MARCH = 3;

    /**
     * A constant defined to be the fourth month of the year
     */
    private final int APRIL = 4;

    /**
     * A constant defined to be the fifth month of the year
     */
    private final int MAY = 5;

    /**
     * A constant defined to be the sixth month of the year
     */
    private final int JUNE = 6;

    /**
     * A constant defined to be the seventh month of the year
     */
    private final int JULY = 7;

    /**
     * A constant defined to be the eighth month of the year
     */
    private final int AUGUST = 8;

    /**
     * A constant defined to be the ninth month of the year
     */
    private final int SEPTEMBER = 9;

    /**
     * A constant defined to be the tenth month of the year
     */
    private final int OCTOBER = 10;

    /**
     * A constant defined to be the eleventh month of the year
     */
    private final int NOVEMBER = 11;

    /**
     * A constant defined to be the last month of the year
     */
    private final int DECEMBER = 12;

    /**
     * A constant defined to be this date's month
     */
    final int TODAY_MONTH;

    /**
     * A constant defined to be this date's day
     */
    final int TODAY_DAY;

    /**
     * A constant defined to be this date's year
     */
    final int TODAY_YEAR;

    /**
     * Constructor for initializing a Date object.
     * @param  month the month to be assigned to this date.
     * @param  day the day to be assigned to this date.
     * @param  year the year to be assigned to this date.
     */
    public Date(int month, int day, int year) {
        this.year = year;
        this.day = day;
        this.month = month;
        Calendar c = Calendar.getInstance();
        TODAY_MONTH = c.get(Calendar.MONTH) + 1; //Calendar's months are 0-based
        TODAY_DAY = c.get(Calendar.DAY_OF_MONTH);
        TODAY_YEAR = c.get(Calendar.YEAR);
    }

    /**
     * Creates a new date that represents today.
     * @return today's date
     */
    public Date today(){return new Date(TODAY_MONTH, TODAY_DAY, TODAY_YEAR);}

    /**
     * Compares this date to another given date.
     * @param  d the date to be compared to
     * @return  0 if this date is equal to the given,
     * 1 if this date is "greater" than the given,
     * -1 otherwise.
     */
    public int compareTo(Date d){
        final int EQUAL = 0, GREATER = 1, LESS = -1;
        if (equals(d))
            return EQUAL;
        else{
            if (year < d.year)
                return LESS;
            else if (year > d.year)
                return GREATER;
            else{
                if (month < d.month)
                    return LESS;
                else if (month > d.month)
                    return GREATER;
                else
                    return Integer.compare(day, d.day);
            }
        }
    }

    /**
     * A static comparing method that works the same as compareTo
     * @param d1 the first date
     * @param d2 the second date
     * @return 0 if the dates are equal, a positive integer if d1 is
     * "greater" than d2, a negative integer otherwise
     */
    public static int compare(Date d1, Date d2){return d1.compareTo(d2);}

    /**
     * Compares 2 dates and determines if they are equal
     * @param o the supposed date to be checked
     * @return true if the dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Date))
            return false;
        Date d = (Date) o;
        return year == d.year && month == d.month && day == d.day;
    }

    /**
     * Checks if this date is valid.
     * @return  true if this date is valid, false otherwise
     */
    public boolean isValid(){
        final int MAX_DAYS = 31, SEMI_MAX = 30, LEAP_YEAR = 29, MIN = 0;
        switch (month){
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return day <= SEMI_MAX && day > MIN;
            case FEBRUARY:
                return day == LEAP_YEAR ? isLeapYear() : (day <= LEAP_YEAR - 1 && day > MIN);
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return day <= MAX_DAYS && day > MIN;
            default:
                return false;
        }
    }

    /**
     * Checks if the year of this date is a leap year
     * @return true if the year is a leap year, false otherwise
     */
    private boolean isLeapYear(){
        final int QUADRENNIAL = 4, CENTENNIAL = 100, QUATERCENTENNIAL = 400;
        final int EQUAL = 0;
        if (year % QUADRENNIAL != EQUAL)
            return false;
        else{
            if (year % CENTENNIAL != EQUAL)
                return true;
            else
                return year % QUATERCENTENNIAL == EQUAL;
        }
    }

    /**
     * Checks if a date is in the future
     * @return true if the registered date is within the foreseeable future,
     * false otherwise
     */
    public boolean isInTheFuture(){
        if (!isValid())
            return false;
        if (year == TODAY_YEAR){
            if (month < TODAY_MONTH)
                return false;
            else if (month > TODAY_MONTH)
                return true;
            else
                return day > TODAY_DAY;
        }else if (year < TODAY_YEAR)
            return false;
        else
            return true;
    }

    /**
     * Checks if this date is within 6 months of today
     * @return true if the date is within 6 months of today, false otherwise
     */
    public boolean isWithinSixMonthsOfToday(){
        if (!isInTheFuture())
            return false;
        int isWithin6Months = 6;
        Calendar future = Calendar.getInstance(), today = Calendar.getInstance();
        future.set(year, month, day);
        today.add(Calendar.MONTH, isWithin6Months);
        return future.before(today) || future.equals(today);
    }

    /**
     * Accessor method for this class' private field year.
     * @return  this date's year
     */
    public int getYear(){return this.year;}

    /**
     * Accessor method for this class' private field month.
     * @return  this date's month
     */
    public int getMonth(){return this.month;}

    /**
     * Accessor method for this class' private field day.
     * @return  this date's day
     */
    public int getDay(){return this.day;}

    /**
     * Mutator method for this class' private field year.
     * @param  year the year to set this date to
     */
    public void setYear(int year){this.year = year;}

    /**
     * Mutator method for this class' private field month.
     * @param  month the month to set this date to
     */
    public void setMonth(int month){this.month = month;}

    /**
     * Mutator method for this class' private field day.
     * @param  day the day to set this date to
     */
    public void setDay(int day){this.day = day;}

    /**
     * Creates a String representation of this date.
     * @return a String representation of this date, in the form MM/DD/YYYY
     */
    @Override
    public String toString(){return String.format("%d/%d/%d", month, day, year);}

    /**
     * A testbed main
     * @param args the command line arguments
     */
    public static void main(String[] args){
        testValidity();
        testIsInFuture();
        testIsWithin6MonthsFromNow();
    }

    /**
     * A helper method made for testing the date's validity
     */
    private static void testValidity(){
        testLeapYear();
        testNotLeapYear();
    }

    /**
     * A helper method made for testing whether a date is a leap year
     */
    private static void testLeapYear(){
        int year1 = 2008, year2 = 2009, m = 2, a = 29;
        System.out.println("Testing to see whether a given date " +
                "is valid if the year is a leap year");
        Date d = new Date(m, a, year1);
        System.out.println("Testing " + d);
        System.out.println(d.isValid() ? d + " is a valid date."
                : d + " is NOT a valid date.");
        d = new Date(m, a, year2);
        System.out.println("Testing " + d);
        System.out.println(d.isValid() ? d + " is a valid date."
                : d + " is NOT a valid date.");
    }

    /**
     * A helper method made for testing whether a date is NOT a leap year
     */
    private static void testNotLeapYear(){
        int year = 2014, month1 = 4, month2 = 10, day1 = 30, day2 = 31;
        Date d = new Date(month1, day1, year);
        System.out.println("Testing to see whether a given date " +
                "is valid if the year is NOT a leap year");
        System.out.println("Testing " + d);
        System.out.println(d.isValid() ? d + " is a valid date."
                : d + " is NOT a valid date.");
        d.setDay(day2);
        System.out.println("Testing " + d);
        System.out.println(d.isValid() ? d + " is a valid date."
                : d + " is NOT a valid date.");
        d = new Date(month2, day1, year);
        System.out.println("Testing " + d);
        System.out.println(d.isValid() ? d + " is a valid date."
                : d + " is NOT a valid date.");
        d.setDay(day2);
        System.out.println("Testing " + d);
        System.out.println(d.isValid() ? d + " is a valid date."
                : d + " is NOT a valid date.");
    }

    /**
     * A helper method designed for testing if this date is in the future
     */
    private static void testIsInFuture(){
        int year1 = 2005, year2 = 2025, month1 = 1, month2 = 10, day1 = 15, day2 = 30;
        Date d = new Date(month1, day1, year1);
        System.out.println("Testing whether a given date is in the future");
        System.out.println("Testing " + d);
        System.out.println(d.isInTheFuture() ? d + " is in the future."
                : d + " is NOT in the future.");
        d.setYear(year2);
        System.out.println("Testing " + d);
        System.out.println(d.isInTheFuture() ? d + " is in the future."
                : d + " is NOT in the future.");
        d = new Date(month2, day2, year2);
        System.out.println("Testing " + d);
        System.out.println(d.isInTheFuture() ? d + " is in the future."
                : d + " is NOT in the future.");
    }

    /**
     * A helper method to see if a future date is within 6 months from now
     */
    private static void testIsWithin6MonthsFromNow(){
        int sampleYear = 2024, sampleDay = 15, month1 = 2, month2 = 7;
        Date d = new Date(month1, sampleDay, sampleYear);
        System.out.println("Testing whether a given future date is within 6 months of today");
        System.out.println("Testing " + d);
        System.out.println(d.isWithinSixMonthsOfToday() ? d + " is within 6 months of today."
                : d + " is NOT within 6 months of today.");
        d.setMonth(month2);
        System.out.println("Testing " + d);
        System.out.println(d.isWithinSixMonthsOfToday() ? d + " is within 6 months of today."
                : d + " is NOT within 6 months of today.");
    }
}
