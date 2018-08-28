#### Test environment:  
OS: macOS 10.13.6 / Ubuntu 16.04  
Java: 1.8.0_131  
Tomcat: 8.5.15  
MySQL: 5.7.18  
Maven: 3.5.0  

#### Run:  
1. set up database:
    * create new database name SirenStore;
    * restore the database use the backup file SirenStore.sql.  $ mysql -u USERNAME -p SirenStore < SirenStore.sql
    * go to /src/main/resources/applicationContext.xml config your datasource
2. install the application to tomcat:
    * use maven to package up the .war file.  $ mvn package
    * copy the /target/SirenStore.war to %Tomcat_Home%/webapps
    * start tomcat
3. open your browser and visit localhost:8080/SirenStore, the port number is based on your tomcat setting