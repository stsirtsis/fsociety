package gr.ntua.ece.softeng.entities;

public class Filters {
	private Integer price;
	private Integer ageGroup;
	private Integer category;
	private Integer distance;
    private String text;
    private String username;

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(Integer ageGroup) {
		this.ageGroup = ageGroup;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
