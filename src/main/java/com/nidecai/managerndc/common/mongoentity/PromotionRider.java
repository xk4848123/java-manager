package com.nidecai.managerndc.common.mongoentity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "promotionRider")
public class PromotionRider {
	 @Id
	 private String id;
	 private Integer newUid;
	 private Integer rid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getNewUid() {
		return newUid;
	}
	public void setNewUid(Integer newUid) {
		this.newUid = newUid;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	 
}
