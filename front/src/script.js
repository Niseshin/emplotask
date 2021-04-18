const employees = function () {
    let table = $('<table style="width:100%">');
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
            tbody.append(bodytr);
            // console.log(employees);
        });
    return table;
};

const tasks = function () {
    let table = $('<table style="width:100%">');
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
            tbody.append(bodytr);
            // console.log(tasks);
        });
    return table;
};

$('#field').append(employees());

$('#employees').click(function (event) {
    $('#field').empty().append(employees());
});

$('#tasks').click(function (event) {
    $('#field').empty().append(tasks());
});
