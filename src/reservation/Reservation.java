package reservation;
import java.time.LocalDateTime;

public class Reservation {
	private LocalDateTime dateReserved;
	private LocalDateTime dateCreated;
	private int pax;
	private String name;
	private int contact;
	private Table table;


	public Reservation(LocalDateTime dateReserved, LocalDateTime dateCreated, int pax, String name, int contact,Table table) {
		super();
		this.dateReserved = dateReserved;
		this.dateCreated = dateCreated;
		this.pax = pax;
		this.name = name;
		this.contact = contact;
		this.table = table;
	}
	public Table getTable() {
		return table;
	}
	public LocalDateTime getDateReserved() {
		return dateReserved;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public int getPax() {
		return pax;
	}
	public String getName() {
		return name;
	}
	public int getContact() {
		return contact;
	}
	public void setDateReserved(LocalDateTime dateReserved) {
		this.dateReserved = dateReserved;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public void setPax(int pax) {
		this.pax = pax;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}

}
