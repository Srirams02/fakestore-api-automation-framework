# 🧪 FakeStore API Automation Framework (CI/CD + Allure + Email Reporting)

> API automation framework built using **Rest Assured + TestNG + Maven**, integrated with **GitHub Actions (CI/CD)**, **Allure reporting**, and **automated email notifications**.  
> Built to simulate a real-world API automation pipeline with execution, reporting, and notification in CI environments.

---

![CI](https://img.shields.io/badge/CI-GitHub_Actions-success)
![Java](https://img.shields.io/badge/Java-11-blue)
![API](https://img.shields.io/badge/API-Rest_Assured-green)

---

## 🔥 Why This Project Stands Out

- Covers core API flows: **Authentication, Products, Cart**
- CI/CD with **push**, **scheduled (cron)**, and **manual triggers**
- Integrated **Allure reporting**
- Sends **automated email summaries after execution**
- Handles **real-world API instability (403 / HTML response in CI)**
- Clean layered structure (**client → endpoints → tests**)
- Executes multiple API scenarios with automated reporting and notification

---

## 🧠 What This Demonstrates

- API automation framework design  
- CI/CD pipeline integration  
- Reporting and result tracking  
- Handling unstable public APIs  
- Writing stable API tests  

---

## ⚙️ CI/CD Pipeline

### Triggers
- Push to `main`
- Scheduled daily execution
- Manual trigger

---

## 🔁 CI/CD Flow

```text
Push / Schedule / Manual
        ↓
GitHub Actions
        ↓
Build (Maven)
        ↓
Test Execution (Headless)
        ↓
Allure Report
        ↓
Summary Extraction
        ↓
Email Notification
```

---

## 📸 Execution Proof

### 🔹 CI Pipeline
<img width="1399" height="880" src="https://github.com/user-attachments/assets/2bd9eaed-f1f1-4d09-bf8d-f23b9678806f" />

---

### 🔹 Allure Report
<img width="1916" height="921" src="https://github.com/user-attachments/assets/1a035176-8782-4139-ac0a-d0f512db0789" />
<img width="1085" height="923" src="https://github.com/user-attachments/assets/ad6f4c99-882a-479d-a8b0-6598580a4040" />

---

### 🔹 Email Report
<img width="1048" height="752" src="https://github.com/user-attachments/assets/bbf9be02-ef0c-471e-8ea2-122d337b88dc" />

---

## 🧪 Automated Test Scenarios 

### 🔹 Product APIs
- Get all products  
- Get product by ID  
- Validate invalid product ID  
- Validate response structure  
- Validate response time  

### 🔹 Auth API
- Login API validation  
- Extract token from response  

### 🔹 Cart APIs
- Create cart  
- Validate cart response  

### 🔹 API Flow Validation
- Fetch products → Create cart  

---

## ⚙️ Real-World Handling

FakeStore API is protected by Cloudflare and may block CI requests.

Observed:
- **403 Forbidden**  
- HTML response instead of JSON  

Handled by:
- Detecting 403 responses  
- Skipping validation safely  
- Preventing false CI failures  

---

## 📂 Project Structure

```text
fakestore-api-automation-framework/
│
├── .github/
│   └── workflows/
│       └── api-automation.yml
│
├── src/
│   ├── main/java/com/api/
│   │   ├── client/
│   │   │   └── ApiClient.java
│   │   ├── endpoints/
│   │   │   ├── AuthAPI.java
│   │   │   ├── CartAPI.java
│   │   │   └── ProductAPI.java
│   │   └── models/
│
│   └── test/java/com/api/
│       ├── base/
│       │   └── BaseTest.java
│       └── tests/
│           ├── AuthTests.java
│           ├── CartTests.java
│           ├── ProductTests.java
│           └── E2ETests.java
│
├── src/test/resources/
│   └── testng.xml
│
├── screenshots/
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## 📊 Reporting

### 🔹 Allure
- Execution summary  
- Pass/Fail details  
- Test insights  

### 🔹 Email
- Total test count  
- Passed / Failed summary  
- Execution chart  

---

## 📅 Scheduled Execution

Runs daily via GitHub Actions cron.

---

## 🛠️ Tech Stack

| Layer        | Tool           |
|-------------|---------------|
| Language     | Java          |
| API Testing  | Rest Assured  |
| Framework    | TestNG        |
| Build        | Maven         |
| CI/CD        | GitHub Actions|
| Reporting    | Allure        |
| Notification | SMTP Email    |

---

## ▶️ Run Locally

```bash
git clone https://github.com/Srirams02/fakestore-api-automation-framework.git
cd fakestore-api-automation-framework
mvn clean test
```

Generate report:

```bash
allure serve target/allure-results
```

---

## 🔐 GitHub Secrets

- EMAIL_USERNAME  
- EMAIL_PASSWORD  
- EMAIL_TO  

---

## 👨‍💻 Author

**Sriram S**

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" width="18"/> <a href="https://github.com/Srirams02">GitHub</a> &nbsp; | &nbsp;
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg" width="18"/> <a href="https://www.linkedin.com/in/sriram-s-a6947423a">LinkedIn</a>

---

## ⭐ Final Note

This project demonstrates a practical API automation framework with CI/CD, reporting, and handling of real-world API limitations.
