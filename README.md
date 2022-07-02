# Cardiac Measurement Recorder

The Cardiac Measurement Recorder app is designed to make managing heart care from home easier than ever, giving you the ability to seamlessly record Blood Pressure and Heart Rate, share heart data with your Cardiologist remotely, keep track of your health history and more!

For patients at high risk for heart disease, this app is greatly convenient since it gives them an access to track their cardiac related records. 

This app will let the patients to create records of blood pressure and heart rate, keep record of time and date of measurement and update and delete previously recorded records. 

See the [Wiki](https://github.com/Tashin-Ahamed/Cardiac-Measurement-Recorder/wiki) for documentation, implementation details, and history.

## Unified Modeling Language
![uml](https://user-images.githubusercontent.com/54912601/177001173-38dbdb8b-81e8-4a45-a854-6b730313e019.png)

## RECORDS

###  -DATE
• date measured (presented in dd-mm-yyyy format)

#### _getDate()_ will get user input of specific DATE
#### _setDate()_ will set the value of DATE takes from user
 
### -TIME
 • time measured (presented in hh:mm format) 
 #### _getTime()_ will get user input of the current Time of that moment
 #### _setTime()_ will set the value of TIME taken from the user

### -Systolic
• Systolic pressure in mm Hg (non-negative integer) 
 #### _getSystolic()_ will get user input of the current Time of that moment
 #### _setSystolic()_ will set the value of TIME taken from the user

### -Diastolic
 • Diastolic pressure in mm Hg (non-negative integer) 
 #### _getDiastolic()_ will get user input of the current Time of that moment
 #### _setSystolic()_ will set the value of TIME taken from the user

### -Heartrate
 • heart rate in beats per minute (non-negative integer) 
 #### _getHeartrate()_ will get user input Heartrate
 #### _setHeartrate()_ will set the value taken from the user

### -Comment
 • it will let the user add notes (textual, up to 20 characters)
 #### _getComment()_ will get user input Comment
 #### _setComment()_ will set the value taken from the user

## -RECORD_LIST

### -addRecords
• Table of Records

### -deleteRecords
• Will delete Records

### -fatchRecordfromDB
• Will fatch Record from Database

### -updateRecords
• Will update Records

# UI DESIGN


![Home Screen](https://user-images.githubusercontent.com/54912601/177003827-39403f57-a03b-4df2-a7b8-307527b3f486.png)
# Home SCREEN
![Home Screen (2)](https://user-images.githubusercontent.com/54912601/177003837-541c63f3-3368-4509-b08b-2e4df7dbc17e.png)
# RECORDS LIST
![Home Screen (3)](https://user-images.githubusercontent.com/54912601/177003841-49f91a96-ea65-4faf-a79e-6d881670b24c.png)
# INSERT NEW MEASUREMENT

![Home Screen (4)](https://user-images.githubusercontent.com/54912601/177003844-43770db3-8440-4f16-876a-9da1ce864722.png)
# VIEW MEASUREMENT'S DETAILS

![Home Screen (5)](https://user-images.githubusercontent.com/54912601/177003847-0e2b57cb-d563-4fbe-bd24-85303feca6e3.png)
# UPDATE MEASUREMENT


## Contributors
1. [Nafia Hossain, Roll: 1807079](https://github.com/nafiahossain)
2. [Tashin Ahamed, Roll:1807083](https://github.com/Tashin-Ahamed)
