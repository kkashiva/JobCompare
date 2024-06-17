# Design Description 
### Yunhe Cui - SDP CS6300 Summer 2024
### ycui71@gatech.edu

1. When the app is started, the user is presented with the main menu, 
which allows the user to 
(1) enter or edit current job details, 
(2) enter job offers, 
(3) adjust the comparison settings, or 
(4) compare job offers (disabled if no job offers were entered yet).
- >&emsp;To realize this requirement, I added class **jobSeeker** for user and an automatically generated private attribute userID; a parent class **Job** and two children classes **currJob**
  > and **futureJob** that inherit from the **Job** class for all jobs; and class **comPara** to handle the weighted parameters.\
  >&emsp;Then, to control whether to enable (4), function getJobCount() is added to class **jobSeeker**, 
  > isCompareAllowed() is added to main class to check if the Jobs >= 2 before enabling user to compare job offers. \
  >&emsp;The actual button enable/disable is handled through GUI implementation. 


2. When choosing to **enter current job details**, a user will:\
- Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consists of:\
   \Title, \
   \Company, \
   \Location (entered as city and state), \
   \Cost of living in the location (expressed as an index), \
   \Yearly salary, \
   \Yearly bonus, \
   \Training and Development Fund, \
   \Leave Time,\
   \Telework Days per Week
- >&emsp;To realize this requirement, I added class **jobOperation** to handle the relationship between **jobSeeker** and **Job**, 
  > which contains createJob(), editJob() functions and a jobID attribute (ascending integer, starting from 1). 
  > There is a one-to-one relationship between **jobSeeker** and **currJob**.\
  > &emsp; createJob() and editJob() both require attributes of isCurrentJob:Boolean, title:String, company:String, 
  > locCity:String, locState:String, costOfLiving:Integer ##as index, yearlySalary:Integer, yearlyBonus:Integer, TDF:Double, 
  > LT:Integer ##in working day, RWT:Integer ##in working day within a week.

- Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
- >&emsp;To realize this requirement by adding saveNewJob() operations to **jobSeeker** class. 
  > Action of save/cancel and returning to main menu is implemented via GUI.


3. When choosing to **enter job offers**, a user will:
- Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
- >&emsp;To realize this requirement, as stated above, association class **jobOperation** will still be used to handle the relationship between **jobSeeker** and **Job/futureJob**.
  > However, there is a one-to-many relationship between **jobSeeker** and **futureJob**. 
  > The requirements for createJob() will remain the same and editJob() is disabled for **futureJob**. 
- Be able to either save the job offer details or cancel.
- >&emsp;To realize this requirement, I added function saveNewJob() to **jobSeeker** class.
- Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
- >&emsp;This requirement will be handled through GUI implementation.

4. When **adjusting the comparison settings**, the user can assign integer weights to:\
   \Yearly salary\
   \Yearly bonus\
   \Training and Development Fund\
   \Leave Time\
   \Telework Days per Week\
- If no weights are assigned, all factors are considered equal.
- NOTE: These factors should be integer-based from 0 (no interest/don’t care) to 9 (highest interest)
- >&emsp;To realize this requirement, I added setWeight() and getWeight() methods to class **comPara** to assign the parameter weight and check the current parameter weight.\
  >&emsp;setWeight(yearlySalaryWeight:Integer, yearlyBonusWeight:Integer, trainingDevelopmentFundWeight:Integer, leaveTimeWeight:Integer, teleworkDaysPerWeekWeight:Integer)\
  >&emsp;Only 0 - 9 values are allowed, set default equal weighted factors (all=1) if no weights are assigned. \
  >&emsp;The relationship between class **jobSeeker** and class **comPara** is one-to-one relationship.

5. When choosing to **compare job offers**, a user will:
- Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
- >&emsp;To realize the ranking requirement, I added functions getAllSavedJobs(), calcJobScore(), rankJobs() and showRankedList() to class **jobSeeker**.\
  >&emsp;Function calcJobScore() will be triggered immediately after saveNewJob(). The inputs for calcJobScore() include the return values from getWeight() in class **comPara** and saveNewJob() in 
  > class **jobSeeker**, while the output is the numeric score for this job.\
  >&emsp;Function getAllSavedJobs() returns all saved jobs. The return values from getAllSavedJobs() in class **jobSeeker** 
  > and getWeight() in class **comPara** serve as input for rankJobs(). Function rankJobs will be triggered immediately after saveNewJob() is implemented.\
  >&emsp;Function showRankedList() return a sorted dictionary that key = jobID, value = [title, company, rank, isCurrentJob] \
  >&emsp;Displaying the sorted list of offers, with selected info and show if a certain 
  > job is the current job will be handled through GUI implementation.
- Select two jobs to compare and trigger the comparison.
- >&emsp;To handle the comparison requirement, I added function compareTwoJob() to class **jobSeeker**. Input will be the 
  > calcJobScore() returns for job1 and job2, while the output will be the index of the job with higher scores. \
  > &emsp;Job selection, comparison trigger, and better job highlighting will be handled through GUI implementation. 
- Be shown a table comparing the two jobs, displaying, for each job:\
   \Title\
   \Company\
   \Location\
   \Yearly salary adjusted for cost of living\
   \Yearly bonus adjusted for cost of living\
   \TDF = Training and Development Fund\
   \LT = Leave Time\
   \RWT = Telework Days per Week\
- >&emsp;To calculate the adjusted yearly salary and bonus, I added adjustEarnByCost() function to class **Job**. 
  > Inputs are yearly salary or bonus (integer) and cost of living, output is the adjusted corresponding number.\
  >&emsp;Table displaying will be handled through GUI implementation.

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
- >&emsp;To realize this requirement, the formula shown above will be included in function calcJobScore(job) in class **jobSeeker**. 


7. The user interface must be intuitive and responsive.
- >&emsp;This requirement is outside the scope of the UML design.
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
- >&emsp;This requirement is outside the scope of the UML design. "All activities will remain in this app" is noted. 

  