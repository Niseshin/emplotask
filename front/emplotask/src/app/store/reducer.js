import { CHANGE_EMPLOYEE_BOSS, CHANGE_EMPLOYEE_BRANCH, CHANGE_EMPLOYEE_NAME, CHANGE_EMPLOYEE_POST, CHANGE_TASK_DESCRIPTION, CHANGE_TASK_PERFORMER, CHANGE_TASK_PRIORITY, LOAD_EMPLOYEES_LIST, LOAD_TASKS_LIST, SAVE_EMPLOYEE, SAVE_TASK, SHOW_EMPLOYEE, SHOW_EMPLOYEES_LIST, SHOW_TASK, SHOW_TASKS_LIST } from "./actions"

// вроде как создатели Реакта рекомендуют хранить в состоянии как можно меньше данных,
// но как без массивов сотрудников и задач тут обойтись не придумал
const initialState = {
    employees: [],
    tasks: [],
    isEmloyeesLoaded: false,
    isTasksLoaded: false,
    employeeIndex: null,
    employeeName: '',
    employeePost: '',
    employeeBranch: '',
    employeeBoss: null,
    taskIndex: null,
    taskPriority: null,
    taskDescription: '',
    taskPerformer: null,
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
                page: 'employeesList',
                employeeIndex: null,
                employeeName: '',
                employeePost: '',
                employeeBranch: '',
                employeeBoss: null
            }
        case SHOW_EMPLOYEE:
            return {
                ...state,
                page: 'employee',
                employeeIndex: action.payload,
                employeeName: action.payload != null ? state.employees[action.payload][1] : '',
                employeePost: action.payload != null ? state.employees[action.payload][2] : '',
                employeeBranch: action.payload != null ? state.employees[action.payload][3] : '',
                employeeBoss: action.payload != null ? state.employees[action.payload][4] : null
            }
        case CHANGE_EMPLOYEE_NAME:
            return {
                ...state,
                employeeName: action.payload
            }
        case CHANGE_EMPLOYEE_POST:
            return {
                ...state,
                employeePost: action.payload
            }
        case CHANGE_EMPLOYEE_BRANCH:
            return {
                ...state,
                employeeBranch: action.payload
            }
        case CHANGE_EMPLOYEE_BOSS:
            return {
                ...state,
                employeeBoss: action.payload != null ? parseInt(action.payload) : null
            }
        case SAVE_EMPLOYEE:
            return {
                ...state,
                page: 'employeesList',
                isEmloyeesLoaded: false
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
                page: 'tasksList',
                taskIndex: null,
                taskPriority: null,
                taskDescription: '',
                taskPerformer: null
            }
        case SHOW_TASK:
            return {
                ...state,
                page: 'task',
                taskIndex: action.payload,
                taskPriority: action.payload != null ? state.tasks[action.payload][1] : null,
                taskDescription: action.payload != null ? state.tasks[action.payload][2] : '',
                taskPerformer: action.payload != null ? state.tasks[action.payload][3] : null
            }
        case CHANGE_TASK_PRIORITY:
            return {
                ...state,
                taskPriority: action.payload
            }
        case CHANGE_TASK_DESCRIPTION:
            return {
                ...state,
                taskDescription: action.payload
            }
        case CHANGE_TASK_PERFORMER:
            return {
                ...state,
                taskPerformer: action.payload
            }
        case SAVE_TASK:
            return {
                ...state,
                page: 'tasksList',
                isTasksLoaded: false
            }
        default:
            // сначала написал по инерции return { state }   -_-
            // не надо так делать
            return state
    }
}
