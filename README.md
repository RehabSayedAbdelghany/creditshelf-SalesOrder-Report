# creditshelf-SalesOrder-Report

### Installation Locally

Clone this repository:

```sh
$ git@github.com:RehabSayedAbdelghany/creditshelf-SalesOrder-Report.git
```

Build  using the below command to run the application

```sh
$ cd <your_git_home>/creditshelf-SalesOrder-Report
$ mvn clean install 
$ mvn spring-boot:run
```
### Installation Using Docker

Build  using the below command to run the application

```sh
$ docker build -f Dockerfile -t creditshelf . 

$ docker run -p 9090:9090 creditshelf -d
```
### How to use
###  In-memory database (H2)
http://localhost:9090/h2-console/     with JDBC url: jdbc:h2:mem:creditshelfdb 
   username : sa  
   password : 123456
   
#### Swagger UI can be used
  http://localhost:9090/swagger-ui.html#/
### Upload File APIs 
POST  http://localhost:9090/upload/product

PSOT  http://localhost:9090/upload/sales

###  all the sales by company

GET  http://localhost:9090/salesorder/<Company name>


###  company revenues report
GET  http://localhost:9090/salesorder/<Company name>/revenues


###  company net profit report
GET  http://localhost:9090/salesorder/<Company name>/netprofits



