Return an un-started `Thread` that runs `task`.
However, when `task.call()` throws `InterruptedException`, catch it and call `onCancel` callback.
