package com.example.hellospringboot;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SoftwareEngineer {

    /** The unique identifier for the software engineer. */
    @Id
    private Integer id;
    /** The name of the software engineer. */
    private String name;
    /**  The technology stack that the software engineer specializes in. */
    private String techStack;

    /**
     * Default constructor for SoftwareEngineer.
     * Initializes a new instance with default values.
     */
    public SoftwareEngineer(Integer id, String name, String techStack) {
        this.id = id;
        this.name = name;
        this.techStack = techStack;
    }

    // Getters and Setters
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getTechStack() {return techStack;}
    public void setTechStack(String techStack) {this.techStack = techStack;}

    @Override
    /**
     * Compares this SoftwareEngineer object with another object for equality.
     * Two SoftwareEngineer objects are considered equal if they have the same id, name, and
     * techStack.
     * 
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(techStack, that.techStack);
    }

    @Override
    /**
     * Generates a hash code for this SoftwareEngineer object.
     * The hash code is based on the id, name, and techStack fields.
     * 
     * @return the hash code value
     */
    public int hashCode() {
        return Objects.hash(id, name, techStack);
    }
}
