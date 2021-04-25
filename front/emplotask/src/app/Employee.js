import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeEmployeeBoss, changeEmployeeBranch, changeEmployeeName, changeEmployeePost, saveEmployee, showEmployeesList } from './store/actions';

export function Employee() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const tasks = useSelector(state => state.tasks);
    const employeeIndex = useSelector(state => state.employeeIndex);
    const employeeName = useSelector(state => state.employeeName);
    const employeePost = useSelector(state => state.employeePost);
    const employeeBranch = useSelector(state => state.employeeBranch);
    const employeeBoss = useSelector(state => state.employeeBoss);

    return (
        <div>
            <h1>{employeeIndex == null ? 'Добавление сотрудника' : 'Редактирование сотрудника №' + employeeIndex}</h1>
            <div>
                <label>ФИО</label>
                <input defaultValue={employeeName}
                    onChange={(event) => dispatch(changeEmployeeName(event.target.value))}></input>
            </div>
            <div>
                <label>Должность</label>
                <input defaultValue={employeePost}
                    onChange={(event) => dispatch(changeEmployeePost(event.target.value))}></input>
            </div>
            <div>
                <label>Филиал</label>
                <input defaultValue={employeeBranch}
                    onChange={(event) => dispatch(changeEmployeeBranch(event.target.value))}></input>
            </div>
            <div>
                <label>Руководитель</label>
                <select defaultValue={employeeBoss != null ? employees[employeeBoss][0] : ''}
                    onChange={(event) => dispatch(changeEmployeeBoss(event.target.value))}>
                    <option value={'-'}></option>
                    {employees.map((value, index) => (
                        <option key={index} value={value[0]}>{value[1]}</option>
                    ))}
                </select>
            </div>
            <div>
                <button onClick={() => deleteE()}>Удалить</button>
                <button onClick={() => saveE()}>Сохранить</button>
                <button onClick={() => dispatch(showEmployeesList())}>Отмена</button>
            </div>
        </div>
    );

    function saveE() {
        if (!employeeName) {
            alert('Заполните имя');
        } else if (!employeePost) {
            alert('Заполните должность');
        } else if (!employeeBranch) {
            alert('Заполните филиал');
        } else {
            if (employeeIndex == null) {
                new Promise((resolve, reject) => {
                    const request = new XMLHttpRequest();
                    request.open('POST', 'http://localhost:8080/emplotask/resources/employees/add');
                    request.onload = () => {
                        if (request.status === 204) {
                            resolve(request.response);
                        } else {
                            reject(Error(request.statusText));
                        }
                    };
                    request.send(JSON.stringify([employeeName, employeePost, employeeBranch, employeeBoss != null ? employeeBoss.toString() : 'null']));
                }).then(() => {
                    dispatch(saveEmployee());
                });
            } else {
                new Promise((resolve, reject) => {
                    const request = new XMLHttpRequest();
                    request.open('POST', 'http://localhost:8080/emplotask/resources/employees/update');
                    request.onload = () => {
                        if (request.status === 204) {
                            resolve(request.response);
                        } else {
                            reject(Error(request.statusText));
                        }
                    };
                    request.send(JSON.stringify([employeeName, employeePost, employeeBranch, employeeBoss != null ? employeeBoss.toString() : 'null', employeeIndex.toString()]));
                }).then(() => {
                    dispatch(saveEmployee());
                });
            }
        }
    }

    function deleteE() {
        if (employeeIndex != null) {
            let isBoss = false;
            let isPerformer = false;
            employees.forEach((e) => {
                if (e[4] === employeeIndex) {
                    isBoss = true;
                }
            });
            tasks.forEach((t) => {
                if (t[3] === employeeIndex) {
                    isPerformer = true;
                }
            });
            if (isBoss) {
                alert('Разберитесь с подчиненными');
            } else if (isPerformer) {
                alert('Закончите задачи');
            } else {
                new Promise((resolve, reject) => {
                    const request = new XMLHttpRequest();
                    request.open('POST', 'http://localhost:8080/emplotask/resources/employees/delete');
                    request.onload = () => {
                        if (request.status === 204) {
                            resolve(request.response);
                        } else {
                            reject(Error(request.statusText));
                        }
                    };
                    request.send(employeeIndex);
                }).then(() => {
                    dispatch(saveEmployee());
                });
            }
        }
    }
}
