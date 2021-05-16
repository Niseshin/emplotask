package niseshin.emplotask;

import generated.tables.pojos.Employee;

public class EmployeeExtended extends Employee {

    private String bossName;
    private Integer taskCount;

    public EmployeeExtended(Integer id, String name, String post, String branch, Integer bossId, String bossName, Integer taskCount) {
        super(id, name, post, branch, bossId);
        this.bossName = bossName;
        this.taskCount = taskCount;
    }

    public EmployeeExtended(Integer id, Integer taskCount) {
        super(id, null, null, null, null);
        this.taskCount = taskCount;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

}
