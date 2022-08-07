# API REST de peliculas hecha con Spring Boot, aplicando Spring Security y JWT para autenticacion de usuarios

### Base de datos
- PostgreSQL

## Entidades

### User
Es el usuario al que se le permite acceder a la informacion almacenada siendo previamente autenticado.

- Atributos
    - Username
    - Email
    - Password

### Movie
Es la pelicula la cual viene representando la informacion que esta protegida en base de datos.

- Atributos
  - Titulo
  - Director
  - Productora
  - Estreno
  - Disponible

### Ejecutar API localmente

#### Preparaciones
- Ajustar el nombre de la base de datos en la cual van a persistir los datos. Asegurarse de ajustarlo en el archivo *application.properties*.
- 

1. Clonar el repositorio y correr a traves de un IDE.
2. Clonar el repositorio, crear un contenedor en Docker para alojar el API y la base de datos.
3. Clonar el repositorio y subir el proyecto a Github. Crear un espacio en Heroku para alojar el API y acceder a esta a traves de un navegador.
4. Correr el artefacto *spring-security-cifrado-0.0.1-SNAPSHOT.jar* situado en la carpeta target.
   1. Ubicarse en la carpeta target en cmd.
   2. insetar el siguiente comando: 
    ```
   java -jar spring-security-cifrado-0.0.1-SNAPSHOT.jar
    ```
   
