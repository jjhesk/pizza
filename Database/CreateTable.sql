CREATE TABLE User (
	userID int NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	PRIMARY KEY (userID)
);

CREATE TABLE MenuItem (
	menuItemID int NOT NULL AUTO_INCREMENT,
	price float NOT NULL,
	description varchar(255),
	status ENUM('active', 'expired') NOT NULL,
	PRIMARY KEY (menuItemID)
);

CREATE TABLE UserOrder (
	orderID int NOT NULL AUTO_INCREMENT,
	customerAddress varchar(255),
    status ENUM('new', 'active', 'cancelled', 'complete'),
	type ENUM('delivery', 'pickup', 'eatin'),
	total float,	
	userID int,
	FOREIGN KEY (userID) REFERENCES User(userID),
	PRIMARY KEY (orderID)
);

CREATE TABLE OrderItem (
	orderItemID int NOT NULL AUTO_INCREMENT,
	quantity varchar(255) NOT NULL,
	menuItemID int,
	orderID int,
	FOREIGN KEY (menuItemID) REFERENCES MenuItem(menuItemID),
	FOREIGN KEY (orderID) REFERENCES UserOrder(orderID),
	PRIMARY KEY (orderItemID)
);

CREATE TABLE DailySpecial (
	dailySpecialID int NOT NULL AUTO_INCREMENT,
	status ENUM('active', 'expired') NOT NULL,
	price float,
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