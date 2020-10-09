<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <title>Home</title>
</head>
<body ng-app="MAIN_PAGE" ng-controller="MAIN_PAGE_CONTROLLER">
<h1>Список студентов</h1>

<table class="table table-hover">
    <tbody class="ui-sortable">
    <thead>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Электронная почта</th>
        <th>Дата рождения</th>
        <th>Действие</th>
    </tr>
    </thead>
    <tr ng-repeat="student in student">
        <td style="width: 20%;">{{student.firstName}}</td>
        <td style="width: 20%">{{student.lastName}}</td>
        <td style="width: 20%">{{student.email}}</td>
        <td style="width: 20%">{{student.dateOfBirth}}</td>
        <td style="width: 20%">
            <div class="button-group" style="align: center; width: 100%;">
                <button class="button" ng-click="deleteStudent(student.id)">Удалить</button>
            </div>
        </td>
    </tr>
</table>

<form>
    <div class="form-group">
        <label for="inputFirstName">Имя</label>
        <input ng-model="student.firstName" class="form-control" id="inputFirstName" placeholder="Имя" required>
    </div>
    <div class="form-group">
        <label for="inputLastName">Фамилия</label>
        <input ng-model="student.lastName" class="form-control" id="inputLastName" placeholder="Фамилия" required>
    </div>
    <div class="form-group">
        <label for="inputEmail">Электронная почта</label>
        <input ng-model="student.email" type="email" class="form-control" id="inputEmail" placeholder="name@example.ru"
               aria-describedby="emailHelp" required>
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="inputDateOfBirth">Дата рождения</label>
        <input ng-model="student.dateOfBirth" class="form-control" id="inputDateOfBirth" placeholder="ГГГГ-ММ-ДД"
               required>
    </div>

    <div class="button-group">
        <button class="button" type="submit" ng-click="save_student()">Сохранить студента</button>
    </div>
</form>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.min.js"></script>


<script type="text/javascript">

    const app = angular.module("MAIN_PAGE", []);
    app.controller("MAIN_PAGE_CONTROLLER", function ($scope, $http) {
        console.log("Start JS");
        $scope.student = [];

        $scope.getStudent = function () {
            $http({
                url: '/brandMaker/app/main/all',
                head: 'Content-Type:application/json',
                method: 'GET'
            }).then(function (response) {
                $scope.student = response.data;
                console.log($scope.student);
            })
        }

        $scope.getStudent();

        $scope.save_student = function () {
            console.log($scope.student);
            $http({
                url: '/brandMaker/app/main/add',
                method: 'PUT',
                head: 'Content-Type:application/json',
                data:
                    {
                        dateOfBirth: $scope.student.dateOfBirth,
                        email: $scope.student.email,
                        firstName: $scope.student.firstName,
                        lastName: $scope.student.lastName,
                    }
            }).then(function (response) {
                $scope.getStudent();
                console.log(response);
            });
        }

        $scope.deleteStudent = function (id_delete) {
            console.log(id_delete)
            $http({
                url: '/brandMaker/app/main/delete_id=' + id_delete,
                method: 'DELETE',
            }).then(function (response) {
                $scope.getStudent();
                console.log("delete student");
            });
        }

    });


</script>

</body>
</html>
