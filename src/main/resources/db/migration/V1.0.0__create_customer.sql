DROP TABLE IF EXISTS MARKETING;
  
CREATE TABLE MARKETING (
  id INT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  template VARCHAR(4000) NOT NULL,  
  PRIMARY KEY (id)
);