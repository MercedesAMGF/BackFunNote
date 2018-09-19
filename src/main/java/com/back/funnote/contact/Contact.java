package com.back.funnote.contact;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
@Data @AllArgsConstructor @NoArgsConstructor
public class Contact implements Serializable {

	@Id
	private String id;
	private int sequence;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String job;
	private String company;
	private String city;
	private String zip;
	private String country;
}
