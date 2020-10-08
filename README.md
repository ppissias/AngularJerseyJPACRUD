# AngularJerseyJPACRUD
This is a basic example of an Angular frontend application with a Java backend REST API.
This is a simple "CRUD" (Create Read Update Delete) example with very simple data model. 
The Java backend is implemented with Jersey (REST provider) and JPA (using Derby as the underlying database).
The Angular frontend uses the Angular Material components.

## Running / compiling the Java part
You need to have Java 11 installed (although I think it can probably run in Java 8 as well)
```
cd crudservices
gradlew run ==> this will run the server
gradlew distZip ==>builds independent java backend services.zip file in builds/distributions 
```

## Running the angular web gui 
You need to have an Angular development environment as described here
https://angular.io/guide/setup-local
```
cd frontend
ng serve
```
You might first need to do an update by executing the commands below
```
npm install
ng update
npm update
```
## Using the browser to access the application
At this develpoment state, 
you need to run the browser using the "NOCORS" mode, for example ein windows
```
"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" --disable-web-security --disable-gpu --user-data-dir=c:/temp
```
This is needed because the Java backend REST API is deployed at localhost:8080 and the Frontend angular app at localhost:4200.
The plan is to include a next version when I have the time where everything runs on the Java side from localhost:8080
