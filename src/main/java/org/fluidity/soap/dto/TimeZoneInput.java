package org.fluidity.soap.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimeZoneInput implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String zoneName;
	
	private AttachmentData attachmentData;

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public AttachmentData getAttachmentData() {
		return attachmentData;
	}

	public void setAttachmentData(AttachmentData attachmentData) {
		this.attachmentData = attachmentData;
	}

}
