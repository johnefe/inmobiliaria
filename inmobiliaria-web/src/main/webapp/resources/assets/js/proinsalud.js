//ERA PARA ACTUALIZAR CAMPOS DE MATERIALIZE
/*function updateFields() {
 $(document).ready(function() {
 Materialize.updateTextFields();
 });
 }*/
/**
 * Funcion para solo permitir numeros en un campo de texto Ejemplo
 * onkeypress="return onlyNumbers(event);"
 * 
 * @param e
 * @returns
 */
function onlyNumbers(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if ((tecla >= 48 && 57 >= tecla) || tecla == 8 || tecla == 0)
		return true;
	else
		return false;
}

function onlyNumbersWithEnter(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if ((tecla >= 48 && 57 >= tecla) || tecla == 8 || tecla == 0 || tecla == 13)
		return true;
	else
		return false;
}

/**
 * Funcion para no permitir pegar en un campo de text
 * 
 * @param e
 * @returns
 */
function noPaste(e) {
	var theEvent = e || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]|\./;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
}

/**
 * Muestra el spinner de la aplicación
 * 
 * @returns
 */
function showSpinner() {
	PF('statusDialog').show()
}

/**
 * Oculta el spinner de la aplicación
 * 
 * @returns
 */
function hideSpinner() {
	PF('statusDialog').hide()
}
/**
 * Pone el foco en el elemento con el id de parametro
 * 
 * @param id
 * @returns
 */
function setFocus(id) {
	$('#' + id).focus();
}

function password() {
	letras = 1;
	numeros = 1;
	mayusculas = 1;
	var texto = "";
	var nume = [ "2", "3", "4", "5", "6", "7", "8", "9" ];
	var min = [ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m",
			"n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" ];
	var may = [ "A",
		"B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N",
		"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" ];

	for (var i = 0; i < 8; i++) {
		if (letras <= 3) {
			num = Math.floor((Math.random() * min.length));
			texto = texto + min[num];
			letras++;
		}

		if (mayusculas <= 2) {
			num = Math.floor((Math.random() * may.length));
			texto = texto + may[num];
			mayusculas++;
		}

		if (numeros <= 3) {
			num = Math.floor((Math.random() * nume.length) + 1);
			texto = texto + num;
			numeros++;
		}
	}
	document.getElementById("txt_password").value = texto;
}

function synchronizerowsheight() {
	var $rows = $(document.getElementById('tbl_analitics_frozenThead')).find('tr');
	$rows.each(function() {
				var $row = $(this);
				$row.innerHeight($(document.getElementById('tbl_analitics_scrollableThead')).outerHeight());
			});
}


function onFinishDownload() {
	var btn = $(".btn_download_hide_delete");
	btn.click();
}
