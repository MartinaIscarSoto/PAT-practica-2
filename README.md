## Práctica 2: pasos y lógica
### Esto sirve como una guía para entender los pasos que se han seguido para elaborar la práctica, así como lógica implementada

##### Tengo que hacer: CRUD
POST - /api/carrito (Crear carrito)
GET - /api/carrito (Listado de carritos)
GET - /api/carrito/<id-carrito> (Descr. de 1 carrito con id <id-carrito>)
PUT - /api/carrito/<id-carrito> (Modificar el carrito)
DELETE - /api/carrito/<id-carrito> (Borrar el carrito)

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
