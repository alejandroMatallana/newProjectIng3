var app = angular.module("miApp", [ "ngRoute", "ngStorage" ]);
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		controller : "menuController",
		controllerAs : "m1",
	}).when("/moras", {
		controller : "controladorMora",
		templateUrl : "paginas/moras.html"
	}).when("/pagos", {
		controller : "ControladorPagPagos",
		templateUrl : "paginas/pagos.html"
	}).when("/compromiso", {
		controller : "controladorCompromiso",
		templateUrl : "paginas/compromiso.html"
	}).when("/busquedaAvanzada", {
		controller : "busquedaAvanzadaController",
		templateUrl : "paginas/busquedaAvanzada.html"
	});
});