var app = angular.module('cricmania',['ngRoute','ui.bootstrap','ngAnimate','jlareau.pnotify','ngFileUpload'])
.config(function ($routeProvider) {
	$routeProvider.when('/', {
        templateUrl: 'assets/views/main.html',
        controller: 'HomeController'
    })
    .when('/add-player', {
        templateUrl : 'assets/views/player-add.html',
        controller:'AddPlayerController'
    })
    .when('/add-umpire', {
        templateUrl : 'assets/views/player-add.html',
        controller:'AddUmpireController'
    })
    .when('/add-tournament', {
        templateUrl : 'assets/views/tournament-add.html',
        controller:'AddTournamentController'
    })
    .when('/add-team', {
        templateUrl : 'assets/views/create-team.html',
        controller:'CreateTeamController'
    })
    .when('/add-ground', {
        templateUrl : 'assets/views/ground-add.html',
        controller:'AddGroundController'
    })
    .when('/registration-success',{
        templateUrl: 'assets/views/registration-success.html',
        controller : 'RegistrationSuccessController'
    })
    .when('/find-player',{
        templateUrl: 'assets/views/find.html',
        controller : 'FindPlayerController'
    })
    .when('/find-umpire',{
        templateUrl: 'assets/views/find.html',
        controller : 'FindUmpireController'
    })
    .when('/login',{
        templateUrl: 'assets/views/login.html',
        controller : 'LoginController'
    })
    .otherwise({
        redirectTo: '/'
    });
});