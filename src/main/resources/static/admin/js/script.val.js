toastr.options = {
	"closeButton": false,
	"debug": false,
	"newestOnTop": true,
	"progressBar": false,
	"positionClass": "toast-top-center",
	"preventDuplicates": true,
	"onclick": null,
	"showDuration": "300",
	"hideDuration": "1000",
	"timeOut": "5000",
	"extendedTimeOut": "1000",
	"showEasing": "swing",
	"hideEasing": "linear",
	"showMethod": "fadeIn",
	"hideMethod": "fadeOut"
}

function validarLetras(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key).toLowerCase();
	letras = " abcdefghijklmnñopqrstuvwxyzáéíóú";
	especiales = "8-10-164";
	teclado_especial = false;
	for (var i in especiales) {
		if (key === especiales[i]) {
			teclado_especial = true;
			break;
		}
	}
	if (letras.indexOf(teclado) === -1 && !teclado_especial) {
		toastr.warning('Solo se permiten letras.', 'Solo letras');
		return false;
	}
}

function validarNumeros(evt) {
	if (window.event) {
		keynum = evt.keyCode;
	} else {
		keynum = evt.which;
	}
	if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13) {
		return true;
	} else {
		toastr.warning('El campo no permite otros caracteres.', 'Solo números enteros');
		return false;
	}
}

function validarDireccion(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key).toLowerCase();
	letras = " abcdefghijklmnñopqrstuvwxyzáéíóú1234567890.-/#";
	especiales = "8-10-164";
	teclado_especial = false;
	for (var i in especiales) {
		if (key === especiales[i]) {
			teclado_especial = true;
			break;
		}
	}
	if (letras.indexOf(teclado) === -1 && !teclado_especial) {
		toastr.warning('No se permiten caracteres especiales.', 'Dirección incorrecta');
		return false;
	}
}

function validarAlfanumerico(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key).toLowerCase();
	letras = " abcdefghijklmnñopqrstuvwxyzáéíóú1234567890.,:";
	especiales = "8-10-164";
	teclado_especial = false;
	for (var i in especiales) {
		if (key === especiales[i]) {
			teclado_especial = true;
			break;
		}
	}
	if (letras.indexOf(teclado) === -1 && !teclado_especial) {
		toastr.warning('No se permiten caracteres especiales.', 'Solo se permiten letras o números');
		return false;
	}
}

function validarUsuario(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key).toLowerCase();
	letras = " abcdefghijklmnñopqrstuvwxyzáéíóú1234567890";
	especiales = "8-10-164";
	teclado_especial = false;
	for (var i in especiales) {
		if (key === especiales[i]) {
			teclado_especial = true;
			break;
		}
	}
	if (letras.indexOf(teclado) === -1 && !teclado_especial) {
		toastr.warning('No se permiten caracteres especiales.', 'Solo se permiten letras o números');
		return false;
	}
	if (event.keyCode === 32) {
		event.preventDefault();
	}
}

function validarCorreo(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key).toLowerCase();
	letras = " abcdefghijklmnñopqrstuvwxyzáéíóú1234567890.@_-";
	especiales = "8-10-164";
	teclado_especial = false;
	for (var i in especiales) {
		if (key == especiales[i]) {
			teclado_especial = true;
			break;
		}
	}
	if (letras.indexOf(teclado) == -1 && !teclado_especial) {
		toastr.warning('No se permiten caracteres especiales fuera del estandar de correo.', 'Correo incorrecto');
		return false;
	}
}

function validarDecimal(evt, input) {
	var key = window.Event ? evt.which : evt.keyCode;
	var chark = String.fromCharCode(key);
	var tempValue = input.value + chark;
	if (key >= 48 && key <= 57) {
		if (filter(tempValue) === false) {
			return false;
		} else {
			return true;
		}
	} else {
		if (key === 8 || key === 13 || key === 0) {
			return true;
		} else if (key === 46) {
			if (filter(tempValue) === false) {
				return false;
			} else {
				return true;
			}
		} else {
			toastr.warning('Ingrese solo numeros enteros o decimales.', 'Solo números');
			return false;
		}
	}
}

function esNumero(dato) {
	var valoresNoAceptados = /^[0-9]+$/;
	if (dato.indexOf(".") === -1) {
		if (dato.match(valoresNoAceptados)) {
			return true;
		} else {
			return false;
		}
	} else {
		var particion = dato.split(".");
		if (particion[0].match(valoresNoAceptados) || particion[0] === "") {
			if (particion[1].match(valoresNoAceptados)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}

function validarCorreoNumero() {
	var correo = document.getElementById("correo").value;
	var emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
	if (esNumero(correo)) {
		toastr.warning('Ingrese un correo que cumpla con el formato correcto.', 'No es un correo válido');
		document.getElementById("correo").select();
		document.getElementById("correo").focus();
	} else if (correo.length === 0) {
		document.getElementById("correo").value = correo;
	} else {
		if (correo.match(emailRegex)) {
			document.getElementById("correo").value = correo;
		} else {
			toastr.warning('Ingrese un correo que cumpla con el formato correcto.', 'No es un correo válido');
			document.getElementById("correo").select();
			document.getElementById("correo").focus();
		}
	}
}

function validarDni() {
	dni = document.getElementById("dni").value;
	if (dni === "00000000") {
		toastr.warning('DNI incorrecto.', 'No es un número de DNI válido');
		document.getElementById("dni").focus();
	} else {
		document.getElementById("dni").value = dni;
	}
}

function validarCelular() {
	celular = document.getElementById("celular").value;
	if (celular.charAt(0) !== "9") {
		toastr.warning('Celular incorrecto.', 'No es un número de celular válido');
		document.getElementById("celular").focus();
	} else {
		document.getElementById("celular").value = celular;
	}
}