# Code Test

# Implemented three end points

- Transactions list
  * http://localhost:8080/webapp-coding-test/rest/transactions
- Transaction filter based on transaction type
  * http://localhost:8080/webapp-coding-test/rest/transactions/{type}
  * http://localhost:8080/webapp-coding-test/rest/transactions/sandbox-payment
- Total amount for transaction type
  * http://localhost:8080/webapp-coding-test/rest/transactions/total/{type}
  * http://localhost:8080/webapp-coding-test/rest/transactions/total/sandbox-payment

Intigrated with Spring security
# API credentials
  username : rest
  password : rest

## Build:
  Busing using maven.
  Using eclipse right click on pom.xml and run maven install.
  It will create war file and deploy the war file in Tomcat 7.
 
