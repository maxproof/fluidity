package org.fluidity.soap.fault;

import javax.xml.ws.WebFault;

//description error to soap message

@WebFault(name="faultElement", faultBean="org.fluidity.soap.fault.FaultElement")
public class FaultMessage extends Exception {

	private static final long serialVersionUID = 1L;
	
	private FaultElement fault;
	
	public FaultMessage() {}
	
	public FaultMessage(FaultElement fault) {
		super(fault.getMessage());
		this.fault = fault;
	}
	
	public FaultMessage(String message, FaultElement fault) {
		super(message);
		this.fault = fault;
	}

}
