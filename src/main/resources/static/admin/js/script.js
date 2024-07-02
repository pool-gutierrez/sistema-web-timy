const subtotal = document.getElementById('subtotal');
const descuento = document.getElementById('descuento');
const total = document.getElementById('total');
function calcularResultado() {
	const numero1 = parseFloat(subtotal.value);
	const numero2 = parseFloat(descuento.value);
	if (!isNaN(numero1) && !isNaN(numero2)) {
		if (numero2 > numero1 || numero2 < 0) {
			total.value = numero1;
		} else {
			const resultado = numero1 - numero2;
			total.value = resultado.toFixed(2);
		}

	} else {
		total.value = '';
	}
}
descuento.addEventListener('input', calcularResultado);