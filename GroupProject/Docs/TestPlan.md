# Test Plan


**Author**: Team 047.


## 1 Testing Strategy


### 1.1 Overall strategy
#### Objective
The objective of the test plan is to ensure that the JobComparison application meets all the specified requirements.

#### Scope
- In-Scope - The features and functionalities of the system.
- Out-Of-Scope - The hardware used such as mobile phone or laptop.

#### Strategy
We will write tests to cover the different sections of code. 
The test cases will be written for each functionality once its development is done.
We will then use the JUNIT testing framework to validate that our test cases succeed.
We will then conduct manual tests such as usability and acceptance tests to ensure that the application meets all requirements.

#### Assumptions
- The development team will provide stable builds for testing.

#### Risks and Mitigation
- Risk: The project has tight deadlines.
- Mitigation: Prioritize critical tests and write test cases after developing each feature.


### 1.2 Test Selection
For our Test selection, we will use both the Black-box and White-box techniques for testing.

#### White-Box Testing:
White-box testing involves testing the internal structures and working of the application.
For this we will use the below:
- Unit Testing: Automated Tests with JUnit to verify that the individual units of code work as expected.
- Integration Testing: Automated Tests with JUnit to verify that the different modules in the application work together.

#### Black-Box Testing:
This testing involves testing the funtionality of the appliiction without looking at internal code.
For this we will use the below:
- Acceptance Testing: Manual tests to verify that the application meets business requirements and is ready for delivery.
- Usability Testing: Manual tests to ensure that the applicaton is user-friendly and meets user experience expectations.


For the Automated tests, the test cases for each functionality will be implemented by the developer handling a specific functionality.
For the Manual tests, all members of the team will be involved with the testing.


### 1.3 Adequacy Criterion
To assess the quality of our test cases, we will use the Test metrics below:
- Test Coverage: Ensure that at least 70% of our application code is tested.
- Pass Rate: Ensure a pass rate of at least 95% for each of our test cases.


### 1.4 Bug Tracking
Whenever a bug is filed, the owner of the section of code where the bug is will be required to resolve and close the bug.
Incase of any enhancement requests, the developer working on that feature will implement 


### 1.5 Technology
The Technology that we will use for most of our automated tests will be JUNIT.
Others will be determined as we continue with the development.



## 2 Test Cases
For this deliverable, we attempt manual testing to test the app's functionality.

#### Main Menu:
- **Test case 1**: *Enter Current Job Details* button is disabled when the current job exists.
  - Manual Steps:
    - From main menu, select *Enter Current Job Details*.
  - Expected Result:
    - The button is disabled.
  - Actual Result:

- **Test case 2**: *Edit Current Job Details* button is disabled when no job offer exists.
  - Manual Steps:
    - From main menu, select *Edit Current Job Details*.
  - Expected Result:
    - The button is disabled.
  - Actual Result:

- **Test case 3**: *Compare Job Offers* button is disabled when current job + job offer is less than 2.
  - Manual Steps:
    - From main menu, select Compare Job Offers.
  - Expected Result:
    - The button is disabled.
  - Actual Result:
    ![compareJobOffers_tc3.png](images/compareJobOffers_tc3.png)

#### Enter Current Job Details
- **Test case 4**: Previously entered current job details should be displayed in EditText input fields and persist after app restart
  - Manual Steps:
    - Click on Enter Current Job Details from MainActivity
    - Input the details and click Save Current Job, then go back to Main Menu
    - Close app and restart
    - Click on Edit Current Job Details from MainActivity
  - Expected Result:
    - All the EditText input fields should have text set to the previously entered values
    - These should be editable
  - Actual Result:
    - UI has input fields pre-filled with saved current job details as per expectations
      ![image](images/editcurrentjobdetails_tc1a.png)
    - Database also shows the saved current job details
      ![image](images/editcurrentjobdetails_tc1b.png)
    
- **Test case 5**: User enters current job details.
  - Manual Steps:
  - Expected Result:
  - Actual Result:
  
- **Test case 6**: User fails to enter one field in the current job details
  - Manual Steps:
  - Expected Result:
  - Actual Result:

- **Test case 7**: User edits/updates current job details
  - Manual Steps:
  - Expected Result:
  - Actual Result:

