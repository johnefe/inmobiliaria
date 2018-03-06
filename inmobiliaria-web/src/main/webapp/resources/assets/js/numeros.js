var numero = "";
var cadena = [];

function numero9() {

	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 9);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 9;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 9);
	}

}
function numero8() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 8);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 8;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 8);
	}
}
function numero7() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 7);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 7;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 7);
	}
}
function numero6() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 6);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 6;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 6);
	}
}
function numero5() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 5);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 5;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 5);
	}
}
function numero4() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 4);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 4;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 4);
	}
}
function numero3() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 3);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 3;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 3);
	}

}
function numero2() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 2);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 2;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 2);
	}

}
function numero1() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 1);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 1;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 1);
	}
}
function numero0() {
	document.getElementById("cedula").focus();

	if (cadena.length != 0) {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		console.log(pos);
		cadena.splice(pos, 0, 0);
		var cadena2 = cadena;
		patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
		nuevaCadena = cadena2.replace(patron, nuevoValor);
		document.getElementById("cedula").value = nuevaCadena;
		console.log(cadena);
		console.log(pos);
	} else {
		var input = document.getElementById("cedula");
		var pos = input.selectionStart;
		numero = document.getElementById("cedula").value;
		numero += 0;
		document.getElementById("cedula").value = numero;
		cadena.splice(pos, 0, 0);
	}

}

function borrar() {
	document.getElementById("cedula").focus();

	if (document.getElementById("cedula").value == "") {
		cadena = [];
	}

	var input = document.getElementById("cedula");
	var pos = input.selectionStart;

	cadena.splice(pos - 1, 1);
	var cadena2 = cadena;
	patron = /,/g, nuevoValor = "", cadena2 = cadena2.toString();
	nuevaCadena = cadena2.replace(patron, nuevoValor);
	document.getElementById("cedula").value = nuevaCadena;

}

function setValue(){
	numero = "";
	cadena = [];
	document.getElementById("cedula").value = "";
}

/*(function() {

	setInterval(function() {
		var el = document.getElementById('blink');
		if (el.className == 'luz on') {
			el.className = 'titulo-tv';
		} else {
			el.className = 'luz on';

		}
	}, 1000);

})();*/

//(function($)

 /*   $(document).ready(function()
    {
        $.ajaxSetup(
        {
            cache: false,
            beforeSend: function() {
                $('#content').hide();
               
            },
            complete: function() {
               
                $('#content').show();
            },
            success: function() {
               
                $('#content').show();
            }
        });
        var $container = $("#content");
       $container.load("digiturno_tv.xhtml");
        var refreshId = setInterval(function()
        {
           $container.load('digiturno_tv.xhtml');
        }, 9000);
});*/

