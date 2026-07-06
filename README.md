# Secure File Protection System
This is a console-based Java application built as a programming project. It provides a localized multi-user interface with built-in registration, login authentication, basic text encryption, and automated file-handling utilities.
## 🚀 Key Features
* **User Authentication:** Supports secure user account registration and login. Enforces safe length constraints on usernames and passwords during creation.
* **Account Recovery System:** Includes a built-in "Forgot Password" feature that uses a security question (favorite animal) to retrieve account passwords safely.
* **Brute-Force Protection:** Automatically tracks incorrect login attempts. If a user gets their password wrong 3 times, their account is permanently deleted from the registry file for security.
* **Text Encryption & Decryption:** Uses a symmetric character-shifting mechanism (shifting text by 3 places) to cipher data before saving it, keeping user files private.
* **File Management Subsystem:** Offers tools to save encrypted text, search text inside files, clear data logs, create automated data backups (`backup.txt`), and generate statistics reports tracking total lines, words, digits, and special characters.
* ---
## 🛠️ Programming Concepts Used
* **File Streams and I/O:** Reading and writing persistent file data dynamically using `BufferedReader`, `FileReader`, `FileWriter`, `PrintWriter`, and `Scanner`.
* **Data Parsing:** Using `.split(",")` to read comma-separated values inside database files to validate matching account records.
* **Flow Control Loops:** Managed using interactive `do-while` loops and nested `switch-case` statements to keep program CLI menus running smoothly.
* **Robust Exception Handling:** Implements `try-catch` blocks across all major storage actions to safely catch file missing errors without letting the program crash.
