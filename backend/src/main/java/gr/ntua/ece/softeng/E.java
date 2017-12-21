package gr.ntua.ece.softeng;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "events")
public class E {
	@Id
	private String Id;
	private String eventname;
	private String description;
	private String Area;
	private String StreetName;
	private String StreetNumber;
	private String AgeGroup;
	private String capacity;
	private String price;
	private String category;
	private String company_name;


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

}