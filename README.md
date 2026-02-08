# README de la práctica 2
Tabla con la descripción de cada endpoint (método, ruta, cuerpo, descripción, posibles respuestas)
Hay que crear un CRUD (Create, Read, Update, Delete)
## CRUD
Create - POST: /api/carrito (Crear carrito)
Read - GET: /api/carrito (Ver listado de carritos)
Read - GET:  /api/carrito/<id-carrito> (Descr. de 1 carrito con id <id-carrito>)
Update - PUT: /api/carrito/<id-carrito> (Modificar el carrito)
Delete - DELETE:  /api/carrito/<id-carrito> (Borrar el carrito)

## Entender formato JSON de carrito
EJ:
{
    "idCarrito": 3,
    "idArticulo": 1,
    "descripcion": "Pantalón",
    "unidades": 2,
    "precioFinal":50
}

| Método | Ruta | Cuerpo | Descripción |  Posibles Respuestas
| :---: | :---: | :---: |:---: |:---: |
| POST, http://localhost:8080/api/carrito | 
| GET carritos - (todos), http://localhost:8080/api/carrito|
| GET carrito/{idCarrito} , http://localhost:8080/api/carrito/{idCarrito}|
| DELETE , http://localhost:8080/api/carrito/{idCarrito} |
| PUT, http://localhost:8080/api/carrito/{idCarrito} |

## Práctica 2: pasos y lógica
### Esto sirve como una guía para entender los pasos que se han seguido para elaborar la práctica, así como lógica implementada
##### Opcional:
Introducir gestión de errores - si no hay nada que borrar que me ponga un mensaje
crear lógica que para cada idArticulo s una descripción distinta
hacer que el Interger que es la clave del HashMap cambie con el idCarrito
-Seguridad

#### Primer commit:
  Clase Practica2Application
  Carpeta model, con Carrito dentro, en Carrito inicializar los atributos con sus getters y setter
  Carpeta controller, con CarritoController: Creación de Get y Post.
#### Segundo Commit
  Borrar un carrito por idCarrito. Delete
#### Tercer Commit
  Modificar un carrito por idCarrito. Put
