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
        new Promise((resolve, reject) => {
            const request = new XMLHttpRequest();
            request.open('GET', 'http://localhost:8080/emplotask/resources/employees');
            request.onload = () => {
                if (request.status === 200) {
                    resolve(request.response);
                } else {
                    reject(Error(request.statusText));
                }
            };
            request.send();
        }).then(result => {
            dispatch(loadEmployeesList(JSON.parse(result).records));
        });
    }

    if (!isTasksLoaded) {
        new Promise((resolve, reject) => {
            const request = new XMLHttpRequest();
            request.open('GET', 'http://localhost:8080/emplotask/resources/tasks');
            request.onload = () => {
                if (request.status === 200) {
                    resolve(request.response);
                } else {
                    reject(Error(request.statusText));
                }
            };
            request.send();
        }).then(result => {
            dispatch(loadTasksList(JSON.parse(result).records));
        });
    }

    // все рисуется на одной странице, хорошо ли это? *задумчивый смайлик*
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
