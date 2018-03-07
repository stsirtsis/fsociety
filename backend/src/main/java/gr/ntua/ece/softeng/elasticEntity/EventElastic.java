package gr.ntua.ece.softeng.elasticEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "events")
public class EventElastic {
    @Id
    private String id;
    @Field  private String eventName;
    private String description;
    private String Area;
    private String StreetName;
    private Integer StreetNumber;
    private Integer ageGroup;
    private Integer capacity;
    private Integer price;
    private Integer category;
    private String company_name;
    private Double latitude;
    private Double longitude;
    private String date;
    private String state;

    public EventElastic(String id, String eventName, String description, String area, String streetName, Integer streetNumber, Integer ageGroup, Integer capacity, Integer price, Integer category, String company_name, Double latitude, Double longitude, String date, String state) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.Area = area;
        this.StreetName = streetName;
        this.StreetNumber = streetNumber;
        this.ageGroup = ageGroup;
        this.capacity = capacity;
        this.price = price;
        this.category = category;
        this.company_name = company_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format(
                "EventElastic " +
                        "{" +
                           "\"id\" = \"%s\", " +
                           "\"eventName\" = \"%s\", " +
                           "\"description\" = \"%s\", " +
                           "\"Area\" = \"%s\", " +
                           "\"StreetName\" = \"%s\", " +
                           "\"StreetNumber\" = %s, " +
                           "\"ageGroup\" = %s," +
                           "\"capacity\" = %s, " +
                           "\"price\" = %s," +
                           "\"category\" = %s," +
                           "\"company_name\" = %s, " +
                           "\"latitude\" = %s," +
                           "\"longitude\" = %s" +
                           "\"state\" = \"%s\" " +
                        "}"
                ,this.id, this.eventName, this.description, this.Area, this.StreetName,
                this.StreetNumber, this.ageGroup, this.capacity, this.price, this.category,
                this.company_name, this.latitude, this.longitude, this.state);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public void setStreetNumber(Integer streetNumber) {
        StreetNumber = streetNumber;
    }

    public Integer getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(Integer ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getCapacity() {
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
}
