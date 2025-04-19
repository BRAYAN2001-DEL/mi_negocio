# Mi Negocio

Sistema de gestión de clientes y direcciones para facturación.

## Requisitos
- Java 21
- Spring Boot
- JPA/Hibernate
- Base de datos configurada (Postgres)

## Ejecución
1. Clona el proyecto
2. Configura `application.properties`
3. Ejecuta con `mvn spring-boot:run`
4. Prueba la API con Postman en:
   - `POST /api/clientes/crear`
   - `GET /api/clientes/buscar?valor=Marcelo`
   - `PUT /api/clientes/{id}`
   - `DELETE /api/clientes/{id}`
   - `POST /api/clientes/{id}/direccion`
   - `GET /api/clientes/{id}/direcciones`
