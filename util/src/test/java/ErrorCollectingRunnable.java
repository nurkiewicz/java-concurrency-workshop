class ErrorCollecting {

	public static Runnable collectErrors(Events<Exception> errors, Runnable runnable) {
		return () -> {
			try {
				runnable.run();
			} catch (Exception e) {
				errors.log(e);
				throw e;
			}
		};
	}
}
