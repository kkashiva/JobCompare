# Use Case Model

#### **Author**: Team 047 | CS6300 Summer2024

## Use Case Diagram

![image](./images/use_case_diagram.png)

## Use Case Descriptions

#### Enter Current Job Details
- Requirements: User is prompted with inputs to enter the current job details. Users are able to save the information, cancel and return to the main menu.
- Pre-conditions: User does not have a current job. In another word, this should be the user's first time entering the current job details.
- Post-conditions: User’s current job is saved.
- Scenarios:
  - User enters job info and selects Save. The job info is saved as the current job for the user.
  - User enters job info and selects Cancel. User is returned back to the main menu screen.

#### Edit Current Job Details
- Requirements: User is able to edit the current job information.
- Pre-conditions: User needs to have an existing current job.
- Post-conditions: User's current job is updated with new info.
- Scenarios:
  - User edits job info and selects Save. The current job info is updated with new info.
  - User edits job info and selects Cancel. The current job is intact. User is returned to main menu screen.

#### Enter Job Offer
- Requirements: User is able to enter job offer information.
- Pre-conditions: N/A
- Post-conditions: A new job offer is saved for the given user.
- Scenarios:
  - User enters job offer and selects Save. The new job offer is saved. The screen should show options to enter another job offers or return to main menu or compare job offers.
  - User enters job offer and selects Cancel. The screen should show options to return to main menu, compare job offers.

#### Compare Job Offers
- Requirements: User is able to compare 2 job offers if they have a current job and a job offer saved.
- Pre-conditions: A current job and at least one saved job offer are needed for comparison.
- Post-conditions: A comparison between 2 selected jobs. A table of job offer rankings is showed. 
- Scenarios:
  - User selects 2 jobs from the list and selects Compare. The table of comparison is showed.
  - User selects the button to perform another comparison. The screen takes user back to the list of offers.

#### Adjust Comparison Settings
- Requirements: User can assign weight factors for each job offer.
- Pre-conditions: A current job exists or a job offer exists.
- Post-conditions: A job's attributes such as Yearly salary, Yearly bonus, Training and Development Fund, Leave Time, Telework Days per Week are assigned with weights.
- Scenarios:
  - User enters weight factors to a job's attributes. Those weight factors are saved into the job info.
  - User does not enter any weight factor. All the weight factors are considered equal. 
