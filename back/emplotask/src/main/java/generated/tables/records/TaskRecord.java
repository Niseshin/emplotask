/*
 * This file is generated by jOOQ.
 */
package generated.tables.records;


import generated.tables.Task;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TaskRecord extends UpdatableRecordImpl<TaskRecord> implements Record4<Integer, Integer, String, Integer> {

    private static final long serialVersionUID = 1098337365;

    /**
     * Setter for <code>emplotask.task.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>emplotask.task.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>emplotask.task.priority</code>.
     */
    public void setPriority(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>emplotask.task.priority</code>.
     */
    public Integer getPriority() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>emplotask.task.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>emplotask.task.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>emplotask.task.performer_id</code>.
     */
    public void setPerformerId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>emplotask.task.performer_id</code>.
     */
    public Integer getPerformerId() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, String, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Task.TASK.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Task.TASK.PRIORITY;
    }

    @Override
    public Field<String> field3() {
        return Task.TASK.DESCRIPTION;
    }

    @Override
    public Field<Integer> field4() {
        return Task.TASK.PERFORMER_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getPriority();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public Integer component4() {
        return getPerformerId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getPriority();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public Integer value4() {
        return getPerformerId();
    }

    @Override
    public TaskRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public TaskRecord value2(Integer value) {
        setPriority(value);
        return this;
    }

    @Override
    public TaskRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public TaskRecord value4(Integer value) {
        setPerformerId(value);
        return this;
    }

    @Override
    public TaskRecord values(Integer value1, Integer value2, String value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TaskRecord
     */
    public TaskRecord() {
        super(Task.TASK);
    }

    /**
     * Create a detached, initialised TaskRecord
     */
    public TaskRecord(Integer id, Integer priority, String description, Integer performerId) {
        super(Task.TASK);

        set(0, id);
        set(1, priority);
        set(2, description);
        set(3, performerId);
    }
}
