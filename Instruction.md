# Banking Account Exercise

A Java console program for registering an account, logging in with an account number, checking a balance, depositing or withdrawing money, and viewing transaction history.

## Files in this repository

```text
.
├── .gitignore
├── Account.java
├── AccountRepo.java
├── BankService.java
├── Exercise5.java   # Main class
└── README.md
```

`Account` stores account details, balance, and transaction history. `BankService` handles registration, login, and account actions. `AccountRepo` reads and writes account data as JSON using Gson.

## Requirements

- JDK 14 or newer (JDK 17 is recommended).
- Visual Studio Code with the Java extensions, or another Java IDE.
- Gson. The Gson JAR is not included in this repository, so each person who clones it must add the library locally.

### Install and configure Gson in VS Code

1. Download the Gson JAR from the [official Gson releases](https://github.com/google/gson/releases). This project has been using Gson 2.13.2; use the `gson-2.13.2.jar` asset for the same version.
2. In the cloned repository, create a folder named `lib` next to `Account.java`, `AccountRepo.java`, and the other source files.
3. Copy `gson-2.13.2.jar` into that `lib` folder. The resulting path should be `lib/gson-2.13.2.jar`.
4. In the cloned repository, create `.vscode/settings.json` and add:

   ```json
   {
     "java.project.sourcePaths": ["."],
     "java.project.outputPath": "bin",
     "java.project.referencedLibraries": ["lib/**/*.jar"]
   }
   ```

   The `referencedLibraries` setting tells VS Code's Java tools to add JAR files in `lib` to the project's classpath. The `sourcePaths` setting is `.` because this repository keeps its `.java` files at the repository root.
5. Open the cloned repository folder in VS Code. Wait for the Java language tools to finish loading the project, then run `Exercise5.java` as a Java application.

If VS Code reports that `com.google.gson` cannot be found, check that the JAR is at `lib/gson-2.13.2.jar` and that the settings file is at `.vscode/settings.json` in the folder you opened.

## Run in VS Code

Open the cloned repository folder in VS Code, open `Exercise5.java`, and run it as a Java application. Set the run configuration's working directory to the repository folder if you want `Users.json` created there; for a `launch.json` configuration, set `"cwd": "${workspaceFolder}"`.

For this local `Lab` workspace, `.vscode/settings.json` already points to `src` for source files and `lib/**/*.jar` for Gson. If you clone only the files in this repository, use the setup above because that parent `Lab/.vscode` folder and `Lab/lib` folder are not included in the clone.

The program uses `Path.of("Users.json")`, so the file is created in the process's working directory. Set the working directory to the repository folder if you want the data file there. `Users.json` is generated at runtime and may contain account data; it is listed in `.gitignore` and should not be committed.
