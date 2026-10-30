# Lab 03: Pseudocode

This guide describes the current behavior of the five Java source files in `src`. For the editable flowcharts, open [Lab_03_Flowcharts.drawio](C:\School\Lab_03\Lab\Lab_03_Flowcharts.drawio); its first page explains the flowchart symbols.

## Exercise1.java — Book list and average price


### Pseudocode

```text
START
    Ask for number of books
    Create an empty list of books

    REPEAT once per book
        Read book ID
        Read book title
        Read author name
        Read price
        Create a book and add it to the list
    END REPEAT

    totalPrice = 0
    displayNumber = 1
    FOR each book in the list
        Print displayNumber and the book's details
        totalPrice = totalPrice + book price
        displayNumber = displayNumber + 1
    END FOR

    averagePrice = totalPrice / number of books
    Print averagePrice
END
```

## Exercise2.java — Celsius to Fahrenheit


### Pseudocode

```text
START
    Ask for number of cities
    Create an empty list of temperatures

    REPEAT once per city
        Read Celsius temperature
        Create a temperature record and add it to the list
    END REPEAT

    cityNumber = 1
    FOR each temperature in the list
        Fahrenheit = Celsius * 9 / 5 + 32
        Print cityNumber, Celsius, and Fahrenheit
        // The current Java code does not increase cityNumber.
    END FOR
END
```

## Exercise3.java — Employee gross salary


### Pseudocode

```text
START
    Ask for number of employees
    Create an empty employee list

    REPEAT once per employee
        Read employee ID
        Read employee name
        Read salary
        Create employee and add it to the list
    END REPEAT

    employeeNumber = 1
    FOR each employee in the list
        grossSalary = salary + 0.30 * salary
        Print employeeNumber, name, ID, salary, and grossSalary
        employeeNumber = employeeNumber + 1
    END FOR
END
```

## Exercise4.java — Student marks and grades


### Pseudocode

```text
START
    subjects = Mathematics, Physics, Chemistry, Programming, History
    Create one marks array with one position per subject
    Ask for number of students
    Create an empty student list

    REPEAT once per student
        Read student ID and name
        FOR each subject position
            Read mark into the marks array
        END FOR
        Create student with ID, name, and marks array
        Add student to the list
    END REPEAT

    reportNumber = 1
    FOR each student in the list
        sum = 0
        FOR each mark
            IF mark >= 90, grade = A
            ELSE IF mark >= 75, grade = B
            ELSE IF mark >= 50, grade = C
            ELSE, grade = F
            Add mark to sum
        END FOR
        average = sum / number of subjects
        Print reportNumber, student ID, name, total, average, and each mark with its grade
        // The current Java code prints reportNumber + 1 and does not increase reportNumber.
    END FOR
END
```

**Current-code note:** The marks array is created once before the student input loop and reused for every student. Each student therefore refers to the same array, which contains the last student's entered marks after input finishes. The report label is also always `Student 2` because the display counter starts at one, is printed plus one, and is never increased.

## Exercise5.java — Account registration and banking menu

The file contains the account model, authentication and registration helpers, and the main menu. The draw.io file has separate pages for the main menu, registration and login, and account actions.




### Pseudocode

```text
CLASS Account
    Store name, account number, and balance

    DEPOSIT(amount)
        IF amount > 0
            balance = balance + amount
            RETURN true
        RETURN false
    END DEPOSIT

    WITHDRAW(amount)
        IF amount > 0 AND amount <= balance
            balance = balance - amount
            RETURN true
        RETURN false
    END WITHDRAW
END CLASS

CLASS AuthService
    Store account list and scanner

    AUTHENTICATE(accountNumber)
        Find account with matching number
        Return that account, or null if none exists
    END AUTHENTICATE

    REGISTER()
        Read name until it is not blank
        Read account number until it has exactly 14 characters and parses as a long
        Read starting balance until it parses as a number from 0 to 10,000,000
        Add a new account to the account list
        Show message asking the user to log in
    END REGISTER

    LOG_IN()
        Read account number
        IF it parses as a long and a matching account exists
            Return the matching account
        ELSE
            Show invalid account message
            Return null
    END LOG_IN
END CLASS

MAIN
    Create scanner, empty account list, and AuthService
    REPEAT until the user chooses Exit
        Show main menu and read option

        IF option is Sign in
            Call REGISTER
        ELSE IF option is Log in
            account = LOG_IN
            IF account is not null
                Show welcome message
                REPEAT until user logs out
                    Show account menu and read option
                    IF option is Check Balance
                        Display account balance
                    ELSE IF option is Withdraw
                        REPEAT
                            Read amount
                            Try WITHDRAW(amount)
                            If successful, show success message
                        UNTIL withdrawal succeeds
                    ELSE IF option is Deposit
                        REPEAT
                            Read amount
                            Try DEPOSIT(amount)
                            If successful, show success message
                        UNTIL deposit succeeds
                    ELSE IF option is Log out
                        Leave account menu
                    ELSE
                        Show invalid option
                    END IF
                END REPEAT
            END IF
        ELSE IF option is Exit
            Show goodbye message
            Close scanner
            Stop program
        ELSE
            Show invalid option
        END IF
    END REPEAT
END MAIN
```

**Current-code note:** Accounts are kept in memory only. Although `Users.json` exists in the project folder, this Java file does not read from or write to it. The account number is checked for length and numeric form during registration, but the code does not check whether that number is already registered.
