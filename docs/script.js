// Configuración de la URL base de tu API
const API_URL = 'http://localhost:8080/api/carrito';

// Diccionario para mapear los nombres del HTML con un ID numérico para el backend
const catalogoProductos = {
    "Caja de verduras": 1,
    "Pack desayuno": 2,
    "Lote ecológico": 3,
    "Fruta fresca": 4,
    "Bolsa reutilizable": 5,
    "Menú semanal": 6
};

// Función inversa para recuperar el nombre del producto en la tabla del carrito
function obtenerNombrePorId(id) {
    return Object.keys(catalogoProductos).find(key => catalogoProductos[key] === id) || "Producto Desconocido";
}

// Se ejecuta cuando el HTML ha cargado completamente
document.addEventListener('DOMContentLoaded', async () => {
    await inicializarCarrito();

    const path = window.location.pathname;

    // Detectar en qué página estamos para ejecutar la lógica correspondiente
    if (path.includes('catalogo.html')) {
        configurarBotonesCatalogo();
    } else if (path.includes('carrito.html')) {
        cargarCarrito();
    } else if (path.includes('compra.html')) {
        // AÑADIMOS ESTA NUEVA LÍNEA
        cargarResumenCompra();
    }
});

/**
 * INICIALIZACIÓN: Crea un carrito con los datos obligatorios del backend
 */
async function inicializarCarrito() {
    let carritoId = localStorage.getItem('carritoId');

    if (!carritoId) {
        try {
            // Tu clase Carrito EXIGE un idUsuario y un correoUsuario válidos
            const datosNuevoCarrito = {
                idUsuario: 101, // Un ID ficticio para la práctica
                correoUsuario: "alumno@mercadoverde.com"
            };

            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(datosNuevoCarrito)
            });

            if (response.ok) {
                const carritoNuevo = await response.json();
                localStorage.setItem('carritoId', carritoNuevo.id);
                console.log("Nuevo carrito creado en la base de datos con ID:", carritoNuevo.id);
            } else {
                console.error("Error validando el carrito en el backend");
            }
        } catch (error) {
            console.error("Error al conectar con el backend:", error);
        }
    }
}

/**
 * CATÁLOGO: Configura los botones de "Añadir al carrito"
 */
function configurarBotonesCatalogo() {
    const botones = document.querySelectorAll('.product-card .button');

    botones.forEach(boton => {
        boton.addEventListener('click', async (evento) => {
            evento.preventDefault();

            // Extraer datos del HTML
            const card = evento.target.closest('.product-card');
            const nombre = card.querySelector('h2').innerText;
            const precioTexto = card.querySelector('.price').innerText;
            const precio = parseFloat(precioTexto.replace(',', '.').replace(' €', ''));

            // Construimos el JSON respetando los nombres de tu clase LineaCarrito de Java
            const linea = {
                idArticulo: catalogoProductos[nombre] || 99, // Mapeamos el nombre al ID
                precioUnitario: precio,
                unidades: 1
            };

            await añadirProductoAlCarrito(linea);

            // Redirigir al carrito tras añadir
            window.location.href = 'carrito.html';
        });
    });
}

/**
 * Llama a la API (POST) para añadir una línea
 */
