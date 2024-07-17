document.addEventListener('DOMContentLoaded', () => {
	const subtotal = document.getElementById('subtotal');
	const descuento = document.getElementById('descuento');
	const total = document.getElementById('total');

	// Verifica si los elementos existen
	if (subtotal && descuento && total) {
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
	}
});

document.addEventListener('DOMContentLoaded', () => {
	const analisisBtn = document.getElementById("analisisBtn");
	const loadingOverlay = document.getElementById("loadingOverlay");

	if (analisisBtn) {
		analisisBtn.addEventListener("click", function() {
			loadingOverlay.style.display = 'flex'; // Mostrar el overlay

			fetch("http://localhost:8096/servicio/inventario")
				.then(response => {
					if (!response.ok) {
						throw new Error("Error en la respuesta de la API");
					}
					return response.json();
				})
				.then(data => {
					loadingOverlay.style.display = 'none'; // Ocultar el overlay
					document.getElementById("modalRespuesta").innerText = data.respuesta;
					$('#respuestaModal').modal('show'); // Mostrar el modal
				})
				.catch(error => {
					loadingOverlay.style.display = 'none'; // Ocultar el overlay
					console.error("Error:", error);
				});
		});
	}
});