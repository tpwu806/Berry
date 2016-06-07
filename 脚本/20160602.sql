CREATE TABLE `threshold_threshold` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '阈值设置编号',
  `MAXTEMPERATURE` int(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '温度上限',
  `MINTEMPERATURE` int(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '温度下限',
  `MAXHUMIDITY` int(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '湿度上限',
  `MINHUMIDITY` int(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '湿度下限',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;