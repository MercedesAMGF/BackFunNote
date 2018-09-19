package com.back.funnote.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data @AllArgsConstructor @NoArgsConstructor
public class Task {

	@Id
	private String id;
	private String type;
	private String description;
}