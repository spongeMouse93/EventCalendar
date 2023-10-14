package csevent;

/**
 * A class made to handle scheduling, removing, and printing events
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since September 18, 2023
 */
public class EventCalendar {

    /**
     * The list of events
     */
    private Event[] events;

    /**
     * The number of events currently registered in the list, right now set to 0
     */
    private int numEvents;

    /**
     * The number of events the list can hold, subject to change when list
     * gets full
     */
    private int INITIAL_CAPACITY = 4;

    /**
     * The starting index of the list
     */
    private int BEGINNER_INDEX = 0;

    /**
     * A default constructor used for initializing the list
     */
    public EventCalendar(){
        numEvents = BEGINNER_INDEX;
        events = new Event[INITIAL_CAPACITY];
    }

    /**
     * Checks if given event is part of the list.
     * @param  e  the Event to be seen if it is part of the list.
     * @return true if the event is part of the list, false otherwise.
     */
    public boolean contains(Event e){
        if (isEmpty())
            return false;
        for (int i = BEGINNER_INDEX; i < numEvents; i++)
            if (e.equals(events[i]))
                return true;
        return false;
    }

    /**
     * Searches for given event in the list
     * @param  e  the Event to be searched for in the list.
     * @return  the index in the list where the event exists, -1 if the event is not in the list.
     */
    private int find(Event e){
        final int NOT_FOUND = -1;
        for (int i = BEGINNER_INDEX; i < numEvents; i++)
            if (e.equals(events[i]))
                return i;
        return NOT_FOUND;
    }

    /**
     * Attempts to remove the given event from the list, assuming it's there
     * @param e the event to be removed
     * @return true if the event was successfully removed, false otherwise
     */
    public boolean remove(Event e){
        if (!contains(e))
            return false;
        Event[] copy = events;
        int indexOfE = find(e);
        for (int i = BEGINNER_INDEX, k = BEGINNER_INDEX; i < numEvents; i++)
            if (i != indexOfE){
                copy[k] = events[k];
                k++;
            }
        events = copy;
        numEvents--;
        return true;
    }

    /**
     * This method makes the events grow by 4 indices; only to be implemented whenever
     * events gets full.
     */
    private void grow(){
        Event[] copy = new Event[events.length + INITIAL_CAPACITY];
        for (int i = BEGINNER_INDEX; i < numEvents; i++)
            copy[i] = events[i];
        events = copy;
    }

    /**
     * Checks if this list of events is full.
     * @return  true if this list of events is full, false otherwise.
     */
    private boolean isFull(){
        if (isEmpty())
            return false;
        for (int i = BEGINNER_INDEX; i < events.length; i++)
            if (events[i] == null)
                return false;
        return true;
    }

    /**
     * Checks if this list of events is empty.
     * @return true if this list of events is empty, false otherwise
     */
    private boolean isEmpty(){return numEvents == BEGINNER_INDEX;}

    /**
     * Attempts to add given event to the list; will not if event is already in
     * the list. If the list becomes full after the addition, the list grows.
     * @param  e the event to be added to the list
     * @return  true if adding the event was successful, false otherwise.
     */
    public boolean add(Event e){
        if (contains(e))
            return false;
        else{
            for (int i = BEGINNER_INDEX; i < events.length; i++)
                if (events[i] == null){
                    events[i] = e;
                    break;
                }
            numEvents++;
            if (isFull())
                grow();
            return true;
        }
    }

    /**
     * Takes two events and swap their values
     * @param e1 the first event to be swapped value
     * @param e2 the second event to be swapped value
     */
    private void swap(Event e1, Event e2){
        Event temp = e1;
        e1 = e2;
        e2 = temp;
    }

    /**
     * Accessor method for getting the list of events
     * @return the list of events
     */
    public Event[] getEvents(){return events;}

    /**
     * Accessor method for the number of events registered in the calendar.
     * @return  the number of events in the calendar.
     */
    public int getNumEvents(){return numEvents;}

    /**
     * Prints all the events in the calendar the way they are, no need for
     * sorting
     */
    public void print(){
        if (isEmpty()){
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar *");
        for (int i = BEGINNER_INDEX; i < numEvents; i++)
            System.out.printf("%s\n", events[i]);
        System.out.println("* end of event calendar *");
    }

    /**
     * Prints all the events in the calendar after they have been sorted based off
     * their campus location
     */
    public void printByCampus(){
        if (isEmpty()) {
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by campus and building *");
        for (int i = BEGINNER_INDEX; i < numEvents; i++){
            Location l1 = events[i].getLocation();
            for (int j = i + 1; j < numEvents; j++){//j starts an index ahead of i
                Location l2 = events[j].getLocation();
                if (l1.compareTo(l2) > BEGINNER_INDEX)
                    swap(events[i], events[j]);
            }
        }
        for (int i = BEGINNER_INDEX; i < numEvents; i++)
            System.out.printf("%s\n", events[i]);
        System.out.println("* end of event calendar *");
    }

    /**
     * Prints all the events in the calendar after they have been sorted based off
     * their departments
     */
    public void printByDepartment(){
        if (isEmpty()){
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by department *");
        for (int i = BEGINNER_INDEX; i < numEvents; i++){
            Department d1 = events[i].getContact().getDepartment();
            for (int j = i + 1; j < numEvents; j++){//j starts an index ahead of i
                Department d2 = events[j].getContact().getDepartment();
                if (d1.compareTo(d2) > BEGINNER_INDEX)
                    swap(events[i], events[j]);
            }
        }
        for (int i = BEGINNER_INDEX; i < numEvents; i++)
            System.out.printf("%s\n", events[i]);
        System.out.println("* end of event calendar *");
    }

    /**
     * Prints all the events in the calendar after they have been sorted based off
     * their dates
     */
    public void printByDate(){
        if (isEmpty()){
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by event date and start time *");
        for (int i = BEGINNER_INDEX; i < numEvents; i++){
            Date d1 = events[i].getDate();
            for (int j = i + 1; j < numEvents; j++){ //j starts an index ahead of i
                Date d2 = events[j].getDate();
                if (d1.compareTo(d2) > BEGINNER_INDEX)
                    swap(events[i], events[j]);
            }
        }
        for (int i = BEGINNER_INDEX; i < numEvents; i++)
            System.out.printf("%s\n", events[i]);
        System.out.println("* end of event calendar *");
    }
}
