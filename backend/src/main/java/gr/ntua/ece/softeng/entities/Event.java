package gr.ntua.ece.softeng.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


import gr.ntua.ece.softeng.entities.Parent;

enum state {OPEN, FINISHED}

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull private String eventname;
    @NotNull private String description;
    @NotNull private String Area;
    @NotNull private String StreetName;
    @NotNull private Integer StreetNumber;
    @NotNull private Integer ageGroup;
    @NotNull private Integer capacity;
    @NotNull private Integer price;
    @NotNull private Integer category;
    @NotNull private Date date;
    @NotNull private state state;
    @NotNull private Integer clicks;
    @NotNull private Integer initial;
		private String photoUri;
		private String photoBody;
		private Double latitude;
		private Double longitude;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Providers provider;


    @ManyToMany(mappedBy = "events", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Parent> parents;


    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Providers getProvider() {
        return provider;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getInitial() {
        return initial;
    }

    public void setInitial(Integer capacity) {
        this.initial = capacity;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
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


}
