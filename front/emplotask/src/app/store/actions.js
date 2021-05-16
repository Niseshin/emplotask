export const LOAD_EMPLOYEES_LIST = 'LOAD_EMPLOYEES_LIST';
export const SHOW_EMPLOYEES_LIST = 'SHOW_EMPLOYEES_LIST';
export const SHOW_EMPLOYEE = 'SHOW_EMPLOYEE';
export const SAVE_EMPLOYEE = 'SAVE_EMPLOYEE';
export const SHOW_EMPLOYEE_DELETE_IMPOSSIBLE = 'SHOW_EMPLOYEE_DELETE_IMPOSSIBLE';

export const LOAD_TASKS_LIST = 'LOAD_TASKS_LIST';
export const SHOW_TASKS_LIST = 'SHOW_TASKS_LIST';
export const SHOW_TASK = 'SHOW_TASK';
export const SAVE_TASK = 'SAVE_TASK';

export const loadEmployeesList = (employees) => {
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

export const showEmployee = (employee) => {
    if (!employee) {
        employee = {
            id: null,
            name: '',
            post: '',
            branch: '',
            bossId: null
        }
    } else {
        delete employee.bossName;
        delete employee.taskCount;
        if (!employee.bossId || employee.bossId === employee.id) {
            employee.bossId = null;
        }
    }
    return {
        type: SHOW_EMPLOYEE,
        payload: employee
    }
}

export const saveEmployee = () => {
    return {
        type: SAVE_EMPLOYEE
    }
}

export const showEmployeeDeleteImpossible = () => {
    return {
        type: SHOW_EMPLOYEE_DELETE_IMPOSSIBLE
    }
}

export const loadTasksList = (tasks) => {
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

export const showTask = (task, minPriority = null, maxPriority = null) => {
    if (!task) {
        task = {
            id: null,
            priority: null,
            description: '',
            performerId: null
        }
    } else {
        delete task.performerName;
        if (minPriority !== null && task.priority < minPriority) {
            task.priority = minPriority;
        }
        if (maxPriority !== null && task.priority > maxPriority) {
            task.priority = maxPriority;
        }
    }
    return {
        type: SHOW_TASK,
        payload: task
    }
}

export const saveTask = () => {
    return {
        type: SAVE_TASK
    }
}
