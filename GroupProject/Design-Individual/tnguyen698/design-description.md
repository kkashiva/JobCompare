## Design description

##### 1. Requirement 1
When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).

> This will be handled within the GUI implementation. Hence, it won't be showed in the UML diagram. This should be a simple menu UI with 4 options.

##### 2. Requirement 2 
When choosing to enter current job details, a user will: 
* Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consists of:
  * Title
  * Company
  * Location (entered as city and state)
  * Cost of living in the location (expressed as an index)
  * Yearly salary
  * Yearly bonus
  * Training and Development Fund
  * Leave Time
  * Telework Days per Week
> Job class will contain setters and getters for all the requirement attributes of a job.
* Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
> The ability to save/cancel/exit will be handled via GUI.

#### 3. Requirement 3
When choosing to enter job offers, a user will:
* Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
> The relationship between Job and Job Offer reflects the ability to enter the details of the offer.
* Be able to either save the job offer details or cancel.
> This is handled via GUI.
* Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
> This is handled via GUI. For compareJobOffer method, as there is a connection to Job class, it should be able to get info of jobs to do comparison. 

#### 4. Requirement 4
When adjusting the comparison settings, the user can assign integer weights to:
* Yearly salary
* Yearly bonus
* Training and Development Fund
* Leave Time
* Telework Days per Week

  If no weights are assigned, all factors are considered equal.
  NOTE: These factors should be integer-based from 0 (no interest/don’t care) to 9 (highest interest)
> In the design, the Comparison Setting class needs to keep track of Job instances in order to assign weights accordingly.
> The method enterWeightsToJob requires a job instance as parameter. It then updates the job instance with given weights (yearly salary, bonus, etc.)

#### 5. Requirement 5
When choosing to compare job offers, a user will:
* Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
> Display will be handled via GUI. The computation of ranking will be discussed below.
* Select two jobs to compare and trigger the comparison.
> This should be called from Job Offer class to compare two jobs.
* Be shown a table comparing the two jobs, displaying, for each job:
  * Title
  * Company
  * Location
  * Yearly salary adjusted for cost of living
  * Yearly bonus adjusted for cost of living
  * TDF = Training and Development Fund
  * LT = Leave Time
  * RWT = Telework Days per Week
> Not showed in the design but should be handled in GUI.
* Be offered to perform another comparison or go back to the main menu.
> Not showed in the design but should be handled in GUI.
#### 6. Requirement 6
When ranking jobs, a job’s score is computed as the weighted average of:


AYS + AYB + TDF + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8))

where:
AYS = Yearly Salary Adjusted for cost of living
AYB = Yearly Bonus Adjusted for cost of living
TDF = Training and Development Fund  ($0 to $18000 inclusive annually)
LT = Leave Time  (0-100 whole number days inclusive)
RWT = Telework Days per Week

NOTE: The rationale for the RWT subformula is:
-  value of an employee hour = (AYS / 260) / 8
-  commute hours per year (assuming a 1-hour/day commute):
1 * (260 - 52 * RWT) therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8

For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for the Training and Development Fund, and 1 for all other factors, the score would be computed as:

2/8 * AYS + 2/8 * AYB + 2/8 * TDF + 1/8 * (LT * AYS / 260) - 1/8 * ((260 - 52 * RWT) * (AYS / 260) / 8)))
> Job class will call getJobWeightAverage to compute the score. Then Job Offer can call compareJobOffers to produce ranking.
#### 7. Requirement 7
The user interface must be intuitive and responsive.

#### 8. Requirement 8
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).