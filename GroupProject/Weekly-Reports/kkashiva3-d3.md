My accomplishments for deliverable 3:
* Met with team members to review progress of the final app and assign ownership of pending features and documentation tasks among us
* Implemented Observer design pattern for ComparisonSettings and Job class. Every time the Subject (ComparisonSettings) has a change in weights, all Observers (Jobs) are notified to recalculate the score
* Fixed a bug in calculateJobScore() method in Job class that resulted in a 0.0 due to integer division nuance in Java. Fixed by explicit type-casting as double
* Implemented Singleton design pattern for the CurrentJob class to ensure only one instance of CurrentJob in the app and also only one row in the DB table
* Encapsulated all database read, write, update operations of CurrentJob and they are now called as methods from EnterCurrentJobDetails and EditCurrentJobDetails activities
* Updated class diagram UML to include the Observer and Subject interfaces implemented by Job and ComparisonSettings, respectively. Updated variables and methods for CurrentJob class
* Unit Testing for Job score recalculation when the weights are updated in Comparison Settings
