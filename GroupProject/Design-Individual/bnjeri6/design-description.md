## Introduction
The job comparison app is designed to assist a user in evaluating job offers against their current job. It provides functionality for entering and editing current job details, adding and editing job offers, add comparison settings and assign them weights, and compare job offers, it ranks the jobs from best to worst based on a computed job score. 
This document outlines how I have integrated each of the given requirements in my design.



### Requirement 1.
## Requirement Details.
When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  
## Description.
This requirement is not handled in my design since it will be handled by the UI implementation. This will be done by creating a menu with the four items above as menu items.



### Requirement 2.
## Requirement Details.
When choosing to enter current job details, a user will:
Be shown a user interface to enter (if it is the first time) or edit all the details of their current job.
Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
## Description.
I have handled this requirement by defining two classes the User class and the Job class. 
The user class has attributes, methods and relationships about the user while the job class has attributes, methods and relationships of a given job. They are defined below:

### 1. User
**Attributes:**
- `userID` (int): A unique identifier for the user.
- `username` (String): The username of the user.
- `currentJob` (Job): The current job details of the user.

**Methods:**
- `editCurrentJobDetails()`: Allows the user to enter or edit current job details.
- `addJobOffer()`: Allows the user to enter a new job offer.
- `editJobOffer()`: Allows the user to edit an existing job offer.
- `adjustComparisonSettings()`: Allows the user to adjust the comparison settings.
- `compareJobs()`: Compares the given list of job offers against the current job.

**Relationships:**
- A `User` has one `currentJob`.


### 2. Job
**Attributes:**
- `jobId` (Int): The unique identifier of the job.
- `title` (String): The title of the job.
- `company` (String): The name of the company offering the job.
- `location` (String): The location of the job.
- `costOfLiving` (Int): The cost of living in the location of the job.
- `yearlySalary` (Double): The annual salary offered for the job.
- `yearlyBonus` (Double): The annual bonus offered for the job.
- `trainingAndDevFund` (Double): The amount of money allocted to Training and Development Benefits provided with the job.
- `leaveTime` (Int): The number of leave days allocated every year of the job.
- `teleworkDaysPerWeek` (Int): The number of days per week assigned for telework in the job.

**Methods:**
- `getJobDetails()`: Retrieves the details of the job.
- `saveJobDetails()`: enter or update and Save the details of the job.



### Requirement 3.
## Requirement Details.
When choosing to enter job offers, a user will:
- Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
- Be able to either save the job offer details or cancel.
- Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
## Description:
For the parts involving showing a User Interface, this document does not cover that since it will be implemented in the UI implementation.
For the details of the offer, I will use the Job class above to add the details of the job offer. I have also added the JobOffer Class
which categorizes a job as a JobOffer. Below are the attributes, methods and relationships about the job Offer.

### JobOffer Class.
**Attributes:**
- `offerID` (Int): A unique identifier for the job offer.
- `job` (Job): The details of the job offer.

**Methods:**
- `getOfferDetails()`: Retrieves the details of the job offer.
- `saveOfferDetails()`: enter or update and save the details of the job offer.

**Relationships:**
- A `JobOffer` is associated with one `Job`.
- Many `JobOffer` are associated with one `User`.



### Requirement 4.
## Requirement Details.
When adjusting the comparison settings, the user can assign integer weights to: Yearly salary, Yearly bonus, Training and Development Fund, Leave Time, Telework Days per Week.
## Description:
For this relationship I have defined a Comparison Settings class with different attributes, methods and relationships that allow the user to assign weights to the settings as shown below:

### ComparisonSettings Class.
**Attributes:**
- `yearlySalaryWeight` (Int): The weight assigned to the yearly salary.
- `yearlyBonusWeight` (Int): The weight assigned to the yearly bonus.
- `trainingAndDevFundWeight` (Int): The weight assigned to the training and development fund.
- `leaveTimeWeight` (Int): The weight assigned to the leave time.
- `teleworkDaysPerWeekWeight` (Int): The weight assigned to the telework days per week.

**Methods:**
- `getComparisonSettings()`: Retrieves the current comparison settings.
- `saveComparisonSettings()`: Sets or updates and saves the comparison settings.

**Relationships:**
- A `ComparisonSettings` is associated with one `User`.



### Requirement 5.
## Requirement Details.
When choosing to compare job offers, a user will:
Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
Select two jobs to compare and trigger the comparison.
Be shown a table comparing the two jobs:
Be offered to perform another comparison or go back to the main menu.
## Description:
This is not represented in my design since it will be handled in the UI implementation. This is because it involves showing a list of job offers, selecting two jobs, showing a table and being offered to perform another tasks all which are requirements that can only be implemented in the UI.



### Requirement 6.
## Requirement Details.
When ranking jobs, a job’s score is computed as the weighted average of:
AYS + AYB + TDF + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
## Description:
This is not represented in the UML design since I have decided that the job ranking computation will be done in the UI. At the point of displaying a list of job offers from best to worst, the computation will be done in the UI then the list is displayed.




### Requirement 7.
## Requirement Details.
The user interface must be intuitive and responsive
## Description:
This is not represented here since, the UI being intuitive and responsive can only be done in the User Interface.



### Requirement 7.
## Requirement Details.
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
## Description:
I have not represented this in my design since, the concept of a single system running on the app can only be implemented in the GUI.







