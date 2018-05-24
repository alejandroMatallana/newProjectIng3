
app.controller("controladorCompromiso", function($scope, $http, $sessionStorage, $filter) {

	
	$scope.compromisos= [];
	$scope.cedula=$sessionStorage.cedulaSelec;

	
	$scope.deuda=0;
	$scope.fecha="";
	$scope.valor=0;
	$scope.descripcion="";

	/**
	 * lista los compromisos de un deudor
	 */
	$scope.listarCompromisosDeudor = function() {
		
		var dato1 = $.param({
			cedula : $scope.cedula
		});
		$http({
			url : 'rest/servicios/listarCompromisosDeudor',
			method : "POST",
			data : dato1,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.compromisos = data.obj;
				
			} else {
				alert(data.mensaje);
				$scope.compromisos= [];
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

	}
	
	
	
	/**
	 * agrega un compromiso al deudor
	 */
	$scope.agregarCompromiso = function() {
		
		if($scope.cedula !='' && $scope.fecha!='' && $scope.deuda!='' && $scope.descripcion !='' && $scope.deuda!='' && $scope.valor>0){
							var dato = ({
							cedula: $scope.cedula,
							descripcion: $scope.descripcion,
							fechaPagoMax: $scope.fecha,
							deuda: $scope.deuda,
							valor: $scope.valor
				});
				$http({
					url : 'rest/servicios/agregarCompromiso',
					method : "POST",
					data : dato,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(data, status, headers, config) {
					if (data.codigo == '00') {
						alert(data.mensaje);
						$scope.listarCompromisosDeudor();
					} else {
						alert(data.mensaje);
					}
				}).error(function(data, status, headers, config) {
					alert('error::' + data.mensaje);
				});
		}else{
			alert('favor llenar todo los campos correctamente');

		}
			
	}
	
		

});


