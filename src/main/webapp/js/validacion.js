$(document).ready(function() {
    $("#afiliadoForm").on("submit", function(event) {
        var nombre = $("#nombre").val();
        var direccion = $("#direccion").val();
        var telefono = $("#telefono").val();

        if (!nombre || !direccion || !telefono) {
            alert('Todos los campos son obligatorios.');
            event.preventDefault();
            return;
        }
    });
});
