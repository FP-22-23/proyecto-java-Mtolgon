# Proyecto del Segundo Cuatrimestre Fundamentos de Programación (Curso  \<XX\>/\<YY\>)
Autor/a: \<Toledo González Manuel\>   uvus:\<GBS1161\>


EL dataset contiene infomracion sobre ediciones de paginas de la wikipedia.

## Estructura de las carpetas del proyecto

* **/src**: Directorio con el codigo fuente.
  * **fp.tipos**: Paquete que contiene los tipos del proyecto.
  * **fp.tipos.test**: Paquete que contiene las clases de test del proyecto.
  * **fp.common**: Paquete que contiene los tipos auxiliares del proyecto
  * **fp.utiles**:  Paquete que contiene las clases de utilidad. 
* **/data**: Contiene el dataset o datasets del proyecto
    * **edits.csv**: El dataset contiene datos sobre ediciones de paginas de la wikipedia
    
    
## Estructura del *dataset*

Aquí debes describir la estructura del dataset explicando qué representan los datos que contiene y la descripción de cada una de las columnas. Incluye también la URL del dataset original.
La url original del dataset es https://www.kaggle.com/datasets/shradhapj/wikipedia-edits?resource=download.
Originalmente contenia 9 columnas de las que se han usado 8 y se ha generado una de forma aleatoria.Cada fila contiene informacion sobre una vez que una página de la wikipedia fue editada
El dataset está compuesto por \<N\> columnas, con la siguiente descripción:

* **\<action>**: de tipo \<cadena\>, representa la accion realizada por el usuario en este caso solo se recogen las de tipo edit
* **\<change_size>**: de tipo \<entero\>, representa el número de caracteres añadidos o eliminados.
* **\<geo_ip>**: de tipo \<cadena\>, representa el lugar desde el que se editó la página, es null si el usuario está registrado sino contiene ciudad,latitud,pais,region y longitud.
* **\<is_bot>**: de tipo \<boolean\>, representa si la pagina fue editada por un bot o no.
* **\<is_minor>**: de tipo \<boolean\>, representa si el usuario era menor de edad cuando edito la página.
* **\<page_title>**: de tipo \<cadena\>, representa el titulo de la pagina editada.
* **\<url>**: de tipo \<cadena\>, representa la url actual y antigua de la pagina editada.
* **\<user>**: de tipo \<cadena\>, representa el nombre de usuario de la persona que edito la página si no estaba registrada será una ip en Ipv4 o Ipv6.
* **\<fecha>**: de tipo \<fecha\>, representa la fecha en la que se edito la página, se generó de forma aleatoria.

....

## Tipos implementados

Describe aquí los tipos que usas en tu proyecto.

### Tipo Base-edits
Contiene informacion sobre una vez que una página concreta de la wikipedia fue editada

**Propiedades**:

- action, de tipo \<Action\>, consultable.Indica la accion realizada por el usuario 
- Changesize, de tipo \<Integer\>, consultable .Indica el número de carácteres cambiados 
- isBot, de tipo \<Boolean\>, consultable.Indica si la pagina fue editada por un bot.
- isMinor, de tipo \<Boolean\>, consultable y modificable.Indica si el usuario que edito la página era menor de edad 
- pageTitle, de tipo \<String\>, consultable y modificable.Indica el titulo de la página editada.
- geoip, de tipo \<Geoip\>, consultable.Indica El lugar desde el que se editó la página, es null si el usuario está registrado 
- user, de tipo \<String\>, consultable.Indica el nombre de usuario del usuario que editó la página. Será una ip si el usuario no está registrado 
- urls, de tipo \<lista\>, consultable y modificable.Contiene la direccion de la página antes y después de que fuese editada
- fecha, de tipo \<LocalDate\>, consultable.Indica la fecha en la que se editó la página
- anonymous, de tipo \<Boolean\>, consultable y derivada.Indica si el usuario está registrado o no. Se deriva de la propiedad geoip, si geoip tiene como valor null anonymous tendrá valor true.

- 
**Constructores**: 

- C1: contiene un parametrop por cada propieda básica del tipo.
- C2:  contiene un parametrop por cada propieda básica del tipo excepto geoip que se inicia como null.

**Restricciones**:
 
- R1:action no puede tomar un valor distinto de EDITS .
- R2: pagetitle no puede ser null.
- R3: fechaEdit no puede ser anterior al 15/1/2001(creación de la wikipedia) ni posterior a la fecha actual
 

**Criterio de igualdad**: dos objetos de tipo edits son iguales si tienen el mismo changeSize y pageTitle

**Criterio de ordenación**: se ordena por changeSize y por pageTitle(si lo hay).

**Otras operaciones**:
 
-	_método 1_: Descripción del método 1.
- ...

#### Tipos auxiliares
Action, enumerado. Puede tomar los valores EDITS,TALK o OTHER

GeoIp,record. Recibe una ciudad(String),país(String),region(String),latitud(Double) y longitud(Double)
### Factoría-FactoriaEdits
Objeto de clase factoria para contruir objetos de clse edits

-create: crea un objeto de tipo edits a partir de una cadena de texto. 
-ParseaGeoIp: crea un objeto de tipo GeoIp a partir de una cadena de texto.
-leerEdits:crea una lista de objetos de tipo edits a partir de un fichero.

### Tipo Contenedor-recopilacionEdits

Clase contenedora de los objetos de tipo edits.Representa una recopilación de páginas editadas de la wikipedia.

**Propiedades**:

- AutorReco, de tipo String, consultable. Representa el nombre del autor de la recopilacion
- creacionReco, de tipo LocalDate, consultable. Representa la fecha en la que se creo la recopilacion.
 - editsList, de tipo List, consultable.Representa una lista de objetos de tipo edits.
- 
**Constructores**: 

- C1: tiene un parametro por cada propiedad básica del tipo .
- C2: tiene un parapmetro para el AutorReco y creacionReco, crea una lista vacia.
- ...


**Criterio de igualdad**: Dos recopilaciones son iguales si lo son su autor y su fecha de creacion.



**Otras operaciones**:
 
-Integer numelementos: devuelve el numero de elementos de editsList
- void añadir: añade un elemento a editsList.
-void añadirColeccion:añade una lista de elementos a editsList
-eliminarElemento: elimina un elemento de editsList, puede recivir el objeto o su posicion.
-boolean ExisteUnEditsDeUsuario: devuelve true si hay al menos un edits hecho por un usario dado.
-Double mediaEditsPorUsuariosRegistrados: devuelve la media  de cuantos edits han sido hechos por usuarios registrados.
-List<edits> editsPorPais: devuelve una lista con todos los edits hechos en un pais dado.
-Map<Integer, List<edits>> editsPorAño:devuelve un Map que agrupa los edits por el año en que se hiceron
-Map<Integer, Integer> NumEditsPorCaracCambiados():devuelve un Map que relaciona en el número de caracteres cambiados con el número de edits en los que se ha cambiado ese número de caracteres.
