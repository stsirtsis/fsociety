package gr.ntua.ece.softeng;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class E {
	@Id
	private String Id;
	private String capacity;
	private String price;
	private String category;
	private String state;	
	private String company_name;


	public E(String capacity,String price, String category,String state,String company_name) {
		this.capacity=capacity;
		this.price=price;
		this.category=category;
		this.state=state;
		this.company_name=company_name;
	}


	@Override
	public String toString() {
		return String.format(
				"Event[id=%s, capacity=%s, price=%s, category=%s, state=%s, comapany_name=%s]",
				Id,capacity,price,category,state,company_name) ;
	}

}