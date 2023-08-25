package dev.satvik.Neo4J.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

// Models are the representation of the data that we're working with.
// Used to transfer data between different components of an app

@Node
public class Course {
	@Id @GeneratedValue
	private Long id;
	private String identifier;
	private String title;
	private String teacher;

	public Course() {
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructor() {
		return teacher;
	}

	public void setInstructor(String instructor) {
		this.teacher = instructor;
	}


}
