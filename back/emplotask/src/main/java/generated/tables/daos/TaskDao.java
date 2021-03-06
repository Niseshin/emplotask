/*
 * This file is generated by jOOQ.
 */
package generated.tables.daos;


import generated.tables.Task;
import generated.tables.records.TaskRecord;

import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TaskDao extends DAOImpl<TaskRecord, generated.tables.pojos.Task, Integer> {

    /**
     * Create a new TaskDao without any configuration
     */
    public TaskDao() {
        super(Task.TASK, generated.tables.pojos.Task.class);
    }

    /**
     * Create a new TaskDao with an attached configuration
     */
    public TaskDao(Configuration configuration) {
        super(Task.TASK, generated.tables.pojos.Task.class, configuration);
    }

    @Override
    public Integer getId(generated.tables.pojos.Task object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Task> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Task.TASK.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<generated.tables.pojos.Task> fetchById(Integer... values) {
        return fetch(Task.TASK.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public generated.tables.pojos.Task fetchOneById(Integer value) {
        return fetchOne(Task.TASK.ID, value);
    }

    /**
     * Fetch records that have <code>priority BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Task> fetchRangeOfPriority(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Task.TASK.PRIORITY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>priority IN (values)</code>
     */
    public List<generated.tables.pojos.Task> fetchByPriority(Integer... values) {
        return fetch(Task.TASK.PRIORITY, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Task> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Task.TASK.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<generated.tables.pojos.Task> fetchByDescription(String... values) {
        return fetch(Task.TASK.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>performer_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Task> fetchRangeOfPerformerId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Task.TASK.PERFORMER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>performer_id IN (values)</code>
     */
    public List<generated.tables.pojos.Task> fetchByPerformerId(Integer... values) {
        return fetch(Task.TASK.PERFORMER_ID, values);
    }
}
