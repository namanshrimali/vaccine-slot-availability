# Vaccine Slot Availability on Push Notification
This java based script provides push notification on windows as soon as there's vaccine slot availability (for age group of 18).

## Why this script ?
Vaccines slots are getting booked as soon as they're open. We need to be faster in order to get vaccinated early !

## Requirements
* Java (11 or higher)

## Setup
* Download this [jar file](https://drive.google.com/file/d/1eXtAxSAAYGfeVymDkODgiqzK9oWXMOBf/view) on your local system
* Go to the folder where it is downloaded
* Open PowerShell window (CommandPrompt would work too)
* Run the following command 
```
 java -Ddate="DD-MM-YYYY" -Dpin="XXXXXX" -jar .\vaccineNotification-0.0.1-SNAPSHOT.jar
```
(Add date in format DD-MM-YYYY & pin will be your 6 digit area pincode)

---
As soon as there's a slot availability for you nearby, it'll throw a push notification. Login into [CoWin portal](https://cowin.gov.in), register yourself and book a desired slot !
