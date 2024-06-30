My accomplishments for deliverable 2:
* Met with team members 3 times this week to assign features to work on and review progress and collaborate
* Implemented SQLite boilerplate code in DatabaseContract and JobDbHelper classes to read/write app data into 2 tables Job and ComparisonSetting as per team design
* Design decision to use a single database table to store both CurrentJob and JobOffer records by adding a jobType attribute for easier querying
* Fixed a bug caused due to creating a new instance of JobDbHelper in every Activity onCreate. Fixed by using Singleton design pattern to ensure only 1 instance of JobDbHelper
* Updated EditCurrentJobDetails and EnterComparisonSettings database operations to update existing records instead of creating new ones
* Manual testing for EditCurrentJobDetails documented in TestPlan.md
