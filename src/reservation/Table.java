package reservation;
/**
 * Represents a Reservation created in this restaurant.
 * 
 * @author Russell
 * @version 1.0
 * @since 2021-08-11
 *
 */
public class Table {
	/**
	 * Table id for this Table.
	 */
	private int tableId;
	/**
	 * Number of seats for this Table.
	 */
	private int numOfSeats;
	/**
	 * Availability for this Table.
	 */
	private String availability = "AVAILABLE";
	/**
	 * Create Table with tableId,numOfSeats
	 * @param tableId is the table id for this Table.
	 * @param numOfSeats is the number of seats for this Table.
	 */
	public Table(int tableId, int numOfSeats) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
	}

	/**
     * Gets tableId of the table.
     * @return tableId of the table.
	 */
	public int getTableId() {
		return tableId;
	}

	/**
     * Gets number of seats of the table.
     * @return numOfSeats of the table.
	 */
	public int getNumOfSeats() {
		return numOfSeats;
	}

	/**
     * Gets availability of the table.
     * @return availability of the table.
	 */
	public String getAvailability() {
		return availability;
	}

	/**
	 * Sets availability of the table.
	 * @param availability of the table.
	 */
	public void setAvailability(String availability) {
		this.availability = availability;
	}

}
