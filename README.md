# rmm
To launch this application, please make the following steps:

# PREREQUISITES
- JDK 1.8
- Install Apache Maven 3.3 or a newer version.
- PostgreSQL
- Eclipse Photon (Optional)


# VIEWING THE PROJECT IN ECLIPSE (OPTIONAL)
If you don't want to view the project in Eclipse, please continue with the next step. Otherwise open a terminal and inside the folder rmm-services/, run the following command:

mvn eclipse:eclipse

Then, open it on Eclipse

# INSTALLING THE APPLICATION
1) Using PostgreSQL, inside the database "postgres", open a query tool and execute the file "RMM-DDL.sql" and then the file "RMM-DML.sql". These archives are inside de folder rmm-services/scripts/. After running these scripts, verify if the schema named "dsandoval" was created.
2) Change the data base credentials (spring.datasource.username and spring.datasource.password) in the file "application.properties" that is inside the folder rmm-services/src/main/resources
3) Open a terminal and inside the folder rmm-services/, run the following command:

mvn spring-boot:run

**NOTE:** If the server 8080 is already used, please run the following command:

mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=[PORT_NUMBER]

where: [PORT_NUMBER] is the port number to be used.

4) Open a web browser and open the URL
http://localhost:8080/rmm/consolidated/valentina

The application should show some data.

# USING THE APPLICATION
**NOTE 1:** To make the CRUD requests, you could POSTMAN or your preferred API develepment environment.
**NOTE 2:** There are a customer called "valentina" that has some services and devices. There is another customer called "pepe" that have no services and have no devices.
**NOTE 3:** If you want to add a new customer, please insert the new one inside the table "customer".

The services published in this application are:

#ENDPOINTS FOR DEVICES

METHOD | PATH | BODY | EXPLANATION
--- | --- | --- | ---
GET | rmm/deviceCustomer/[userName] | NA | Retrieves the devices data for a given [userName]
POST | rmm/deviceCustomer/[userName] | {"name":"[device_name]","osId":"[OS_ID]"} | Insert a new device for a given customer, where:[userName] is the name of the consumer, [device_name] is the name of the device, [OS_ID] is the operative system ID. There are 3 of them: WIN_WORKSTATION, WIN_SERVER, MAC
DELETE | rmm/deviceCustomer/[userName]/[device_name] | NA | Delete a device for a given customer, where: [userName] is the name of the consumer, [device_name] is the name of the device to be deleted
PUT | rmm/deviceCustomer/[userName] | {"name":"[device_name]","osId":"[OS_ID]"} | Update device for a given customer, where: [userName] is the new name of the consumer, [device_name] is the new name of the device, [OS_ID] is the new operative system ID. There are 3 of them: WIN_WORKSTATION, WIN_SERVER, MAC

#ENDPOINTS FOR DEVICE SERVICES

METHOD | PATH | BODY | EXPLANATION
--- | --- | --- | ---
GET | rmm/serviceCustomer/[userName] | NA | Retrieves the services chosen for a [userName]
POST | rmm/serviceCustomer/[userName]/[service] | NA | Insert a new service for a given customer, where:[userName] is the name of the consumer, [service] is the service ID. There are 4 of them: ANTIVIRUS, PSA, CLOUDBERRY, TEAMVIEWER
DELETE | rmm/serviceCustomer/[userName]/[service] | NA | Delete a service for a given customer, where:[userName] is the name of the consumer, [device_name] is the name of the service to be deleted

#ENDPOINT FOR CONSOLIDATED DATA

METHOD | PATH | BODY | EXPLANATION
--- | --- | --- | ---
GET | rmm/consolidated/[userName] | NA | Retrieves all the consolidated data for a given 																									customer [userName]



