import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useStyles } from '../styles';
import { saveEmployee, showEmployee, showEmployeeDeleteImpossible, showEmployeesList } from './store/actions';

export function Employee() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const employee = useSelector(state => state.employee);
    const isEmployeeDeleteImpossible = useSelector(state => state.isEmployeeDeleteImpossible);
    const classes = useStyles();

    return (
        <div>
            <h1 className={classes.title}>{employee.id == null ? 'Добавление сотрудника' : 'Редактирование сотрудника №' + employee.id}</h1>
            <div>
                <label className={classes.label}>ФИО</label>
                <input className={classes.input}
                    defaultValue={employee.name}
                    onChange={(event) => dispatch(showEmployee({ ...employee, name: event.target.value }))}></input>
                {!employee.name ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Должность</label>
                <input className={classes.input}
                    defaultValue={employee.post}
                    onChange={(event) => dispatch(showEmployee({ ...employee, post: event.target.value }))}></input>
                {!employee.post ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Филиал</label>
                <input className={classes.input}
                    defaultValue={employee.branch}
                    onChange={(event) => dispatch(showEmployee({ ...employee, branch: event.target.value }))}></input>
                {!employee.branch ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Руководитель</label>
                <select className={classes.select}
                    defaultValue={employee.bossId}
                    onChange={(event) => dispatch(showEmployee({ ...employee, bossId: parseInt(event.target.value) ? parseInt(event.target.value) : null }))}>
                    <option value={null}></option>
                    {employees.map((value, index) => (
                        <option key={index} value={value.id}>{value.name}</option>
                    ))}
                </select>
            </div>
            <div>
                <button className={classes.deleteButton}
                    disabled={employee.id ? false : true}
                    onClick={() => deleteE()}>Удалить</button>
                <button className={classes.saveButton}
                    disabled={!employee || !employee.name || !employee.post || !employee.branch ? true : false}
                    onClick={() => saveE()}>Сохранить</button>
                <button className={classes.cancelButton}
                    onClick={() => dispatch(showEmployeesList())}>Отмена</button>
            </div>
            {isEmployeeDeleteImpossible ? <label className={classes.labelWarning}>{'Удаление невозможно'}</label> : null}
        </div>
    );

    function saveE() {
        fetch('http://localhost:8080/emplotask/resources/employees/save', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(employee)
        }).then((response) => {
            if (response.status === 204) {
                dispatch(saveEmployee());
            } else {
                throw new Error(response.statusText);
            }
        });
    }

    function deleteE() {
        fetch('http://localhost:8080/emplotask/resources/employees/delete', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(employee)
        }).then((response) => {
            if (response.status === 204) {
                dispatch(saveEmployee());
            } else {
                dispatch(showEmployeeDeleteImpossible());
            }
        });
    }
}
