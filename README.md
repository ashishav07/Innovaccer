# Innovaccer
Description:
First screen that user will see is a form for entering details of host and visior, and in the navigatoin menu the user can see the current visitors(visitors who are checked in) and history of visitors who have checked out.

Approach:
Firebase is used as backend in this application , when a visitor checks in,his details aand the host details are sent to the database and an email is sent to the host through JavaMail API and SMS is sent through the mobile in which the app is being used and when user checks out, an Email is sent to the visitor regarding the visit.To display the current visitors and history, a memory efficient Recycler View is used instead of a simple List View.
