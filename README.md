# Expense Tracker
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

## List of pages
|  |  |  |
|---------|---------|---------|
| ![Login Page](https://lh3.googleusercontent.com/pw/AP1GczOUSI4uTGPr-7HeRP5mlHAqeTZagPGPtXGCjXlXczGz4a9aqkJh_ZiqSvzUo7bKs9BqwJgXP8DBrAHVpKLr-O54i57Ia5SdcgnFHTH6PnUdfqZjju7KzyYiPmRfPmlYpeix8LwSYKZ7qpQg2LiDCK1qgxj8SrYYZoGZl13Wc3W4NukhIKRLulUTHh9_wRnr6mwfzNLvw6WVEhQRUptI0M9O16Lluv3r-OBANnqkOO3lXOLEN9JK6jAgEcxs11j7kTU9jv85cAEiAFKMmawqS1VHNI7NidJ-2Paaky6wkpcftwUE_ilSQlVucZ4nlP-ntj5cE_3Ts2MmyVCktE4t1hmuJeYM_384Xu2IC0sJe9GP8YhrmMU0Fx0T7Jjh67bNDor-0QZITkO6MRjWd9uHPwtHE9kRQ-1EiHeutaRansD0Dio9JOzPD-vz_Fm3k-k-p-8nJrU0Dbu0ooQVk-x395-NDhCIbvM_DpyuMZpF3F4bXq1qbexkjXDmP1JaFn5t60nTnXgB-_LcGEgO6zvNHUvfBsviehIY7cUmZ5vuF_kLzwj14xLr-l5StmQSissbK5k6gUIWxgUiPApQ8SVGodVxBiRoSDVvtcp7kTRmgd03DKze53EMXI1G5CiB2bUA_xZI1dzt0Zk2_duzU4vJrbb5-N3qlSUNCjYLLbtr8PMswIY4-6tSq67DCrOjy7eDxNqq4ASXnFXhNQZ5s0Rx0iE2WpxfkK_HqvXpIikaqXnPXS0o9V-NiKKMCSEvvYz-Z4bVrCM8sOSxqYQosuiJAwzlyuQp-HkeI7Fsz9hYSCFHlSaWiodASXcsLfe0y-tMqsrax_4tcefNCWPh_FrkO960-IQ75JTKVXjHqJWW7UXGkav2RxutrB4yppfuAazRMR7Xp1lWeP3UsFSCU3QmPH3RvHWGAuBbSlfSr82r3YOyLn_metOXWksAn0OnrhF8QUcn5ad8WL6zx3A35bJP7Db6cnM-hAz63-Ae0ltUY2MYIyauzyIlt3OLhaC5IGdO2r2R=w416-h879-s-no-gm?authuser=0) | ![SignUp Page](https://lh3.googleusercontent.com/pw/AP1GczOnSQ3NNybDhio8eNXGKm7QsBmDAiVUdoH3muU0yXi7VI-Mpa06Bu1-v1MixmJgOagQHvDfQeIMyEU7mpLGqjaW32LIInxw6NcLpZciiiudatwJ2gftpeLf5RNr7xRLkmQJOvHoRCAJpfQXvy3UjgxYHTao1oGObXsNorwgfhCP7Y-Vr0H-NROKHSlifbzZ-VvvXwCZP-ub7RFeNxIkbvcQIPPg9G95aTdmp9_lmJkvcgCmeti-JOeCtdfM_WgcIKOODNLxqIb2Tr26w6KBGBxDtEUlii6_cMEJ1kPwEOz4sDPhsGqP52vhQmprB6QL48xcO1WmJ14dC7wZyT2FKy5Nq9PrkzRBjr2QRknDR_kOuFFH4-FsiFMtMiZRsvgect6bp0YMI3OaX9rBl-q14lD9b1roCRZ2JQzZBB2Tenp11dz-C423wGvY6s4Sz8eVU1SYHfd9eLgw2Av_GN6vPMcmRuIPtI8n3Oyfo6u9_v7H7FinFSnHBF25D4N7vUgb1DS-bi38WqeHSJREmGXjIJ2oxZ8SfV5yj64VdfvhkObwpqwhJWsMjj6Z6uDOKlUslgXvnrdlj2FKLux4iL5E6D_OML1wiJsrAWvzMDPVDjm_y0i5rF-8P-NQ3Iwh2zEPMW9AIdSJD-cHUztrnCdA7wCkL6bH7XEf9qxGvvnZoq_GtgtAPV4Pbv6my0Q3vMBmTfeZUVIy3qLdPGvmUhqrXtPyne5v76qjQs6F6S2Ck6Ta4sMjDWoCb2ivcLtxdJa8KSm5mB8VB5OnWMfVlZhVPcooWmBGg-nAR28LeQx-caygzaW5lZoGVytolxvBnFBGBXNhZme5p_rU1AkXRPlnV3T_UmDtwJ3CHraSaIlGmYiEQddjPciN7QqxDBV7oGp_MRryDgr4l-7-wvKVO9P7SehViHF10XO5wqbeogsTF0Uo3N-lMD14pCSXp5M8E2q-dFZUzsVsxbn2HySzqDyioMYY1aRH15bup-Y7wEjmP17MfcYLsP_PhMBjplCCyzP7Ne2Q=w416-h879-s-no-gm?authuser=0) | ![Home Page](https://lh3.googleusercontent.com/pw/AP1GczO0EoaQWCkDP2etfgYqGUNXqzxwdygsNBekTvxjn2G6nxfzQz5XjBvTHyIHVJJwm0WLys4QYfov_Wne_NAL7ouGWrxE6O-1Pi2c7lgvtHaueqprUqCUWmvhkdcl1Ip6blwYWJ5MbddUO4ndXHZgNiZMbwj0Iw2BSZRWT4kxVuJtYpJ_3IAq6ymSYM3JltETc1Io7Sm5L17aCjOh-yay0f0AtMoOFecI_xEE7r4LbI-RSB7066TAyePi5tESBaDrwyJOYyZlXhwDLAtQyD02TwGKYPWb8hNlVrW-O6ZaMSQ6564dnThaKtEOsX0X2iQM226JNBKH4LSjrCu7EL_4C-PX9fIIH4ZXzLnmyjUi1ZGCLc4rtL_B4BPoZq-vTqzrjO2qEht9dqo1Q1P9YmL9kJMz6Zvgi5mEJ5koSD6Ts3_q35non45FGFp--V7WnppSaTFWIK219OI_tMubB-ofFkGy2qJr-_5e7HiHRTqA1aUBudoixwTeBd3C887GWXowYjAmaZzFk_1rbMevJ2CyOHVvEx8Jw4gochqbf50-3EQIXVfbLb07IfXUEoixrsh5BaJj0N4QLNmsmcFs2Ibg9DROkoy98HJED3qRxXhrNHEkZNSq-T39B2YEZs4vbk3G2AEk8_OrRoQ919-UvxYvAnFv5_BNz1zWpoAzpCLj0431YzDiAlgpgt0sur8xGaZ5T7Y_jSVkEd26-o_5Tq9lv2bx22khi-5085Z5-YR4MKTMQRWtW5yOwD1aH9SdaiMsy_lGdQ3ndWC9YkAZ0Y7-mrcp3ZFXR23cgzeolEggADCxdo8RLvK-geJqopnZrr6PuE65MRJMSZAmrcXVcxH8IN0RTZfvrEj1QkUHnhf5Oe-jLjvNQmBKfk9yoqo6IiCqRidCY6OeU-V95_EoSankhHWS8nrEIh3q8N-WUI3OQ8ROCJixzvxTdMkCMLo2qKuYHhOqa5R5hcvLzH8SL9j_Do5tkrHNkKMNPHgQ7fF3MKz7cmAga_X637xmMQqblVvyuaSf=w416-h879-s-no-gm?authuser=0) |
| ![Add Transaction Page](https://lh3.googleusercontent.com/pw/AP1GczNEzvT6ktMKDpFGGr6TeeJnFS-X8hkFwezAeaogX09bMhhuj6EqhRIabOl6Iv-ETmjm_BXKEbnyKoz-TGO2czd8Foxt2NxLHqvbGDP8fj8Ou3-eRvAq9qc6ojw6pnYGQ_Cf8faLanjZxcY-wiXqqknFCwmqNqQsP56xSdDqB7cxYPHU1DRsgpvsi0t5fHZnhElqDSd-oJsEE5YEkPNe1c4m4Qm2EeO8i5YVAw-XwRO3m9HSiqgvG6QRGX9hsw1S8W0qWPJWm0I5RKuKx8dhZ_lodMGiUrWHQ26Vgme0g2bOHWMZoUGIDaixteWKfT0xxxfkSpXo4mSXhIX-b2vlh0dSrxMGUV4oGRbatNYzJvEUhTI_7-rxG4C7bs-KBu02lVhXGa5pOf6d399J_PgrbRB4ICAb59IYfMw2WgobIRBaE5whnZCCVNFbX_vlv_7v1m9-PSaMsuHEC8EyxyGCgl-5Sx15U9IfU05agTKQSWC9SdaU_BsuBZeyphCvY9Y4cwewdod6yOiBKk950PWXuseTQku5Dh8WexI3By-KsgS6Sa_Aq-5JCkI2UhinU1VJSf5vuclqYP2_tJs4HeBmnq7-OUKIotgtdp9H_BgDqNsmCYhmiuAxQyT7a3dKb8MjONs_pxmdPz6xjMe83DrIHRCv4ujVnWGxsX999DxNvKxCpEeWAkbMfAc6o7S0--DdnZrTRXhkmqeJwYjK9v5hJ9Gw_hvnIKd0gu5tK9jLk-egcTMHrxuKW55-FmKqMTNE53dc_37d7qF40IdCwqG2rW5WeHOWqsM5dp0woMIGuB4Hkbx2Bi1s0OfThMbCu0FjSVSCNFfTE7gsWrHOolyZobKsjxU7kA5-WRKXQSSV_f4M8UpmxlPFOJbSdUt1YmjSgpmJfTE3QYeX5w8K11yegUUgSwMjo0UeKmEEtHQxvPx8q0tDKTuje-6rZcIXACkmFNn-3QbsOpWfrHYSVUb4cDLAA9DpIHYzFNNLEelWmAmle9JBLkPuDbp1tmB68CeFkUb4=w416-h879-s-no-gm?authuser=0) | ![Update Transaction Page](https://lh3.googleusercontent.com/pw/AP1GczMpuYTv87Rgf0EKqxP_QoPA6uEeduBbfRCAl5P3vELUWiCe6GJcXRtbSZ8TYDZCPsHOo3Fo1GjakDhxEsR6Ear1lejTNcKQ-mlKMBQMcvpUFXUAuegTNCR9-NV4A5AHAtJBVrAQUyalkZduUMoIWDMSNkmzFnl4VBpiAksc2W4QaId_ylzQXOxrcNGPMkyDnQ4KPB1WpRQK_F79-Ittzn_aT2Wk1Y_AA65pl1NGbCVfd8MBhJkzAhuEAOOxNJuq2y8MgAOW-gha-8W1VbkGql2ax9YoN7YUPJJgK-sHG6ot6aQuavgwI84BvZhYomU2SzQgQABVuFl2jUcbx3334fmzASTplbeTBSWZT3XK0RHVBfp3bwxb3LZ7IJVBTKR4Fhvuj_-HdC5dDvAvqowM1tlEi5_961XUYyUSN0T8MUN4r920Skny6NNAC-kXHDW8OytkueSge7k9p_mFrehXQ2ar08N2JCq6WgDE6z-VEX_wpwChK0sTW3g5uqBAfBXTmbk7nGOTSHm1v8me04zmTaCgAxXjF9zD2yzv844YsKCWE4B-r2UzTA9DFtlrUNZSLKVNopwly9MySIW7y5O9EK4ucygvg9g8shCMXmRhdWdLwotIsyP1NN1hcAdVjS_16yJFIxRJazQqyr_X6wFbEfCByg77502wqX1ShEwsk1ZWjZYcEg_VnklqJHggt4jlJNlJ0S_ozKBe2sW0ekvc0tsfB5QWcqYqJmtRjBHbCdejJCXOzFKOj-pZ9uRsn-qm9Tm1mkngKmB4YZBs-5powrCUD8d7KWqHwh17Haq2OKL-UApexKloyxAXXacA4S441BEV2OMcd6jNUYyE2h-_uS66dQB6UESOxKPgGxbRopy4sely374qcREpbIGffrUsX0xCKPGJQ-fOUorOGMi2lCq6vF032u9IYheGkke0XzoEscERGjpRpF9rxlx8Ayvf43o_zn53s9yeoN0VdcctXKN1eNiFHtRe0O3ooW5oc_kF153FE--5c6RfdlDVgYEGCKMo=w416-h879-s-no-gm?authuser=0) | ![Account Page](https://lh3.googleusercontent.com/pw/AP1GczO49N6Rzrft1E-cG_FCDrhBY3wZIq309vLUU-OcXST_MjAX936GOzd7_hXB0SLVFDsXtCL2ym7kNQYCl5caSWnML2PgUw45rmKt0AXgBBg4kVN9mcELNxygkFHNZhbVNon-x4c3E-JkFjcXnkAPIlnkiClwVjWLSY-I71sCYwJ3gqmOu8zSUchq8F9FrQ-ejjyMqVPfIaedVkYE7rLlf6EYhzSyUZErfPTL_dShZOtqDixMMR3mudIxjm_S3B0miLgkFhgni2GUXD-B-BJw3xNe3tTvv7xvv7qtozc2mGjcwDTYSkCfzbNbTt_gLf_CIziXiRjZywVFefLuqHwFX3Snj9R3yTBDlfxKg7WuOCAQHAqa-ZfNjCXGM9IEaaIdpE9ruZ6gYuNGDAp9jBxgfLeJTIS8xtBALztAxH3wYrlJY_iaqkMvL4JoyeB7T5kUFZ9hgX2zSVnlRlW8_9W3S508IUMgTQZvAagUkBZqUayzxVpe6Ig3aAhyl2rZJHlqZoOa3CgolXEZveeqsGxBcJQ9qmeB0t8r4cmsdTmZutPgObLnEwweOpCmDA05t-RSc6qX2KeEAX4a6Y_RJznjAwaAkywCEe9zUaTCoAW1IxJ1SHSRzwznqAoyysO7edVxwVj-Nog9roxCNY4vxMYphFQlzZdIXukjxJPUlwL2YXDGC3Xjqb8iDRSSDPigXLJnjaOLqk_lejs85dU1rHN7z9yYanUPNohqrFJUrjMy8kBjkg9msW9xKj8CT7dS6jOsJdt5EmGTpOrOQueXQIWM-kVWUiDzTJMdn6YE-RCSm9gi423RUqOK11KXlfzgxhTDe05k6faOw80_R7evJAmSqJ1Qk3LaYxGs8IibYeQTNvPLvvIXjrzKfdH17wpXnPQ6zKM_49LK9dE5QaK_hMU-X1kB20Lhlnj-jPuV5kuq5tOldcm4Xmfm19PMcdYuDolpxnT6f1c5XFa_JzobFjaD-cI8UlrSzno5ht8HjrilmewSFSlbwlaSJTKuOWnLBV5qUhwF=w416-h879-s-no-gm?authuser=0) |
| ![Update User Information Page](https://lh3.googleusercontent.com/pw/AP1GczNTr3uN0pxfv_TCllFIBaWXDf7eUh5zXqAbrdmdY99eDgUXTXO5lOOFG2h2lOhkqqecK7iFPTkOPlySns3k2xnuwkU-q324Ewf_JjGQqQTwyE-LUB5iLe0Ma9GPj0zlgW5Y_C1GrTwA-Z-FVJ7lMXJdhWBArrMxd4dn8v-xxN1N71f6MSSidWnOkg0mmCSn1M_Vzsbdy0nUMUs1snUExLAD1GKWSofTOvBQAR7AJf5bSEMWe7K4SHNxslPXacgEJNQaz5C1OLXvi85w_JbRUs9kgHkpKQKB29AH21otpakIrTK-xoXLOowdfmhgT3Sob5NUIL5hwxYRTCPp7yy7aKrQSZGrYHmf7EkWNH2X4Z8XMPK1enRPCin7gYL6uGlQOuA6dcGtEqBzBE8HFAsVRPscdNnUjNNBch-GFK0T9iqFbUWAJQDD7w7S5aZ_zLNYWoK_UhBLl6IBIx-Nk9NoZDZLGYnY7OB8kGWIFdk1kSzL7QXgMfUtqTd1ydY7zQnqtQL4_sh-V-_wj1tHXHHF_oTsXvV4Dd4B3Tj1CPmjc03jbH8Ur8O9f0sPUra1dPyK44jGbj0RIXoyZzrvMNM219sWoAAP66-BjUPIlizUWWJ9RwzhXcOYiqqQ0m4ljLNvqoka0t6WKZYQcxwdD_QOHbvS9xdmjN3RkI5Bssflbk2iMvQEtl9sdjA3b0xVQQW9DX_WGuZp4KUOFSsb7gTLrPdX8P92ZnQniip7F-GMl-fExrLGPruvRRI5qRxQSRYfk74xrDPvjXp6uc8OdDph4ZpeHa7VW9KSDZpNsJ1jegV45mkgK1VlfgGcVYamSnN5gpu1DlBSKzb26ijzpmUGTP3cw_J8SRZmGX7EAOw4g66YwV-KgdBCdad3L8Lb1uWmL-So9um12WrNWIlwvy67_EFVuFIeLUwBhyjm5vQZpJ5E8wGV-48Bg5asPmet4Pb9wc_8gOEhcGsI_fuJJ84gF0FmYsA2Yl4nqk4owLDFBtns3FYCQzQSel-soDWEsGVOlUuA=w416-h879-s-no-gm?authuser=0) | ![Transaction Reports Page](https://lh3.googleusercontent.com/pw/AP1GczOQ3WitdwWGxVmANvk9mLYNBC8LNHgaRMhkyVj4mDUBuy84a6A845YRfr3pje82bK3ruOCRgpolnYFLw10IU7F3mrT_Lm7SEz3MBaDkwVM78wEmp-RKM4LHiLWtK_BT3TjqHu7giRjymsF9TFPH3KWmWOKBW5TD7kh_a5Oi5YGEZ3nelvEmCNnt9Qu14LZmiw-b7xv5r19ChC2UD8_EsDg4n7yAEOzLTE6BTNaR8HAhR0NXcMMimtejlCI-zaKQL_PWWtsKOQf6dLOMGHXzLcMX1d2lY62OekoEewwAfQWpN_0uoTjqRkDtRhKYa9j70QQGbe6Wno21GrIJH4askqQPBTI_VSgLHHM478PhadfvuEf5uwWsJJ9jNBwjAC4f4TJ_qXnl3no45OdBVbUrlpp1VzUJhO9tw1LQoIXBGoSDSJ0w24U22AlF4_GGEKasNH-DMExAU5q4RmbNHpcn_vr-OQlDy6kKh_OQwdp0SPEgR8IcO882L3Vz6e8GZ0VmYsPOUHsA0JZbICEerEoThLKH9FfnZmnsG__DEG4kDZ7CSk1q2bET_8iCMh1s0sy4Ihfi5ee1i8XMlAWQIrKekSjQw8uiX8DYyxMinl8h85MRZGQ2mXuQ4KSMF-k5sRRvGxloQxPqbS9XBop2EVBsx5tcMzEvnBqjx5wjmkoz9xNy13gHbdxZvwWzpNZcjioZEg6Sypa0KLl5nOMZbcZENKa3yQGZrN2kwepuQRgdAeu35XMES1BvJaSqRi_o9ZuSJDIPcIN4V1CcWl6Ysw32-M7AhzirD2n2Z1FWBuQrCmB0RcU_gPtM9ldHZaxDKTS0QAJIpmVV5p0nXZ1pv2yz48OcwmOmWwveHdmkLZapYXzL3ngGGtwJqB0yMD3jh0nhr47LF0ZO9g-3cgOxFZ1-qeUNy0VT-KA5gIgmcosIEs_VbheqgRaiRoFcEXybGnXp-zSfm2zqxDcycQra8hfElesdyKY96-G8PYPWtr1l30pG0cCkHwhla9oeBbZouX0N--k=w416-h879-s-no-gm?authuser=0) | ![News Page](https://lh3.googleusercontent.com/pw/AP1GczNZdXm4hOFQbuRhYjy6ZZ0OmcrDIL2uOatWsMpibWZIqT5COGcO77a7Xnf5ZSZQYwIFFD2N_wkaZap7Xru3jL-29jTd-3EJcSylV5x5B-oBFYBPpvDTitb6Og572pvdWTsvmn-HhSWRbWS4v9w4doMRK3_PPNLB7VTt_EUApiUDNUCLBPMkUyK_qwF_vSFL8seHxCoUvIljD50UQhjdHKpBshuY3AqYJnFjL5EePSo_V_KUNW4Oa7oA4f0ZqhS9ELiP98z5nnvfGsUVjOmraHh2Br_p6YsWVgMjZ9TYuOWIs6238_Nwd5roGXh7nWKowMDq_ViC_I5rm9jC-9sWzHUKgeg4XgFpiVxLueebkGGDRZXkl8jvd_YqHGCP6Hr5j1puoh4h3oPodFoZxG7DcpnrQ8FMuwGO6Up73Rmhg76YgvXqRKy9DOxJsSEGNXIuNmkat1JWI3FmHK9RQkHE0-ACYDc_UbYPYFZpLrKRATPlpoxKhUiUVMcDKADRZlhYXVWotPxyeoKgZtVxTyRseT7PHcTLB_2lTKBmbw9fhV6OO8vodCLHDHWqW5Ifh8KGEkOmb-jNg8GmEQ5xn40zckkJbweIgRqwOn0ydxUqB0ds3opRo7Pz1F8i6WSNHHuLmjRcxjPIOkfFpBwQPKkqsnOr-1-WqOEpSAtL491ll5VKdLzhOPdrycPYjUn4O1iiGMWkvm14i0De45VA-J2uuDINaLv-HYljhHdXymMQrQaj49FyueOTcoaz1Jr_SZmTwWZIemevLD4hSwkta3W-3-x5ECMNbp5LAtvsQzFyuEC2wmQeQkxHs7366l4a8ZXBE3mKUTvzZebaHTTUsfsQhUNdzWWzVlnpt6Is7NXSI7nDeX-BF91wrWMqUztWE2JBB99xIEcEyZEqNChJe1RVNQ1znWUd-pJqTHo9morBMWgXy1Bsq6EkBvVsaFigiYP0zY7T8je41JtgCwKmVSMp_iKhKK9OVcIEYhEGpkUyXFz6DIT87H9F38SYbNQiOO9KoHC4=w416-h879-s-no-gm?authuser=0) |




