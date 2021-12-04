Schedule a task to run with 3 seconds delay.
This means that the second execution of the task happens exactly 3 seconds after the **beginning** of the first execution.
It also means if the task takes more than 3 seconds to run, subsequent executions will overlap.

The initial delay should be 3 seconds as well.
