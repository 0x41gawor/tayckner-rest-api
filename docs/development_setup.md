# Development Setup

## 1 Get sources

### 1.1 Install IntellijIdea

You can download it [here](https://www.jetbrains.com/idea/download/#section=windows)

> If you have an access to Ultimate version, I highly recommend it :D

### 1.2 Import Project

1. Launch Intellij Idea
2. If you're on welcome page search for "Import Project" or "Project from version control". Once you log in to your gh account, you can copy the repo url https://github.com/0x41gawor/tayckner-rest-api and import project to Intellij.
3. If you have some other project already open in Intellij just go `File -> New -> Project from Version Control`, then paster repo url https://github.com/0x41gawor/tayckner-rest-api

### 1.3 Run the project

This step may require some googling about SDKs, Gradle, Build in Intellij etc.

Finally when you can build and run the project it shouldn't start, because the database is not correctly configured. We will cover that in the next step.

## 2 Configure the Database

### 2.1 Download and Install MySQL

The most important step here is to have "MySQL Workbench" installed.

I personally use the community version, which can be downloaded [here](https://dev.mysql.com/downloads/) as [MySQL Installer for Windows](https://dev.mysql.com/downloads/windows/)

### 2.2 Create the database

Open MySQL workbench and connect to your Server.

Once you are connected to your MySQL server your can run [this script](db.sql) on it to create the database.

### 2.3 Change Application Properties

Go to `src/main/resources/application.properties` and change username and password* to your ones.

*And maybe port if you're not using the default one.

Remember not to commit this file. Keep it locally. The best option is to have the same credentials to db as in original file.

### 2.4 Check if you can run the app

If not go to google or contact me.

## 3 Test api with Postman

### 3.1 Install postman

You can find a lot of postman tutorials on the web.

### 3.2 Test api

With Tayckner it does not require any special configuration. Just use the proper endpoint in the proper way. [This doc](endpoints.md) comes with help.

Remember that you first need to have a registered user, then to log in, and later use the CRUD api authorizing with the JWT received in login response.

