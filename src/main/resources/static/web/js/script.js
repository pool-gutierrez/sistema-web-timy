const inputBuscar = document.getElementById('buscador');
const productos = document.querySelectorAll('.producto');

function validar() {
	if ($('#buscador').val().length == 0) {
		$('#listaProductos').hide();
		return false;
	}
}

validar();

inputBuscar.addEventListener('keyup', (e) => {
	let texto = e.target.value;

	if (texto === '') {
		$('#listaProductos').hide();
	} else {
		let er = new RegExp(texto, "i");
		var size = productos.length;
		var count = 0;
		for (let i = 0; i < productos.length; i++) {
			let valor = productos[i];
			$('#listaProductos').show();

			if (er.test(valor.innerText)) {
				count = count + 1;
				valor.classList.remove("filter");
			} else {
				size = size - 1;
				valor.classList.add("filter");
			}
		}
		if (size === 0) {
			$('#empty').show();
			$('.contador').hide();
		} else {
			$('.contador').show();
			$('#empty').hide();
		}
		document.getElementById('count').innerText = count;
	}
})


function scrollToBottom() {
	const chatBody = document.getElementById('chatBody');
	chatBody.scrollTop = chatBody.scrollHeight;
}

function toggleChat() {
	const chatContainer = document.getElementById('chatContainer');
	if (chatContainer.style.display === 'none' || chatContainer.style.display === '') {
		chatContainer.style.display = 'flex';
		scrollToBottom();
	} else {
		chatContainer.style.display = 'none';
	}
}

window.onload = scrollToBottom;