app.controller('MainController',function($scope,$http) {
	$scope.logout = function() {
		window.location.href =  "/logout";
	};
	$scope.index = 0;
});
app.controller('HomeController',function($scope,$http) {
	
});
app.controller('AddGroundController',function($scope,$http,notificationService,cricmaniaFactory) {
	$scope.ground = {
		name : null,
		country : null,
		state : null,
		city : null,
		zipcode : null
	};
	cricmaniaFactory.getCountries().then(function (data) {
		$scope.countries = data;
	});
	
	$scope.getStates = function(id) {
		cricmaniaFactory.getStates(id).then(function (data) {
			$scope.states = data;
		});
	};
	$scope.getCities = function(id) {
		cricmaniaFactory.getCities(id).then(function (data) {
			$scope.cities = data;
		});
	};
	$scope.saveEntity = function() {
		var ground = $.extend({},$scope.ground);
		$http.post('/save-ground',ground).success(function(data) {
			$scope.ground.id = data;
			notificationService.success("Ground added Successfully.");
		});
	};
});
app.controller('AddPlayerController',function($scope,$http,cricmaniaFactory,$location,notificationService,Upload) {
	$scope.showPlayerField = true;
	$scope.player = {
		firstName:null,
		middleName:null,
		lastName:null,
		email:null,
		contactNumber: null,
		dob:null,
		street:null,
		country:null,
		state:null,
		city:null,
		zipcode:null,
		battingStyle:null,
		bowlingStyle:null,
		bowlingTypes:[],
		isKeeper:null,
		playerType:null,
		password:null,
		cpassword:null
	};
	
	$scope.dateOptions = {
	    formatYear: 'yyyy',
	    startingDay: 1,
	};
	$scope.format = 'dd/MM/yyyy';
	$scope.status = {
	    opened: false
	};
	$scope.isOpen = true;
	$scope.open = function($event) {
		$scope.status.opened = true;
	};
	$scope.disabled = function(date) {
		if(date> new Date()) {
			return true;
		} else {
			return false;
		}
	};
	$scope.maxDate = new Date();
	
	cricmaniaFactory.getBowlingTypes().then(function (data) {
		$scope.bowlingTypes = data;
	});
	cricmaniaFactory.getPlayerTypes().then(function (data) {
		$scope.playerTypes = data;
	});
	cricmaniaFactory.getCountries().then(function (data) {
		$scope.countries = data;
	});
	
	$scope.getStates = function(id) {
		cricmaniaFactory.getStates(id).then(function (data) {
			$scope.states = data;
		});
	};
	$scope.getCities = function(id) {
		cricmaniaFactory.getCities(id).then(function (data) {
			$scope.cities = data;
		});
	};
	$scope.saveEntity = function() {
		var player = $.extend({},$scope.player);
		player.dob = player.dob ? player.dob.getFullYear()+"/"+(player.dob.getMonth()+1)+"/"+player.dob.getDate() : null;
		$http.post('/save-player',player).success(function(data) {
			$scope.player.id = data;
			notificationService.notify({
				title: 'Player created successfully.', 
				title_escape: false,
				text: 'Player id is '+data,
				text_escape: false,
				styling: "bootstrap3",
				type: "notice", 
				icon: true
			});
		});
	};
});

app.controller('LoginController',function($scope,$http) {
	
	$scope.user = {};
	$scope.loginFailed = false;
	
	$scope.submit = function() {
		$scope.loginFailed = false;
		$http.post('/login-entity',$scope.user).success(function(data) {
			if(data.status == 'ok') {
				window.location.href = data.url;
			} else {
				$scope.loginFailed = true;
			}
		});
	};
	
});

app.controller('RegistrationSuccessController',function($scope,$http,cricmaniaFactory) {
	$scope.user = cricmaniaFactory.getUser();
});

