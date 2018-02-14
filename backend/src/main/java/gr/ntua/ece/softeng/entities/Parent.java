package gr.ntua.ece.softeng.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import gr.ntua.ece.softeng.entities.Event;

@Entity
public class Parent {
	@Id
	private String username;
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phoneNumber;
	private String debitCard;
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
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDebitCard() {
		return debitCard;
	}
	public void setDebitCard(String debitCard) {
		this.debitCard = debitCard;
	}
	public Integer getFpoints() {
		return Fpoints;
	}
	public void setFpoints(Integer fpoints) {
		Fpoints = fpoints;
	}
	@Override
	public String toString() {
		return "Parent [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + " , password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", debitCard=" + debitCard + " ,Fpoints=" + Fpoints + "]";
	}
	
}
