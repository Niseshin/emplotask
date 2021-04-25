import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeTaskDescription, changeTaskPerformer, changeTaskPriority, saveTask, showTasksList } from './store/actions';

export function Task() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const tasks = useSelector(state => state.tasks);
    const taskIndex = useSelector(state => state.taskIndex);
    const taskPriority = useSelector(state => state.taskPriority);
    const taskDescription = useSelector(state => state.taskDescription);
    const taskPerformer = useSelector(state => state.taskPerformer);

    // это жуть
    let minPriority;
    let maxPriority;
    tasks.forEach((element) => {
        if (element[1] < minPriority || !minPriority) {
            minPriority = element[1];
            if (taskIndex !== element[0]) {
                --minPriority;
            }
        } else if (element[1] > maxPriority || !maxPriority) {
            maxPriority = element[1];
            if (taskIndex !== element[0]) {
                ++maxPriority;
            }
        }
    });

    return (
        <div>
            <h1>{taskIndex == null ? 'Добавление задачи' : 'Редактирование задачи №' + taskIndex}</h1>
            <div>
                <label>Описание</label>
                <textarea defaultValue={taskDescription}
                    onChange={(event) => dispatch(changeTaskDescription(event.target.value))}></textarea>
            </div>
            <div>
                <label>Ответственный</label>
                <select defaultValue={taskPerformer != null ? employees[taskPerformer][0] : ''}
                    onChange={(event) => dispatch(changeTaskPerformer(event.target.value))}>
                    <option value={'-'}></option>
                    {employees.map((value, index) => (
                        <option key={index} value={value[0]}>{value[1]}</option>
                    ))}
                </select>
            </div>
            <div>
                <label>Приоритет</label>
                <input type="number" value={taskPriority != null ? taskPriority : ''}
                    onChange={(event) => dispatch(changeTaskPriority(event.target.value, minPriority, maxPriority))}></input>
            </div>
            <div>
                <button onClick={() => deleteT()}>Удалить</button>
                <button onClick={() => saveT()}>Сохранить</button>
                <button onClick={() => dispatch(showTasksList())}>Отмена</button>
            </div>
        </div>
    );

    function saveT() {
        if (!taskPriority) {
            alert('Установите приоритет');
        } else if (!taskDescription) {
            alert('Заполните описание');
        } else if (!taskPerformer) {
            alert('Выберете исполнителя');
        } else {
            if (taskIndex == null) {
                new Promise((resolve, reject) => {
                    const request = new XMLHttpRequest();
                    request.open('POST', 'http://localhost:8080/emplotask/resources/tasks/add');
                    request.onload = () => {
                        if (request.status === 204) {
                            resolve(request.response);
                        } else {
                            reject(Error(request.statusText));
                        }
                    };
                    request.send(JSON.stringify([taskPriority.toString(), taskDescription, taskPerformer.toString()]));
                }).then(() => {
                    dispatch(saveTask());
                });
            } else {
                new Promise((resolve, reject) => {
                    const request = new XMLHttpRequest();
                    request.open('POST', 'http://localhost:8080/emplotask/resources/tasks/update');
                    request.onload = () => {
                        if (request.status === 204) {
                            resolve(request.response);
                        } else {
                            reject(Error(request.statusText));
                        }
                    };
                    request.send(JSON.stringify([taskPriority.toString(), taskDescription, taskPerformer.toString(), taskIndex.toString()]));
                }).then(() => {
                    dispatch(saveTask());
                });
            }
        }
    }

    function deleteT() {
        if (taskIndex != null) {
            new Promise((resolve, reject) => {
                const request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:8080/emplotask/resources/tasks/delete');
                request.onload = () => {
                    if (request.status === 204) {
                        resolve(request.response);
                    } else {
                        reject(Error(request.statusText));
                    }
                };
                request.send(taskIndex);
            }).then(() => {
                dispatch(saveTask());
            });
        }
    }
}
