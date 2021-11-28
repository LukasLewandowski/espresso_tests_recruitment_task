# Sliide QA Technical Test
## Congratulations, you have reached the next stage which is solving a Sliide practical test.
##### Please create your own repo and share the solution with us

### Description
During the test we want you to imagine yourself as a member of our team, with a collective goal of getting our tasks completed. 

When we have reviewed your test, and any accompanying documents you feel necessary, if we like what we see, we’ll invite you to join us for a video conversation during which we’ll ask you to go through your test, explaining any decisions that you made.

Let’s start!

We are in the middle of the sprint and the following 2 user stories were just moved to the QA testing column on our Jira board:

##
### 1 - As a user I want to log in to the app

#### Scenario 1 - user opens the android app first time (when not logged in yet)

Given - the user opens app for the first time (when not logged in yet)

Then - login screen with user name and password entries and login button is displayed

#### Scenario 2 - user login failed

Given - the user provided wrong user name and/or password

When - login button is clicked

Then - error markers are displayed by user name and/or password entries

#### Scenario 3 - user login succeed (credentials provided below)

Given - the user provided right user name and password

When - login button is clicked

Then - user is taken to the news screen

#### Scenario 4 - user opens app next time (when previously logged in)

Given - the user opens app next time (when previously logged in)

Then - user is taken straight to the news screen

 ##

### 2 - As a user I want to see my news

#### Scenario 1 - news images are loaded

Given - the user successfully logged in to the app

When - there is internet connection

Then - images are displayed in the rows on the list (row can have one or more images scrollable horizontally)

#### Scenario 2 - failed to load images

Given - the user successfully logged in to the app

When - there is no internet connection

Then - “failed to load news” error message is displayed and Retry button

#### Scenario 3 - news image is clicked

Given - the news images are successfully loaded on the screen

When - the user clicks one of the image

Then - user is navigated to the external browser with clicked image loaded

#### Login credentials:
#### user: password
#### password: password

##

Now it’s your turn. You need to verify if we can move these two tickets to Done column on our Jira board.
We expect that these functions will be tested both manually and automatically by you.

### Manual tests - we expect that any bugs will be reported in clear form

### Automated tests - using Espresso or any other tool of your choosing (explain why)

* At Sliide we love clean code, so please try to write your tests neatly. 

* It’s not mandatory but using an additional abstraction level for your tests (like your own framework to facilitate writing tests) will be very much appreciated

* As a note, we won't consider any automation task submission created with a test recorder.


At Sliide we highly appreciate good communication at all times so, if you have any questions, don’t hesitate to ask   

## Good luck!   

------------------------------------------------------------------------------------------------------------------
# Espresso UI tests:
 
Framework structure:
- package "e2e" contains tests files
- class "User" in package "models" contains user credentials
- package "pages" contains robots for performing actions on the app views, there you can find selectors/matchers for UI elements
- package "utils" contains helper functions

## Executing tests:
- All tests can be launched by running tests suite class: "UIRegressionTestsSuite"

### Issue to report in Jira:

Title: Android - missing "Retry" button on News view when lack internet connection

Steps to reproduce:
1. Turn off internet connection on the device
2. Login to the app, using for example user1/password
3. Wait for News view to load

Actual result:
User can see news load error

Expected result:
User should see load error and retry button

Tested on Android emulator with API 28.
Attachment: screenshot/video

