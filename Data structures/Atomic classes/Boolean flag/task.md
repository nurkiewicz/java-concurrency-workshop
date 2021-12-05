# `AtomicBoolean`

The biggest benefit of `AtomicLong` is changing the value of the variable atomically, based on condition.
I.e. you can flip it from `true` to `false` or vice versa without loosing any updates.
Technically, you can implement critical section using `AtomicBoolean` as well.
