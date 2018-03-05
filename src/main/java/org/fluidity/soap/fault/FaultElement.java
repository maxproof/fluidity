package org.fluidity.soap.fault;

import java.io.Serializable;

//error messages
public class FaultElement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//error code
	private int code;
	
	//detail error message
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
