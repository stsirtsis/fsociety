package gr.ntua.ece.softeng.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Providers {
	@Id
	private String companyName;
	@NotNull
	private String FirstName;
	@NotNull
	private String LastName;
	@NotNull
	private String userName;
	@NotNull
	private String Password;
	@NotNull
	private String Description;
	@NotNull
	private String Area;
	@NotNull
	private String StreetName;
	@NotNull
	private Integer StreetNumber;
	@NotNull
	private String TelNumber;
	@Email
	@NotNull
	private String Mail;
	@NotNull
	private String Iban;

	@OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
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
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
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
	@Override
	public String toString() {
		return "Provider [companyName=" + companyName + ",FirstName=" + FirstName + ", LastName=" + LastName + ", UserName=" + userName + " , Password=" + Password + ", Description= "+Description+" , Area= "+Area+", StreetName= "+StreetName+", StreetNumber= "+StreetNumber+", TelNumber= "+TelNumber+", Mail=" + Mail + ", Iban= "+Iban+"]";
	}

}
