app.controller("busquedaAvanzadaController", function($scope, $http, $window,$sessionStorage,
		$location) {

	$scope.compromisos = [];
	$scope.fecha = new Date();
	$scope.cedula = '';
	$scope.dias = '';
	$scope.date = '';
	$scope.tipo = '';

	/**
	 * estados para la visibilidad de componentes
	 */
	$scope.estado = {
		tipo : false,
		diasMora : false,
		fecha : false,
		cedula : false
	}

	/**
	 * visualizar tipo
	 */
	$scope.buscarPorTipo = function() {
		$scope.estado.tipo = true;
		$scope.estado.diasMora = false;
		$scope.estado.fecha = false;
		$scope.estado.cedula = false;

	}

	/**
	 * visualizar dias
	 */
	$scope.buscarPorDiasMora = function() {
		$scope.estado.tipo = false;
		$scope.estado.diasMora = true;
		$scope.estado.fecha = false;
		$scope.estado.cedula = false;
	}

	/**
	 * visualizar fecha
	 */
	$scope.buscarPorFecha = function() {
		$scope.estado.tipo = false;
		$scope.estado.diasMora = false;
		$scope.estado.fecha = true;
		$scope.estado.cedula = false;
	}

	/**
	 * visualizar cedula
	 */
	$scope.buscarPorCedula = function() {
		$scope.estado.tipo = false;
		$scope.estado.diasMora = false;
		$scope.estado.fecha = false;
		$scope.estado.cedula = true;
	}

	/**
	 * visualizar hoy
	 */
	$scope.buscarVencenHoy = function() {
		$scope.estado.tipo = false;
		$scope.estado.diasMora = false;
		$scope.estado.fecha = false;
		$scope.estado.cedula = false;
	}

	/**
	 * abrir ventana compromiso
	 */
	$scope.abrirCompromiso = function(cedulaSelec) {
		$sessionStorage.cedulaSelec = cedulaSelec;
		window.setTimeout(function() {
			window.location.href = '/dealmanager-web/menu.html#/compromiso';
		}, 1800);
	}
	
	/**
	 * metodo para la busqueda avanzada
	 */
	$scope.busquedaAvanzada = function() {
		if ($scope.estado.cedula == true) {
			$scope.buscarCompromisoCedula();
		} else if ($scope.estado.fecha == true) {
			$scope.buscarCompromisoFecha();
		} else if ($scope.estado.diasMora == true) {
			$scope.buscarCompromisoDias();
		} else if ($scope.estado.tipo == true) {
			$scope.buscarCompromisoTipo();
		} else {
			$scope.listarCompromisosHoy();
		}
	}
	
	/**
	 * busca los compromisos por tipo
	 */
	$scope.buscarCompromisoTipo = function() {
		if ($scope.tipo == '') {
			alert('Debe ingresar un tipo de compromiso');
		} else {
			var dato2 = $.param({
				tipo : $scope.tipo
			});
			$http({
				url : 'rest/servicios/listarCompromisosTipo',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded"
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					$scope.compromisos = data.obj;
					$scope.diasMora();
				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	}

	/**
	 * busca los compromisos por fecha
	 */
	$scope.buscarCompromisoFecha = function() {
		if ($scope.date == '') {
			alert('Debe ingresar una fecha');
		} else {
			var dato2 = $.param({
				fecha : $scope.date
			});
			$http({
				url : 'rest/servicios/listarCompromisosFecha',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded"
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					$scope.compromisos = data.obj;
					$scope.diasMora();
				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	}

	/**
	 * busca los compromisos de un cliente
	 */
	$scope.buscarCompromisoCedula = function() {
		if ($scope.cedula == '') {
			alert('Debe ingresar una cedula');
		} else {
			var dato2 = $.param({
				cedula : $scope.cedula
			});
			$http({
				url : 'rest/servicios/listarCompromisosCedula',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded"
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					$scope.compromisos = data.obj;
					$scope.diasMora();
				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	}

	/**
	 * busca los compromisos por dias
	 */
	$scope.buscarCompromisoDias = function() {
		if ($scope.dias == '') {
			alert('Debe ingresar los dias');
		} else {
			var dato2 = $.param({
				dias : $scope.dias
			});
			$http({
				url : 'rest/servicios/listarCompromisosDias',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded"
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					$scope.compromisos = data.obj;
					$scope.diasMora();
				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	}
	
	/**
	 * busca los compromisos de hoy
	 */
	$scope.listarCompromisosHoy = function() {
		$http({
			url : 'rest/servicios/listarCompromisosHoy',
			method : "GET",
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.compromisos = data.obj;
				$scope.diasMora();
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	/**
	 * saca la diferencia entre fechas
	 */
	$scope.diasMora = function() {
		for (var i = 0; i < $scope.compromisos.length; i++) {
			var fechaTemp = new Date($scope.compromisos[i].deuda.fecha);
			var diasDif = $scope.fecha.getTime() - fechaTemp.getTime();
			var diasM = Math.round(diasDif / (1000 * 60 * 60 * 24));
			$scope.compromisos[i].diasM = diasM;
		}
	}
});
