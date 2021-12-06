import java.util.concurrent.ExecutorService;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;

public class Task {

	ExecutorService addMetrics(MeterRegistry meterRegistry, ExecutorService executor) {
		//TODO Implement this method
		return ExecutorServiceMetrics.monitor(meterRegistry, executor, "workshop");
	}

}