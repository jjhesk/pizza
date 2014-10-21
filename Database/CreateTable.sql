CREATE TABLE User (
	userName varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	userType ENUM('cashier', 'chef', 'customer', 'manager') NOT NULL,
	PRIMARY KEY (userName)
);

CREATE TABLE MenuItem (
	menuItemID int NOT NULL AUTO_INCREMENT,
    name varchar(255),
	price double NOT NULL,
	description varchar(255),
	status ENUM('active', 'expired') NOT NULL,
	PRIMARY KEY (menuItemID)
);

CREATE TABLE UserOrder (
	orderID int NOT NULL AUTO_INCREMENT,
	customerName varchar(255),
	customerAddress varchar(255),
    status ENUM('new', 'pending', 'cancelled', 'complete'),
	type ENUM('delivery', 'pickup', 'eatin'),
	total double,
	PRIMARY KEY (orderID)
);

CREATE TABLE OrderItem (
	orderItemID int NOT NULL AUTO_INCREMENT,
	quantity int,
	menuItemID int,
	orderID int,
	FOREIGN KEY (menuItemID) REFERENCES MenuItem(menuItemID),
	FOREIGN KEY (orderID) REFERENCES UserOrder(orderID),
	PRIMARY KEY (orderItemID)
);

CREATE TABLE DailySpecial (
	dailySpecialID int NOT NULL AUTO_INCREMENT,
	status ENUM('active', 'expired') NOT NULL,
	price double,
	PRIMARY KEY (dailySpecialID)
);

CREATE TABLE DailySpecialItem (
	dailySpecialItemID int NOT NULL AUTO_INCREMENT,
	dailySpecialID int,
	menuItemID int,
	FOREIGN KEY (menuItemID) REFERENCES MenuItem(menuItemID),
    FOREIGN KEY (dailySpecialID) REFERENCES DailySpecial(dailySpecialID),
    PRIMARY KEY (dailySpecialItemID)
);