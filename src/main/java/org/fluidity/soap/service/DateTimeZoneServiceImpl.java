package org.fluidity.soap.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import javax.jws.WebService;

import org.fluidity.soap.dto.TimeZoneInput;
import org.fluidity.soap.fault.FaultElement;
import org.fluidity.soap.fault.FaultMessage;

import org.apache.commons.io.IOUtils;

@WebService(endpointInterface = "org.fluidity.soap.service.DateTimeZoneService", serviceName = "DateTimeZoneService")
public class DateTimeZoneServiceImpl implements DateTimeZoneService {

	//in case of app error
	private Integer errorCode = 1000;
	
	//content attch' file
	private String serverAttachmentFile = "D:/testsFiles/AttachmentContents.txt";

	//fetch date time
	@Override
	public String getDateTimeByZone(TimeZoneInput zone) throws FaultMessage {
		System.out.println("getDateTimeByZone() - Start");
		String zoneName = zone.getZoneName();
		System.out.println("getDateTimeByZone() - Getting Date & Time for Zone Name " + zoneName);
		Map<String, String> SHORT_IDS = ZoneId.SHORT_IDS;
		if(!SHORT_IDS.containsValue(zoneName)) {
			FaultElement faultElement = new FaultElement();
			faultElement.setCode(errorCode);
			faultElement.setMessage(("Incorrect Zone name provided. Supported zone zone names are: " + SHORT_IDS));
			throw new FaultMessage(faultElement);
		}
		ZonedDateTime zonedDateTime = getDateTime(zoneName);
		System.out.println("getDateTimeByZone() - Current Date & Time ::::: " + zonedDateTime);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(serverAttachmentFile);
			fw = new FileWriter(file.getAbsolutePath());
			bw = new BufferedWriter(fw);
			String stringToWrite = IOUtils.toString(zone.getAttachmentData().getImageData().getInputStream(), "UTF-8");
			bw.write(stringToWrite);
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fw) {
					fw.close();
				}
				if (null != bw) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(zonedDateTime);
	}
	
	private ZonedDateTime getDateTime(String zoneName) {
		ZoneId zoneId = ZoneId.of(zoneName);
		ZonedDateTime dateTime = java.time.ZonedDateTime.now(zoneId);
		return dateTime;
	}
	
}
