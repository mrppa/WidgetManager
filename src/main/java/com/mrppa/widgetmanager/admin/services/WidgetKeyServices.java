package com.mrppa.widgetmanager.admin.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mrppa.widgetmanager.WidgetException;
import com.mrppa.widgetmanager.logging.EnhanceLogging;
import com.mrppa.widgetmanager.modal.Widget;
import com.mrppa.widgetmanager.modal.WidgetKey;

@EnhanceLogging
@Component
public class WidgetKeyServices {
	private static final Logger LOG = LoggerFactory.getLogger(WidgetKeyServices.class);

	private MongoOperations mongoOperations;

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	public void addWidgetKey(WidgetKey widgetKey) throws WidgetException {
		LOG.info("ADD WIDGET KEY SERVICE PROCESS STARTED");
		try {
			widgetKey.setKey(UUID.randomUUID().toString());
			mongoOperations.insert(widgetKey);
			LOG.info("WIDGET KEY SUCCESSFULLY SAVED");
		} catch (Exception e) {
			LOG.error("ERROR WHILE SAVING WIDGET KEY ", e);
			throw new WidgetException("ERROR WHILE SAVING WIDGET KEY");
		}
	}
	
	public void removeWidgetKey(WidgetKey widgetKey) throws WidgetException {
		LOG.info("REMOVE WIDGET KEY SERVICE PROCESS STARTED");
		try {
			Query searchQuery = new Query(Criteria.where("widgetName").is(widgetKey.getWidgetName()).and("key").is(widgetKey.getKey()));
			mongoOperations.remove(searchQuery, widgetKey.getClass());
			LOG.info("WIDGET KEY SUCCESSFULLY REMOVE");
		} catch (Exception e) {
			LOG.error("ERROR WHILE REMOVING WIDGET KEY ", e);
			throw new WidgetException("ERROR WHILE REMOVING WIDGET KEY");
		}
	}
	
	public List<WidgetKey> getWidgetKeys(String widgetName) throws WidgetException {
		LOG.debug("LISTING WIDGET KEYS\t widgetName\t"+widgetName);
		List<WidgetKey> widgetKeys = null;
		try {
			Query searchQuery = new Query(Criteria.where("widgetName").is(widgetName));
			widgetKeys=mongoOperations.find(searchQuery, WidgetKey.class);
		} catch (Exception e) {
			LOG.error("ERROR WHILE WIDGET KEYS", e);
			throw new WidgetException("ERROR WHILE WIDGET KEYS");
		}
		return widgetKeys;
	}
	
	public void deleteWidgetKeys(String widgetName) throws WidgetException {
		LOG.debug("DELETING WIDGET KEYS\t widgetName\t"+widgetName);
		try {
			Query searchQuery = new Query(Criteria.where("widgetName").is(widgetName));
			mongoOperations.remove(searchQuery, WidgetKey.class);
		} catch (Exception e) {
			LOG.error("ERROR WHILE DELETING KEYS", e);
			throw new WidgetException("ERROR WHILE DELETING KEYS");
		}
	}
	
	public WidgetKey getWidgetKey(String widgetName,String key) throws WidgetException {
		LOG.debug("LOADING WIDGET KEY . widgetName\t" + widgetName+" key\t" + key);
		WidgetKey widgetKey;
		try {
			Query searchQuery = new Query(Criteria.where("widgetName").is(widgetName).and("key").is(key));
			widgetKey = mongoOperations.findOne(searchQuery, WidgetKey.class);
		} catch (Exception e) {
			LOG.error("ERROR WHILE LISTING WIDGETS", e);
			throw new WidgetException("ERROR WHILE LISTING WIDGETS");
		}
		return widgetKey;
	}

}
