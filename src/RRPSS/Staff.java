package RRPSS;
/**
 * Represent a staff working for this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class Staff {
	/**
	 * Name of staff.
	 */
    private String name;
    /**
     * Unique staff ID.
     */
    private String ID;
    /**
     * Update staff name.
     * @param name New name as input.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Update staff ID.
     * @param ID New ID as input.
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    /**
     * Get staff name.
     * @return Name of this staff.
     */
    public String getName() {
        return name;
    }
    /**
     * Get staff ID.
     * @return Unique ID of this staff.
     */
    public String getID() {
        return ID;
    }
}
