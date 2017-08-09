CREATE TABLE `servletdb`.`EMPLOYEE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,                  
  `EMPNAME` varchar(255) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `servletdb`.`EMPLOYEE`(`ID`,`EMPNAME`,`SALARY`) 
VALUES(null, "Rahamathullah", "10000000000");
INSERT INTO `servletdb`.`EMPLOYEE`(`ID`,`EMPNAME`,`SALARY`) 
VALUES(null, "John", "2000");
INSERT INTO `servletdb`.`EMPLOYEE`(`ID`,`EMPNAME`,`SALARY`) 
VALUES(null, "Michel", "3030");