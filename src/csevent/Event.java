package csevent;
import java.util.StringTokenizer;

/**
 * An Event object class that implements the Comparable Interface
 * for the Event calendar to use.
 *
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since September 18, 2023
 */
public class Event implements Comparable<Event>{

    /**
     * The duration of the event
     */
    private int duration;

    /**
     * The date the event is set to take place
     */
    private Date date;

    /**
     * The starting time of the event
     */
    private Timeslot startTime;

    /**
     * The location of the event
     */
    private Location location;

    /**
     * The contact who agreed to let the event take place
     */
    private Contact contact;

    /**
     * Parametrized constructor for initializing an Event object
     * @param  date the date for which the event should take place
     * @param  startTime the timeslot for which the event should take place
     * @param  location the location where the event should take place
     * @param  contact the contact who approves of your decision
     * @param  duration how long the event is taking place
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact,
                 int duration){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * A default constructor made with no parameters, set to 30 minutes
     */
    public Event(){
        this(null, null, null, null, 30);
        //This event has been set to 30 minutes, the minimum duration required
        //for scheduling events
    }

    /**
     * Compares another event to this one.
     * Starts by comparing their dates. If their dates are equal, then
     * compares their respective timeslots.
     * @param  e the event to be compared to this one.
     * @return  returns 0 if this event is equal to the given,
     * a positive integer if this event is "greater" than the given,
     * or a negative integer otherwise.
     */
    @Override
    public int compareTo(Event e){
        if (this.date.equals(e.date))
            return this.startTime.compareTo(e.startTime);
        return this.date.compareTo(e.date);
    }

    /**
     * A static comparing method that works the same as compareTo
     * @param e1 the first event
     * @param e2 the second event
     * @return 0 if the events are equal, a positive integer if e1 is
     * "greater" than e2, a negative integer otherwise
     */
    public static int compare(Event e1, Event e2){return e1.compareTo(e2);}

    /**
     * Returns a String representation of this event.
     * @return  a String representation of this event.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        sb.append("Event Date: " + date.toString());
        String ending = "";
        switch (startTime){
            case MORNING:
                sb.append("] [Start: 10:30am] [");
                ending = newTime("10:30am", duration);
                break;
            case AFTERNOON:
                sb.append("] [Start: 02:30pm] [");
                ending = newTime("2:30pm", duration);
                break;
            case EVENING:
                sb.append("] [Start: 06:30pm] [");
                ending = newTime("6:30pm", duration);
                break;
        }
        sb.append(ending + "] ");
        sb.append(locationString() + contact.toString());
        return sb.toString();
    }

    /**
     * Creates a String based off the location for this event to make event string
     * representation
     * @return a String based off the location for this event
     */
    private String locationString(){
        switch (location){
            case ACADEMIC_BUILDING:
                return " @AB2225 (Academic Building, College Avenue) ";
            case HILL_CENTER:
                return " @HLL114 (Hill Center, Busch) ";
            case ALLISON_ROAD_CLASSROOM:
                return " @ARC103 (Allison Road Classroom, Busch) ";
            case MURRAY_HALL:
                return " @MU302 (Murray Hall, College Avenue) ";
            case BECK_HALL:
                return " @BE_AUD (Beck Hall, Livingston) ";
            default:
                return " @TIL232 (Tillett Hall, Livingston) ";
        }
    }

