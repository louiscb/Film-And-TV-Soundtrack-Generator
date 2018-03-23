# Hackday Project

Please read the [Wiki](https://gl-intern.shazamteam.net/hackday/intern-hackday/wikis/home) and follow the instructions.

## Idea

A website that is as an online collection of music used in films and tv shows.

Users type in their favourite film or TV show into the website and will see a list of the music it contains at the specific time it appears in the video.

The program will use Shazam's audio recognition software to automatically process videos and will add data about the music it detects to the website.

### Milestones:

1. A program that takes a video and returns a collection of the detected songs in the video.

2. Updating a database with this newly gathered data.

3. A website that connects to this database.

4. Displaying the information from the database on the website.

## Technologies

I will be creating two separate systems: one is the website the user will browse, the other is the software that gathers the data from film and TV.

I will develop all the software using the IntelliJ IDE.

#### Website
The website will be a RESTful web service.

These are the technologies I will be using:

* Node.js - server side code

* Express - server

* MongoDB - database

* HTML & CSS - for the frontend (if time permits)

#### Audio Processing software

The data collection will feed into a shared database between the audio software and the website.

These are the technologies I will be using:

* Java - video & audio processing

* MongoDB - database
