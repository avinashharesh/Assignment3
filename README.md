# Expense Echo
Expense Echo is an expense tracker app designed for ease of use. It appeals to those who value
straightforward, efficient tools without the complications often found in more complex
software. The app's unique selling point is its simple and user-friendly interface, which
promotes a seamless experience for all users.

In terms of functionality, Expense Echo offers a variety of features to help users manage their
finances. It supports expense tracking in Australian dollars, allowing users to categorize their
spending for better organization. Additionally, it provides analyses and graphical displays of
spending trends, which help users gain deeper insights into their financial habits. For a
personalized experience, user profiles are securely stored and can be accessed via email or
Google sign-in.

However, the app does have some limitations. It offers limited customization options for both
the user interface and features, which may not meet the needs of all users. Another significant
drawback is the lack of functionality for managing shared or group expenses, which could be
a drawback for users who share financial responsibilities with others. Finally, the currency
support is limited to only Australian dollars. This may not suffice for users who engage in
frequent international transactions or deal with a wide variety of global currencies.


## Implementation of the proposed functionalities:

| Proposed Functionalities | Detailed Description | Implementation status |
|--------------------------|----------------------|-----------------------|
| **Login/Registration**   |                      |                       |
| User Authentication      | Provides a secure entry point for existing users to access their accounts by entering a username and password. A fundamental feature for maintaining user-specific data security and personalization. | Fully implemented |
| Sign-up Option           | A dedicated registration screen for new users to create an account by providing essential details such as name, username, email, and password. Essential for expanding user base and securing user data. | Fully implemented |
| **Main/Home**            |                      |                       |
| Central Hub              | Acts as the primary navigation interface of the app, directing users to various functionalities like adding expenses, viewing summaries, and accessing reports. This is the core interface that users interact with upon login. | Fully implemented |
| **Add Expense**          |                      |                       |
| Expense Logging          | Allows users to manually enter specific details of each transaction including the amount, date, and category. This feature is crucial for tracking personal finances accurately. | Fully implemented |
| Category Selection       | Users can choose from a predefined list of categories via an expanded dropdown menu, simplifying the classification of expenses and enhancing the organization of financial data. | Fully implemented |
| **Show Expense**         |                      |                       |
| Expense Visualization    | Provides visual feedback on spending through dynamic bar or pie charts, helping users understand their financial trends at a glance. Visuals are a powerful tool for better financial planning and analysis. | Fully implemented |
| Data Representation      | Employs local storage solutions like Room for efficient data management. This ensures quick retrieval and secure storage of financial data, enhancing the app’s performance and reliability. | Fully implemented |
| **Profile**              |                      |                       |
| User Information         | Intended to display essential user information such as username and profile picture. Helps personalize the app experience but is currently not available. | Partially Implemented |
| Account Management       | Would allow users to manage their accounts, including viewing their financial balance after expenses and signing out. Important for personal finance management but not yet available. | Fully Implemented |
| **Budget**               |                      |                       |
| Budget Management        | Planned to enable users to set specific budget limits for various categories and track their financial allocation efficiently. A key feature for effective financial management, yet to be implemented. | Not implemented |
| Budget Tracking          | Would offer functionalities like notifications and alerts when expenses approach or exceed the budgeted amount, helping users stay within financial limits. Critical for maintaining budgets, but currently missing. | Not implemented |


## List of advanced features and their implementation status:
| Feature name  | Description  | Implementation status |
|----------|----------|----------|
| Google Authentication | To streamline the login process for our app, we're implementing Google Authentication, allowing users to conveniently sign in using their existing Google accounts for a seamless and secure access experience.  | Fully Implemented |
| Firebase database | We have integrated a Firebase database to enhance the ExpenseEcho Android app. This database securely stores all user-added expenses in a structured JSON format with keys for description, category, name, and amount. This arrangement not only improves data management and retrieval but also ensures real-time synchronization across various devices. Additionally, it enhances security and scalability, providing robust protection for users' financial data and ensuring that it is consistently updated and accessible across all user interfaces. This feature was not initially proposed but has been added to create a more comprehensive and user-friendly experience by utilizing advanced cloud storage technology.| Fully Implemented |
| Google Calendar  |  By incorporating Google Calendar into our app, we aim to leverage the user’s calendar events to perform context-aware and personalized tasks, enhancing the app's functionality with tailored recommendations and reminders based on their schedule. | Not Implemented |
| Retrofit – News | We have integrated a news API into the ExpenseEcho app, which displays news in a lazy list format on the app's home screen. This feature provides users with general news coverage, not limited to financial topics. The implementation of a lazy list ensures that the app remains efficient in data handling and user interface responsiveness, allowing news items to load progressively as the user scrolls. This addition enhances the app’s utility, making it a more informative and engaging platform for users.| Fully Implemented |

## List of pages:
|  |  |  |
|---------|---------|---------|
| ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/5911d6d9-9ef3-4df9-8e24-cc9207689a1f)| ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/6116fcf8-a228-47bf-9b61-ae40be654015)| ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/8696ead0-31f2-4119-bec8-755306ea40ce) |
| ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/13cd00f8-2897-4243-8104-303c1ebdb4d2) | ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/055ff972-5332-4571-b161-edc9c441beaa) | ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/bc18ab6e-ff4c-40a4-bea2-8ff187773afa) |
| ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/e168986d-d951-4348-b1b2-804344fd1c00) | ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/455e1e44-a9fb-4540-bc6b-400c397fd0b5) | ![image](https://github.com/avinashharesh/Assignment3/assets/61133789/a5f0ff43-1df7-4f61-a446-b3ebd7e90579)|




