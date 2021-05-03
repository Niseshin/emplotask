import { createUseStyles } from "react-jss";

export const useStyles = createUseStyles({

    title: {
        background: "#cccccc",
        fontSize: 40,
        padding: [20, 30, 20, 30]
    },

    table: {
        width: "100%",
        borderCollapse: "collapse",
        "& th, & td": {
            textAlign: "left",
            padding: [5, 5, 5, 5],
            border: {
                width: 1,
                style: "solid",
                color: "#cccccc"
            }
        },
        "& tr": {
            "&:hover": {
                backgroundColor: "#eeeeee"
            }
        }
    },

    titleRow: {
        backgroundColor: "#eeeeee"
    },

    tabButtonEmployees: {
        width: 100,
        cursor: "pointer",
        padding: 10,
        margin: [0, 0, 0, 0],
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        borderBottom: "none",
        backgroundColor: page => page === 'employeesList' ? "#cccccc" : "#eeeeee",
        "&:hover": {
            backgroundColor: "#dddddd"
        }
    },

    tabButtonTasks: {
        width: 100,
        cursor: "pointer",
        padding: 10,
        margin: [0, 0, 0, 0],
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        borderBottom: "none",
        backgroundColor: page => page === 'tasksList' ? "#cccccc" : "#eeeeee",
        "&:hover": {
            backgroundColor: "#dddddd"
        }
    },

    addButton: {
        width: 200,
        padding: 5,
        margin: [0, 0, 0, 20],
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        "&:hover": {
            backgroundColor: "#dddddd"
        }
    },

    label: {
        display: "inline-block",
        width: 100,
        margin: [20, 20, 20, 20]
    },

    labelWarning: {
        display: "inline-block",
        width: 200,
        margin: [20, 20, 20, 20],
        color: "#ff0000"
    },

    input: {
        width: 200,
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        "&:hover": {
            border: {
                width: 1,
                style: "solid",
                color: "#808080"
            }
        },
        "&:focus": {
            outline: "none",
            border: {
                width: 1,
                style: "solid",
                color: "#000000"
            }
        }
    },

    inputNumber: {
        width: 50,
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        "&:hover": {
            border: {
                width: 1,
                style: "solid",
                color: "#808080"
            }
        },
        "&:focus": {
            outline: "none",
            border: {
                width: 1,
                style: "solid",
                color: "#000000"
            }
        }
    },

    textArea: {
        verticalAlign: "middle",
        resize: "none",
        // fontSize: 14,
        width: 200,
        height: 86,
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        "&:hover": {
            border: {
                width: 1,
                style: "solid",
                color: "#808080"
            }
        },
        "&:focus": {
            outline: "none",
            border: {
                width: 1,
                style: "solid",
                color: "#000000"
            }
        }
    },

    select: {
        width: 205,
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        "&:hover": {
            border: {
                width: 1,
                style: "solid",
                color: "#cccccc"
            }
        },
        "&:focus": {
            outline: "none",
            border: {
                width: 1,
                style: "solid",
                color: "#cccccc"
            }
        }
    },

    deleteButton: {
        width: 100,
        padding: 5,
        margin: [50, 50, 0, 0],
        border: {
            width: 1,
            style: "solid",
            color: "#ff0000"
        },
        backgroundColor: "#f0a0a0",
        "&:hover": {
            backgroundColor: "#f05050"
        },
        "&:disabled": {
            border: {
                width: 1,
                style: "solid",
                color: "#cccccc"
            },
            backgroundColor: "#eeeeee"
        }
    },

    saveButton: {
        width: 100,
        padding: 5,
        margin: [0, 10, 0, 0],
        border: {
            width: 1,
            style: "solid",
            color: "#00ff00"
        },
        backgroundColor: "#a0f0c0",
        "&:hover": {
            backgroundColor: "#50f070"
        },
        "&:disabled": {
            border: {
                width: 1,
                style: "solid",
                color: "#cccccc"
            },
            backgroundColor: "#eeeeee"
        }
    },

    cancelButton: {
        width: 100,
        padding: 5,
        border: {
            width: 1,
            style: "solid",
            color: "#cccccc"
        },
        "&:hover": {
            backgroundColor: "#dddddd"
        }
    }
});
