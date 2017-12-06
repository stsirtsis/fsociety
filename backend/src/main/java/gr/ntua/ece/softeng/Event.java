package gr.ntua.ece.softeng;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

enum state { OPEN, IN_PROGRESS, FINISHED }

@Entity
public class Event {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	private Integer capacity;
	private Integer price;
	private String category;
	private state state;	
	private String provider;
	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}


	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public state getState() {
		return state;
	}

	public void setState(state state) {
		this.state = state;
	}
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	

}