app.controller('FindPlayerController',function($scope,$http) {
	$scope.email = "";
	$scope.hideBoth = true;
	$scope.user = {};
	$scope.type = "Player";
	$scope.findUser = function() {
		if($scope.email) {
			$http.post('/find-player',{email:$scope.email}).success(function(data) {
				$scope.hideBoth = false;
				if( data == "not found" ) {
					$scope.userFound = false;
				} else {
					$scope.userFound = true;
					$scope.user = data;
				}
			});
		}
	};
});
app.controller('FindUmpireController',function($scope,$http) {
	$scope.type = "Umpire";
	$scope.email = "";
	$scope.hideBoth = true;
	$scope.user = {};
	$scope.findUser = function() {
		if($scope.email) {
			$http.post('/find-umpire',{email:$scope.email}).success(function(data) {
				$scope.hideBoth = false;
				if( data == "not found" ) {
					$scope.userFound = false;
				} else {
					$scope.userFound = true;
					$scope.user = data;
				}
			});
		}
	};
});
app.controller('AddUmpireController',function($scope,$http,cricmaniaFactory,$location,notificationService,Upload) {
	$scope.player = {
		firstName:null,
		middleName:null,
		lastName:null,
		email:null,
		contactNumber: null,
		dob:null,
		street:null,
		country:null,
		state:null,
		city:null,
		zipcode:null
	};
	
	$scope.showPlayerField = false;
	
	$scope.dateOptions = {
	    formatYear: 'yyyy',
	    startingDay: 1,
	};
	$scope.format = 'dd/MM/yyyy';
	$scope.status = {
	    opened: false
	};
	$scope.isOpen = true;
	$scope.open = function($event) {
		$scope.status.opened = true;
	};
	$scope.disabled = function(date) {
		if(date> new Date()) {
			return true;
		} else {
			return false;
		}
	};
	$scope.maxDate = new Date();
	
	cricmaniaFactory.getBowlingTypes().then(function (data) {
		$scope.bowlingTypes = data;
	});
	cricmaniaFactory.getPlayerTypes().then(function (data) {
		$scope.playerTypes = data;
	});
	cricmaniaFactory.getCountries().then(function (data) {
		$scope.countries = data;
	});
	
	$scope.getStates = function(id) {
		cricmaniaFactory.getStates(id).then(function (data) {
			$scope.states = data;
		});
	};
	$scope.getCities = function(id) {
		cricmaniaFactory.getCities(id).then(function (data) {
			$scope.cities = data;
		});
	};
	$scope.saveEntity = function() {
		console.log($scope.player);
		var umpire = $.extend({},$scope.player);
		umpire.dob = umpire.dob ? umpire.dob.getFullYear()+"/"+(umpire.dob.getMonth()+1)+"/"+umpire.dob.getDate() : null;
		$http.post('/save-umpire',umpire).success(function(data) {
			$scope.player.id = data;
			notificationService.notify({
				title: 'Umpire created successfully.', 
				title_escape: false,
				text: 'Umpire id is '+data,
				text_escape: false,
				styling: "bootstrap3",
				type: "notice", 
				icon: true
			});
		});
	};
});
app.controller('AddTournamentController',function($scope, $http, cricmaniaFactory, $location,notificationService,Upload) {
	$scope.dateOptions = {
		formatYear: 'yyyy',
		startingDay: 1,
	};
	$scope.format = 'dd/MM/yyyy';
	$scope.status = {
	    opened: false
	};
	$scope.isOpen = true;
	$scope.open = function($event) {
		$scope.status.opened = true;
	};
	$scope.disabled = function(date) {
		if(date < new Date()) {
			return true;
		} else {
			return false;
		}
	};
	$scope.minDate = new Date();
	$scope.tournament = {
			name:null,
			organizedBy:null,
			startDate:null,
			password:null,
			cpassword:null,
			country:null,
			state:null,
			city:null,
			zipcode:null,
			ground:null
	};
	cricmaniaFactory.getCountries().then(function (data) {
		$scope.countries = data;
	});
	
	$scope.states = [];
	$scope.cities = [];
	$scope.grounds = [];
	
	$scope.getStates = function(id) {
		cricmaniaFactory.getStates(id).then(function (data) {
			$scope.states = data;
		});
	};
	$scope.getCities = function(id) {
		cricmaniaFactory.getCities(id).then(function (data) {
			$scope.cities = data;
		});
	};
	
	$scope.getGrounds = function(id) {
		cricmaniaFactory.getGrounds(id).then(function (data) {
			$scope.grounds = data;
		});
	};
	
	$scope.saveEntity = function() {
		$http.post("/save-tournament",$scope.tournament).success(function(data) {
			$scope.tournament.id = data;
			notificationService.notify({
				title: 'Tournament created successfully.', 
				title_escape: false,
				text: 'Tournament id is '+data,
				text_escape: false,
				styling: "bootstrap3",
				type: "notice", 
				icon: true
			});
		});
	};
});

