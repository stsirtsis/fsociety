package gr.ntua.ece.softeng.entities;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gr.ntua.ece.softeng.entities.Event;

@Entity
public class Parent {
	@Id
	private String username;

	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String Area;
	@NotNull
	private String StreetName;
	@NotNull
	private Integer StreetNumber;

	private Double latitude;
	private Double longitude;
	@NotNull
	private String password;
	@Email
	@NotNull
	private String email;
	@NotNull
	private String phoneNumber;
	@NotNull
	private String debitCard;
	@NotNull
	private Integer Fpoints;
	@NotNull
	private Integer wallet;

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
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getStreetName() {
		return StreetName;
	}
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}
	public Integer getStreetNumber() {
		return StreetNumber;
	}
	public void setStreetNumber(Integer streetNumber) {
		StreetNumber = streetNumber;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude2) {
		this.latitude = latitude2;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getWallet() {
		return wallet;
	}
	public void setWallet(Integer wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Parent [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + " , password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", debitCard=" + debitCard + " ,Fpoints=" + Fpoints + "]";
	}

}
