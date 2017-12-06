package gr.ntua.ece.softeng;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private String FirstName;
	private String LastName;
	private String username;
	private String password;
	private String email;
	private String PhoneNumber;
	private String DebitCard;
	private Integer Fpoints;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