#### Edit Current Job Details
- **Test case 8**: Previously entered current job details should be displayed in EditText input fields and persist after app restart
  - Manual Steps:
    - Click on Enter Current Job Details from MainActivity
    - Input the details and click Save Current Job, then go back to Main Menu
    - Close app and restart
    - Click on Edit Current Job Details from MainActivity
  - Expected Result:
    - All the EditText input fields should have text set to the previously entered values
    - These should be editable
  - Actual Result:
    - UI has input fields pre-filled with saved current job details as per expectations
    ![image](images/editcurrentjobdetails_tc8a.png)
    - Database also shows the saved current job details
    ![image](images/editcurrentjobdetails_tc8b.png)

- **Test case 9**: Edit Current Job Details should update the exiting current job in the database instead of creating another current job
  - Manual Steps:
    - Ensure there is a saved current job, if not enter one
    - Click on Edit Current Job Details from MainActivity
    - Edit the pre-filled details from the previously saved job, click Update Current Job
  - Expected Result:
    - Toast to confirm successful update
    - Database Job table should have the updated values for the current job row (indicated by jobType = 0)
  - Actual Result:
    - Toast "Current job details updated"
    ![image](images/editcurrentjobdetails_tc9a.png)
    - Job table row for current job updated with the edited values
    ![image](images/editcurrentjobdetails_tc9b.png)

- **Test case 10**: Disable Edit Current Job Details button if no current job has been saved yet
  - Manual Steps:
    - Increment the database version to delete all entries
    - Start the app and verify the Job table has no records
  - Expected Result:
    - The Edit Current Job Details button in MainActivity should be disabled
  - Actual Result:
    - button disabled as expected
    ![image](images/editcurrentjobdetails_tc10.png)

- **Test case 10**: User enters an incorrect data input type in the edit current job details
  - Manual Steps:
  - Expected Result:
  - Actual Result:

#### Enter Job offer
- **Test case 12**: User enters job offer details
  - Manual Steps:
  - Expected Result:
  - Actual Result:
- **Test case 13**: User fails to enter one field in the job offer details
  - Manual Steps:
  - Expected Result:
  - Actual Result:

- **Test case 14**: User enters an incorrect datatype input field in the job offer details
  - Manual Steps:
  - Expected Result:
  - Actual Result:

#### Compare Job Offers
- **Test case 15**: show a table of ranking of job offers with no current job 
  - Manual Steps:
    - Enter 3 job offers via Enter Job Offers button.
    -From main menu, selects Compare Job Offers.
  - Expected Result:
    - A table of 3 job offers with ranking from high to low.
    - No entry is indicated as current job.
  - Actual Result:
    - Show picture here

- **Test case 16**: show a table of ranking of job offers with current job 
  - Manual Steps:
    - Enter 3 job offers via Enter Job Offers button.
    - Enter a current job via Enter Current Job Details button.
    - From main menu, selects Compare Job Offers.
  - Expected Result:
    - A table of 4 job offers with ranking from high to low.
    - The current job entry is marked bold and italic to indicate as current job.
  - Actual Result:
    - Show picture here

  
- **Test case 17**: Select 2 offers and compare
  - Manual Steps:
    - TODO
  - Expected Result:
    - TODO
  - Actual Result:

#### Enter and Adjust Comparison Settings

- **Test case 18**: Verify that Input for comparison settings are integers.
  - Manual Steps:
    - From main menu, select Adjust comparison settings.
    - Select any edittext to input a weight.
  - Expected Result:
    - The keyboard that appears to input values is a numbers-only keyboard.
  - Actual Result:
    ![comparisonsettings_tc1.png](images/comparisonsettings_tc1.png)

- **Test case 19**: Retrive and show the existing comparison settings.
  - Manual Steps:
    - From main menu, select Adjust comparison settings.
  - Expected Result:
    - The comparison settings screen shows the currently saved weights for each setting.
  - Actual Result:
    ![comparisonsettings_tc2.png](images/comparisonsettings_tc2.png)

- **Test case 20**: Update the yearly bonus weight to 7.
  - Manual Steps:
    - From main menu, select Adjust comparison settings.
    - In the comparison settings screen, select the yearly bonus text and change the value to 7.
    - Click on the Save Weights Button.
  - Expected Result:
    - The comparison settings screen shows weight for yearly bonus as 7.
    - A toast message showing Weights updated is displayed at the botton of the screen.
  - Actual Result:
    ![comparisonsettings_tc3.png](images/comparisonsettings_tc3.png)
