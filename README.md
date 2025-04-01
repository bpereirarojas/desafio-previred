# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.

## Objetivo:
Crear una API RESTful utilizando Spring Boot que gestione usuarios y tareas, aplicando buenas prácticas, principios SOLID y utilizando las tecnologías especificadas.

## Requisitos Técnicos:
### Java:
- Utiliza Java 17 para la implementación.
- Utiliza las características de Java 17, como lambdas y streams, cuando sea apropiado.
- Utilizar Maven como gestor de dependencias

### Spring Boot 3.4.x:
- Construye la aplicación utilizando Spring Boot 3.4.x (última versión disponible).

### Base de Datos:

- Utiliza una base de datos H2.
- Crea tres tablas: usuarios, tareas y estados_tarea.
- La tabla usuarios debe contener datos pre cargados.
- La tabla estados_tarea debe contener estados pre cargados.

### JPA:
- Implementa una capa de persistencia utilizando JPA para almacenar y recuperar las tareas.

### JWT (JSON Web Token):

- Implementa la autenticación utilizando JWT para validar usuarios.

### OpenAPI y Swagger:

- Documenta la API utilizando OpenAPI y Swagger.

## Funcionalidades:
### Autenticación:
- Implementa un endpoint para la autenticación de usuarios utilizando JWT. 

### CRUD de Tareas:
- Implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las tareas.

## Consideraciones:
### Seguridad:
- Asegúrate de que las operaciones CRUD de tareas solo sean accesibles para usuarios autenticados.

### Documentación:
- Utiliza OpenAPI y Swagger para documentar claramente la API.
- Puntos adicionales si se genera el API mediante metodologia API First. Generar el archivo openapi.yml Nota: Ejemplo Plugin Maven groupId org.openapitools, artifactId openapi-generator-maven-plugin

### Código Limpio:
- Escribe código ordenado, aplicando buenas prácticas y principios SOLID.

### Creatividad
- Se espera dada la descripción del problema se creen las entidades y metodos en consecuencia a lo solicitado.

## Entregables:
### Repositorio de GitHub:
- Realiza un Pull request a este repositorio indicando tu nombre, correo y cargo al que postulas.
- Todos los PR serán rechazados, no es un indicador de la prueba.

### Documentación:
- Incluye instrucciones claras sobre cómo ejecutar y probar la aplicación.
- **Incluir Json de prueba en un archivo texto o mediante un proyecto postman** Nota: Si no va se restaran puntos de la evaluación

## Evaluación:
Se evaluará la solución en función de los siguientes criterios:

- Correcta implementación de las funcionalidades solicitadas.
- Aplicación de buenas prácticas de desarrollo, patrones de diseño y principios SOLID.
- Uso adecuado de Java 17, Spring Boot 3.4.x, H2, JWT, OpenAPI y Swagger.
- Claridad y completitud de la documentación.
- **Puntos extras si la generación de la API se realizo mediante API First**



# Guía de Ejecución y Pruebas de la Aplicación

Este documento describe los pasos necesarios para **clonar**, **ejecutar** y **probar** la aplicación, incluyendo el acceso a la base de datos H2, la autenticación con JWT y la documentación generada con OpenAPI/Swagger.

---

## 1. Clonar el Repositorio

1. Copia la URL del repositorio (por ejemplo, en GitHub).
2. En la terminal, navega hasta el directorio donde desees clonar el proyecto.
3. Ejecuta el comando:

```bash
git clone <URL-DEL-REPOSITORIO> 
```

---

## 2. Configurar el Proyecto

### Verificar Java y Maven

- **Java 17**  
  Asegúrate de tener instalado Java 17:
  ```bash
  java -version
  ```
- **Maven**  
  Comprueba que Maven esté disponible:
  ```bash
  mvn -version
  ```

### Revisar `application.properties`

En el archivo `src/main/resources/application.properties` se configuran aspectos clave, como:

