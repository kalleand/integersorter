angular.module('HM', [])
    .controller('HMController', function ($scope, $http) {

        $scope.previous = [];
        $scope.inputList = [];
        $scope.newNumber = "";

        $scope.addNumber = function () {
            if (isInt($scope.newNumber)) {
                var parsed = parseInt($scope.newNumber);
                if (parsed > 0 && parsed < 100)
                    $scope.inputList.push($scope.newNumber);
                else
                    console.error($scope.newNumber + ' is not between 0 and 100');
            } else {
                console.error($scope.newNumber + ' is not a number');
            }
            $scope.newNumber = "";
        };

        $scope.sort = function () {
            $http.post('/sort', $scope.inputList).then(function (response) {
                $scope.previous.splice(0, 0, response.data);
            }, function () {
                console.error('Could not sort list');
            });
            $scope.inputList = [];
        };

        $scope.getPreviousSortings = function () {
            $http.get('/all').then(function (response) {
                $scope.previous = response.data.reverse();
            }, function () {
                console.error('Could not get previous results');
            })
        };

        $scope.getPreviousSortings();

        function isInt(value) {
            return !isNaN(value) &&
                parseInt(Number(value)) == value &&
                !isNaN(parseInt(value, 10));
        }
    });
