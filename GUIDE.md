# USER GUIDE FOR MAINTENANCE
This easy-to-use, step-by-step guide aims to inform the user of the Work
Wizard’s content and instruct the user on how to set up and run the application. 


## Procedures
The following steps allow you to easily set up and deploy the application as a web app: 
### Cloning Project:
Open Git Bash. 

Use the git clone command:
git clone https://gitlab.com/wgu-gitlab-environment/student-repos/jereid7/d424-software-engineering-capstone.git

Navigate to the Project Directory:
cd your-repository

### Build the Project:
mvn clean package

### Deploying Project:
Set up an account on Heroku. 
Select the “Create a new app” button.
Follow the following git commands to add the Heroku project as a remote repository: 
	heroku login
	cd ~/appname
	git init
	git add .
	git commit -m "first commit"
	heroku create 
	git push heroku master

### Attach the Heroku PostgreSQL Database along with its configuration in the project:
•	Use these git commands to set up the database:
o	heroku addons:create heroku-postgresql
o	heroku config
o	heroku pg 
### Add these dependencies to the pom.xml :

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
</dependency>


### In the application.properties file write: 
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.maxActive=10
spring.datasource.maxIdle=5
spring.datasource.minIdle=2
spring.datasource.initialSize=5
spring.datasource.removeAbandoned=true

###	Add this configuration bean:

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
}

### Finally, push your code to Heroku for a final time.
### Visit the URL given to you when creating the application in Heroku. 





# USER GUIDE FOR USER PERSPECTIVE
The aim of this step-by-step guide is to inform the user on the Work Wizard’s content and instruct the user on how to utilize the web application.

## Login and Signup
When opening up Work Wizard, you will be greeted by the landing page and then prompted to either Register an account or Log-in with an existing account.
If you already have an account, click on “Log-in” to enter your account username and password. If you don’t have an account, click on the “Register” button.
In order to successfully create a new account, you must create a unique username and password, and the password must include at least one special character.

## Dashboard
Once you are logged in, you will arrive at the Dashboard page and see three boxes: Consultations, Supply List, and Pay.

## Create, Edit, Delete Consultations
To create new consultations, you will utilize the first box labeled, “Consultations” and select the “new” button.
This will open a new page with a form where you can enter in a consultation name, phone number, date, and notes. You can submit
the form by pressing the “Save” button. Once the new consultation is saved it will automatically populate in the consultation table.
You can update the consultation by clicking the name of the consultation in the table. This will open a new page that shows all of the 
previously entered details. Press the “Update” button to edit the information. Press “save” to submit the form. To delete a consultation, select
the consultation you’d like to delete. It will open the details page once again. Select the “Delete” button to delete the consultation. 

## Create, Edit, and Delete Supply List
To create a new supply on the Supply List, first select the “new” button in the box labeled “Supply List”.
This will open a new page where you will be prompted to enter a supply name and a quantity. The quantity needs to be more than 1. 
To submit the form, press the “Add” button. Once the supply is added, it will automatically populate to the Supply List table.
To update a supply, select the supply you wish to update. This will open a new page that will provide the details for the supply. 
Press the “Update” button to edit the details. Select the next “Update” button. To delete the supply you can either press the red “X” box next to
the supply you wish to delete or select the name of the supply and click the “Delete” button.

## Create and Delete Payment Information
To create a new Payment, select the “new” button in the payment box. This will open a new page where you will
fill out a form with the date, amount, and percentage. Select the “Add” button to save the new payment. Once saved, the payment
will populate in the payment table. You can delete a payment by selecting the red “X” box next to the payment you wish to delete.

## Reports
To access the reporting feature, in the Payment box, click on “Generate Report” near the top right of the box.

A report detailing each saved payment will appear with a sum of all payments at the bottom of the report. 

## Delete Account
To delete an account, select the user’s full name at the top right corner. This will open an account settings page where you will be
prompted to either go back to the dashboard or delete the account. Select “Delete Account” and select “OK” on the pop up message. 


### URL LINK TO GITLAB 
https://gitlab.com/wgu-gitlab-environment/student-repos/jereid7/d424-software-engineering-capstone

### URL LINK TO HOSTED WEBSITE
https://d424-capstone-d7449667325d.herokuapp.com/

### URL LINK TO PANOPTO VIDEO
https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=aa3860aa-f502-43ce-b002-b13400151e67

# UNIT TEST PLAN

## Introduction
### Purpose
The purpose of the unit test plan is to validate the behavior of a specific function, method, or class by isolating it from the rest of the code to ensure that the Work Wizard Employee Dashboard behaves as intended. This unit test plan helps catch and fix bugs within the codebase to provide a well-functioning application. 

