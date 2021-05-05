import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useStyles } from '../styles';
import { changeEmployeeBoss, changeEmployeeBranch, changeEmployeeName, changeEmployeePost, saveEmployee, showEmployeeDeleteImpossible, showEmployeesList } from './store/actions';

export function Employee() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const employeeIndex = useSelector(state => state.employeeIndex);
    const employeeName = useSelector(state => state.employeeName);
    const employeePost = useSelector(state => state.employeePost);
    const employeeBranch = useSelector(state => state.employeeBranch);
    const employeeBoss = useSelector(state => state.employeeBoss);
    const isEmployeeDeleteImpossible = useSelector(state => state.isEmployeeDeleteImpossible);
    const classes = useStyles();

    return (
        <div>
            <h1 className={classes.title}>{employeeIndex == null ? 'Добавление сотрудника' : 'Редактирование сотрудника №' + employeeIndex}</h1>
            <div>
                <label className={classes.label}>ФИО</label>
                <input className={classes.input}
                    defaultValue={employeeName}
                    onChange={(event) => dispatch(changeEmployeeName(event.target.value))}></input>
                {!employeeName ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Должность</label>
                <input className={classes.input}
                    defaultValue={employeePost}
                    onChange={(event) => dispatch(changeEmployeePost(event.target.value))}></input>
                {!employeePost ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Филиал</label>
                <input className={classes.input}
                    defaultValue={employeeBranch}
                    onChange={(event) => dispatch(changeEmployeeBranch(event.target.value))}></input>
                {!employeeBranch ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Руководитель</label>
                <select className={classes.select}
                    defaultValue={employeeBoss != null ? employees[employeeBoss][0] : ''}
                    onChange={(event) => dispatch(changeEmployeeBoss(event.target.value))}>
                    <option value={'-'}></option>
                    {employees.map((value, index) => (
                        <option key={index} value={value[0]}>{value[1]}</option>
                    ))}
                </select>
            </div>
            <div>
                <button className={classes.deleteButton}
                    disabled={employeeIndex == null ? true : false}
                    onClick={() => deleteE()}>Удалить</button>
                <button className={classes.saveButton}
                    disabled={!employeeName || !employeePost || !employeeBranch ? true : false}
                    onClick={() => saveE()}>Сохранить</button>
                <button className={classes.cancelButton}
                    onClick={() => dispatch(showEmployeesList())}>Отмена</button>
            </div>
            {isEmployeeDeleteImpossible ? <label className={classes.labelWarning}>{'Удаление невозможно'}</label> : null}
        </div>
    );

    function saveE() {
        if (employeeIndex == null) {
            new Promise((resolve, reject) => {
                const request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:8080/emplotask/resources/employees/add');
                request.setRequestHeader("Content-Type", "application/json");
                request.onload = () => {
                    if (request.status === 204) {
                        resolve(request.response);
                    } else {
                        reject(Error(request.statusText));
                    }
                };
                request.send(JSON.stringify({ "id": employeeIndex, "name": employeeName, "post": employeePost, "branch": employeeBranch, "bossId": employeeBoss }));
            }).then(() => {
                dispatch(saveEmployee());
            });
        } else {
            new Promise((resolve, reject) => {
                const request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:8080/emplotask/resources/employees/update');
                request.setRequestHeader("Content-Type", "application/json");
                request.onload = () => {
                    if (request.status === 204) {
                        resolve(request.response);
                    } else {
                        reject(Error(request.statusText));
                    }
                };
                request.send(JSON.stringify({ "id": employeeIndex, "name": employeeName, "post": employeePost, "branch": employeeBranch, "bossId": employeeBoss }));
            }).then(() => {
                dispatch(saveEmployee());
            });
        }
    }

    function deleteE() {
        if (employeeIndex != null) {
            new Promise((resolve, reject) => {
                const request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:8080/emplotask/resources/employees/delete');
                request.setRequestHeader("Content-Type", "application/json");
                request.onload = () => {
                    if (request.status === 204) {
                        resolve(204);
                    } else if (request.status === 400) {
                        resolve(400);
                    } else {
                        reject(Error(request.statusText));
                    }
                };
                request.send(JSON.stringify({ "id": employeeIndex, "name": employeeName, "post": employeePost, "branch": employeeBranch, "bossId": employeeBoss }));
            }).then((response) => {
                if (response === 204) {
                    dispatch(saveEmployee());
                } else {
                    dispatch(showEmployeeDeleteImpossible());
                }
            });
        }
    }
}
