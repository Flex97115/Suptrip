package com.supinfo.suptrip.exception;

public class ItemAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object item;
	
	public ItemAlreadyExistException(Object item) {
		this(item, "The following item already exists: " + item);
	}
	
	public ItemAlreadyExistException(Object item, String message) {
		super(message);
		this.item = item;
	}
	
	public Object getEntity() {
		return item;
	}

}
