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
    .when('/find-player',{
        templateUrl: 'assets/views/find.html',
        controller : 'FindPlayerController'
    })
    .when('/find-umpire',{
        templateUrl: 'assets/views/find.html',
        controller : 'FindUmpireController'
    })
    .when('/register-team', {
        templateUrl : 'assets/views/register-team.html',
        controller:'RegisterTeamController'
    })
    .when('/create-team', {
        templateUrl : 'assets/views/create-team.html',
        controller:'CreateTeamController'
    })
    .when('/schedule-match', {
        templateUrl : 'assets/views/schedule-match.html',
        controller:'ScheduleMatchController'
    })
    .otherwise({
        redirectTo: '/'
    });
});