# Banking Account Exercise Instructions

This is a Java console program for registering an account, logging in with an account number, checking a balance, depositing or withdrawing money, and viewing transaction history.

## Project layout

Open the `Lab` folder as the project/workspace root.

```text
Lab/
├── .vscode/settings.json  # Java source, output, and library configuration
├── bin/                   # Compiled classes (generated)
├── lib/
│   └── gson-2.13.2.jar    # Gson dependency
├── src/
│   ├── Account.java
│   ├── AccountRepo.java
│   ├── BankService.java
│   └── Exercise5.java     # Main class
├── Instruction.md
├── README.md
└── Users.json             # Created at runtime; ignored by Git
```

`Account` stores account details, balance, and transaction history. `BankService` handles registration, login, and account actions. `AccountRepo` reads and writes account data as JSON using Gson.

## Requirements

- JDK 14 or newer (JDK 17 is recommended).
- Visual Studio Code with the Java extensions.
- Gson 2.13.2. The JAR is included in `lib/`.

## Gson and VS Code setup

The project is already configured to use Gson:

- `.vscode/settings.json` sets `src` as the source folder and `bin` as the compiled output folder.
- Its `java.project.referencedLibraries` setting includes `lib/**/*.jar`, which adds `lib/gson-2.13.2.jar` to the Java classpath.

To set up the project on another computer, clone or copy the complete `Lab` project, including `.vscode/` and `lib/`. Open the `Lab` folder in VS Code and wait for the Java extension to load the project. You should not need to add the JAR manually while those folders and settings are present.

If the Gson JAR is missing, download the `gson-2.13.2.jar` file from the [official Gson releases](https://github.com/google/gson/releases), place it in `Lab/lib/`, and confirm `.vscode/settings.json` contains:

```json
"java.project.referencedLibraries": ["lib/**/*.jar"]
```

If VS Code reports that `com.google.gson` cannot be found, confirm that the folder you opened is `Lab`, the JAR is at `lib/gson-2.13.2.jar`, and the referenced-libraries setting is present. Then reload the Java project.

## Run the program

1. Open the `Lab` folder in VS Code.
2. Open `src/Exercise5.java`.
3. Run it as a Java application.

For consistent data-file placement, set the run configuration working directory to the `Lab` folder. In a VS Code `launch.json`, this is `"cwd": "${workspaceFolder}"`.

The program uses `Path.of("Users.json")`, so it creates or reads `Users.json` in its working directory. The root `.gitignore` excludes this runtime data file; do not commit account data.
