package com.cricmania.models;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.MongoException;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

public class MetaDataDocument extends Base {
	private Map<String,Long> table;
	
	private static JacksonDBCollection<MetaDataDocument,String> coll = MongoDB.getCollection(MetaDataDocument.class.getSimpleName(), MetaDataDocument.class, String.class);
	
	public Map<String, Long> getTable() {
		return table;
	}

	public void setTable(Map<String, Long> table) {
		this.table = table;
	}
	
	public static MetaDataDocument getMetaDataDocument() {
		MetaDataDocument document = null;
		try {
			document = coll.findOneById("1");
		} catch(MongoException e) {
			document = new MetaDataDocument();
			document.setId("1");
			document.setTable(new HashMap<String,Long>());
			coll.save(document);
		}
		if(document == null) {
			document = new MetaDataDocument();
			document.setId("1");
			document.setTable(new HashMap<String,Long>());
			coll.save(document);
		}
		return document;
	}
	
	public void update() {
		coll.updateById(this.id,this);
	}
	
	public void save() {
		coll.save(this);
	}
	
	public static String getGeneratedId(String className) {
		MetaDataDocument document = MetaDataDocument.getMetaDataDocument();
		Long id = document.getTable().get(className);
		if(id == null) {
			id = 1l;
		} else {
			id++;
		}
		document.getTable().put(className, id);
		document.update();
		return id.toString();
	}
}
