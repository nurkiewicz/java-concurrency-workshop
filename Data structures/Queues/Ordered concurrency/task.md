When there's just a single consumer of a queue, jobs are run sequentially, not concurrently.
Thus, if two jobs are related to the same resource (e.g. client), they should be run in sequence.
However, if two jobs are unrelated (e.g. two different clients), they can be run concurrently.
This can improve throughput of the system without breaking the consistency of the data.

# Task

Your `CustomerJob` jobs have an `int key` property.
Jobs with the same key cannot run concurrently.
But jobs with different `key` are free to run at the same time.
For example, `key=1` and `key=2` _should_ run concurrently.
How do you implement such a consumer?

<div class="hint">
Imagine you have a separate thread for each unique key.
However, this doesn't scale.
</div>
