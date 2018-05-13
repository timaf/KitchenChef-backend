package at.refugeesCode.kitchenchefbackend.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Assistants {

    @Id
    private String id;
    private String assistant;


    public Assistants(String assistant) {
        this.assistant = assistant;
    }

    public Assistants() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    @Override
    public String toString() {
        return "Assistants{" +
                "id='" + id + '\'' +
                ", assistant='" + assistant + '\'' +
                '}';
    }
}
