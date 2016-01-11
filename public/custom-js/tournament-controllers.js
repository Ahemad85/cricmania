app.controller('MainController',function($scope,$http,$location) {
	$scope.logout = function() {
		window.location.href =  "/logout";
	};
	var route = {
		'/':0,
		'/add-player':1,
		'/add-umpire':1,
		'/find-player':2,
		'/find-umpire':2,
		'/create-team':3,
		'/register-team':4,
		'/add-match':5
	}
	$scope.index = route[$location.path()];
});
app.controller('HomeController',function($scope,$http) {
	
});
app.controller('CreateTeamController',function($scope,$http,cricmaniaFactory,notificationService,Upload) {
	$scope.team = {
		name : null,
		sponsor : null,
		contactNumber : null,
		contactPerson : null,
		email : null,
		password:null,
		cpassword:null,
		country: null,
		state : null,
		city : null,
		zipcode : null,
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
						url:'/upload-team-logo',
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
app.controller('AddPlayerController',function($scope,$http,cricmaniaFactory,$location,notificationService ) {
	$scope.showPlayerField = true;
	var template = {
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
	$scope.player = $.extend({},template);
	
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
			$scope.player = $.extend({},template);
			notificationService.notify({
				title: 'Player added successfully.', 
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
app.controller('AddUmpireController',function($scope,$http,cricmaniaFactory,$location,notificationService ) {
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
	$scope.ajaxWorking = false;
	$scope.saveEntity = function() {
		if(!$scope.ajaxWorking) {
			$scope.ajaxWorking = true;
			var player = $.extend({},$scope.player);
			player.dob = player.dob ? player.dob.getFullYear()+"/"+(player.dob.getMonth()+1)+"/"+player.dob.getDate() : null;
			$http.post('/save-umpire',player).success(function(data) {
				player.id = data;
				notificationService.notify({
					title: 'Umpire added successfully.', 
					title_escape: false,
					text: 'Umpire id is '+data,
					text_escape: false,
					styling: "bootstrap3",
					type: "success", 
					icon: true
				});
				$scope.ajaxWorking = false;
			});
		}
	};
});
app.controller('RegisterTeamController',function($scope,$http,notificationService) {
	$http.get('/logged-in-tournament-detail').success(function(data) {
		console.log(data);
		if(typeof data == 'string') {
			//window.location.href = data;
		} else {
			$scope.tournament = data;
		}
	});
	
	$scope.addMoreTeam = function() {
		$scope.tournament.teamVMs.push({isNew:true,id:null});
	};
	
	$scope.removeTeam = function(index) {
		if($scope.tournament.teamVMs[index].isNew) {
			$scope.tournament.teamVMs.splice(index,1);
		} else {
			notificationService.notify({
				title : 'Confirmation needed',
				text : 'Are you sure?',
				confirm : {
					confirm : true,
				},
				hide : false,
				buttons : {
					closer : false,
					sticker : false
				},
				history : {
					history : false
				}
			}).get().on('pnotify.confirm', function() {
				console.log("removing..");
			}).on('pnotify.cancel',function() {
				console.log("ok no problem");
			});
		}
	};
	
	$scope.addTeamToTournament = function(index) {
		if($scope.tournament.teamVMs[index].id) {
			var notPresent = true;
			angular.forEach($scope.tournament.teamVMs,function(val,key) {
				if( index != key && val.id == $scope.tournament.teamVMs[index].id) {
					present = false;
				}
			});
			if(notPresent) {
				$http.post('/add-team-to-tournament',{teamId:$scope.tournament.teamVMs[index].id}).success(function(data){
					if(typeof data == 'string') {
						if(data == 'team id is empty') {
							notificationService.error("Please provide team id to add.");
						} else if(data == 'invalid team id'){
							notificationService.error("Invalid team id.");
						} else if(data == 'team already present') {
							notificationService.error("Team is already added.");
						} else {
							notificationService.error("You are not autherized user.");
						}
					} else {
						$scope.tournament.teamVMs.splice(index,1,data);
					}
				});
			} else {
				notificationService.info("Team is already added.");
			}
		} else {
			notificationService.error("Please provide team id to add.");
		}
	};
});