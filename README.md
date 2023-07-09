# Medical-Care

### About

##### This basically helps people to manage their medicines and appointment with doctor

##### This project aims at solving problem of people who have to look after multiple people's medicine routine, so this project will help them to not mistakenly give wrong medicine to wrong person as it will keep track of following things:-

1. Information about User
2. Information of Medicine that we have to give to a user, info like when to take [Day,Afternoon,Night], how much to take, name of medicine etc
3. Information of Doctor, like which doctor recommended which medicine

###### You can also upload an image of medicine from your device or online, so that if want to go purchase a new one, then it will be easy for people to buy correct medicine


### How to setup and run at your machine

1. You need to setup docker environment on your computer so follow following instructions :-
   ```
   cd Project-for-Medical
   docker composer up -d
   ```
2. Now PHPMYADMIN is running on localhost:8080, now open your browser and [go to this link](http://localhost:8080)
     1. Now when your PHPMYADMIN is running, create a new table **"mlhDatabase"**
3. Now your application is ready to run
4. To test APIs, open Postman and test them but you need authentication before testing it, so in basic authentication, provide username="prathamesh", password="prathamesh", it will generate a token and use that token to access all APIs
