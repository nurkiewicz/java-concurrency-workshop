# Monitoring queue size

`ExecutorService` has two parts:

* a pool of worker threads
* a queue of tasks in front of the pool

In a healthy system, tasks should not be queued extensively.