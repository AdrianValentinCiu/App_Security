In this project I integreted all the security need for a web application. I managed to integrate the fucntionality of creating a new account as a new user, validating the account via email. The validation 
token will expire in 15 minutes.
For login validation I used JWT.
A user can change the password: a 4 digit validation code will be sent via email and the the user can use the code to change the password. The code will expire in 15 minutes.
