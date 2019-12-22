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

Clone this repository:

```sh
$ git@github.com:RehabSayedAbdelghany/creditshelf-SalesOrder-Report.git
```

Build  using the below command to run the application

```sh
$ docker build -f Dockerfile -t creditshelf . 

$ docker run -p 9090:9090 creditshelf -d
```
### How to use
#### can test the APIs from swagger http://localhost:900/swagger-ui.html#/
