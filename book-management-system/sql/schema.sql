USE books;
CREATE TABLE books(
   id INT PRIMARY KEY AUTO_INCREMENT,
   author VARCHAR(25) NOT NULL,
   title VARCHAR(40) NOT NULL,
   genre VARCHAR(50) NOT NULL,
   price FLOAT NOT NULL
);
