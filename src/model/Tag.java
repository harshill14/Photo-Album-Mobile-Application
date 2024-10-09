package model;

import java.io.Serializable;


public class Tag implements Serializable {


	private String tagName;

	private String tagValue;
	
	
	public Tag(String tagName, String tagValue) {
		
		this.tagName = tagName;
		this.tagValue = tagValue;
	}
	
	public String getTag() {
		
		return "(" + this.tagName + ", " + this.tagValue + ")";
	}
	
	/**
	 * Returns the name of the tag.
	 * 
	 * @return the name of the tag
	 */
	public String getTagName() {
		
		return this.tagName;
	}
	
	/**
	 * Returns the value of the tag
	 * 
	 * @return the value of the tag
	 */
	public String getTagValue() {
		
		return this.tagValue;
	}
	
	/**
	 * Sets the name of the tag.
	 * 
	 * @param tagName the new name of the tag
	 */
	public void setTagName(String tagName) {
		
		this.tagName = tagName;
	}
	
	/**
	 * Sets the value of the tag.
	 * 
	 * @param tagValue the new value of the tag
	 */
	public void setTagValue(String tagValue) {
		
		this.tagValue = tagValue;
	}
}
