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



























function crearMensaje(message) {
    let newMessage = document.createElement('div');
    newMessage.className = message.emisor === 'asistente' ? 'message received' : 'message sent';

    // Contenido del mensaje
    let messageContent = document.createElement('div');
    messageContent.className = 'message-content';
    messageContent.textContent = message.mensaje;
    newMessage.appendChild(messageContent);

    // Hora del mensaje
    let messageTime = document.createElement('div');
    messageTime.className = message.emisor === 'asistente' ? 'message-time-received' : 'message-time-sent';
    messageTime.textContent = message.hora;  // Puedes ajustar la hora según la lógica de tu aplicación
    newMessage.appendChild(messageTime);

    return newMessage;
}

// Función para enviar un mensaje y recibir una respuesta
function sendMessage() {
    const userMessageInput = document.getElementById('userMessageInput');
    const userMessage = userMessageInput.value.trim();

    // Validar que el mensaje del usuario no esté vacío
    if (userMessage === '') {
        return; // Salir de la función si el mensaje está vacío
    }

    // URL de la API REST
    let url = 'http://localhost:8096/chatbot/enviar?mensaje=' + encodeURIComponent(userMessage);

    // Realizar la solicitud GET usando jQuery
    $.get(url, function(response) {
        // 'response' es el arreglo de objetos JSON que contiene las respuestas del chatbot

        // Limpiar el chatBody antes de agregar nuevos mensajes
        document.getElementById('chatBody').innerHTML = '';

        // Agregar mensajes de respuesta al chatBody
        response.forEach(message => {
            let newMessage = crearMensaje(message);
            document.getElementById('chatBody').appendChild(newMessage);
        });

        // Limpiar el campo de entrada después de enviar el mensaje
        userMessageInput.value = '';

        // Hacer scroll hacia abajo para mostrar el mensaje más reciente
        scrollToBottom();

    }, "json");
}


// Función para cargar los mensajes iniciales del chat desde la API REST
function cargarMensajesIniciales() {
    // URL de la API REST
    let url = 'http://localhost:8096/chatbot/enviar?mensaje=';  // Cambia esta URL según tu endpoint

    // Realizar la solicitud GET usando jQuery
    $.get(url, function(response) {
        // 'response' es el arreglo de objetos JSON que contiene los mensajes iniciales
        response.forEach(message => {
            // Crear un nuevo elemento de mensaje
            let newMessage = crearMensaje(message);

            // Agregar el nuevo mensaje al contenedor del chat
            document.getElementById('chatBody').appendChild(newMessage);
        });

        // Hacer scroll hacia abajo para mostrar el mensaje más reciente
        scrollToBottom();

    }, "json");
}

// Llamar a la función para cargar los mensajes iniciales al cargar la página
window.onload = function() {
    cargarMensajesIniciales();
    scrollToBottom();  // Hacer scroll hacia abajo al cargar la página
};