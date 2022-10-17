package com.bezkoder.spring.files.upload.db.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bezkoder.spring.files.upload.db.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "FileTables")
public class FileDB implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  private String name;
  private String client;
  private String country ;
  @Temporal(TemporalType.DATE)
  private Date date ;
  
  
  public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
private String type;
  
  @Lob
  private byte[] data;
  
 /* @ManyToOne
  private clients clients;*/
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getType() {
	return type;
}
public FileDB( String name, String type, byte[] data) {
	super();

	this.name = name;
	this.type = type;
	this.data = data;
	
}
public FileDB( String name, String client, String country, String type, byte[] data,Date date) {
	super();
	
	this.name = name;
	this.client = client;
	this.country = country;
	this.date=date;
	this.type = type;
	this.data = data;
}
public String getClient() {
	return client;
}
public void setClient(String client) {
	this.client = client;
}
public FileDB() {
	super();
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public void setType(String type) {
	this.type = type;
}
public byte[] getData() {
	return data;
}
public void setData(byte[] data) {
	this.data = data;
}

/*public static long getSerialversionuid() {
	return serialVersionUID;
}*/
  
 
}
