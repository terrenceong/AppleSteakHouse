package RRPSS;

public class Table {
	private int tableId;
	private int numOfSeats;
	private String availability = "AVAILABLE";

	public Table(int tableId, int numOfSeats) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
	}

	public int getTableId() {
		return tableId;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public String getAvailability() {
		return availability;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

}
