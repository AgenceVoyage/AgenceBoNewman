monApp.config(function($routeProvider) {

    $routeProvider.when("/commande", {
        templateUrl : "commande.html",
        controller : "commandeCtrl"
    }).when("/accueil", {
        templateUrl : "index.html",
        controller : "indexCtrl"
    }).otherwise({
        redirectTo : "/accueil"
    })
});