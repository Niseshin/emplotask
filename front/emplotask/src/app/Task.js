import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useStyles } from '../styles';
import { changeTaskDescription, changeTaskPerformer, changeTaskPriority, saveTask, showTasksList } from './store/actions';

export function Task() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const tasks = useSelector(state => state.tasks);
    const taskIndex = useSelector(state => state.taskIndex);
    const taskPriority = useSelector(state => state.taskPriority);
    const taskDescription = useSelector(state => state.taskDescription);
    const taskPerformer = useSelector(state => state.taskPerformer);
    const classes = useStyles();

    // жуткий способ определить максимальный и минимальный приоритет
    let minPriority;
    let maxPriority;
    tasks.forEach((element) => {
        if (element[1] <= minPriority || minPriority === undefined) {
            minPriority = element[1];
            if (taskIndex !== element[0]) {
                --minPriority;
            }
        }
        if (element[1] >= maxPriority || maxPriority === undefined) {
            maxPriority = element[1];
            if (taskIndex !== element[0]) {
                ++maxPriority;
            }
        }
    });

    return (
        <div>
            <h1 className={classes.title}>{taskIndex == null ? 'Добавление задачи' : 'Редактирование задачи №' + taskIndex}</h1>
            <div>
                <label className={classes.label}>Описание</label>
                <textarea className={classes.textArea}
                    defaultValue={taskDescription}
                    onChange={(event) => dispatch(changeTaskDescription(event.target.value))}></textarea>
                {!taskDescription ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Приоритет</label>
                <input className={classes.inputNumber}
                    type="number" value={taskPriority != null ? taskPriority : ''}
                    onChange={(event) => dispatch(changeTaskPriority(event.target.value, minPriority, maxPriority))}></input>
                {taskPriority === null ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Ответственный</label>
                <select className={classes.select}
                    defaultValue={taskPerformer != null ? employees[taskPerformer][0] : ''}
                    onChange={(event) => dispatch(changeTaskPerformer(event.target.value))}>
                    <option value={'-'}></option>
                    {employees.map((value, index) => (
                        <option key={index} value={value[0]}>{value[1]}</option>
                    ))}</select>
                {taskPerformer === null ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <button className={classes.deleteButton}
                    onClick={() => deleteT()}
                    disabled={taskIndex == null ? true : false}>Удалить</button>
                <button className={classes.saveButton}
                    disabled={!taskDescription || !taskPriority || !taskPerformer ? true : false}
                    onClick={() => saveT()}>Сохранить</button>
                <button className={classes.cancelButton}
                    onClick={() => dispatch(showTasksList())}>Отмена</button>
            </div>
        </div>
    );

    function saveT() {
        if (taskIndex == null) {
            new Promise((resolve, reject) => {
                const request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:8080/emplotask/resources/tasks/add');
                request.setRequestHeader("Content-Type", "application/json");
                request.onload = () => {
                    if (request.status === 204) {
                        resolve(request.response);
                    } else {
                        reject(Error(request.statusText));
                    }
                };
                request.send(JSON.stringify({ "id": taskIndex, "priority": taskPriority, "description": taskDescription, "performerId": taskPerformer }));
            }).then(() => {
                dispatch(saveTask());
            });
        } else {
            new Promise((resolve, reject) => {
                const request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:8080/emplotask/resources/tasks/update');
                request.setRequestHeader("Content-Type", "application/json");
                request.onload = () => {
                    if (request.status === 204) {
                        resolve(request.response);
                    } else {
                        reject(Error(request.statusText));
                    }
                };
                request.send(JSON.stringify({ "id": taskIndex, "priority": taskPriority, "description": taskDescription, "performerId": taskPerformer }));
            }).then(() => {
                dispatch(saveTask());
            });
        }
    }

    function deleteT() {
        if (taskIndex != null) {
            new Promise((resolve, reject) => {
                const request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:8080/emplotask/resources/tasks/delete');
                request.setRequestHeader("Content-Type", "application/json");
                request.onload = () => {
                    if (request.status === 204) {
                        resolve(request.response);
                    } else {
                        reject(Error(request.statusText));
                    }
                };
                request.send(JSON.stringify({ "id": taskIndex, "priority": taskPriority, "description": taskDescription, "performerId": taskPerformer }));
            }).then(() => {
                dispatch(saveTask());
            });
        }
    }
}
