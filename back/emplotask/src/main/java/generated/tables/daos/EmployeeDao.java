/*
 * This file is generated by jOOQ.
 */
package generated.tables.daos;


import generated.tables.Employee;
import generated.tables.records.EmployeeRecord;

import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EmployeeDao extends DAOImpl<EmployeeRecord, generated.tables.pojos.Employee, Integer> {

    /**
     * Create a new EmployeeDao without any configuration
     */
    public EmployeeDao() {
        super(Employee.EMPLOYEE, generated.tables.pojos.Employee.class);
    }

    /**
     * Create a new EmployeeDao with an attached configuration
     */
    public EmployeeDao(Configuration configuration) {
        super(Employee.EMPLOYEE, generated.tables.pojos.Employee.class, configuration);
    }

    @Override
    public Integer getId(generated.tables.pojos.Employee object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Employee> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Employee.EMPLOYEE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<generated.tables.pojos.Employee> fetchById(Integer... values) {
        return fetch(Employee.EMPLOYEE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public generated.tables.pojos.Employee fetchOneById(Integer value) {
        return fetchOne(Employee.EMPLOYEE.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Employee> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Employee.EMPLOYEE.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<generated.tables.pojos.Employee> fetchByName(String... values) {
        return fetch(Employee.EMPLOYEE.NAME, values);
    }

    /**
     * Fetch records that have <code>post BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Employee> fetchRangeOfPost(String lowerInclusive, String upperInclusive) {
        return fetchRange(Employee.EMPLOYEE.POST, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>post IN (values)</code>
     */
    public List<generated.tables.pojos.Employee> fetchByPost(String... values) {
        return fetch(Employee.EMPLOYEE.POST, values);
    }

    /**
     * Fetch records that have <code>branch BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Employee> fetchRangeOfBranch(String lowerInclusive, String upperInclusive) {
        return fetchRange(Employee.EMPLOYEE.BRANCH, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>branch IN (values)</code>
     */
    public List<generated.tables.pojos.Employee> fetchByBranch(String... values) {
        return fetch(Employee.EMPLOYEE.BRANCH, values);
    }

    /**
     * Fetch records that have <code>boss_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.tables.pojos.Employee> fetchRangeOfBossId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Employee.EMPLOYEE.BOSS_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>boss_id IN (values)</code>
     */
    public List<generated.tables.pojos.Employee> fetchByBossId(Integer... values) {
        return fetch(Employee.EMPLOYEE.BOSS_ID, values);
    }
}