```properties
spring.datasource.url=jdbc:h2:mem:gestion_tareas
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

Ajusta estos valores si es necesario.

---

## 3. Ejecutar la Aplicación

Desde la raíz del proyecto, puedes optar por:

```bash
mvn spring-boot:run
```

o

```bash
mvn clean install
java -jar target/<nombre-del-jar>.jar
```

La aplicación se iniciará normalmente en `http://localhost:8080`, a menos que hayas configurado otro puerto.

---

## 4. Probar la Aplicación

### 4.1. Consola de H2

1. Visita `http://localhost:8080/h2-console`.
2. Asegúrate de usar la misma URL indicada en `application.properties` (por defecto, `jdbc:h2:mem:gestion_tareas`).
3. Ingresa con usuario `sa` y sin contraseña (o lo que hayas definido).

Podrás examinar las tablas creadas (`tareas`, `task_status`, `usuarios`, etc.) e inspeccionar la información almacenada.

### 4.2. Documentación Swagger (OpenAPI)

1. Accede a `http://localhost:8080/swagger-ui.html` (o la ruta configurada).
2. Encontrarás la **documentación interactiva** donde podrás:
    - Ver los endpoints disponibles.
    - Enviar peticiones de prueba (GET, POST, PUT, DELETE).
    - Visualizar respuestas y códigos de estado.

### 4.3. Autenticación y JWT

- **Endpoint de autenticación**: `POST /api/auth/login`
  ```json
  {
    "email": "admin@example.com",
    "password": "password123"
  }
  ```
  Respuesta (ejemplo):
  ```json
  {
    "token": "eyJhbGciOiJIUzI1Ni..."
  }
  ```
- Usa el **token JWT** en el header `Authorization: Bearer <TOKEN>` para los demás endpoints protegidos.

### 4.4. CRUD de Tareas

- **GET /api/tasks**  
  Lista las tareas (normalmente asociadas al usuario autenticado).
- **GET /api/tasks/{id}**  
  Retorna detalles de la tarea cuyo `id` se especifique.
- **POST /api/tasks**  
  Crea una nueva tarea.
  ```json
  {
    "title": "Nueva Tarea",
    "description": "Descripción",
    "user": { "id": 1 },
    "status": "PENDIENTE"
  }
  ```
- **PUT /api/tasks/{id}**  
  Actualiza una tarea existente.
- **DELETE /api/tasks/{id}**  
  Elimina la tarea por su `id`.

### 4.5. Pruebas con cURL o Postman

- **Ejemplo Login** (cURL):
  ```bash
  curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"email": "bryan@desafiotecnico.cl", "password": "password123"}' \
    http://localhost:8080/api/auth/login
  ```
- **Crear Tarea** (cURL) con token JWT:
  ```bash
  curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer <TOKEN>" \
    -d '{"title":"Tarea cURL","description":"Creada vía cURL","user":{"id":1}}' \
    http://localhost:8080/api/tasks
  ```

En **Postman**, puedes importar la especificación OpenAPI que se expone en `/v3/api-docs/` o simplemente crear las peticiones manualmente con los encabezados necesarios.

---

## 5. Registro de Logs y Manejo de Errores

- En la consola donde ejecutas la aplicación se mostrarán los logs, posibles errores y traces de excepciones.
- Para incrementar el nivel de detalle, puedes configurar el nivel de log (`logging.level.org.hibernate.SQL=debug`, por ejemplo).

---

## 6. Resumen

1. **Clonar** el repositorio y verificar Java/Maven.
2. **Ejecutar** la aplicación con `mvn spring-boot:run`.
3. **Probar** los endpoints:
    - **H2 Console**: `http://localhost:8080/h2-console`
    - **Swagger UI**: `http://localhost:8080/swagger-ui.html`
    - **Autenticación**: `POST /api/auth/login`
    - **CRUD Tareas**: `/api/tasks` (GET, POST, PUT, DELETE)
4. **Revisar** los logs en caso de errores.
5. **Postman o cURL** para pruebas adicionales, autenticándote con el JWT donde sea requerido.

Con estos pasos, tienes una guía completa para iniciar, probar y validar el correcto funcionamiento de la aplicación.
```
