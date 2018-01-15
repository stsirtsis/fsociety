package gr.ntua.ece.softeng;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;


@Document(collection = "events")
public class E {
	@Id
	private String Id;
	@TextIndexed	private String eventname;
	@TextIndexed    private String description;
    private String Area;
	private String StreetName;
	private String StreetNumber;
	private String AgeGroup;
	private String capacity;
	private String price;
	private String category;
	@TextIndexed private String company_name;
	@TextScore private Float score;


	public E(String eventname,String description,String Area,String StreetName,String StreetNumber,String AgeGroup,String capacity,String price, String category,String company_name) {
		this.eventname=eventname;
		this.description=description;
		this.Area=Area;
		this.StreetName=StreetName;
		this.StreetNumber=StreetNumber;
		this.AgeGroup=AgeGroup;
		this.capacity=capacity;
		this.price=price;
		this.category=category;
		this.company_name=company_name;
	}


	@Override
	public String toString() {
		return String.format(
				"Event[id=%s, eventname=%s, description=%s, Area=%s, StreetName=%s, StreetNumber=%s, AgeGroup=%s, capacity=%s, price=%s, category=%s, comapany_name=%s]",
				Id,eventname,description,Area,StreetName,StreetNumber,AgeGroup,capacity,price,category,company_name) ;
	}


	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}


	public String getEventname() {
		return eventname;
	}


	public void setEventname(String eventname) {
		this.eventname = eventname;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public String getStreetNumber() {
		return StreetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		StreetNumber = streetNumber;
	}


	public String getAgeGroup() {
		return AgeGroup;
	}


	public void setAgeGroup(String ageGroup) {
		AgeGroup = ageGroup;
	}


	public String getCapacity() {
		return capacity;
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getCompany_name() {
		return company_name;
	}


	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}


	public Float getScore() {
		return score;
	}


	public void setScore(Float score) {
		this.score = score;
	}

}