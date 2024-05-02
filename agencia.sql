-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-05-2024 a las 22:43:24
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agencia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `id` bigint(20) NOT NULL,
  `ciudad` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `disponible_desde` date DEFAULT NULL,
  `disponible_hasta` date DEFAULT NULL,
  `precio_por_noche` double NOT NULL,
  `reservado` bit(1) NOT NULL,
  `tipo` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `hotel_id` bigint(20) DEFAULT NULL,
  `codigo` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`id`, `ciudad`, `disponible_desde`, `disponible_hasta`, `precio_por_noche`, `reservado`, `tipo`, `hotel_id`, `codigo`) VALUES
(1, 'Barcelona', '2024-05-05', '2024-05-10', 100, b'0', 'Doble', 1, 'HAB001');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL,
  `ciudad` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `codigo` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id`, `ciudad`, `codigo`, `nombre`) VALUES
(1, 'Ciudad Barcelona', 'H001', 'Hotel Nuevo'),
(3, 'Madrid', 'H002', 'Hotel Viejo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `apellido`, `nombre`) VALUES
(1, 'Perez', 'Juan'),
(2, 'Gonzalez', 'Alfonso'),
(7, 'Ramirez', 'Daniel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id` bigint(20) NOT NULL,
  `cantidad_personas` int(11) NOT NULL,
  `codigo_hotel` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `monto_total` double NOT NULL,
  `nombre_cliente` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tipo_habitacion` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `habitacion_id` bigint(20) DEFAULT NULL,
  `vuelo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `cantidad_personas`, `codigo_hotel`, `fecha_fin`, `fecha_inicio`, `monto_total`, `nombre_cliente`, `tipo_habitacion`, `habitacion_id`, `vuelo_id`) VALUES
(2, 2, 'H001', '2024-05-05', '2024-05-01', 500, 'Juan Pérez', 'Doble', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva_persona`
--

CREATE TABLE `reserva_persona` (
  `reserva_id` bigint(20) NOT NULL,
  `persona_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `reserva_persona`
--

INSERT INTO `reserva_persona` (`reserva_id`, `persona_id`) VALUES
(2, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

CREATE TABLE `vuelo` (
  `id` bigint(20) NOT NULL,
  `asientos_disponibles` int(11) NOT NULL,
  `destino` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fecha_ida` date DEFAULT NULL,
  `numero_vuelo` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `origen` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `precio_por_persona` double NOT NULL,
  `reservado` bit(1) NOT NULL,
  `tipo_asiento` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `vuelo`
--

INSERT INTO `vuelo` (`id`, `asientos_disponibles`, `destino`, `fecha_ida`, `numero_vuelo`, `origen`, `precio_por_persona`, `reservado`, `tipo_asiento`) VALUES
(1, 100, 'Barcelona', '2024-05-01', 'ABC123', 'Madrid', 500, b'0', 'Primera Clase');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk3l154yy3cd6te71b3vc7wlp7` (`hotel_id`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_7shn083e1hv1x1cevkstme4h6` (`codigo`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtr5bg864m3dseko7gif2bl239` (`habitacion_id`),
  ADD KEY `FK4tvli56vtc61fgd5dktdd24l` (`vuelo_id`);

--
-- Indices de la tabla `reserva_persona`
--
ALTER TABLE `reserva_persona`
  ADD KEY `FK4tocrgkw93pdbyk9kvnoaje7n` (`persona_id`),
  ADD KEY `FKf79pj8qtyrmj4lonjih1agjf4` (`reserva_id`);

--
-- Indices de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `FKk3l154yy3cd6te71b3vc7wlp7` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `FK4tvli56vtc61fgd5dktdd24l` FOREIGN KEY (`vuelo_id`) REFERENCES `vuelo` (`id`),
  ADD CONSTRAINT `FKtr5bg864m3dseko7gif2bl239` FOREIGN KEY (`habitacion_id`) REFERENCES `habitacion` (`id`);

--
-- Filtros para la tabla `reserva_persona`
--
ALTER TABLE `reserva_persona`
  ADD CONSTRAINT `FK4tocrgkw93pdbyk9kvnoaje7n` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
  ADD CONSTRAINT `FKf79pj8qtyrmj4lonjih1agjf4` FOREIGN KEY (`reserva_id`) REFERENCES `reserva` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
