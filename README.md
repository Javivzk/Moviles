# Phono Store
Actividad de aprendizaje de 1º de DAM de las 
asignaturas de Programacion, Bases de datos y Entornos de 
desarrollo.
Realizada por Javier Vizcaino Olivares

Funcionalidades de Programacion:

● Dar de alta: Se pueden dar de alta datos de 2 entidades al menos

● Listado y Vista Detalle: Se podrá listar toda la información de las 2 entidades que
hayas decidido que se pueden dar de alta y se podrá visualizar, haciendo click en
cada uno de ellos, la información completa sobre dicho elemento

● Funcionalidad (búsqueda): Los usuarios podrán realizar búsquedas de las 2
entidades con los que hayas decidido trabajar

● Dar de baja: Se podrá dar de baja cualquiera de las entidades existentes (por
ejemplo desde la vista Listado/Vista Detalle)

● Diseño: Se utilizará el framework Bootstrap para diseñar la aparencia de la
aplicación web

● Login de usuarios: Implementa un sistema de login de forma que los usuarios de la
web puedan iniciar/cerrar sesión

● Repositorio GitHub para el proyecto: Crea un repositorio en GitHub para guardar
el código del proyecto. Utiliza Git Flow para subir los cambios que vayas haciendo

● Funcionalidad (modificar): Se podrá modificar la información de cualquier
elemento desde su vista detalle.

● Funcionalidad (relaciones): Implementa funcionalidades en la web que permitan
establecer relaciones entre datos de diferentes tablas. Por ejemplo: que un usuario
pueda comprar un producto

● Zona privada: Diseña una zona privada para los usuarios, modificar su perfil

Entornos de Desarrollo:

● Crea un repositorio en GitHub para almacenar el código del proyecto de forma que
dispongan de las ramas master y develop. Configura también el fichero
README.md del repositorio para que explique en que consiste la aplicación y
enumere los puntos que has realizado

● Para cada nueva funcionalidad se creará una nueva rama feature y se fusionará con
develop mediante Pull Request:
    ○ Crear las 3 clases
    ○ Crear las opciones de menú para registrar objetos de las 3 clases
    ○ Crear las opciones de menú para visualizar los objetos creados

● Configura un job en Jenkins para obtener el código del proyecto del repositorio y
que se compile y empaquete el proyecto

● Añade al Job la configuración necesaria para que el código del proyecto sea
analizado por un SonarQube

● Instala y ejecuta VisualVM y monitoriza el rendimiento y el uso de memoria de tu
aplicación 


● Configura maven para poder lanzar un análisis de código del proyecto directamente
con esta herramienta

● Añade algunos test JUnit al proyecto y configura el Job de Jenkins para que se
ejecuten cada vez que éste se lance
    ○ Test para comprobar que se puede crear cada uno de los objetos, invocando
    al constructor y comprobando luego los valores de sus atributos
    ○ Comprobar el método equals de al menos una de las clases

● Añade una release del proyecto al repositorio para que los usuarios puedan
descargarse tu aplicación para usarla directamente
