package org.fluidity.soap.dto;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;

//pass file

@XmlType()
@XmlAccessorType(XmlAccessType.FIELD)
public class AttachmentData {
	
	//binary data
	@XmlMimeType("application/octet-stream")
	private DataHandler imageData;

	public DataHandler getImageData() {
		return imageData;
	}

	public void setImageData(DataHandler imageData) {
		this.imageData = imageData;
	}

}
