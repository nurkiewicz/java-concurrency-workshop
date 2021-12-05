When there's just a single consumer of a queue, jobs are run sequentially, not concurrently.
Thus, if two jobs are related to the same resource (e.g. client), they should be run in sequence.
However, if two jobs are unrelated (e.g. two different clients), they can be run concurrently.

# Task

Your jobs have an `int key` property.
Jobs with the same key cannot run concurrently.
But jobs with different `key` are free to run at the same time.
How do you implement such a consumer?

<div class="hint">
Imagine you have a separate thread for each unique key.
However, this doesn't scale.
</div>
