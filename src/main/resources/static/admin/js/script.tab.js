$('#tablaDatos').DataTable({
	language: returnLenguaje(), info: false, bPaginate: true, dom: "Bfrtlp", responsive: true,
	buttons: {
		dom: {
			button: {
				className: 'btn'
			}
		},
		buttons: [
			{
				extend: "copy",
				text: '<i class="fas fa-copy"></i>',
				titleAttr: 'Copiar datos',
				className: 'btn btn-primary'
			},
			{
				extend: "csv",
				text: '<i class="fas fa-file-csv"></i>',
				titleAttr: 'Descargar CSV',
				className: 'btn btn-primary'
			},
			{
				extend: "excel",
				text: '<i class="fas fa-file-excel"></i>',
				titleAttr: 'Descargar en Excel',
				className: 'btn btn-primary',
				excelStyles: {
					"template": [
						"blue_medium",
						"header_blue",
						"title_medium"
					]

				}
			},
			{
				extend: "pdf",
				text: '<i class="fas fa-file-pdf"></i>',
				titleAttr: 'Descargar en PDF',
				className: 'btn btn-primary'
			},
			{
				extend: "print",
				text: '<i class="fas fa-print"></i>',
				titleAttr: 'Imprimir',
				className: 'btn btn-success'
			}
		]
	}
});

$('#tablaProductos').DataTable({
	language: returnLenguaje(), info: false, bPaginate: true, dom: "Bfrtlp", responsive: true
});

function returnLenguaje() {
	response = JSON.stringify({
		"sProcessing": "Procesando...",
		"sLengthMenu": "Mostrar _MENU_ registros",
		"sZeroRecords": "No se encontraron resultados",
		"sEmptyTable": "No se han encontrado datos",
		"sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		"sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
		"sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
		"sSearch": "Buscar:",
		"sInfoThousands": ",",
		"sLoadingRecords": "Cargando...",
		"oPaginate": {
			"sFirst": "Primero",
			"sLast": "Último",
			"sNext": "Siguiente",
			"sPrevious": "Anterior"
		},
		"oAria": {
			"sSortAscending": ": Activar para ordenar la columna de manera ascendente",
			"sSortDescending": ": Activar para ordenar la columna de manera descendente"
		},
		"buttons": {
			"copy": "Copiar",
			"colvis": "Visibilidad"
		}
	});
	return JSON.parse(response);
}