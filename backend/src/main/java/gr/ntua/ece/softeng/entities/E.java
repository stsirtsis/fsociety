package gr.ntua.ece.softeng.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document(collection = "events")
public class E {
	@Id
	private String id;
	@TextIndexed	private String eventName;
	@TextIndexed    private String description;
	private String Area;
	private String StreetName;
	private Integer StreetNumber;
	private Integer ageGroup;
	private Integer capacity;
	private Integer price;
	private Integer category;
	@TextIndexed private String company_name;
	@TextScore private Float score;
	private Double latitude;
	private Double longitude;
	private String date;
	private String state;
	private String photoUri;
	private String photoBody;


	public E(String id, String eventName,String description,String Area,String StreetName,Integer StreetNumber,Integer ageGroup,Integer capacity,Integer price, Integer category,String company_name,Double latitude,Double longitude,String date,String state,String photoUri,String photoBody) {
		this.id=id;
		this.eventName=eventName;
		this.description=description;
		this.Area=Area;
		this.StreetName=StreetName;
		this.StreetNumber=StreetNumber;
		this.ageGroup=ageGroup;
		this.capacity=capacity;
		this.price=price;
		this.category=category;
		this.company_name=company_name;
		this.latitude=latitude;
		this.longitude=longitude;
		this.date=date;
		this.state=state;
		this.photoUri=photoUri;
		this.photoBody=photoBody;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEventname() {
		return eventName;
	}


	public void setEventname(String eventname) {
		this.eventName = eventname;
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


	public Integer getStreetNumber() {
		return StreetNumber;
	}


	public void setStreetNumber( Integer streetNumber) {
		StreetNumber = streetNumber;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		else if (obj instanceof E) {
			E e = (E) obj;
			return Objects.equals(this.id,e.id);

		}
		else return false;
	}


	public Integer getAgeGroup() {
		return ageGroup;
	}


	public void setAgeGroup( Integer ageGroup) {
		this.ageGroup = ageGroup;
	}


	public  Integer getCapacity() {
		return capacity;
	}


	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Integer getCategory() {
		return category;
	}


	public void setCategory(Integer category) {
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


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPhotoUri() {
		return photoUri;
	}


	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}


	public String getPhotoBody() {
		return photoBody;
	}


	public void setPhotoBody(String photoBody) {
		this.photoBody = photoBody;
	}

	}
