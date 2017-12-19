package gr.ntua.ece.softeng;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import gr.ntua.ece.softeng.Event;

@Entity
public class Parent {
	@Id
	private String username;
	
	private String FirstName;
	private String LastName;
	private String password;
	private String email;
	private String PhoneNumber;
	private String DebitCard;
	private Integer Fpoints;
	
	@ManyToMany
	@JsonIgnore
	Set<Event> events;
	
	
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getDebitCard() {
		return DebitCard;
	}
	public void setDebitCard(String debitCard) {
		DebitCard = debitCard;
	}
	public Integer getFpoints() {
		return Fpoints;
	}
	public void setFpoints(Integer fpoints) {
		Fpoints = fpoints;
	}
	
}
