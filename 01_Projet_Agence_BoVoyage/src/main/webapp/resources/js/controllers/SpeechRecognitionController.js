"use strict";

angular.module('monApp').controller('SpeechRecognitionController', function ($scope, $rootScope,$location) {

    var vm = this;

    vm.displayTranscript = displayTranscript;
    vm.transcript = '';

 
    function displayTranscript() {
        vm.transcript = $rootScope.transcript;

        //This is just to refresh the content in the view.
        if (!$scope.$$phase) {
            $scope.$digest();
        }
        console.log("Vocale="+$rootScope.transcript)
        if(vm.transcript=='alcool'){
            console.log('Alcool')
            $scope.$apply(function() {
                setTimeout(function() {
                    window.location = "http://localhost:63342/coursAngoularJS/Test%20speech%20github/commande.html";
                },2000); // Nb de millisecondes de délai
            });
            $location.path('commande');
            $location.path('/commande');
        }
        $location.path('/commande');
    }



}).controller('commandeCtrl', function ($scope, $rootScope,$location) {

    console.log("Commande Controlleur")


})
