-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-10-2024 a las 13:12:31
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel_management`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idpersona` int(11) NOT NULL,
  `codigo_cliente` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idpersona`, `codigo_cliente`) VALUES
(6, '10101');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante`
--

CREATE TABLE `comprobante` (
  `idcomprobante` int(11) NOT NULL,
  `idreserva` int(11) DEFAULT NULL,
  `tipo_comprobante` varchar(20) DEFAULT NULL,
  `num_comprobante` varchar(30) DEFAULT NULL,
  `igv` decimal(4,2) DEFAULT NULL,
  `costo_consumo` decimal(9,2) DEFAULT NULL,
  `costo_reserva` decimal(9,2) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `idhabitacion` int(11) NOT NULL,
  `numero` varchar(4) DEFAULT NULL,
  `piso` varchar(2) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  `caracteristicas` varchar(512) DEFAULT NULL,
  `precio_diario` decimal(7,2) DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `tipo_habitacion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`idhabitacion`, `numero`, `piso`, `descripcion`, `caracteristicas`, `precio_diario`, `estado`, `tipo_habitacion`) VALUES
(4, '21', '1', 'asda', 'sdaq', 34.00, 'Disponible', 'doble');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `idpersona` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apaterno` varchar(45) DEFAULT NULL,
  `amaterno` varchar(45) DEFAULT NULL,
  `tipo_documento` varchar(20) DEFAULT NULL,
  `numero_documento` varchar(8) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idpersona`, `nombre`, `apaterno`, `amaterno`, `tipo_documento`, `numero_documento`, `direccion`, `telefono`, `email`, `estado`) VALUES
(6, 'asd', 'ads', 'asd', 'DNI', '75934666', 'ada', '96857412', 'srfvqa', 'españa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `idreserva` int(11) NOT NULL,
  `idhabitacion` int(11) DEFAULT NULL,
  `idcliente` int(11) DEFAULT NULL,
  `idtrabajador` int(11) DEFAULT NULL,
  `tipo_reserva` varchar(30) DEFAULT NULL,
  `fecha_reserva` date DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `costo_total` decimal(7,2) DEFAULT NULL,
  `observacion` varchar(512) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`idreserva`, `idhabitacion`, `idcliente`, `idtrabajador`, `tipo_reserva`, `fecha_reserva`, `fecha_ingreso`, `fecha_salida`, `costo_total`, `observacion`, `estado`) VALUES
(16, 4, 6, NULL, 'Individual', '2024-10-01', '2024-10-07', '2024-10-09', 800.00, 'nada', 'Reservada'),
(17, 4, 6, NULL, 'Individual', '2024-10-02', '2024-10-10', '2024-10-13', 102.00, '', 'Reservada'),
(18, 4, 6, NULL, 'Individual', '2024-10-08', '2024-10-10', '2024-10-12', 68.00, 'nada', 'Reservada');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idpersona`);

--
-- Indices de la tabla `comprobante`
--
ALTER TABLE `comprobante`
  ADD PRIMARY KEY (`idcomprobante`),
  ADD KEY `idreserva` (`idreserva`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`idhabitacion`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`idpersona`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idreserva`),
  ADD KEY `idhabitacion` (`idhabitacion`),
  ADD KEY `idcliente` (`idcliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comprobante`
--
ALTER TABLE `comprobante`
  MODIFY `idcomprobante` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `idhabitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idpersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idreserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE CASCADE;

--
-- Filtros para la tabla `comprobante`
--
ALTER TABLE `comprobante`
  ADD CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`idreserva`) REFERENCES `reserva` (`idreserva`) ON DELETE CASCADE;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idhabitacion`) REFERENCES `habitacion` (`idhabitacion`) ON DELETE CASCADE,
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idpersona`) ON DELETE CASCADE,
  ADD CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`idtrabajador`) REFERENCES `trabajador` (`idpersona`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
