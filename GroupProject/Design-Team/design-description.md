# Design Description 
### Team 047 | Summer 2024

1. When the app is started, the user is presented with the main menu, 
which allows the user to 
(1) enter or edit current job details, 
(2) enter job offers, 
(3) adjust the comparison settings, or 
(4) compare job offers (disabled if no job offers were entered yet).
- >&emsp;To realize this requirement, we added class **User** and an automatically generated private attribute userID;
  >&emsp;Then, to control whether to enable (4), function getJobCount() is added to class **User**, 
  > If the job count >= 2, the UI will ****enable user to compare job offers. \
  >&emsp;The actual button enable/disable is handled through GUI implementation. 


2. When choosing to **enter current job details**, a user will:\
- Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consists of:\
   \Title, \
   \Company, \
   \Location (entered as city and state), \
   \Cost of living in the location (expressed as an index), \
   \Yearly bonus, \
   \Training and Development Fund, \
   \Leave Time,\
   \Telework Days per Week
- >&emsp;To handle this requirement, we further defined two classes: the **User** class and the **Job** class; and the **Job** class has two subclasses: currentJob and jobOffer.
  The user class has attributes, methods and relationships about the user while the job class has attributes, methods and relationships of a given job. They are defined below:

- > ### 1. User
    > **Attributes:**
    > - `userID` (int): The unique identifier for the user.
    > 
    > **Methods:**
    > - `getJobCount()`: Allows the GUI to know the total of current job and job offer(s).
    > - `showJob`: Allows the user to examine the existing job based on jobID.
    > - `enterJob()`: Allows the user to enter current job or job offer.
    > - `saveJob()`: Allows the user to save the job that is currently being entered.
    > - `editJob()`: Allows the user to edit their existing current job or job offer.
    > - `deleteJob()`: Allows the user to delete their existing current job or job offer.
    > - `compareJobs()`: Allows the user to compares two jobs that picked from the given list of job.
    > - `adjustWeight()`: Allows the user to adjust comparison parameters (default = 1, more details in the latter part)
    > - `rankJobs()`: Allows the user to rank all jobs (current job and job offer(s)) based on their scores, descending.
    > 
    > **Relationships:**
    >   - A `User` may have 0 to one `currentJob`.
    >   - A `User` may have 0 to many `jobOffer`.


- > ### 2. Job
    > **Attributes:**
    > - `jobId` (Int): The unique identifier of the job.
    > - `title` (String): The title of the job.
    > - `company` (String): The name of the company offering the job.
    > - `locationState` (String): The working state of the job.
    > - `locationCity` (String): The working city of the job.
    > - `costOfLiving` (Int): The cost of living in the location of the job.
    > - `yearlySalary` (Double): The annual salary offered for the job.
    > - `yearlyBonus` (Double): The annual bonus offered for the job.
    > - `trainingDevelopmentFund` (Double): The amount of money allocated to Training and Development Benefits provided with the job.
    > - `leaveTime` (Int): The number of leave days allocated every year of the job.
    > - `teleworkDaysPerWeek` (Int): The number of days per week assigned for telework in the job.
    > - `AYS` (Double): Derived variable. The number of yearly Salary Adjusted for cost of living.
    > - `AYB` (Double): Derived variable. The number of early Bonus Adjusted for cost of living. 
    > - `Score` (Double): Derived variable. The number of weighted scores based on default or user entered comparison parameter.
    > 
    > **Methods:**
    > - `setTitle()`: Enter the job title. 
    > - `setCompany()`: Enter the job company.
    > - `setLocation()`: Enter the state and city strings of the job.
    > - `setCostOfLiving()`: Enter the index of cost of living for the job location.
    > - `setYearlySalary()`: Enter the amount of yearly salary as integer.
    > - `setYearlyBonus()`: Enter the amount of yearly bonus as integer.
    > - `setTrainingDevelopmentFund()`: Enter the amount of training and development fund as integer.
    > - `setLeaveTime()`: Enter the leave time per year as integer.
    > - `setTeleworkDaysWeekly()`: Enter the number of permitted telework day per week.
    > - 
    > - `getTitle()`: Get the job title.
    > - `getCompany()`: Get the job company.
    > - `getLocation()`: Get the state and city strings of the job.
    > - `getCostOfLiving()`: Get the index of cost of living for the job location.
    > - `getAdjustedYearlySalary()`: Get the amount of adjusted (by the cost of living) yearly salary.
    > - `getAdjustedYearlyBonus()`: Get the amount of adjusted (by the cost of living) yearly bonus.
    > - `getTrainingDevelopmentFund()`: Get the amount of training and development fund.
    > - `getLeaveTime()`: Get the leave time per year.
    > - `getTeleworkDaysWeekly()`: Get the number of permitted telework day per week.
    > - 
    > - `calculateScore()`: Calculate job score based on comparison parameters.
    > - `getJobScore()`: Get the calculated job score. 

    >**Subtypes:**
    > - currentJob
    > - jobOffer

- Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
- >&emsp;Realize this requirement by adding saveJob() operations to **User** class. 
  > Action of save/cancel and returning to main menu is handled via GUI.


3. When choosing to **enter job offers**, a user will:
- Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
- Be able to either save the job offer details or cancel.
- Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
- > &emsp;For the parts involving showing a User Interface, this document does not cover that since it will be implemented in the UI implementation. \
  > &emsp;We use the **Job** class above to handle the details of the job, 
  > and use functions enterJob(), editJob(), deleteJob(), and saveJob() in **User** class to handle to action of the job

4. When **adjusting the comparison settings**, the user can assign integer weights to:\
   \Yearly salary\
   \Yearly bonus\
   \Training and Development Fund\
   \Leave Time\
   \Telework Days per Week\
- If no weights are assigned, all factors are considered equal.
- NOTE: These factors should be integer-based from 0 (no interest/don’t care) to 9 (highest interest)
- >&emsp;For this relationship we defined a **comparisonSetting** class with different attributes, methods and relationships 
  > that allows the user to assign weights to the settings as shown below:
  > 
  > ### 3. Comparison Setting
  > **Attributes:**
    > - `yearlySalaryWeight` (Int): The weight assigned to the yearly salary. Default = 1.
    > - `yearlyBonusWeight` (Int): The weight assigned to the yearly bonus. Default = 1.
    > - `trainingAndDevFundWeight` (Int): The weight assigned to the training and development fund. Default = 1.
    > - `leaveTimeWeight` (Int): The weight assigned to the leave time. Default = 1.
    > - `teleworkDaysPerWeekWeight` (Int): The weight assigned to the telework days per week. Default = 1.
    > 
    > **Methods:**
    > - `saveComparisonSetting()`: Set or update and save the comparison settings.
    > - `getComparisonSetting()`: Retrieve the current comparison settings.
    > 
    > **Relationships:**
    > - A `ComparisonSetting` is associated with one `User`.

5. When choosing to **compare job offers**, a user will:
- Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
- >&emsp;Display of list and indication of current job will be handled via GUI. The job score computation will be handled by calculateScore() from class **Job**, 
  > and the ranking will be handled by rankJobs() in class **User**.
- Select two jobs to compare and trigger the comparison.
- >&emsp;Display will be implemented via GUI. To handle the comparison requirement, we will use compareJobs() from class **User** 
- Be shown a table comparing the two jobs, displaying, for each job:\
   \Title\
   \Company\
   \Location\
   \Yearly salary adjusted for cost of living\
   \Yearly bonus adjusted for cost of living\
   \TDF = Training and Development Fund\
   \LT = Leave Time\
   \RWT = Telework Days per Week\
- >&emsp;The series of display will be handled by functions getTitle(), getCompany(), getLocation(), getCostOfLiving(),
  > getAdjustedYearlySalary(), getAdjustedYearlyBonus(), getTrainingDevelopmentFund(), getLeaveTime(), and getTeleworkDaysWeekly() 
  > from class **Job**.

- Be offered to perform another comparison or go back to the main menu.
- >&emsp;This requirement will be handled through GUI implementation.
  
6. When ranking jobs, a job’s score is computed as the weighted average of:
- AYS + AYB + TDF + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8))

  where:\
  \AYS = Yearly Salary Adjusted for cost of living\
  \AYB = Yearly Bonus Adjusted for cost of living\
  \TDF = Training and Development Fund  ($0 to $18000 inclusive annually)\
  \LT = Leave Time  (0-100 whole number days inclusive)\
  \RWT = Telework Days per Week
  > &emsp;The job score computation will be handled by calculateScore() from class **Job**.
  
7. The user interface must be intuitive and responsive.
- >&emsp;This requirement is outside the scope of the UML design. The UI being intuitive and responsive can only be done in the User Interface.
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
- >&emsp;This requirement is outside the scope of the UML design. "All activities will remain in this app" is noted. 

  