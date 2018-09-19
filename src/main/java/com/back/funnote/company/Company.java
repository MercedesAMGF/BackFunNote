package com.back.funnote.company;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
@Data @AllArgsConstructor @NoArgsConstructor
public class Company implements Serializable {

	@Id
	private String id;
	private int sequence;
	private String name;
}
