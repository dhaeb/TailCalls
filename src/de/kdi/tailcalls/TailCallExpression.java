package de.kdi.tailcalls;

public abstract class TailCallExpression<T> {

	public abstract TailCallExpression<T> apply();

	public abstract T getResult();

	public T runRecursiveFunction() {
		boolean isFinished = false;
		TailCallExpression<T> expression = this;
		do {
			if (Done.class.isInstance(expression)) {
				isFinished = true;
			} else {
				expression = expression.apply();
			}
		} while (!isFinished);
		return expression.getResult();
	}

	public abstract static class TailCall<T> extends TailCallExpression<T> {

		public abstract TailCallExpression<T> apply();

		@Override
		public T getResult() {
			throw new UnsupportedOperationException("The result is not computed yet! You need to call runRecursiveFunction() first!");
		}

	}

	public static class Done<T> extends TailCallExpression<T> {

		private T result;

		public Done(T result) {
			this.result = result;
		}

		@Override
		public TailCallExpression<T> apply() {
			throw new UnsupportedOperationException("Done instance can't be applied to anything, the result is retrievable using the result method.");
		}

		@Override
		public T getResult() {
			return result;
		}

	}

}
