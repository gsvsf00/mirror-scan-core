# Mirror Scan API

A Spring Boot-based API service that handles background operations for data retrieval and database modifications.

## 🚀 Features

- RESTful API endpoints
- Database integration with JPA and MongoDB
- OAuth2 client support
- Web scraping capabilities using JSoup
- Actuator endpoints for monitoring
- Development tools for hot reloading

## 🛠️ Tech Stack

- Java 22
- Spring Boot 3.3.2
- Spring Data JPA
- Spring Data MongoDB
- Spring Security (OAuth2)
- Lombok
- JSoup
- Maven

## 📋 Prerequisites

- Java 22 or higher
- Maven
- MongoDB
- Your preferred IDE (IntelliJ IDEA, Eclipse, etc.)

## 🚀 Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/mirror-scan-core.git
cd mirror-scan-core
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## 🔧 Configuration

The application uses Spring Boot's configuration system. You can configure the following properties in `application.properties` or `application.yml`:

- Database connection settings
- MongoDB connection settings
- OAuth2 client configuration
- Server port and other Spring Boot properties

## 📦 Project Structure

```
mirror-scan-core/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── .mvn/
├── target/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## 🔍 API Documentation

API documentation will be available at `/swagger-ui.html` when the application is running (if Swagger is configured).

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- Your Name - Initial work

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- All contributors who have helped shape this project 
