package reservation;
import java.time.LocalDateTime;
/**
 * Represents a Reservation created in this restaurant.
 * 
 * @author Russell
 * @version 1.0
 * @since 2021-08-11
 *
 */
public class Reservation {
	/**
	 * Reservation date in dd/MM/yyyy HH:mm format.
	 */
	private LocalDateTime dateReserved;
	/**
	 * Reservation creation date in dd/MM/yyyy HH:mm format.
	 */
	private LocalDateTime dateCreated;
	/**
	 * Number of persons for the reservation.
	 */
	private int pax;
	/**
	 * Name used for reservation.
	 */
	private String name;
	/**
	 * Contact used for reservation.
	 */
	private int contact;
    /**
     * Reference of table object used for reservation.
     */
	private Table table;

	/**
	 * Create Reservation with dateReserved,dateCreated,pax,name,contact,table object
	 * @param dateReserved is the reservation date in dd/MM/yyyy HH:mm format.
	 * @param dateCreated is the reservation creation date in dd/MM/yyyy HH:mm format.
	 * @param pax is the number of persons for the reservation.
	 * @param name is the name used for reservation.
	 * @param contact is the contact used for reservation.
	 * @param table is the reference of the table object is linked to the reservation.
	 */
	public Reservation(LocalDateTime dateReserved, LocalDateTime dateCreated, int pax, String name, int contact,Table table) {
		super();
		this.dateReserved = dateReserved;
		this.dateCreated = dateCreated;
		this.pax = pax;
		this.name = name;
		this.contact = contact;
		this.table = table;
	}
    /**
     * Gets reference of the table object used for reservation.
     * @return reference of table object.
     */
	public Table getTable() {
		return table;
	}
	/**
     * Gets Date Reserved of the reservation.
     * @return  Date Reserved of the reservation.
	 */
	public LocalDateTime getDateReserved() {
		return dateReserved;
	}
	/**
     * Gets Date Created of the reservation.
     * @return Date Created of the reservation.
	 */
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	/**
     * Gets pax of the reservation.
     * @return pax of the reservation.
	 */
	public int getPax() {
		return pax;
	}
	/**
     * Gets name of the reservation.
     * @return name of the reservation.
	 */
	public String getName() {
		return name;
	}
	/**
     * Gets contact of the reservation.
     * @return contact of the reservation.
	 */
	public int getContact() {
		return contact;
	}
}
