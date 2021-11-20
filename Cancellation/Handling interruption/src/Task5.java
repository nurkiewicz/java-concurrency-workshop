import java.util.concurrent.Callable;

public class Task5 {
  Thread create(Callable<Void> task, Runnable onCancel) {
//	  throw new UnsupportedOperationException("TODO");
	  return new Thread(() -> {
		  try {
			  task.call();
		  } catch (InterruptedException e) {
			  onCancel.run();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  });
  }
}