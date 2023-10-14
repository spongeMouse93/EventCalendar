package csevent;

/**
 * This is a helper enum class to handle the locations of the events. There are 6 main
 * locations: ARC103, HLL114, BE_AUD, TIL232, MU302, and AB2225.
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since September 18, 2023
 */
public enum Location {
    /**
     * Handles room 103 in the Allison Road Classroom building
     */
    ALLISON_ROAD_CLASSROOM("ARC103"),
    /**
     * Handles room 114 in the Hill Center building
     */
    HILL_CENTER("HLL114"),
    /**
     * Handles the auditorium in Beck Hall
     */
    BECK_HALL("BE_AUD"),
    /**
     * Handles room 232 in Tillett Hall
     */
    TILLETT_HALL("TIL232"),
    /**
     * Handles room 2225 in the Academic Building
     */
    ACADEMIC_BUILDING("AB2225"),
    /**
     * Handles room 302 in Murray Hall
     */
    MURRAY_HALL("MU302");

    /**
     * The room number assigned to each building: the auditorium for Beck Hall,
     * room 2225 in the Academic Building, room 302 in Murray Hall, room 232 in Tillet Hall,
     * room 103 in Allison Road Classrooms, and room 114 in the Hill Center
     */
    private final String roomNum;

    /**
     * A mini constructor made to assign each location their room numbers
     * @param roomNum the locations' specific room number
     */
    Location(String roomNum){
        this.roomNum = roomNum;
    }

    /**
     * A helper accessor method created for accessing the room number associated
     * with each building
     * @return the room number associated with each respective building
     */
    public String getRoomNum(){return roomNum;}
}
