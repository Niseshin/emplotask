let getInputVal = function (input) {
    return input.value
}

// жуткий костыль -_-
let selectEmployee1 = $('<select style="width: 200px">');
let selectEmployee2 = $('<select style="width: 200px">');

let addEmployeeButt = $('<button style="width: 200px">').text('Добавить сотрудника');
let addEmployeeInputName = $('<input>');
let addEmployeeInputPost = $('<input>');
let addEmployeeInputBranch = $('<input>');
let addEmployeeRow = $('<tr>').append(
    $('<td>').append(addEmployeeButt),
    $('<td>').append(addEmployeeInputName),
    $('<td>').append(addEmployeeInputPost),
    $('<td>').append(addEmployeeInputBranch),
    $('<td>').append(selectEmployee1)
);

let addTaskButt = $('<button id="addTask" style="width: 200px">').text('Добавить задачу');
let addTaskInputPriority = $('<input>');
let addTaskInputDescription = $('<input>');
let addTaskRow = $('<tr>').append(
    $('<td>').append(addTaskButt),
    $('<td>').append(addTaskInputPriority),
    $('<td>').append(addTaskInputDescription),
    $('<td>').append(selectEmployee2)
);

const refreshSelectEmployee = function () {
    $.get("http://localhost:8080/emplotask/resources/employees")
        .then(function (res) {
            selectEmployee1.empty();
            selectEmployee2.empty();
            let employees = JSON.parse(res).records;
            $.each(employees, function (index, record) {
                selectEmployee1.append($('<option>').text(record[1]));
                selectEmployee2.append($('<option>').text(record[1]));
            });
        });
};

const employees = function () {
    let table = $('<table>');
    let thead = $('<thead>');
    let headtr = $('<tr>');
    let tbody = $('<tbody>');
    let bodytr = [];
    headtr.append(
        $('<th>ID</th>'),
        $('<th>Имя</th>'),
        $('<th>Должность</th>'),
        $('<th>Филиал</th>'),
        $('<th>Руководитель</th>')
    );
    thead.append(headtr);
    table.append(thead);
    table.append(tbody);
    $.get("http://localhost:8080/emplotask/resources/employees")
        .then(function (res) {
            let employees = JSON.parse(res).records;
            $.each(employees, function (index, record) {
                let tr = $('<tr>').append(
                    $('<td>').text(record[0]),
                    $('<td>').text(record[1]),
                    $('<td>').text(record[2]),
                    $('<td>').text(record[3]),
                    $('<td>').text(record[4])
                );
                bodytr[index] = tr;
            });
            bodytr[bodytr.length] = addEmployeeRow;
            tbody.append(bodytr);
            // console.log(employees);
        });
    return table;
};

const tasks = function () {
    let table = $('<table>');
    let thead = $('<thead>');
    let headtr = $('<tr>');
    let tbody = $('<tbody>');
    let bodytr = [];
    headtr.append(
        $('<th>ID</th>'),
        $('<th>Приоритет</th>'),
        $('<th>Описание</th>'),
        $('<th>Исполнитель</th>')
    );
    thead.append(headtr);
    table.append(thead);
    table.append(tbody);
    $.get("http://localhost:8080/emplotask/resources/tasks")
        .then(function (res) {
            let tasks = JSON.parse(res).records;
            $.each(tasks, function (index, record) {
                let tr = $('<tr>').append(
                    $('<td>').text(record[0]),
                    $('<td>').text(record[1]),
                    $('<td>').text(record[2]),
                    $('<td>').text(record[3])
                );
                bodytr[index] = tr;
            });
            bodytr[bodytr.length] = addTaskRow;
            tbody.append(bodytr);
            // console.log(tasks);
        });
    return table;
};

refreshSelectEmployee();

$('#field').append(employees());

$('#employees').click(function (event) {
    $('#field').empty().append(employees());
});

$('#tasks').click(function (event) {
    $('#field').empty().append(tasks());
});

// работает 1 раз, видимо из-за удаления и добавления кнопки в коде
addEmployeeButt.click(function (event) {
    $.post("http://localhost:8080/emplotask/resources/employees/add",
        JSON.stringify([addEmployeeInputName.val(), addEmployeeInputPost.val(), addEmployeeInputBranch.val(), selectEmployee1.val()]))
        .then(function (res) {
            $('#field').empty().append(employees());
        });
    // console.log('addEmployee');
});

// тоже работает 1 раз
addTaskButt.click(function (event) {
    $.post("http://localhost:8080/emplotask/resources/tasks/add",
        JSON.stringify([addTaskInputPriority.val(), addTaskInputDescription.val(), selectEmployee2.val()]))
        .then(function (res) {
            $('#field').empty().append(tasks());
        });
    // console.log('addTask');
});
