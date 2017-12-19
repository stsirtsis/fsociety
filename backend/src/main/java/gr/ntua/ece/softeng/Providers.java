package gr.ntua.ece.softeng;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class Providers {
	@Id
	private String companyName;
	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private String Category;
	private String Description;
	private String Area;
	private String StreetName;
	private Integer StreetNumber;
	private String TelNumber;
	private String Mail;
	private String Iban;
	
	@OneToMany(mappedBy = "provider")
	@JsonIgnore
	private Set<Event> events;
	
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
	
	public String getcompanyName() {
		return companyName;
	}
	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
	
	public String getTelNumber() {
		return TelNumber;
	}
	public void setTelNumber(String telNumber) {
		TelNumber = telNumber;
	}
	
	public String getMail() {
		return Mail;
	}
	public void setMail(String Mail) {
		this.Mail = Mail;
	}
	
	public String getIban() {
		return Iban;
	}
	public void setIban(String iban) {
		Iban = iban;
	}
	
}


