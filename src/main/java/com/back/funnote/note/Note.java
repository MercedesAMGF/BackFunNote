package com.back.funnote.note;

import java.io.Serializable;
import java.util.Date;

import com.back.funnote.contact.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
@Data @AllArgsConstructor @NoArgsConstructor
class Note implements Serializable {

	@Id
	private String id;
	private int sequence;
	private Date date;
	private String type;
	private Contact contact;
	private String message;

}
