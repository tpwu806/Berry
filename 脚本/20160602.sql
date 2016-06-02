CREATE TABLE `threshold_threshold` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MAXTEMPERATURE` int(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MINTEMPERATURE` int(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MAXHUMIDITY` int(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MINHUMIDITY` int(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;