app.controller("ControladorPagPagos", function($scope, $http, $sessionStorage) {

	$scope.cedula = '';
	$scope.codigo = '';
	$scope.valor = '';
	$scope.interes = '';
	$scope.fecha = new Date();
	$scope.cliente = '';
	$scope.deuda = '';
	$scope.pagos = [];

	/**
	 * crea un pago de la deuda
	 */
	$scope.registrarPagos = function() {
		if ($scope.cedula == '' || $scope.codigo == '' || $scope.valor == ''
				|| $scope.interes == '') {
			alert('Señor usuario, todos los campos son obligatorios');
		} else {
			$scope.buscarCliente();
			$scope.buscarDeuda();
			window.setTimeout(function() {

				if ($scope.cliente != '' && $scope.deuda != '') {
					var dato3 = ({
						codigo : '',
						cliente : $scope.cliente,
						fecha : $scope.fecha,
						valor : $scope.valor,
						interes : $scope.interes,
						deuda : $scope.deuda
					});
					$http({
						url : 'rest/servicios/crearPago',
						method : "POST",
						data : dato3,
						headers : {
							"Content-Type" : "application/json"
						}
					}).success(function(data, status, headers, config) {
						if (data.codigo == '00') {
							alert(data.mensaje);
							$scope.listarPagos();
						} else {
							alert(data.mensaje);
						}
					}).error(function(data, status, headers, config) {
						alert('error::' + data.mensaje);
					});
				}
			}, 1800);
		}
	}

	/**
	 * busca un cliente
	 */
	$scope.buscarCliente = function() {
		var dato2 = $.param({
			cedula : $scope.cedula
		});
		$http({
			url : 'rest/servicios/buscarCliente',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.cliente = data.obj;
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	/**
	 * busca una deuda
	 */
	$scope.buscarDeuda = function() {
		var dato2 = $.param({
			codigo : $scope.codigo
		});
		$http({
			url : 'rest/servicios/buscarDeuda',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.deuda = data.obj;
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	/**
	 * lista los pagos de un cliente
	 */
	$scope.listarPagos = function() {
		if($scope.cedula==''||$scope.codigo==''){
			alert('Señor usuario, debe ingresar una cedula y un codigo de la deuda')
		}else{
		var dato2 = $.param({
			cedula : $scope.cedula,
			codigo : $scope.codigo
		});
		$http({
			url : 'rest/servicios/buscarPagos',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.pagos = data.obj;
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	/**
	 * limpia los campos de la ventana
	 */
    $scope.cancelar = function() {
		$scope.cliente = '';
		$scope.deuda = '';
		$scope.pagos = [];
		$scope.cedula = '';
		$scope.codigo = '';
		$scope.valor = '';
		$scope.interes = '';
		}	
 }
});