async function añadirProductoAlCarrito(linea) {
    const carritoId = localStorage.getItem('carritoId');
    try {
        const response = await fetch(`${API_URL}/${carritoId}/lineas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(linea)
        });

        if (!response.ok) throw new Error("Datos rechazados por el backend (Revisa @NotNull o @Min)");
        console.log("Línea añadida correctamente");
    } catch (error) {
        console.error("Error:", error);
        alert("No se pudo añadir el producto. Asegúrate de que el backend está encendido.");
    }
}

/**
 * CARRITO: Carga los datos del backend y pinta la tabla HTML
 */
async function cargarCarrito() {
    const carritoId = parseInt(localStorage.getItem('carritoId'));
    if (!carritoId) return;

    try {
        const response = await fetch(API_URL);
        const carritos = await response.json();

        // Buscamos nuestro carrito entre todos los devueltos
        const miCarrito = carritos.find(c => c.id === carritoId);

        if (miCarrito) {
            pintarTablaCarrito(miCarrito);
        }
    } catch (error) {
        console.error("Error al descargar el carrito:", error);
    }
}

/**
 * Manipulación del DOM para pintar la tabla
 */
function pintarTablaCarrito(carrito) {
    const tbody = document.querySelector('tbody');
    const tfootTotal = document.querySelector('tfoot td');

    tbody.innerHTML = '';
    let sumaTotal = 0;

    // En tu Java, la lista se llama "lineas"
    const lineas = carrito.lineas || [];

    if (lineas.length === 0) {
        tbody.innerHTML = '<tr><td colspan="4" style="text-align:center; padding: 20px;">El carrito está vacío</td></tr>';
        tfootTotal.innerText = '0,00 €';
        return;
    }

    lineas.forEach(linea => {
        // Recuperamos el nombre usando el ID numérico guardado en la base de datos
        const nombreDelProducto = obtenerNombrePorId(linea.idArticulo);

        // Calculamos subtotal (si el backend no devolvió costeLinea actualizado)
        const subtotal = linea.costeLinea > 0 ? linea.costeLinea : (linea.precioUnitario * linea.unidades);
        sumaTotal += subtotal;

        const tr = document.createElement('tr');
        tr.innerHTML = `
            <th scope="row">${nombreDelProducto}</th>
            <td>${linea.precioUnitario.toFixed(2).replace('.', ',')} €</td>
            <td>${linea.unidades}</td>
            <td>
                ${subtotal.toFixed(2).replace('.', ',')} €
                <button onclick="eliminarLinea(${carrito.id}, ${linea.id})" class="button button-secondary" style="padding: 0.2rem 0.5rem; margin-left: 10px; font-size: 0.8rem; background-color: #d9534f; border:none; color: white;">Borrar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });

    // Actualizamos el total general
    tfootTotal.innerText = `${sumaTotal.toFixed(2).replace('.', ',')} €`;
}

/**
 * Llama a la API (DELETE) para borrar un producto
 */
async function eliminarLinea(carritoId, lineaId) {
    try {
        const response = await fetch(`${API_URL}/${carritoId}/lineas/${lineaId}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            // Recargar la vista de la tabla
            cargarCarrito();
        } else {
            alert("No se pudo eliminar el producto en el backend.");
        }
    } catch (error) {
        console.error("Error al intentar eliminar:", error);
    }
}

/**
 * COMPRA: Carga los datos del carrito para el resumen final
 */
async function cargarResumenCompra() {
    const carritoId = parseInt(localStorage.getItem('carritoId'));
    if (!carritoId) return;

    try {
        const response = await fetch(API_URL);
        const carritos = await response.json();
        const miCarrito = carritos.find(c => c.id === carritoId);

        if (miCarrito) {
            const lineas = miCarrito.lineas || [];

            let cantidadTotalProductos = 0;
            let precioTotalEstimado = 0;

            // Recorremos las líneas para sumar todo
            lineas.forEach(linea => {
                cantidadTotalProductos += linea.unidades;

                const subtotal = linea.costeLinea > 0 ? linea.costeLinea : (linea.precioUnitario * linea.unidades);
                precioTotalEstimado += subtotal;
            });

            // Escribimos los resultados en el HTML usando los ID que creamos
            const elementoCantidad = document.getElementById('resumen-cantidad');
            const elementoTotal = document.getElementById('resumen-total');

            if (elementoCantidad) {
                elementoCantidad.innerText = cantidadTotalProductos;
            }
            if (elementoTotal) {
                elementoTotal.innerText = `${precioTotalEstimado.toFixed(2).replace('.', ',')} €`;
            }
        }
    } catch (error) {
        console.error("Error al cargar el resumen de la compra:", error);
    }
}