# Thread

A running thread needs CPU core.
When a thread is blocked or waiting for I/O, it releases a CPU.

![img.png](tweet.png)

[twitter.com/adamgordonbell/status/1467229754029969408](https://twitter.com/adamgordonbell/status/1467229754029969408)

# Process

A group of threads that share the same memory.
For Linux kernal, the distinction between a thread and a process is minimal.

# Critical section

A place in code that can be run only by a single thread.

# Lock

A resource/object that can be obtained by a single thread only.

# Lost update

A bad phenomenon where a thread updates a shared state and another thread reads it before the update is complete.
