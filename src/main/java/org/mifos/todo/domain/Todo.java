package org.mifos.todo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sachith Senarathne
 * <p>Domain object for the application</p>
 *
 */
public class Todo {

	@Id
	private String id;
	private String title;
	private String message;
	private String dateCreated;

	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getTitle() {
		return title;
	}

	@JsonProperty(required = true)
    @ApiModelProperty(notes = "Title of the TODO task", required = true)
	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	@JsonProperty(required = true)
    @ApiModelProperty(notes = "Message of the TODO task", required = true)
	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Todo() {
		// TODO Auto-generated constructor stub
	}

	public Todo(String title, String message) {

		this.title = title;
		this.message = message;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateCreated = dateFormat.format(new Date());
	}
}
