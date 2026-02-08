# README de la práctica 2
Tabla con la descripción de cada endpoint (método, ruta, cuerpo, descripción, posibles respuestas)
Hay que crear un CRUD (Create, Read, Update, Delete)

Para facilitar a la hora de realizar la práctica se inicializa el HashMap con dos valores iniciales, con id's 1 y 2
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
| POST| http://localhost:8080/api/carrito | {"idCarrito": 3,"idArticulo": 1, "descripcion": "Pantalón", "unidades": 2, "precioFinal":50 } | Crea un nuevo carrito con su artículo y atributos de Carrito| 201 - Created
| GET carritos - (todos)| http://localhost:8080/api/carrito| No hace falta | Lee los carritos que haya creados, te devuelve todos los que tengas en fromato JSON separdos {atributos_Carrito1},{atributos_Carrito2},... | 200 - OK|
| GET carrito/{idCarrito} | http://localhost:8080/api/carrito/{idCarrito}|No hace falta | Lee los carritos por idCarrito y te devuelve el que tiene asignado ese id| 200 - OK, 404 - Not Found 
| DELETE | http://localhost:8080/api/carrito/{idCarrito} | No hace falta | Borra el carrito que conrresponde con el idCarrito| 200 - OK
| PUT |http://localhost:8080/api/carrito/{idCarrito} |{"idCarrito": X,"idArticulo": , "descripcion": "", "unidades": , "precioFinal": } | Coges el objeto Carrito asociado al idCarrito y cambias los atributos que quieras. Va a funcionar todo excepto si cambias idCarrito, ese cambio no lo va a registar porque se ha implementado que si ya existe el idCarrito que cambias, no lo registras. Pero si no existe y se intenta cambiar no va a parmitir el cambio ni incluirlo en la lista| 200 - OK, 400 - Bad Request, 404 - Not Found |

## Práctica 2: pasos y lógica
### Esto sirve como una guía para entender los pasos que se han seguido para elaborar la práctica, así como lógica implementada
##### Opcional:
- Introducir gestión de errores - si no hay nada que borrar que me ponga un mensaje
- Validaciones

#### Primer commit:
  Clase Practica2Application
  Carpeta model, con Carrito dentro, en Carrito inicializar los atributos con sus getters y setter
  Carpeta controller, con CarritoController: Creación de Get y Post.
#### Segundo Commit
  Borrar un carrito por idCarrito. Delete
#### Tercer Commit
  Modificar un carrito por idCarrito. Put
#### Resto de Commits
Para modificar cambios en el README y para añadir funcionalidades adicionales donde se implementan validaciones y respuestas de error
