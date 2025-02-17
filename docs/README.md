# Solyu Chatbot - User Guide

## Introduction
> "Efficiency is doing things right; effectiveness is doing the right things." – [Peter Drucker](https://intranet.engineering.ucdavis.edu/sites/g/files/dgvnsk9601/files/inline-files/Effectiveness-vs.-efficiency-%E2%80%93-Let%E2%80%99s-not-confuse-the-two-1.pdf)

![Solyu Chatbot](/docs/Ui.png)   
Solyu is a **task management application** optimized for fast typists. It is a **Command Line Interface (CLI)-based task management chatbot** that helps you keep track of your tasks, deadlines, and events. Designed for **fast and efficient task management**, Solyu supports multiple task types and provides useful features such as task searching, marking, and deletion.

---

##  **Getting Started**
### **1️⃣ System Requirements**
- Java **17 or above** installed on your system.
- JavaFX dependencies (if running with GUI).

### **2️⃣ Running Solyu**
1. Download the latest `Solyu.jar` from [GitHub Releases](https://github.com/caxewhy/releases).
2. Open a terminal and navigate to the folder where `Solyu.jar` is located.
3. Run the chatbot using the command: ```java -jar Solyu.jar```
4. Solyu will greet you and wait for your commands.

---

## **Features & Commands**
Solyu understands the following commands:

### **1. Listing all tasks**
**Command:** list

**Example Output:**

    Here is your task list:
    [T][ ] Buy groceries
    [D][X] Submit assignment (Due Date: Feb 20 2025)
    [E][ ] Team meeting (from: 3PM to 5PM)

### **2. Adding a To-Do Task**
**Command:** todo TASK_DESCRIPTION

**Example:**
todo Read a book

**Expected Output:**

    Got it. I've added this task: [T][ ] Read a book Now you have 1 task in the list.

### **3. Adding a Deadline**
**Command:** deadline TASK_DESCRIPTION /by YYYY-MM-DD

**Example:** deadline Project submission /by 2025-02-28

**Expected Output:**

    Got it. I've added this task: [D][ ] Project submission (Due Date: Feb 28 2025) Now you have 2 tasks in the list.

### **4. Adding an Event**
**Command:** event EVENT_DESCRIPTION /from START_TIME /to END_TIME

**Example:** event CS2103 meeting /from 12PM /to 2PM

**Expected Output:**

    Got it. I've added this task: [E][ ] CS2103 meeting (from: 12PM to 2PM) Now you have 3 tasks in the list.

### **5. Marking a Task as Done**
**Command:** mark TASK_NUMBER

**Example:** mark 1

**Expected Output:**

    Task marked as done: [T][X] Read a book

### **6. Unmarking a Task**
**Command:** unmark TASK_NUMBER

**Example:** unmark 1

**Expected Output:**

    Task unmarked: [T][ ] Read a book

### **7. Deleting a Task**
**Command:** delete TASK_NUMBER

**Example:** delete 2

**Expected Output:**

    Noted. I've removed this task: [D][ ] Project submission (Due Date: Feb 28 2025) Now you have 2 tasks in the list.

### **8. Finding Tasks by Keyword**
**Command:** find KEYWORD

**Example:**

find meeting

**Expected Output:**

    Here are the matching tasks in your list:
    [E][ ] CS2103T meeting (from: 10AM to 12PM)

### **9. Exiting the Chatbot**
**Command:** bye

**Expected Output:**

    Bye. Hope to see you again soon!

---

## ⚠️ **Error Handling**
Solyu handles various errors:
- **Invalid commands:** Displays an error message.
- **Duplicate tasks:** Prevents duplicate task entries.
- **Invalid dates:** Ensures correct date format (`YYYY-MM-DD`).
- **Missing parameters:** Notifies users when required input is missing.

---

## **Advanced Features**
### **🛑 Detecting Duplicate Tasks**
Solyu **prevents duplicate tasks** by checking if a task with the **same description** already exists.

**Example:** todo Read a book todo Read a book

**Expected Output:**

    This task already exists in your list!
---
## **FAQ**
### Where is my data stored?
Your tasks are stored in a file called **`task.txt`**, located in:

    {User Home}/ip/task.txt

### What happens if I delete `task.txt`?
If the file is missing, Solyu will **automatically create a new one** upon startup.

### Can I use multiple words in tasks?
Yes! You can **add spaces** between words in tasks.

**Example:** todo Buy groceries from supermarket

---

### **Developed ️ by Chan Xiao Yong**

---