import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useStyles } from '../styles';
import { showEmployeesList, showTask, showTasksList } from './store/actions';

export function TasksList() {
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
                    onClick={() => dispatch(showTask(null))}>Добавить задачу</button>
            </div>
            <TaskTable />
        </div>
    );
}

function TaskTable() {
    const dispatch = useDispatch();
    const tasks = useSelector(state => state.tasks);
    const isTasksLoaded = useSelector(state => state.isTasksLoaded);
    const classes = useStyles();

    if (isTasksLoaded) {
        return (
            <table className={classes.table}>
                <thead>
                    <tr className={classes.titleRow}>
                        <th>ID</th>
                        <th>Приоритет</th>
                        <th>Описание</th>
                        <th>Исполнитель</th>
                    </tr>
                </thead>
                <tbody>
                    {tasks.map((value, index) => (
                        <tr key={index} onClick={() => dispatch(showTask({ ...value }))}>
                            <td>{value.id}</td>
                            <td>{value.priority}</td>
                            <td>{value.description}</td>
                            <td>{value.performerName}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    } else {
        return null;
    }
}
