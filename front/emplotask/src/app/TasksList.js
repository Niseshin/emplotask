import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { showEmployeesList, showTask, showTasksList } from './store/actions';

export function TasksList() {
    const dispatch = useDispatch();

    return (
        <div>
            <h1>Список сотрудников и задач</h1>
            <p>
                <button onClick={() => dispatch(showEmployeesList())}>Сотрудники</button>
                <button onClick={() => dispatch(showTasksList())}>Задачи</button>
                <button onClick={() => dispatch(showTask(null))}>Добавить задачу</button>
            </p>
            <div>
                <TaskTable />
            </div>
        </div>
    );
}

function TaskTable() {
    const dispatch = useDispatch();
    const tasks = useSelector(state => state.tasks);
    const isTasksLoaded = useSelector(state => state.isTasksLoaded);

    // сортировка
    let sortedTasks = tasks.slice();
    sortedTasks.sort((a, b) => {
        return b[1] - a[1];
    })

    if (isTasksLoaded) {
        return (
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Приоритет</th>
                        <th>Описание</th>
                        <th>Исполнитель</th>
                    </tr>
                </thead>
                <tbody>
                    {sortedTasks.map((value, index) => (
                        <tr key={index} onClick={() => dispatch(showTask(value[0]))}>
                            <td>{value[0]}</td>
                            <td>{value[1]}</td>
                            <td>{value[2]}</td>
                            <td>{value[4]}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    } else {
        return null;
    }
}