    /**
     * Calculates the new time given the start time and the number
     * of minutes after it
     * @param time the starting time in hh:mmam/hh:mmpm format
     * @param duration the minutes after the starting time
     * @return the new time that is the given minutes after the start,
     * also written in hh:mmam/hh:mmpm format
     */
    private String newTime(String time, int duration){
        final int minutesInHour = 60, hoursInDay = 12;
        StringTokenizer st = new StringTokenizer(time, ":");
        int hours = Integer.parseInt(st.nextToken());
        char[] arr = st.nextToken().toCharArray();
        //the ints will be in the first and second indices of the array
        String s = "" + arr[0] + arr[1];
        int minutes = Integer.parseInt(s);
        //the am/pm parts will be the remaining array
        String ampm = "" + arr[2] + arr[3];
        int totalMinutes = (hours * minutesInHour) + minutes + duration;
        int hoursAfterMinutes = totalMinutes / minutesInHour,
                minutesAfterMinutes = totalMinutes % minutesInHour;
        System.out.println(hoursAfterMinutes);
        if (hoursAfterMinutes >= hoursInDay)
            ampm = ampm.toLowerCase().equals("am") ? "pm" : "am";
        return String.format("%02d:%02d%s", hoursAfterMinutes, minutesAfterMinutes, ampm);
    }

    /**
     * Checks if two events have dates that are equal to each other.
     * @param  o  the supposed event to be checked
     * @return  true if the two events are equal to each other, false otherwise.
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Event))
            return false;
        Event e = (Event) o;
        if (this.date.equals(e.date)){
            if (this.location.equals(e.location))
                return this.startTime.equals(e.startTime);
            else
                return false;
        }else
            return false;
    }

    /**
     * Mutator method for setting this event's date
     * @param date the event date
     */
    public void setDate(Date date){this.date = date;}

    /**
     * Mutator method for setting the event's duration
     * @param duration the event's duration
     */
    public void setDuration(int duration){this.duration = duration;}

    /**
     * Mutator method for setting the event's starting time
     * @param startTime the event's starting time
     */
    public void setStartTime(Timeslot startTime){this.startTime = startTime;}

    /**
     * Mutator method for setting the event's location
     * @param location the event's location
     */
    public void setLocation(Location location){this.location = location;}

    /**
     * Mutator method for setting the event's contact
     * @param contact the event's contact
     */
    public void setContact(Contact contact){this.contact = contact;}

    /**
     * Accessor method for getting the event's date
     * @return the event date
     */
    public Date getDate(){return this.date;}

    /**
     * Accessor method for getting the event's contact
     * @return the event's contact
     */
    public Contact getContact() {return contact;}

    /**
     * Accessor method for getting the event's duration
     * @return the event's duration
     */
    public int getDuration() {return duration;}

    /**
     * Accessor method for getting the event's location
     * @return the event location
     */
    public Location getLocation() {return location;}

    /**
     * Accessor method for getting the event's start time
     * @return the event's starting time
     */
    public Timeslot getStartTime() {return startTime;}

    /**
     * A testbed main class to test the equals method of Event
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Event e1, e2, e3;
        int sampleMonth = 10, sampleDay = 31, sampleYear = 2023, sampleDur = 60;
        e1 = new Event(new Date(sampleMonth, sampleDay, sampleYear), Timeslot.MORNING,
                Location.ALLISON_ROAD_CLASSROOM, new Contact(Department.CS,
                "cs@rutgers.edu"), sampleDur);
        e2 = new Event(new Date(sampleMonth, sampleDay, sampleYear), Timeslot.AFTERNOON,
                Location.ALLISON_ROAD_CLASSROOM, new Contact(Department.CS,
                "cs@rutgers.edu"), sampleDur);
        e3 = new Event(new Date(sampleMonth, sampleDay, sampleYear), Timeslot.MORNING,
                Location.ALLISON_ROAD_CLASSROOM, new Contact(Department.CS,
                "cs@rutgers.edu"), sampleDur);
        System.out.println("Testing event equality");
        System.out.println("Event 1: " + e1);
        System.out.println("Event 2: " + e2);
        System.out.println("Event 3: " + e3);
        System.out.println(e1.equals(e2) ? "Events 1 and 2 are the same." :
                "Events 1 and 2 are NOT the same.");
        System.out.println(e1.equals(e3) ? "Events 1 and 3 are the same." :
                "Events 1 and 3 are NOT the same.");
        System.out.println(e2.equals(e3) ? "Events 2 and 3 are the same." :
                "Events 2 and 3 are NOT the same.");
    }
}
