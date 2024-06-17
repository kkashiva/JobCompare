# Design Description

> When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

I started with creating the classes **MainMenu**, **CurrentJob**, **JobOffer**, **ComparisonSettings** <br>
I chose the **MainMenu** class as the ***entry point*** to the app. <br>

It has the attributes ‘currentJob’ (object of CurrentJob class) and ‘jobOffers’ (list of objects of JobOffer class). 

It also has methods *enterCurrentJob(), editCurrentJob(), enterJobOffer(), adjustComparisonSettings(), compareJobOffers()* to interact with other classes.

> When choosing to enter current job details, a user will:
Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consists of:<br>
Title<br>
Company<br>
Location (entered as city and state)<br>
Cost of living in the location (expressed as an index)<br>
Yearly salary<br>
Yearly bonus<br>
Training and Development Fund<br>
Leave Time<br>
Telework Days per Week<br>
Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.<br>

**CurrentJob** and **JobOffer** are designed as sub classes of the super class **Job**. I’ve shown this with a generalization (is-a) relationship. All the attributes ‘title’, ‘company’, ‘city’, ‘state’, … are part of the **Job** super class. I skipped including methods for save, cancel or exit as there are purely GUI and DB CRUD operations specific.

>When choosing to enter job offers, a user will:
Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
Be able to either save the job offer details or cancel.
Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

Since the attributes are inherited from the **Job** super class, no need to add them again on the **JobOffer** sub class. Entering details, saving, and canceling are again GUI and DB specific so not shown in UML.<br>

If the 'enter another job offer' UI button is clicked we can call the same *enterJobOffer()* method from the entry point **MainMenu** class. Each **JobOffer** object will have a unique ‘jobOfferID’ attribute.<br>

Added a method *addToCompare()* to the **Job** class. This method can be called on both the **JobOffer** and **CurrentJob** objects to add them to the **JobsToCompare** class attributes ‘job1’ and ‘job2’. If the GUI button ‘compare with current job’ is selected the job1 attribute will be set as CurrentJob and job2 as the JobOffer object in focus using the ‘jobOfferID’<br>

>When adjusting the comparison settings, the user can assign integer weights to:<br>
Yearly salary<br>
Yearly bonus<br>
Training and Development Fund<br>
Leave Time<br>
Telework Days per Week<br>
If no weights are assigned, all factors are considered equal.<br>
NOTE: These factors should be integer-based from 0 (no interest/don’t care) to 9 (highest interest)<br>

Added attributes for each weight to the **ComparisonSettings** class ‘weightYearlySalary’, ‘weightYearlyBonus’, ‘weightTrainingDevelopmentFund’, …<br>

Added a method *adjustWeights()* that can take integer arguments for each weight to update the attribute values.<br>

>When choosing to compare job offers, a user will:
Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
Select two jobs to compare and trigger the comparison.
Be shown a table comparing the two jobs, displaying, for each job:<br>
Title<br>
Company<br>
Location<br>
Yearly salary adjusted for cost of living<br>
Yearly bonus adjusted for cost of living<br>
TDF = Training and Development Fund<br>
LT = Leave Time<br>
RWT = Telework Days per Week<br>
Be offered to perform another comparison or go back to the main menu.<br>

The list of job offers to choose from is stored in the entry point’s ‘jobOffers’ attribute as an array of **JobOffer** objects. Their title and company can be fetched using the **Job** class getter methods *getTitle(), getCompany()*.<br>

Added a class **JobsToCompare** that will have two attributes ‘job1’ and ‘job2’ of the type Job. These are set by calling the *addToCompare()* method on the **Job** object selected from the list. After 2 jobs are selected the comparison is triggered by calling the *compareJobs(job1, job2)* function that takes the 2 Job objects as arguments.<br>

Added a class **CompareResults** to show the properties of the two jobs side-by-side. As an example, the attributes ‘job1Title’ and ‘job2Title’ get their value from a setter method of **CompareResults** class that can call the getter method of the Job class.<br>

>When ranking jobs, a job’s score is computed as the weighted average of:<br>
AYS + AYB + TDF + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8))<br>
where:<br>
AYS = Yearly Salary Adjusted for cost of living<br>
AYB = Yearly Bonus Adjusted for cost of living<br>
TDF = Training and Development Fund  ($0 to $18000 inclusive annually)<br>
LT = Leave Time  (0-100 whole number days inclusive)<br>
RWT = Telework Days per Week <br>
NOTE: The rationale for the RWT subformula is:<br>
-  value of an employee hour = (AYS / 260) / 8<br>
-  commute hours per year (assuming a 1-hour/day commute): 1 * (260 - 52 * RWT)<br> - therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8<br>
For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for the Training and Development Fund, and 1 for all other factors, the score would be computed as:<br>
2/8 * AYS + 2/8 * AYB + 2/8 * TDF + 1/8 * (LT * AYS / 260) - 1/8 * ((260 - 52 * RWT) * (AYS / 260) / 8)))

The list of job offers shown GUI purely and skipped from UML, however the ranked sorting of the jobs depends on application logic. This is represented by the dependent ‘score’ attribute in the Job class. The logic to calculate the score is in 3 helper methods calcAYS(), calcAYB(), calcScore().<br>

>The user interface must be intuitive and responsive.

This is not included in UML diagram and will be handled in GUI front-end design<br>

>For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

Not included in class diagram<br>

