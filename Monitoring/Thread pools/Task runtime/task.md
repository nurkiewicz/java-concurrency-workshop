Measure how much time a task runs is very valuable.
If a certain task in a thread pool takes longer than usual, it can lead to starvation of other tasks.

# Your task

Implement a `Callable` that wraps the original `Callable` and returns the time it was executed.
Most of the work is already done in `CallableWithTaskRuntime` class.
We use [Decorator pattern](https://en.wikipedia.org/wiki/Decorator_pattern) here.