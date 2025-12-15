# Emergency Supply Tracker 🚨

A comprehensive Java Swing desktop application designed for managing emergency supply donations, inventory tracking, and distribution coordination during disaster relief operations.

![Java](https://img.shields.io/badge/Java-8+-orange.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-blue.svg)
![NetBeans](https://img.shields.io/badge/IDE-NetBeans-green.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

## Prerequisites

1. **Java Development Kit (JDK) 8 or higher**
2. **NetBeans IDE** (recommended)
3. **PostgreSQL Database Server**
4. **Required JAR files:**
   - PostgreSQL JDBC Driver (postgresql-42.7.8.jar)
   - JCalendar library (jcalendar-1.4.jar)

## Setup Instructions

### 1. Database Setup
1. Install PostgreSQL if not already installed
2. Create the database by running the SQL script:
   ```sql
   -- Run in PostgreSQL command line or pgAdmin
   \i database_setup.sql
   ```
3. Update database credentials in `src/util/DatabaseConnection.java` if needed:
   - Default: localhost:5432
   - Database: emergency_supply_tracker_db
   - Username: postgres
   - Password: 123

### 2. NetBeans Project Setup
1. Open NetBeans IDE
2. File → Open Project
3. Navigate to the project folder and select it
4. The project should load with all dependencies

### 3. Required Libraries
Make sure these JAR files are in your classpath:
- `postgresql-42.7.8.jar` (PostgreSQL JDBC Driver)
- `jcalendar-1.4.jar` (Date picker component)

If libraries are missing:
1. Right-click project → Properties
2. Libraries → Add JAR/Folder
3. Add the required JAR files

### 4. Running the Application
1. Right-click on the project
2. Select "Run" or press F6
3. The Dashboard window should open

## Project Structure

```
src/
├── controller/     # Business logic controllers
├── dao/           # Data Access Objects
├── model/         # Data models
├── util/          # Utility classes (Database connection)
└── view/          # GUI components
```

## Features

- **Donation Management**: Add, update, delete donations
- **Inventory Tracking**: Automatic inventory updates from donations
- **Request Management**: Handle supply requests
- **Distribution Tracking**: Track distributed supplies
- **Reporting**: View logs and reports

## Troubleshooting

### Common Issues:

1. **Database Connection Error**
   - Check if PostgreSQL is running
   - Verify database credentials in DatabaseConnection.java
   - Ensure database exists

2. **Missing Libraries**
   - Add required JAR files to project classpath
   - Check project properties → Libraries

3. **Compilation Errors**
   - Clean and rebuild project (Build → Clean and Build)
   - Check Java version compatibility

4. **GUI Issues**
   - Ensure all .form files are present
   - Rebuild project to regenerate GUI code

## Main Classes

- `DashBoardPage.java` - Main application window
- `DonationInventoryPage.java` - Donation and inventory management
- `RequestDistributionPageTemplate.java` - Request and distribution management
- `DatabaseConnection.java` - Database connectivity

## Database Schema

- `donations` - Store donation records
- `inventory` - Auto-updated from donations
- `requests` - Supply requests
- `distributions` - Distribution records
- `view_logs` - Activity logs

## Screenshots

*Add screenshots of your application here*

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or support, please contact [your-email@example.com]

## Acknowledgments

- Built with Java Swing for cross-platform compatibility
- PostgreSQL for robust data management
- JCalendar library for date selection components