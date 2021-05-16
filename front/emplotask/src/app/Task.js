import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useStyles } from '../styles';
import { saveTask, showTask, showTasksList } from './store/actions';

export function Task() {
    const dispatch = useDispatch();
    const employees = useSelector(state => state.employees);
    const tasks = useSelector(state => state.tasks);
    const task = useSelector(state => state.task);
    const classes = useStyles();

    // жуткий способ определить максимальный и минимальный приоритет
    let minPriority;
    let maxPriority;
    tasks.forEach((element) => {
        if (element.priority <= minPriority || minPriority === undefined) {
            minPriority = element.priority;
            if (task.id !== element.id) {
                --minPriority;
            }
        }
        if (element.priority >= maxPriority || maxPriority === undefined) {
            maxPriority = element.priority;
            if (task.id !== element.id) {
                ++maxPriority;
            }
        }
    });

    return (
        <div>
            <h1 className={classes.title}>{task.id == null ? 'Добавление задачи' : 'Редактирование задачи №' + task.id}</h1>
            <div>
                <label className={classes.label}>Описание</label>
                <textarea className={classes.textArea}
                    value={task.description}
                    onChange={(event) => dispatch(showTask({ ...task, description: event.target.value }))}></textarea>
                {!task.description ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Приоритет</label>
                <input className={classes.inputNumber}
                    type="number" value={task.priority != null ? task.priority : ''}
                    onChange={(event) => dispatch(showTask({ ...task, priority: parseInt(event.target.value) }, minPriority, maxPriority))}></input>
                {task.priority === null ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <label className={classes.label}>Ответственный</label>
                <select className={classes.select}
                    value={task.performerId ? task.performerId : 0}
                    onChange={(event) => dispatch(showTask({ ...task, performerId: parseInt(event.target.value) ? parseInt(event.target.value) : null }))}>
                    <option value={0}></option>
                    {employees.map((value, index) => (
                        <option key={index} value={value.id}>{value.name}</option>
                    ))}</select>
                {task.performer === null ? <label className={classes.labelWarning}>{'<'}</label> : null}
            </div>
            <div>
                <button className={classes.deleteButton}
                    onClick={() => deleteT()}
                    disabled={task.id ? false : true}>Удалить</button>
                <button className={classes.saveButton}
                    disabled={!task.description || !task.priority || !task.performerId ? true : false}
                    onClick={() => saveT()}>Сохранить</button>
                <button className={classes.cancelButton}
                    onClick={() => dispatch(showTasksList())}>Отмена</button>
            </div>
        </div>
    );

    function saveT() {
        fetch('http://localhost:8080/emplotask/resources/tasks/save', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(task)
        }).then((response) => {
            if (response.status === 204) {
                dispatch(saveTask());
            } else {
                throw new Error(response.statusText);
            }
        });
    }

    function deleteT() {
        fetch('http://localhost:8080/emplotask/resources/tasks/delete', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(task)
        }).then((response) => {
            if (response.status === 204) {
                dispatch(saveTask());
            } else {
                throw new Error(response.statusText);
            }
        });
    }
}
