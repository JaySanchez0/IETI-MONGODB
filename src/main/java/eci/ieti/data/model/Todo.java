package eci.ieti.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Todo")
public class Todo {

    @Id
    private String id;

    private Date dueDate;

   @DBRef(lazy = true)
    private User responsible;

    private String description;

    private int priority;

    public Todo(){

    }

    public Todo(Date dueDate,String description,int priorty){
        this.dueDate = dueDate;
        this.description = description;
        this.priority = priorty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }
}
