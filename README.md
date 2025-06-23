## Requirements

### Core Business logic

| Ticket Type | Age Rule | Price(AUD) | Notes                              |
|-------------|----------|------------|------------------------------------|
| CHILD       | <11      | $5.00      | 25% Discount if 3 or more children |
| TEEN        | 11-17    | $12.00     |                                    |
| ADULT       | 18-64    | $25.00     |                                    |
| SENIOR      | 65+      | $17.50     | 30% off Adult price                |
  ---------------------------------------------------------------------------

* The ID of the transaction
* Each individual type of movie ticket present in that transaction, ordered alphabetically, and it's quantity and total cost 
* The total cost of all movie tickets for that transaction


## Additional Notes on Requirements Document

- In the provided requirements PDF, the example responses incorrectly show `"transactionId": 1` for all sample responses, even when the corresponding request had a different `transactionId` (2 or 3).
- In this implementation, the API correctly preserves and returns the same `transactionId` from the request in the response.

## Tech Stack 
* Java 17
* Spring Boot 3.5.0
* Lombok
* JUnit 5
* Gradle


### Build and Run
./gradlew clean build
./gradlew bootrun

### Tests
./gradlew test

### Coverage Report

build/reports/jacoco/test/html/index.html

### API Endpoint 

 POST : http://localhost:8080/api/tickets/calculate

## Sample Request

`
        {
        "transactionId": 1,
        "customers": [
        { "name": "John", "age": 70 },     
        { "name": "Jane", "age": 5 },      
        { "name": "Bob", "age": 6 },       
        { "name": "Tom", "age": 8 },       
        { "name": "Alex", "age": 30 }     
        ]
        }`

## Expected Response
`
        {
        "transactionId": 1,
        "tickets": [
        { "ticketType": "ADULT", "quantity": 1, "totalCost": 25.0 },
        { "ticketType": "CHILD", "quantity": 3, "totalCost": 11.25 },
        { "ticketType": "SENIOR", "quantity": 1, "totalCost": 17.5 }
        ],
        "totalCost": 53.75
        }`


👩‍💻 Author
-Sudarvizhi Kadhiroli(Software Engineer)






