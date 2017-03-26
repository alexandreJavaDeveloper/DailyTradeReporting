# DailyTradeReporting

## Some statements
- No database or UI was created.
- I assumed the code will only ever be executed in a single threaded environment.
- Used only JUnit library as external jar dependencies, just to run all tests.
- All data is saved in memory. There is no database.
- Output format in plain text, printed out to the console.
- There is no main method, only can be executed by tests.

## How to execute (running tests)
- git clone  https://github.com/alexandreJavaDeveloper/DailyTradeReporting.git
- cd DailyTradeReporting/
- mvn clean package
- At this stage, will be logged out in the console the Instruction Report.

## Test Coverage
- 100%

## TODO
- The method showReport() of the class InstructionReport needs to check out the kind of financial type, and today is done by the 'if' command, where there is two types at the moment (SELL and BUY).
- One choice is to use the Design Pattern Strategy https://sourcemaking.com/design_patterns/strategy, because if in the future needs more financial types, will be created more 'if' command, resulting in a bad maintenance.
