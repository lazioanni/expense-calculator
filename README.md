# expense-calculator
A Spring Boot application for expense calculation. It is built with
JAVA 17 and Spring Boot 3.4.2, using Lombok to reduce boilerplate code
and Thymeleaf for the user interface.
Additionally, I used ChatGPT to generate the HTML template and integrate it with the controller.


# Technologies Used
- Java 17
- Spring Boot 3.4.2
- Lombok
- Thymeleaf (for HTML templates)
- SLF4J (for logging events)
- JUnit (for testing)
- Bootstrap (for styling)

## Application Structure


- index.html → Main page using Thymeleaf & Bootstrap.
- ExpenseController → Handles user input and binds Thymeleaf attributes to Java code.
- ExpenseDTO → Data Transfer Object for passing user input to the Controller.
- ExpenseCalculator → Main calculation interface, implemented in ExpenseCalculatorImpl.

### Utility Classes
- DiscountUtils → Handles discount calculations.
- ExpenseCalculatorUtils → Extracts distance & computes additional charges.

### Enums
- FuelType → Maps fuel type to cost per kilometer.
- VehicleType → Maps vehicle type to max passengers.

### Testing
The application includes test cases for all major components.

