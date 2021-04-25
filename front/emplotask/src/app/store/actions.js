export const LOAD_EMPLOYEES_LIST = 'LOAD_EMPLOYEES_LIST';
export const SHOW_EMPLOYEES_LIST = 'SHOW_EMPLOYEES_LIST';
export const SHOW_EMPLOYEE = 'SHOW_EMPLOYEE';
export const CHANGE_EMPLOYEE_NAME = 'CHANGE_EMPLOYEE_NAME';
export const CHANGE_EMPLOYEE_POST = 'CHANGE_EMPLOYEE_POST';
export const CHANGE_EMPLOYEE_BRANCH = 'CHANGE_EMPLOYEE_BRANCH';
export const CHANGE_EMPLOYEE_BOSS = 'CHANGE_EMPLOYEE_BOSS';
export const SAVE_EMPLOYEE = 'SAVE_EMPLOYEE';

export const LOAD_TASKS_LIST = 'LOAD_TASKS_LIST';
export const SHOW_TASKS_LIST = 'SHOW_TASKS_LIST';
export const SHOW_TASK = 'SHOW_TASK';
export const CHANGE_TASK_PRIORITY = 'CHANGE_TASK_PRIORITY';
export const CHANGE_TASK_DESCRIPTION = 'CHANGE_TASK_DESCRIPTION';
export const CHANGE_TASK_PERFORMER = 'CHANGE_TASK_PERFORMER';
export const SAVE_TASK = 'SAVE_TASK';

export const loadEmployeesList = (value) => {
    // костыль для простоты, возможно обернется проблемами с памятью
    const employees = [];
    value.forEach(element => {
        employees[element[0]] = element;
    });
    return {
        type: LOAD_EMPLOYEES_LIST,
        payload: employees
    }
}

export const showEmployeesList = () => {
    return {
        type: SHOW_EMPLOYEES_LIST
    }
}

export const showEmployee = (id) => {
    return {
        type: SHOW_EMPLOYEE,
        payload: id
    }
}

export const changeEmployeeName = (name) => {
    return {
        type: CHANGE_EMPLOYEE_NAME,
        payload: name
    }
}

export const changeEmployeePost = (post) => {
    return {
        type: CHANGE_EMPLOYEE_POST,
        payload: post
    }
}

export const changeEmployeeBranch = (branch) => {
    return {
        type: CHANGE_EMPLOYEE_BRANCH,
        payload: branch
    }
}

export const changeEmployeeBoss = (boss) => {
    if (boss === '-') boss = null;
    return {
        type: CHANGE_EMPLOYEE_BOSS,
        payload: boss
    }
}

export const saveEmployee = () => {
    return {
        type: SAVE_EMPLOYEE
    }
}

export const loadTasksList = (value) => {
    // костыль для простоты, возможно обернется проблемами с памятью
    const tasks = [];
    value.forEach(element => {
        tasks[element[0]] = element;
    });
    return {
        type: LOAD_TASKS_LIST,
        payload: tasks
    }
}

export const showTasksList = () => {
    return {
        type: SHOW_TASKS_LIST
    }
}

export const showTask = (id) => {
    return {
        type: SHOW_TASK,
        payload: id
    }
}

export const changeTaskPriority = (priority, minPriority, maxPriority) => {
    priority = parseInt(priority);
    if (priority < minPriority || priority <= 0) {
        priority = minPriority;
    } else if (priority > maxPriority) {
        priority = maxPriority;
    }
    return {
        type: CHANGE_TASK_PRIORITY,
        payload: priority
    }
}

export const changeTaskDescription = (description) => {
    return {
        type: CHANGE_TASK_DESCRIPTION,
        payload: description
    }
}

export const changeTaskPerformer = (performer) => {
    if (performer === '-' || performer === null) {
        performer = null;
    } else {
        performer = parseInt(performer);
    };
    return {
        type: CHANGE_TASK_PERFORMER,
        payload: performer
    }
}

export const saveTask = () => {
    return {
        type: SAVE_TASK
    }
}