app.controller('CreateTeamController',function($scope,$http,notificationService,Upload) {
	$scope.team = {
		name : null,
		sponsor : null,
		contactNumber : null,
		contactPerson : null,
		email : null,
		password:null,
		cpassword:null,
		players : [{
			player : null,
			isCaptain : true,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : true,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : true
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		},{
			player : null,
			isCaptain : false,
			isViceCaptain : false,
			isBowler : false,
			isBatsman : false,
			isKeeper : false
		}]
	};
	
	$scope.changeCaptain = function(index) {
		angular.forEach($scope.team.players,function(value,key) {
			if(key == index) {
				value.isCaptain = true;
			} else {
				value.isCaptain = false;
			}
		});
	};
	
	$scope.changeViceCaptain = function(index) {
		angular.forEach($scope.team.players,function(value,key) {
			if(key == index) {
				value.isViceCaptain = true;
			} else {
				value.isViceCaptain = false;
			}
		});
	};
	
	$scope.changeKeeper = function(index) {
		angular.forEach($scope.team.players,function(value,key) {
			if(key == index) {
				value.isKeeper = true;
			} else {
				value.isKeeper = false;
			}
		});
	};
	
	$scope.addMorePlayer = function() {
		if($scope.team.players.length < 15) {
			$scope.team.players[$scope.team.players.length] = {
				player : null,
				isCaptain : false,
				isViceCaptain : false,
				isBowler : false,
				isBatsman : false,
				isKeeper : false
			};
		} else {
			notificationService.error("You can not add more than 15 player.");
		}
	};
	
	$scope.removeLastPlayer = function() {
		if($scope.team.players.length <= 11) {
			notificationService.error("Can not remove player! Minimum 11 player required");
		} else {
			$scope.team.players.splice($scope.team.players.length-1,1);
		}
	};
	
	$scope.selectFile = function($file) {
		if(!$file) {
			notificationService.error("Invalid image type.");
		} else {
			$scope.file = $file;
		}
	};
	
	$scope.saveEntity = function() {
		var team = $.extend({},$scope.team);
		$http.post('/save-team',team).success(function(data) {
			if(data.status == 'ok') {
				$scope.team.id = data;
				if($scope.file) {
					Upload.upload({
						url:'/save-team-image',
						file:$scope.file,
						data:{id:data}
					}).then(function(data) {
						notificationService.notify({
							title: 'Team created successfully.', 
							title_escape: false,
							text: 'Team id is '+data,
							text_escape: false,
							styling: "bootstrap3",
							type: "notice", 
							icon: true
						});
						if(data != 'ok') {
							notificationService.error("Problem in saving logo image. please update it from your account.");
						}
					},function(error) {
						
					},function(evt) {
						
					});
				} else {
					notificationService.notify({
						title: 'Team created successfully.', 
						title_escape: false,
						text: 'Team id is '+data,
						text_escape: false,
						styling: "bootstrap3",
						type: "notice", 
						icon: true
					});
				}
			} else {
				angular.forEach(data.errors,function(value,key) {
					notificationService.error(value);
				});
			}
		});
	};
});