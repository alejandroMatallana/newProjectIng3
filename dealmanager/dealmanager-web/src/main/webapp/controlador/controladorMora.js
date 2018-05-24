app.controller("controladorMora", function($scope, $http, $window, $sessionStorage, $filter) {

	$scope.deudores= [];
	$scope.f1 = new Date();
	$scope.rad="";

	/**
	 * lista los deudores
	 */
	$scope.listarDeudores = function() {
		
		
		$http({
			url : 'rest/servicios/listarDeudores',
			method : "GET",
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.deudores = data.obj;
				$scope.fechaaaaas();
				
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

	}
	
	/**
	 * saca la diferencia entre fechas
	 */
	$scope.fechaaaaas = function(){
		for (var i = 0; i <  $scope.deudores.length; i++) {
			var fechaTemp = new Date($scope.deudores[i].deuda.fecha);
			var diasDif = $scope.f1.getTime() - fechaTemp.getTime();
			var dias = Math.round(diasDif/(1000 * 60 * 60 * 24));
			$scope.deudores[i].dias = dias;
			
		}
		
	}
	
	
	$scope.variable = function(variable) {
		$scope.rad=variable;
	}
	
	/**
	 * abrir ventana compromiso
	 */
	$scope.abrirCompromiso = function() {
		$sessionStorage.cedulaSelec = $scope.rad;
		window.setTimeout(function() {
			window.location.href = '/dealmanager-web/menu.html#/compromiso';
		}, 1800);
	}
	
	
	$scope.listarDeudores();
	

});


