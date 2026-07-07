
# NEP Timetable System - Launch & Deployment Guide 🚀

The project is now fully prepared to be pushed to GitHub and deployed! The codebase compiles cleanly, has unit tests, includes a proper `.gitignore`, and is ready for production hosting.

Below is a comprehensive guide to launching the application.

---

## 1. Pushing to GitHub

Since you have all the files ready, initialize your Git repository and push your project to GitHub. 
Run these commands from the root directory (`nep-timetable-system`):

```bash
# Initialize git repository
git init

# Stage all files (the newly created .gitignore will prevent node_modules and target/ from being added)
git add .

# Create the first commit
git commit -m "Initial commit: Complete NEP timetable system with GA engine and React frontend"

# Link to your newly created GitHub repository
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git

# Push the code to GitHub
git push -u origin main
```

---

## 2. Launching Locally (Development Mode)

If you need to show the project locally to a professor or stakeholder, follow these steps:

### Backend (Spring Boot)
Ensure you have **Java 17+** and **Maven** installed and available in your `PATH`.

1. Open a terminal and navigate to the `backend` folder:
   ```bash
   cd backend
   ```
2. Compile and start the server:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. The backend will start on `http://localhost:8080`. Seed data (Admin users, faculties, and courses) will be populated automatically by `DataInitializer`.

### Frontend (React + TypeScript)
Ensure you have **Node.js** installed.

1. Open a new terminal and navigate to the `frontend` folder:
   ```bash
   cd frontend
   ```
2. Install dependencies and start the dev server:
   ```bash
   npm install
   npm run dev
   ```
3. Open your browser and navigate to `http://localhost:5173`. You can log in using the credentials populated by the backend:
   - **Username**: `admin`
   - **Password**: `admin123`

---

## 3. Production Deployment Guide

For a full production launch, you will need to host the backend and frontend separately (or deploy them together using Docker).

### Option A: Using Docker (Recommended)
You already have a `docker-compose.yml` file in your repository! This is the easiest way to launch the entire stack on any cloud provider (AWS EC2, DigitalOcean Droplet, Linode).

1. Install Docker and Docker Compose on your production server.
2. Clone your repository.
3. Run the following command in the root folder:
   ```bash
   docker-compose up -d --build
   ```
This will automatically build the Spring Boot `.jar`, compile the React frontend via Vite, set up the PostgreSQL database, and run everything in isolated containers.

### Option B: Cloud Platform Deployment (Free/PaaS Platforms)

**1. Database (PostgreSQL)**
- Create a free PostgreSQL instance on [Render](https://render.com) or [Neon](https://neon.tech).
- Note down your connection string (e.g., `jdbc:postgresql://host:port/dbname`).
- Update your backend's `application.yml` or set environment variables (`SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`) in your hosting provider to connect to this database.

**2. Backend (Render / Railway / Heroku)**
- Connect your GitHub repository to a PaaS like Render (Web Service).
- Set the Root Directory to `backend`.
- **Build Command**: `mvn clean package -DskipTests`
- **Start Command**: `java -jar target/nep-timetable-system-0.0.1-SNAPSHOT.jar`

**3. Frontend (Vercel / Netlify)**
- Connect your GitHub repository to [Vercel](https://vercel.com) or [Netlify](https://netlify.com).
- Set the Root Directory to `frontend`.
- Add an Environment Variable: `VITE_API_URL` pointing to your deployed backend URL (e.g., `https://your-backend-app.onrender.com/api`).
- **Build Command**: `npm run build`
- **Output Directory**: `dist`

---

## 4. Final Verification Checklist

- [ ] **Login Works**: Attempt logging in with `admin` / `admin123`.
- [ ] **Data Seeded**: Check the Faculty and Course tabs to verify that the `DataInitializer` seeded the database.
- [ ] **AI Generation Test**: Go to the Timetables screen and click the "Regenerate with AI" button. Check the Insights panel to see the Genetic Algorithm metrics (Fitness Score, Conflicts).
- [ ] **Responsive UI**: Test the dashboard on both desktop and mobile to ensure it retains its premium aesthetic.

Congratulations on completing the NEP Timetable Scheduling System! 🎉
