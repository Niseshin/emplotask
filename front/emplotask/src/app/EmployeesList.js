import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { showEmployee, showEmployeesList, showTasksList } from './store/actions';

export function EmployeesList() {
    const dispatch = useDispatch();

    return (
        <div>
            <h1>Список сотрудников и задач</h1>
            <p>
                <button onClick={() => dispatch(showEmployeesList())}>Сотрудники</button>
                <button onClick={() => dispatch(showTasksList())}>Задачи</button>
                <button onClick={() => dispatch(showEmployee(null))}>Добавить сотрудника</button>
            </p>
            <div>
                <EmployeeTable />
            </div>
        </div>
    );
}

function EmployeeTable() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const tasks = useSelector(state => state.tasks);
    const isEmloyeesLoaded = useSelector(state => state.isEmloyeesLoaded);
    const isTasksLoaded = useSelector(state => state.isTasksLoaded);

    // считаем задачи
    let taskCount = [];
    tasks.forEach((element) => {
        taskCount[element[3]] = taskCount[element[3]] ? ++taskCount[element[3]] : 1;
    });

    if (isEmloyeesLoaded && isTasksLoaded) {
        return (
            <table>
                <thead>
                    <tr>
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
                        <tr key={index} onClick={() => dispatch(showEmployee(index))}>
                            <td>{value[0]}</td>
                            <td>{value[1]}</td>
                            <td>{value[2]}</td>
                            <td>{value[3]}</td>
                            <td>{value[4] ? employees[value[4]][1] : ''}</td>
                            <td>{taskCount[index] ? taskCount[index] : 0}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    } else {
        return null;
    }
}
