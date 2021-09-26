[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.5528822.svg)](https://doi.org/10.5281/zenodo.5528822)
[![Made with](https://img.shields.io/badge/Made%20with-Java-blue)](https://www.java.com/en/)
[![License: MIT](https://img.shields.io/badge/license-MIT-green)](https://github.com/elric97/CalBot/blob/master/LICENSE)
[![Platform](https://img.shields.io/badge/platform-discord-blue)](https://discord.com/)
# aPAS - A Personal Agile Scheduler
Many companies use agile methodology for software development and manage everything from requirement gathering to product delivery smoothly, by following some good practices.
Managing everyone's assignment using sprint model is very effective and gives the true picture of how much of assignment load someone has and how to further manage it.

*Then why not to use it in daily life?*

In daily life it's quite difficult to manage mundane tasks using some jira tools etc, which can show you exact amount of workload pending on your plate and time left before the deadline. The best way to manage it is by using some chatbot, which at some point can look into your existing schedule and can further able to handle other unscheduled items like study, playing, coding etc.
If integrated with some personal assistant like alexa, siri, cortana, ok google, this can actually help scheduling such unscheduled events in very easy way.

So here we are trying to make similar prototype, but instead of personal assistant we are using discord bot for now.

Architecture:
![image](https://user-images.githubusercontent.com/16212546/133550649-23125bf6-476e-435e-8f16-42a777d034fb.png)

Initial class diagram for product(considering future prospects):
![image](https://user-images.githubusercontent.com/16212546/133550846-e297d9f2-9907-4174-b1ec-d261a8b1353f.png)

## Future Scope 🐾
There are multiple dimensions to this project catering to the interests of
Various developers. Please check [CONTRIBUTING.md](./CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md) for contributing rules

### If you are a front-end developer 💻
Currently aPAS has been configured to work with Discord as the GUI but given the structure of the project it can be easily extended for other platforms such as
* Telegram (add links probably!)
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