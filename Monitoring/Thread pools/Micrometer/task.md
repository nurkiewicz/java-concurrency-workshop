Instrument existing `ExecutorService` using `io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics`.


# Testing Graphite with Docker

As a bonus you can try to run this container and configure Graphite integration.
Once done, you should see graph of `ExecutorSerivce` metrics.

```bash
docker run -d\                                                                                                                                       20:15:42
 --name graphite\
 -p 80:80\
 -p 81:81\
 -p 2003-2004:2003-2004\
 -p 2023-2024:2023-2024\
 -p 8125:8125/udp\
 -p 8126:8126\
 hopsoft/graphite-statsd
 ```