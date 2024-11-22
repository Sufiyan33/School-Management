# School Management System
In this project discover the inside and outside of our School Management Project. Whether you're an administrator or a student, this system is designed to simplify school management tasks, improve communication, and provide a secure and efficient educational environment.

# Project Highlights:
1. Modular Architecture:
Our School Management Project is divided into two distinct modules: Admin and Student, each with its own routing system, ensuring a seamless and organized user experience.

2. Robust Authentication and Authorization:
To safeguard your data and maintain security, we've implemented JWT (JSON Web Token) authentication and authorization. This ensures that only authorized users can access the various APIs.

- Admin Functions:
  - Admin Login and Token Generation: Admin can securely log in to the system and generate access tokens.
  - Student Management: Admin can perform CRUD operations for students, making it easy to manage student records.
  - Fee Management: Admin can conveniently process student fee payments within the system.
  - Teacher Management: Admin have full control over teacher information, including adding, updating, and deleting teacher profiles.
  - Leave Management: Admin can view leave applications from students and approve or disapprove them, ensuring efficient leave management.

- Student Functions:
  - Student Login and Token Generation: Students can log in securely and generate access tokens.
  - Self-Record Access: Students can access and update their own records, providing a convenient way to maintain personal information.
  - Leave Application: Students can apply for leave, making it easier to request time off when needed.
  - Teacher Directory: Students can view a list of teachers, facilitating communication and access to important educator information.

- Angular Material UI:
  - We've incorporated Angular Material UI as the user interface library, ensuring a visually appealing and user-friendly interface.
  - This modern UI library enhances the overall look and feel of the system.
  
- Test Backend application:
  - Run Application on define port.
  - Open postman and give url:
	- http://localhost:8080/authenticate
  - Body content:
	```bash
	{
		"email": "admin@test.com,
		"password": "admin"
	}
  - You will get jwt token in postman console.
  - If you are getting it means your admin api is working.
