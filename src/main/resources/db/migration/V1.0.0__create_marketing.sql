DROP TABLE IF EXISTS MARKETING;
  
CREATE TABLE MARKETING (
  id INT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  template VARCHAR(4000) NOT NULL,  
  PRIMARY KEY (id)
);

insert into MARKETING (id, name, template) values (1001, 'Welcome', '<html><title>Welcome Financial Service</title><body><h2>Welcome!</h2><p>Hi <customer>, we’re glad you’re here! </p><p>You can enjoy deposit and invest in great portfolio we have to ofer to you to improve your benefits.</p><br><p align="center">Your Information:</p><table align="center"><tr><th>Identification:</th><td><identification></td></tr><tr><th>Email: </th><td><email></td></tr></table><br> <p> Best Regards</p><br><p> Financial Service Team</p></body></html>');