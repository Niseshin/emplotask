import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useStyles } from '../styles';
import { showEmployee, showEmployeesList, showTasksList } from './store/actions';

export function EmployeesList() {
    const dispatch = useDispatch();
    const page = useSelector(state => state.page);
    const classes = useStyles(page);

    return (
        <div>
            <h1 className={classes.title}>Список сотрудников и задач</h1>
            <div>
                <button className={classes.tabButtonEmployees}
                    onClick={() => dispatch(showEmployeesList())}>Сотрудники</button>
                <button className={classes.tabButtonTasks}
                    onClick={() => dispatch(showTasksList())}>Задачи</button>
                <button className={classes.addButton}
                    onClick={() => dispatch(showEmployee(null))}>Добавить сотрудника</button>
            </div>
            <EmployeeTable />
        </div>
    );
}

function EmployeeTable() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const isEmloyeesLoaded = useSelector(state => state.isEmloyeesLoaded);
    const classes = useStyles();

    if (isEmloyeesLoaded) {
        return (
            <table className={classes.table}>
                <thead>
                    <tr className={classes.titleRow}>
                        <th>ID</th>
                        <th>Имя</th>
                        <th>Должность</th>
                        <th>Филиал</th>
                        <th>Руководитель</th>
                        <th>Количество задач</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((value, index) => (
                        <tr key={index} onClick={() => dispatch(showEmployee({ ...value }))}>
                            <td>{value.id}</td>
                            <td>{value.name}</td>
                            <td>{value.post}</td>
                            <td>{value.branch}</td>
                            <td>{value.bossName}</td>
                            <td>{value.taskCount}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    } else {
        return null;
    }
}
