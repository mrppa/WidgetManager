package com.mrppa.widgetmanager.modal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "widget")
public class Widget {
	@Id
	@NotEmpty
	@Size(min=4,max=10)
	private String name;
	@NotEmpty
	@Size(min=4,max=50)
	private String description;
	private String widgetID;

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getWidgetID() {
		return widgetID;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWidgetID(String widgetID) {
		this.widgetID = widgetID;
	}

}
