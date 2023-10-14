package csevent;

import java.util.StringTokenizer;

/**
 * A class made to handle the contacts for scheduling events.
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since September 18, 2023
 */
public class Contact {
    /**
     * This contact's department
     */
    private Department department;

    /**
     * This contact's email
     */
    private String email;

    /**
     * Parametrized constructor for setting the contact's department and email
     * @param department the contact's department
     * @param email the contact's email
     */
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    /**
     * Default constructor with no department or email provided
     */
    public Contact(){this(null, "");}

    /**
     * Checks if this contact is valid.
     * @return  true if this contact is valid, false otherwise.
     */
    public boolean isValid(){
        if (!isEmailValid() || department == null)
            return false;
        for (Department d : Department.values())
            if (d.equals(department))
                return true;
        return false;
    }

    /**
     * A helper method created to check if the email is valid.
     * @return true if the email is valid, false otherwise
     */
    private boolean isEmailValid(){
        if (email == null || email.isEmpty())
            return false;
        if (!email.contains("@") || !email.contains("."))
            return false;
        StringTokenizer st = new StringTokenizer(email, "@");
        String localPart = st.nextToken(), domain = st.nextToken();
        if (localPart.isEmpty() || domain.isEmpty())
            return false;
        if (!localPart.toLowerCase().equals(department.toString().toLowerCase()))
            return false;
        for (char c : localPart.toCharArray())
            if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                    && c != '.' && c != '_' && c != '-')
                return false;
        for (char c : domain.toCharArray())
            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && c != '.')
                return false;
        if ((email.indexOf('@') != email.lastIndexOf('@')) || !email.endsWith("rutgers.edu"))
            return false;
        return true;
    }

    /**
     * Returns a String representation of this contact.
     * @return  a String representation of this contact.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[Contact: ");
        switch (department){
            case CS:
                sb.append("Computer Science, ");
                break;
            case MATH:
                sb.append("Mathematics, ");
                break;
            case EE:
                sb.append("Electrical Engineering, ");
                break;
            case ITI:
                sb.append("Information Technology and Informatics, ");
                break;
            case BAIT:
                sb.append("Business Analytics and Information Technology, ");
                break;
        }
        sb.append(email + "]");
        return sb.toString();
    }

    /**
     * Checks if this contact is equal to another given one
     * @param obj the supposed contact to check
     * @return true if the 2 contacts are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Contact))
            return false;
        Contact c = (Contact) obj;
        return department.equals(c.department) && email.equals(c.email);
    }

    /**
     * Accessor method for getting the contact's department
     * @return the contact's department
     */
    public Department getDepartment(){return this.department;}

    /**
     * Accessor method for getting the contact's email address
     * @return the contact's email address
     */
    public String getEmail(){return this.email;}

    /**
     * Mutator method for setting the contact's department
     * @param department the contact's department
     */
    public void setDepartment(Department department){this.department = department;}

    /**
     * Mutator method for setting contact's email address
     * @param email the contact's email address
     */
    public void setEmail(String email) {this.email = email;}
}
