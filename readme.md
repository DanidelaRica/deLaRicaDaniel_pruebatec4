# API de Reservas de Vuelos y Habitaciones de Hotel

API para gestionar reservas de vuelos y habitaciones de hotel para una agencia de viajes.

## Descripción

Esta API permite a los clientes de la agencia de viajes realizar reservas de vuelos y habitaciones de hotel, consultar disponibilidad, precios y realizar reservas.

## Funcionalidades

- Gestión de reservas de vuelos y habitaciones de hotel.
- Consulta de disponibilidad de vuelos y habitaciones de hotel.
- Calcula precios de reservas.
- Registro de clientes y pasajeros.
- Gestión de usuarios y roles de acceso.

## Requisitos

- Java 8 o superior.
- Maven 3.x
- Spring Boot 2.x
- Base de datos MySQL

## Instalación

1. Clona el repositorio: `git clone https://github.com/tuusuario/agencia-reservas-vuelos-hotel.git`
2. Importa el proyecto en tu IDE.
3. Configura la conexión a la base de datos en `application.properties`.
4. Ejecuta el proyecto.

## Uso

1. Realiza una solicitud HTTP a los endpoints para realizar reservas, consultar disponibilidad, etc.
2. Autentica a los usuarios y gestiona los tokens de acceso.
3. Utiliza los datos de respuesta para mostrar información a los clientes.

## Endpoints Principales

- `POST /agency/flights/availableFlights`: Realiza una reserva de vuelo.
- `POST /agency/rooms/room-booking/new`: Realiza una reserva de habitación de hotel.
- `GET /agency/flights/availableFlights`: Consulta disponibilidad de vuelos.
- `GET /agency/rooms/available`: Consulta disponibilidad de habitaciones de hotel.
- Otros endpoints para gestionar usuarios, clientes, etc.

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una rama con una nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Haz tus cambios y haz commit (`git commit -am 'Agrega nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Crea un pull request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más detalles.

## Contacto

Para más información, contáctame en danidelarica@gmail.com.

## Créditos

Este proyecto fue desarrollado por [Tu Nombre](https://github.com/tuusuario).
