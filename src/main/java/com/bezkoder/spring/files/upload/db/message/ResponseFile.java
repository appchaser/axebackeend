package com.bezkoder.spring.files.upload.db.message;

import java.sql.Date;

public class ResponseFile {
	
	/* public String client;
	public String country;*/
	public String name;
	public String url;
	public String type;
	public int size;
	public String client;
	public String country;
	public String contenu;
	//public Date date;

  public ResponseFile(String name, String url ,String type, int size, String client, String country, String contenu) {
    this.name = name;
    this.url = url;
    this.type = type;
    this.size = size;
    this.client = client;
    this.country = country;
    this.contenu = contenu;
  //  this.date= date;
  }
  

  /*public ResponseFile(String client2, String country2, String name2, String fileDownloadUri, String string, int length) {
	// TODO Auto-generated constructor stub
}
*/




/*public Date getDate() {
	return date;
}


public void setDate(Date date) {
	this.date = date;
}*/


public String getClient() {
	return client;
}

public void setClient(String client) {
	this.client = client;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}
public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

}
