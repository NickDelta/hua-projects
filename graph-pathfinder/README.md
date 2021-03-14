# Graph Pathfinding
This project was the assignment of the Data Structures course back in 2019. It deals with graph pathfinding using directed graph datasets taken from the [Stanford Large Network Dataset Collection](http://snap.stanford.edu/data/index.html).

# General Notes
Back then, the assignment did not allow the use of any external libraries or external software except core Java. If I had complete freedom, this project would have been designed differently.

Also, because I love that project, I decided to refactor a small part of it, to improve its' code quality. Also, I decided to write some unit tests for it.

**NOTE :** The report may be outdated compared to the code. Someday, I may update that too. Despite that fact, it's still useful for someone that wants to understand more about the logic of this project.

# Run The Project
You need Maven for jar packaging. Run the following command :
```bash
mvn jar:jar
````
You may find your jar in the `target` folder. To run the app execute:
```bash
java -jar graph-pathfinder-1.0.jar <graph dataset path> [-f <queries file path> ] 
``` 
The `[-f <queries file path> ]` part is optional and if not omitted, the application runs in non-interactive mode and serves the queries specified in the queries file, otherwise, the user can type their queries through the console.

# Testing
For unit testing this project I used:
- Junit 5
- Mockito 3
- Harmcrest (to ease the assertion of some collections)

More effort is required to achieve an acceptable test coverage. Still, I consider that example a good start for someone that wants to learn unit testing. I may make an article on this on my blog someday.

# Contributors

- Nick Dimitrakopoulos ([GitHub](https://github.com/NickDelta))
- Michail Vlasopoulos ([GitHub](https://github.com/MichailVlasopoulos))
- Giorgos Athanasopoulos ([GitHub](https://github.com/athagiorgos))