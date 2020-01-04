package com.javaprojects.course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.javaprojects.topic.Topic;

@Entity
public class Course {
	
	@Id
	private int id;
	private String name;
	private String description;
	
	@ManyToOne
	private Topic topic;
	
	public Course() {}
	
	public Course(int id, String name, String desc, int topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = desc;
		this.topic = new Topic(topicId, "", "");
	}
	
	public Course(String name, String desc) {
		super();
		this.name = name;
		this.description = desc;
	}
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
