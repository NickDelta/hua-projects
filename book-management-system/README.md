# Book Management System
A Very Basic Full-Stack Web Application that manages books.

It uses: 

On The Front-End:
 - HTML/CSS
 - 100% Vanilla JS

On The Back-End:
 - A REST API built with Express.js
 - MySQL as RDBMS

This was an assignment from my university back in the summer of 2020. I was forbidden from using any external libraries (such as Bootstrap). If I had complete freedom, this app would have been designed differently.

# Deployment
You may use docker-compose to test this app. If you have docker-compose install then simply run:
```bash
docker-compose up -d
```
The app is then accessible at http://localhost/

To remove everything after testing run:
```bash
docker-compose down --rmi all
```
Be careful as `--rmi all` will also delete all associated images. By omitting that part, you keep the images intact.


# Contributors

- Nick Dimitrakopoulos ([GitHub](https://github.com/NickDelta))