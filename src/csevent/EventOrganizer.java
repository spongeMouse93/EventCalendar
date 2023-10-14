package csevent;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * The main class that handles the event calendar and accepts user input and breaks
 * it down into tokens so the program can handle each command
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since September 18, 2023
 */
public class EventOrganizer {
    /**
     * The main event calendar
     */
    private EventCalendar ec;

    /**
     * Default constructor for initializing the calendar
     */
    public EventOrganizer(){ec = new EventCalendar();}

    /**
     * Main method used for running (and eventually terminating) the event
     * calendar
     */
    public void run(){
        System.out.println("Event organizer running...\n");
        Scanner sc = new Scanner(System.in);
        while (true){
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            try{
                handleCommand(st);
            }catch (InterruptedException ie){
                System.out.println("Event Organizer terminated.");
                return;
            }
        }
    }

    /**
     * Handles the command passed in through the given StringTokenizer
     * @param st the StringTokenizer containing the command to be processed
     * @throws InterruptedException when a "Q" is passed in to terminate the process
     */
    private void handleCommand(StringTokenizer st) throws InterruptedException {
        String s = st.nextToken();
        switch (s){
            case "A":
                handleAdd(st);
                break;
            case "R":
                handleRemove(st);
                break;
            case "P":
                ec.print();
                break;
            case "PE":
                ec.printByDate();
                break;
            case "PC":
                ec.printByCampus();
                break;
            case "PD":
                ec.printByDepartment();
                break;
            case "Q":
                throw new InterruptedException();
            default:
                System.out.println(s + " is not a valid token.");
        }
    }

    /**
     * A helper method made to handle adding events to the calendar,
     * using the remaining tokens in the StringTokenizer made in the run method.
     * @param st the StringTokenizer containing the remaining tokens that were scanned.
     */
    private void handleAdd(StringTokenizer st){
        final int MINI = 30, MAXI = 120;
        Date d = makeDate(new StringTokenizer(st.nextToken(), "/"));
        if (!isViable(d))
            return;
        Timeslot ts = getTimeslot(st.nextToken());
        Location l = getLocation(st.nextToken());
        if (ts == null || l == null)
            return;
        Contact c = new Contact(getDepartment(st.nextToken()), st.nextToken());
        if (!c.isValid()){
            System.out.println("Invalid contact information!");
            return;
        }
        int duration = Integer.parseInt(st.nextToken());
        if (duration < MINI || duration > MAXI){
            System.out.println("Event duration must be at least 30 minutes " +
                    "and at most 120 minutes");
            return;
        }
        Event e = new Event(d, ts, l, c, duration);
        if (!ec.add(e)){ //if the event is already in the calendar
            System.out.println("The event is already on the calendar.");
            return;
        }
        ec.add(e);
        System.out.println("Event added to the calendar.");
    }

    /**
     * A helper method to determine if a date is viable. The date is considered
     * "viable" if it is valid, in the future, and within 6 months of today.
     * If it is not viable, an error message should be printed to the console
     * detailing its exact error.
     * @param d the date to be checked
     * @return true if the date is viable, false otherwise
     */
    private boolean isViable(Date d){
        if (!d.isValid()){
            System.out.println(d + ": Invalid calendar date!");
            return false;
        }
        if (!d.isInTheFuture()){
            System.out.println(d + ": Event date must be a future date!");
            return false;
        }
        if (!d.isWithinSixMonthsOfToday()){
            System.out.println(d + ": Event date must be within 6 months!");
            return false;
        }
        return true;
    }


    /**
     * A helper method made to handle removing events from the calendar
     * @param st the StringTokenizer containing the remaining tokens that were scanned
     */
    private void handleRemove(StringTokenizer st){
        String date = st.nextToken();
        Date d = makeDate(new StringTokenizer(date, "/"));
        if (!isViable(d))
            return;
        String timeslot = st.nextToken(), roomNum = st.nextToken();
        Timeslot t = getTimeslot(timeslot);
        Location l = getLocation(roomNum);
        if (t == null || l == null)
            return;
        Event e = new Event();
        e.setDate(d);
        e.setStartTime(t);
        e.setLocation(l);
        if (!ec.remove(e)){  //if the event does not exist in the calendar
            System.out.println("Cannot remove; event is not in the calendar! ");
            return;
        }
        ec.remove(e);
        System.out.println("Event has been removed from the calendar!");
    }

    /**
     * A helper method created to make a Date out of a tokenizer
     * @param  dateToken the tokenizer containing the date token
     * @return a date made out of the token
     */
    private Date makeDate(StringTokenizer dateToken){
        int month = Integer.parseInt(dateToken.nextToken());
        int day = Integer.parseInt(dateToken.nextToken());
        int year = Integer.parseInt(dateToken.nextToken());
        return new Date(month, day, year);
    }

    /**
     * A helper method created to get the actual department enum based off
     * the passed-in String
     * @param department the String representation of the department
     * @return the Department corresponding to the String, or null if it does not exist
     */
    private Department getDepartment(String department){
        switch (department.toUpperCase()){
            case "CS":
                return Department.CS;
            case "EE":
                return Department.EE;
            case "BAIT":
                return Department.BAIT;
            case "ITI":
                return Department.ITI;
            case "MATH":
                return Department.MATH;
            default:
                return null;
        }
    }

    /**
     * A helper method created to get the actual timeslot enum based off
     * the passed-in String
     * @param timeslot the String representation of the timeslot
     * @return the Timeslot corresponding to the String, or
     * prints an error message and returns null if it does not exist
     */
    private Timeslot getTimeslot(String timeslot){
        switch (timeslot.toLowerCase()){
            case "afternoon":
                return Timeslot.AFTERNOON;
            case "morning":
                return Timeslot.MORNING;
            case "evening":
                return Timeslot.EVENING;
            default:
                System.out.println("Invalid timeslot!");
                return null;
        }
    }

    /**
     * A helper method created to get the actual location enum based off
     * the passed-in String
     * @param location the String representation of the location
     * @return the Location corresponding to the room number, or
     * prints an error message and returns null if it
     * does not exist
     */
    private Location getLocation(String location){
        location = location.toLowerCase();
        for (Location l : Location.values())
            if (location.equals(l.getRoomNum().toLowerCase()))
                return l;
        System.out.println("Invalid location!");
        return null;
    }
}
