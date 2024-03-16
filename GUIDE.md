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