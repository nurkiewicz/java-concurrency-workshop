Instrumenting a `Callable` to figure out how much time it spent in the queue is quite come.
Ideally, the task shouldn't spend much time there and be executed immediately.
If you consistently see tasks queueing up, consider tuning your system.

# Task

Implement a `Callable` that wraps the original `Callable` and returns the time spent in the queue.
Most of the work is already done in `CallableWithWaitTime` class.
We use [Decorator pattern](https://en.wikipedia.org/wiki/Decorator_pattern) here.