### Overview
Implementing the unit test plan was important for the creation of the Work Wizard Employee Dashboard application. This can be seen in the two unit test scripts provided within the code to ensure that key methods within the application function as intended. 
	These tests cover two specific scenarios possible within the application. The first test ensures that all supply lists can be returned when prompted. The second unit test ensures that the ‘specialCharacters’ method returns true when the password contains a special character. This approach ensures that the application’s methods can function correctly in isolation.
•	Supply List Retrieval: This test ensures that the service layer can successfully fetch and return all supply lists in the database specific to the current user which is a fundamental aspect of the application’s functionality. 
•	Special Character in Password: This test checks if a password contains at least one special character and returns true if it does, false otherwise. This is crucial for password validation when users create accounts. 
## Test Plan
### Items
•	Development Environment: A development environment with Java installed, as the application is built using Java. IntelliJ IDEA or Eclipse for development.
•	Database: PostgreSQL is used as the application’s database. Set up PostgreSQL database and configure the application to connect to it. Set up tables for testing purposes. 
•	Test Framework: JUnit is commonly used for testing Java applications. It’s integrated with Spring Boot, so no additional setup is required.
•	HTTP Integration Testing: For testing HTTP requests and responses, you can use Spring’s MockMvc for integration testing.
•	Application Source Code: Clone the source code from the Git repository. Ensure you have a copy of the codebase to run your tests against. 

### Features
•	User Authentication and Validation: Test user registration validation.
•	Retrieve Supply Lists: Test the ability to retrieve all supply lists when prompted.
### Deliverables
•	Test Scripts: A collection of test scripts that are part of the source codebase, written using the JUnit testing framework and Spring’s MockMvc for integration testing.

### Tasks
•	Set up the development environment with Java, JUnit, and Spring’s MockMvc. Needs
•	Ensure unit test scripts that are outlined in the Test Plan are in the correct place, such as supply list and special characters. The test scripts are already apart of the codebase. 
•	Make sure that the test data can be used during testing by prefilling data in the SQL file. This includes passwords and supply lists. 
•	Execute the test scripts using JUnit and review the test results. 
•	Analyze the test results and ensure that there are no failed tests.

### Needs
•	Software Requirements:  Ensure that you have the Java Development Kit (JDK) installed. Spring Boot requires JDK 8, 11, or 16. An IDE such as IntelliJ IDEA or Eclipse should be downloaded. Ensure that all required dependencies and libraries are included in the project configuration file.  
•	Testing Tools and Libraries. JUnit is used as the testing framework and Spring’s MockMvc is used for integration testing. Having these tools installed and configured will ensure easy test execution. 
•	Access to Source Code and Test Scripts: During the testing process you will need to have access to the application’s source code and test scripts. Setting up a VCS will be needed to manage code changes. 
•	Test data such as passwords and supply lists can be used through the prefilled data from the SQL file provided.

### Pass/Fail Criteria
### Supply List Retrieval: 
Pass: The supply list details are successfully retrieved upon request.
Fail: The supply list details are not successfully retrieved upon request.
### Special Characters in Password:
Pass: The password includes a special character and the register form is submitted.
Fail: The password does not include a special character and the register form is not submitted. 

When a test fails during the testing process, the following strategies will be applied to resolve the matter:
Identify the root cause: Examine the test to find the cause of the issue. Review error messages and code to understand the problem. 
Document the problem: Create a report to document the issue. Explain which test failed, the error message and the steps taken during the testing process. 

### Procedures
The testing process began by finding critical functionalities and features of the application that required testing. Test cases were then created to cover various scenarios, with clear definitions of expected outcomes and criteria for pass/fail results. 
Next, the test environment was set up, ensuring all necessary dependencies and frameworks were in place. This included verifying the proper installation and configuration of tools such as JUnit and MockMvc for the Spring Boot application. 
Tests were executed using JUnits test runner, and the results were analyzed. Any failed tests were identified, and the test cases were refined iteratively to address any issues. After executing the tests, the results were documented. This included recording pass/fail outcomes, noting any found issues or modifications made to the code or test cases. Finally, the test plan was updated to reflect the outcomes of the testing process. 
Results
The test results for the Work Wizard Employee Dashboard were gathered after running the test scripts using the JUnit testing framework. These are examples of the results:
•	Supply List Retrieval: The test was passed when the getAll method of the SupplyListService successfully retrieved and returned all supply lists associated with the user. The test ensured that the findByUsername method of the UserRepository returned the correct user, which was then used to retrieve the expected supply lists from the mockSupplyListRepostory. The test then compared the size of the expected and actual supply lists to verify that all supply lists were fetched correctly.
•	Special Characters in Password: The test was passed when a user submitted a password that included a special character. The test used a password string with a special character and called the specialCharacters method to check if it returns true. 
