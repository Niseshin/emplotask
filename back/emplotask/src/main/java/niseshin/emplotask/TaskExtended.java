package niseshin.emplotask;

import generated.tables.pojos.Task;

public class TaskExtended extends Task {

    private String performerName;

    public TaskExtended(Integer id, Integer priority, String description, Integer performerId, String performerName) {
        super(id, priority, description, performerId);
        this.performerName = performerName;
    }

    public String getPerformerName() {
        return performerName;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

}
