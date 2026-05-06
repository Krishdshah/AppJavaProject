# AppJavaProject

This project contains two components:
1. **Backend** (Spring Boot)
2. **Frontend** (JavaFX)

## How to Run

To run this application, you will need to open **two separate terminal windows** (one for the backend and one for the frontend).

### 1. Backend (Spring Boot)
Navigate to the `backend` folder and run the Maven wrapper:
```powershell
cd backend
.\mvnw spring-boot:run
```

*Note: The backend requires a MySQL database. Database credentials and settings are located in `backend/src/main/resources/application.properties`.*

### 2. Frontend (JavaFX)
Navigate to the `frontend` folder and run the Maven wrapper:
```powershell
cd frontend
.\mvnw clean javafx:run
```
