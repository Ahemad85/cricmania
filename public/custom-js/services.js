app.factory('cricmaniaFactory',function($http, $q) {
	var service = {};
	var bowlingTypes = [];
	var playerTypes = [];
	var countries = [];
	var stateBuffer = {};
	var cityBuffer = {};
	var groundBuffer = {};
	var user;
	service.getBowlingTypes = function() {
		var deferred = $q.defer();
		if(bowlingTypes.length == 0) {
			$http.get('/bowling-types').success(function(data) {
				bowlingTypes = data;
				deferred.resolve(data);
			}).error(function() {
				deferred.reject("Error in getting bowling types!");
			});
		} else {
			deferred.resolve(bowlingTypes);
		}
		return deferred.promise;
	};
	service.getPlayerTypes = function() {
		var deferred = $q.defer();
		if(playerTypes.length == 0) {
			$http.get('/player-types').success(function(data) {
				playerTypes = data;
				deferred.resolve(data);
			}).error(function() {
				deferred.reject("Error in getting bowling types!");
			});
		} else {
			deferred.resolve(playerTypes);
		}
		return deferred.promise;
	};
	service.getCountries = function() {
		var deferred = $q.defer();
		if(countries.length == 0) {
			$http.get('/countries').success(function(data) {
				countries = data;
				deferred.resolve(data);
			}).error(function() {
				deferred.reject("Error in getting countries types!");
			});
		} else {
			deferred.resolve(countries);
		}
		return deferred.promise;
	};
	service.getStates = function(id) {
		var deferred = $q.defer();
		if(!stateBuffer[id]) {
			$http.get('/states-by-country-id/'+id).success(function(data) {
				states = data;
				stateBuffer[id] = data;
				deferred.resolve(data);
			}).error(function() {
				deferred.reject("Error in getting states!");
			});
		} else {
			deferred.resolve(stateBuffer[id]);
		}
		return deferred.promise;
	};
	service.getCities = function(id) {
		var deferred = $q.defer();
		if(!cityBuffer[id]) {
			$http.get('/cities-by-state-id/'+id).success(function(data) {
				cityBuffer[id] = data;
				deferred.resolve(data);
			}).error(function() {
				deferred.reject("Error in getting cities!");
			});
		} else {
			deferred.resolve(cityBuffer[id]);
		}
		return deferred.promise;
	};
	service.getGrounds = function(id) {
		var deferred = $q.defer();
		if(!groundBuffer[id]) {
			$http.get('/grounds-by-city-id/'+id).success(function(data) {
				groundBuffer[id] = data;
				deferred.resolve(data);
			}).error(function() {
				deferred.reject("Error in getting grounds!");
			});
		} else {
			deferred.resolve(groundBuffer[id]);
		}
		return deferred.promise;
	};
	service.setUser = function(u) {
		user = u;
	};
	service.getUser = function() {
		return user;
	};
	return service;
});

app.directive('crictypeahead',function($http,$compile) {
	return {
		scope: {
		},
		link: function(scope,element,attr) {
			if(attr.url) {
				element.parent().css('position','relative');
				var typeAheadContainer = $("<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12' style='background:white;display:none;position:absolute;cursor:pointer;padding:0;z-index:10;max-height:100px;over-flow:auto;'><div ng-repeat='item in items' style='padding-left:10px;' ng-click='selectItem(item)'>{{item.name}}</div></div>");
				typeAheadContainer.insertAfter(element);
				var cloneElement = $(element[0].outerHTML);
				element.hide();
				cloneElement.insertAfter(element);
				scope.selectItem = function(item) {
					element.val(item.value);
					cloneElement.val(item.name);
					element.trigger('change');
					typeAheadContainer.hide();
				};
				typeAheadContainer.on('click',function() {
					console.log("click");
				});
				$compile(typeAheadContainer)(scope);
				var lastLength = 0;
				cloneElement.on('focus',function() {
					if(cloneElement.val().length>3) {
						typeAheadContainer.width(cloneElement.outerWidth());
						typeAheadContainer.show();
					}
				});
				cloneElement.on('focusout',function(e) {
					console.log("focusout");
					//typeAheadContainer.hide();
				});
				cloneElement.on('keyup',function(e) {
					element.val(cloneElement.val());
					element.trigger('change');
					if(lastLength != cloneElement.val().length && cloneElement.val().length > 3) {
						$http.get(attr.url,{params:{q:cloneElement.val()}}).success(function(data) {
							if(data && data.length>0) {
								scope.items = data;
								typeAheadContainer.width(cloneElement.outerWidth());
								typeAheadContainer.show();
							} else {
								typeAheadContainer.hide();
							}
						});
					} else if(cloneElement.val().length <= 3) {
						typeAheadContainer.hide();
					}
					lastLength = cloneElement.val().length;
				});
			} else {
				throw "Url attriute not found. Please provide url attribute. type initialization failed";
			}
		}
	};
})