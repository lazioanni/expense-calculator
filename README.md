# expense-calculator
A Spring Boot aaplication for expense calucalation. It is built with
JAVA 17 and Spring Boot 3.4.2, using Lonbok to reduce boilerplate code
and Thymeleaf for the user interface.

# Technologies Used
<li>Java 17</li>
<li>Spring Boot 3.4.2 </li>
<li>Lombok </li>
<li>Thymeleaf (for HTML templates) </li>
<li>SLF4J (for logging events) </li>
<li>JUnit (for testing) </li>
<li>Bootstrap (for styling) </li>

#  Application Structure

<li>index.html: The main page, implemented using Thymeleaf and Bootstrap.</li>
<li>ExpenseController: Handles user input and binds Thymeleaf attributes to Java code.</li>
<li>ExpenseDTO: Data Transfer Object for passing user input to the Controller.</li>
<li>ExpenseCalculator Interface: The main calculation interface, implemented in ExpenseCalculatorImpl.</li>
<li>Utility Classes:
 <ul>
      <li>DiscountUtils: Handles discount calculations.</li>
      <li>ExpenseCalculatorUtils: Extracts distance and computes additional charges.</li>
    </ul>
</li>
<li>Enums:'
 <ul>
      <li>FuelType: Maps fuel type to cost per kilometer.</li>
      <li>VehicleType: Maps vehicle type to the maximum number of passengers.</li>
    </ul>
</li>
<li>Testing: The application includes test cases for all major components.</li>

