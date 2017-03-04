package com.mrppa.widgetmanager.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "widget")
public class Widget {
	@Id
	private String name;
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
