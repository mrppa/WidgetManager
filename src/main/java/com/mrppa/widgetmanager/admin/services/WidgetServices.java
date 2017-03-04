package com.mrppa.widgetmanager.admin.services;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mrppa.widgetmanager.WidgetException;
import com.mrppa.widgetmanager.modal.Widget;
import com.mrppa.widgetmanager.store.WidgetStore;

public class WidgetServices {
	private static final Logger LOG = LoggerFactory.getLogger(WidgetServices.class);

	private MongoOperations mongoOperations;
	private WidgetStore widgetStore;

	public void addWidget(Widget widget) throws WidgetException {
		LOG.info("ADD WIDGET SERVICE PROCESS STARTED");
		try {
			widget.setWidgetID(UUID.randomUUID().toString());
			this.widgetStore.startUpWidgetSpace(widget.getWidgetID());
			mongoOperations.insert(widget);
			LOG.info("WIDGET SUCCESSFULLY SAVED");
		} catch (Exception e) {
			LOG.error("ERROR WHILE SAVING WIDGET", e);
			throw new WidgetException("ERROR WHILE SAVING WIDGET");
		}

	}

	public List<Widget> getAllWidgets() throws WidgetException {
		LOG.debug("LISTING ALL WIDGETS");
		List<Widget> widgets = null;
		try {
			widgets = mongoOperations.findAll(Widget.class);
		} catch (Exception e) {
			LOG.error("ERROR WHILE LISTING WIDGETS", e);
			throw new WidgetException("ERROR WHILE LISTING WIDGETS");
		}
		return widgets;
	}

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public Widget getWidget(String widgetName) throws WidgetException {
		LOG.debug("LOADING WIDGET . NAME\t" + widgetName);
		Widget widget;
		try {
			Query searchUserQuery = new Query(Criteria.where("name").is(widgetName));
			widget = mongoOperations.findOne(searchUserQuery, Widget.class);
		} catch (Exception e) {
			LOG.error("ERROR WHILE LISTING WIDGETS", e);
			throw new WidgetException("ERROR WHILE LISTING WIDGETS");
		}
		return widget;
	}

	public WidgetStore getWidgetStore() {
		return widgetStore;
	}

	public InputStream readWidgetContentFile(Widget widget, String filePath) throws WidgetException {
		LOG.trace("READ WIDGET CONTENT \t" + widget.getName() + " FILE PATH\t" + filePath);
		InputStream inputStream = null;
		try {
			inputStream = this.widgetStore.readWidgetFile(widget.getWidgetID(), filePath);
			LOG.info("WIDGET SUCCESSFULLY UPLOADED");
		} catch (Exception e) {
			LOG.error("ERROR WHILE UPLOAD WIDGET", e);
			throw new WidgetException("ERROR WHILE UPLOAD WIDGET");
		}
		return inputStream;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public void setWidgetStore(WidgetStore widgetStore) {
		this.widgetStore = widgetStore;
	}

	public void updateWidget(Widget widget) throws WidgetException {
		LOG.info("UPDATE WIDGET SERVICE PROCESS STARTED");
		try {
			mongoOperations.save(widget);
			LOG.info("WIDGET SUCCESSFULLY UPDATE");
		} catch (Exception e) {
			LOG.error("ERROR WHILE UPDATE WIDGET", e);
			throw new WidgetException("ERROR WHILE UPDATE WIDGET");
		}

	}
	
	public void deleteWidget(Widget widget) throws WidgetException {
		LOG.info("DELETE WIDGET SERVICE PROCESS STARTED");
		try {
			this.widgetStore.cleanUpWidgetSpace(widget.getWidgetID());
			Query searchQuery = new Query(Criteria.where("name").is(widget.getName()));
			mongoOperations.remove(searchQuery, Widget.class);
			LOG.info("WIDGET SUCCESSFULLY DELETED");
		} catch (Exception e) {
			LOG.error("ERROR WHILE DELETING WIDGET", e);
			throw new WidgetException("ERROR WHILE DELETING WIDGET");
		}

	}

	public void uploadWidget(Widget widget, InputStream inputStream) throws WidgetException {
		LOG.info("UPLOAD WIDGET SERVICE PROCESS NAME\t" + widget.getName());
		try {
			this.widgetStore.uploadWidget(widget.getWidgetID(), inputStream);
			LOG.info("WIDGET SUCCESSFULLY UPLOADED");
		} catch (Exception e) {
			LOG.error("ERROR WHILE UPLOAD WIDGET", e);
			throw new WidgetException("ERROR WHILE UPLOAD WIDGET");
		}

	}
}
