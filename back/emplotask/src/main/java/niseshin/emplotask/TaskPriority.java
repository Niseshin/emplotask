package niseshin.emplotask;

public class TaskPriority {

    private Integer minPriority;
    private Integer minPriorityTaskId;
    private Integer maxPriority;
    private Integer maxPriorityTaskId;

    public TaskPriority() {
    }

    public TaskPriority(int minPriority, int minPriorityTaskId, int maxPriority, int maxPriorityTaskId) {
        this.minPriority = minPriority;
        this.minPriorityTaskId = minPriorityTaskId;
        this.maxPriority = maxPriority;
        this.maxPriorityTaskId = maxPriorityTaskId;
    }

    public Integer getMinPriority() {
        return minPriority;
    }

    public void setMinPriority(Integer minPriority) {
        this.minPriority = minPriority;
    }

    public Integer getMinPriorityTaskId() {
        return minPriorityTaskId;
    }

    public void setMinPriorityTaskId(Integer minPriorityTaskId) {
        this.minPriorityTaskId = minPriorityTaskId;
    }

    public Integer getMaxPriority() {
        return maxPriority;
    }

    public void setMaxPriority(Integer maxPriority) {
        this.maxPriority = maxPriority;
    }

    public Integer getMaxPriorityTaskId() {
        return maxPriorityTaskId;
    }

    public void setMaxPriorityTaskId(Integer maxPriorityTaskId) {
        this.maxPriorityTaskId = maxPriorityTaskId;
    }

}
