When there's just a single consumer of a queue, jobs are run sequentially, not concurrently.

# Task

Your jobs have an `int key` property.
Jobs with the same key cannot run concurrently.
But jobs with different `key` are free to run at the same time.
How do you implement such a consumer?