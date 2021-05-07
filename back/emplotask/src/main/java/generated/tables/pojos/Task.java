/*
 * This file is generated by jOOQ.
 */
package generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Task implements Serializable {

    private static final long serialVersionUID = -2126022745;

    private Integer id;
    private Integer priority;
    private String  description;
    private Integer performerId;

    public Task() {}

    public Task(Task value) {
        this.id = value.id;
        this.priority = value.priority;
        this.description = value.description;
        this.performerId = value.performerId;
    }

    public Task(
        Integer id,
        Integer priority,
        String  description,
        Integer performerId
    ) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.performerId = performerId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPerformerId() {
        return this.performerId;
    }

    public void setPerformerId(Integer performerId) {
        this.performerId = performerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Task (");

        sb.append(id);
        sb.append(", ").append(priority);
        sb.append(", ").append(description);
        sb.append(", ").append(performerId);

        sb.append(")");
        return sb.toString();
    }
}