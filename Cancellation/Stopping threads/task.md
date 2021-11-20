Return an un-started `Thread` that runs `task`.
However, when a `task` throws `InterruptedException`, catch it and call `onCancel` callback.
