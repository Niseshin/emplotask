import { LOAD_EMPLOYEES_LIST, LOAD_TASKS_LIST, SAVE_EMPLOYEE, SAVE_TASK, SHOW_EMPLOYEE, SHOW_EMPLOYEES_LIST, SHOW_EMPLOYEE_DELETE_IMPOSSIBLE, SHOW_TASK, SHOW_TASKS_LIST } from "./actions"

const initialState = {
    employees: [],
    employee: null,
    isEmloyeesLoaded: false,
    isEmployeeDeleteImpossible: false,
    
    tasks: [],
    task: null,
    isTasksLoaded: false,

    page: 'employeesList'
}

export function reducer(state = initialState, action) {
    switch (action.type) {
        case LOAD_EMPLOYEES_LIST:
            return {
                ...state,
                employees: action.payload,
                isEmloyeesLoaded: true
            }
        case SHOW_EMPLOYEES_LIST:
            return {
                ...state,
                employee: null,
                isEmployeeDeleteImpossible: false,
                page: 'employeesList'
            }
        case SHOW_EMPLOYEE:
            return {
                ...state,
                employee: action.payload,
                page: 'employee'
            }
        case SAVE_EMPLOYEE:
            return {
                ...state,
                isEmloyeesLoaded: false,
                isEmployeeDeleteImpossible: false,
                isTasksLoaded: false,
                page: 'employeesList'
            }
        case SHOW_EMPLOYEE_DELETE_IMPOSSIBLE:
            return {
                ...state,
                isEmployeeDeleteImpossible: true
            }
        case LOAD_TASKS_LIST:
            return {
                ...state,
                tasks: action.payload,
                isTasksLoaded: true
            }
        case SHOW_TASKS_LIST:
            return {
                ...state,
                task: null,
                page: 'tasksList'
            }
        case SHOW_TASK:
            return {
                ...state,
                task: action.payload,
                page: 'task'
            }
        case SAVE_TASK:
            return {
                ...state,
                isEmloyeesLoaded: false,
                isTasksLoaded: false,
                page: 'tasksList'
            }
        default:
            return state
    }
}
