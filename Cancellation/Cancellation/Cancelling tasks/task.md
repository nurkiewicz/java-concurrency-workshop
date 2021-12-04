Use `Thread.currentThread().isInterrupted()` flag to check if the task was interrupted by someone else.
If that's the case, stop the task gracefully by calling `onInterrupt` callback.
The task should stop immediately.