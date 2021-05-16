import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Employee } from './app/Employee';
import { EmployeesList } from './app/EmployeesList';
import { loadEmployeesList, loadTasksList } from './app/store/actions';
import { Task } from './app/Task';
import { TasksList } from './app/TasksList';

export function App() {
    const dispatch = useDispatch();
    const page = useSelector(state => state.page);
    const isEmloyeesLoaded = useSelector(state => state.isEmloyeesLoaded);
    const isTasksLoaded = useSelector(state => state.isTasksLoaded);

    // вывод в консоль состаяния при каждой перерисовке
    const state = useSelector(state => state);
    console.log(state);

    if (!isEmloyeesLoaded) {
        fetch('http://localhost:8080/emplotask/resources/employees/listex', {
            method: 'GET'
        }).then((response) => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error(response.statusText);
            }
        }).then((result) => dispatch(loadEmployeesList(result)));
    }

    if (isEmloyeesLoaded && !isTasksLoaded) {
        fetch('http://localhost:8080/emplotask/resources/tasks/listex', {
            method: 'GET'
        }).then((response) => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error(response.statusText);
            }
        }).then((result) => dispatch(loadTasksList(result)));
    }

    switch (page) {
        case 'employeesList':
            return (
                <EmployeesList />
            );
        case 'tasksList':
            return (
                <TasksList />
            );
        case 'employee':
            return (
                <Employee />
            );
        case 'task':
            return (
                <Task />
            );
        default:
            return (
                <h2>Ошибочка!</h2>
            );
    }
}
