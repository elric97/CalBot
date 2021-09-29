[![example workflow](https://github.com/elric97/CalBot/actions/workflows/maven.yml/badge.svg)](https://github.com/elric97/CalBot/actions/workflows/maven.yml)
[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.5528822.svg)](https://doi.org/10.5281/zenodo.5528822)
[![Made with](https://img.shields.io/badge/Made%20with-Java-blue)](https://www.java.com/en/)
[![License: MIT](https://img.shields.io/badge/license-MIT-green)](https://github.com/elric97/CalBot/blob/master/LICENSE)
[![Platform](https://img.shields.io/badge/platform-discord-blue)](https://discord.com/)
[![GitHub issues open](https://img.shields.io/github/issues/elric97/CalBot)](https://github.com/elric97/CalBot/issues)
[![GitHub issues closed](https://img.shields.io/github/issues-closed/elric97/CalBot?color=magenta)](https://github.com/elric97/CalBot/issues)
# aPAS
![image](https://raw.githubusercontent.com/elric97/CalBot/master/Design/Logo.png)

## Introduction 😁
* Have you ever wondered how easy it would be if there was someone to help you manage your mundane tasks in a better way? If you have then aPAS has got you covered!
* Today, many companies use agile methodology for software development. What has proven to be the most effective is the use of a sprint model. 
* This model gives the true picture of how much of an assignment load someone has and how to further manage it. We bring to you exactly that to manage your everyday tasks with our discord bot.
* Do you ever want to find time for Leetcode and workouts amidst a very busy week full of assignments but just can’t? Our discord bot will look into your existing schedule and will further help you handle such unscheduled tasks. 
* How will it do that? It will show you the exact amount of workload pending on your plate and time left before the deadline. This makes scheduling such unscheduled events very easy. 

#### Here's a short introduction video:
https://user-images.githubusercontent.com/55187770/135184919-845304ad-91ee-4fb7-9886-9b1a1e577254.mp4


#### Architecture:
![image](https://user-images.githubusercontent.com/16212546/133550649-23125bf6-476e-435e-8f16-42a777d034fb.png)

## Technologies
<p>
  <a href="https://www.java.com/en/" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="java" width="50" height="50"/>
  </a>
  <a href="https://spring.io/" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="spring" width="50" height="50"/>
  </a>
  <a href="https://www.postgresql.org/" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" alt="postgresql" width="50" height="50"/>
  </a>
  <a href="https://www.docker.com/" target="_blank">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" alt="docker" height="50" width="50">
  </a>
  <a href="https://www.docker.com/" target="_blank">
    <img src="https://symbols.getvecta.com/stencil_74/18_apache-maven-icon.2a3ad94f03.svg" alt="maven" height="50" width="50">
  </a>
  <a href="https://discord4j.com/">
    <img src="https://raw.githubusercontent.com/Discord4J/discord4j-web/master/public/logo.svg?sanitize=true" alt="Discord4J" height="50" width="50"/>
  </a>
</p> 

Java\
Spring Boot\
PostgreSQL\
Docker\
Maven\
Discord4J
## Getting started
* Java8
* IntelliJ or Eclipse (IntelliJ preferred)
* PostgreSQL - We have used [Postgress](https://www.postgresql.org) as our database, it can be set locally as well as on cloud. You just need to start it on your localhost it will automatically create tables required.
* Google Cloud Platform - We used Google calendar API by creating a project on [GCP](https://developers.google.com/workspace/guides/create-project), Check [Quickstart](https://developers.google.com/calendar/api/quickstart/java) for setting up Google calendar APIs. The ./Credentials.json of the project is required for authenticating the user.
* Discord Developer Application -We created a [Discord Developer profile](https://discord.com/developers/docs/intro) for configuring a Discord BOT 
* Environment variables can be found at [applications.properties](/src/main/resources/application.properties)
* Credentials need to be updated at [credentials.json](/src/main/resources/credentials.json)
* To start project using docker run the following command in main directory
````
docker build . -t 'image-name' //Don't forget the dot
docker run -dp 8080:8080 'image-name //This will start app on port on 8080
````


## Future Scope 🐾
There are multiple dimensions to this project catering to the interests of
Various developers. Please check [CONTRIBUTING.md](./CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md) for contributing rules

### Finish the work pending 
* Add more CRUD operations to give more flexibility to user to delete and edit events via chatbot

### If you are a front-end developer 💻
Currently aPAS has been configured to work with Discord as the GUI but given the structure of the project it can be easily extended for other platforms such as
* Telegram
* WhatsApp
* Facebook 

### If you are a back-end developer 🛠️
Why just stop at Google calendar? Why not make it more user-friendly for all users with various calendars such as:
* Samsung Calendar
* Apple calendar
Extending aPAS for these will require addition of service classes for these calendars

### If you are an ML Enthusiast 🧑‍🔬
* Why not use a voice-bot to set and manage events,Implementing this using an audio model can be an interesting extension to the project.
* The priority algorithm can be personalized for a user based on the amount of time they spend on a particular task. 

## Team Members
* Aditi Bhagwat
* Anumit Garg
* Palvit Garg
* Rachit Sharma
* Shree Ramasubramanian
