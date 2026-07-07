# 🚀 Testing & Usage Guide

Follow these steps to test the AI Timetable System locally.

## 1. Prerequisites
- **Java 17** installed.
- **Node.js 20+** installed.
- **PostgreSQL** running with a database named `nep_timetable`.

## 2. Automatic Data Seeding
The system includes a `DataInitializer` that automatically creates sample data on the first run:
- **Admin Login**: `admin` / `admin123`
- **Faculty Login**: `smith` / `faculty123`
- **Sample Data**: 1 Department (CS), 2 Professors, 3 Course Categories (Major, VAC), and 2 Rooms.

## 3. Running the Backend
1. Open a terminal in `backend/`.
2. Run: `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`)
3. The API will be available at `http://localhost:8080/api`.
4. Check Swagger at `http://localhost:8080/api/swagger-ui.html`.

## 4. Running the Frontend
1. Open a terminal in `frontend/`.
2. Run: `npm install`
3. Run: `npm run dev`
4. Open `http://localhost:5173` in your browser.

## 5. How to Test the AI
1. **Login** as `admin` / `admin123`.
2. Go to the **Timetables** section.
3. Click **"New Timetable"** or **"Generate"**.
4. Select the semester (e.g., 3) and department.
5. Click **Generate**.
6. Wait 5-10 seconds for the Genetic Algorithm to evolve.
7. Click on the generated timetable to see the grid and **AI reasoning** (hover over cards).

## 6. How to Upload to GitHub
Since `git` is required on your machine, follow these commands in the root folder:

```bash
git init
git add .
git commit -m "Complete AI-Based Timetable Generation System"
git branch -M main
# Go to github.com, create a repo named "AI-timetable"
git remote add origin https://github.com/YOUR_USERNAME/AI-timetable.git
git push -u origin main
```
