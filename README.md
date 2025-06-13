# Budget Tracker

This project contains a simple Dropwizard backend and an AngularJS 1.x frontend.

## Backend

```
cd backend
mvn clean package
java -jar target/budget-tracker-api-1.0-SNAPSHOT.jar server config.yml
```

The service listens on `http://localhost:8080`.

## Frontend

Open `frontend/index.html` in a browser and use the forms to interact with the API running locally.
