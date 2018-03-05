package org.fluidity.soap.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

import org.fluidity.soap.dto.TimeZoneInput;
import org.fluidity.soap.fault.FaultMessage;

// web service endpoint interface

@MTOM(enabled = true) //mark this as ws endpoint interface
@WebService
public interface DateTimeZoneService {
	
	@WebMethod(action = "timeZoneAction")
	String getDateTimeByZone(@WebParam(name = "TimeZone") TimeZoneInput zone) throws FaultMessage;

}
