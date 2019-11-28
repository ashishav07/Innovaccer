# Innovaccer

# Description
Innovaccer is an entry management Android application . When a visitor comes to meet any employee , he/she will enter his/her details along with the host details . After check-in the host will receive an e-mail and sms regarding the visitor details. After the meeting is over the visitor can check-out and will receive an email regarding the visit.

Compatible OS - Android <br/>
Frontend - Java <br/>
Backend - Firebase(Firestore) <br/>

# Installation

You can install it by downloading the apk from the apk folder.
You can also install it in your device/emulator by cloning this repository.

# How to use
First Screen which pops up is form which he visitors need to fill when they arrive at the office. After filling the form when they click on check-in the details of the visitor are sent to the host through e-mail and sms.
<br/>
<img src="https://github.com/ashishav07/Innovaccer/blob/master/New%20folder/form.png" height = "700" width = "300" ><img src="https://github.com/ashishav07/Innovaccer/blob/master/New%20folder/loading.jpg" height = "700" width = "300">

<br/>SMS and E-mail Screenshots <br/>
<img src="https://github.com/ashishav07/Innovaccer/blob/master/New%20folder/host%20email.jpg" height = "700" width = "300" >
<img src="https://github.com/ashishav07/Innovaccer/blob/master/New%20folder/host%20sms.jpg" height = "700" width = "300">

# Approach:
Firebase is used as backend in this application , when a visitor checks in,his details aand the host details are sent to the database and an email is sent to the host through JavaMail API and SMS is sent through the mobile in which the app is being used and when user checks out, an Email is sent to the visitor regarding the visit.To display the current visitors and history, a memory efficient Recycler View is used instead of a simple List View.
