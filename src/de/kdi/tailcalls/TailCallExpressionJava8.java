package de.kdi.tailcalls;

import java.util.stream.Stream;

@FunctionalInterface
public interface TailCallExpressionJava8<T> {
	TailCallExpressionJava8<T> apply();

	default T result() {
		throw new UnsupportedOperationException("There is no result computed yet!");
	}

	default boolean isCompleted() {
		return false;
	}

	default T runRecursiveFunction() {
		return Stream.iterate(this, TailCallExpressionJava8::apply)
					 .filter(TailCallExpressionJava8::isCompleted)
					 .findFirst()
					 .get()
					 .result();
	}
		
	public static class Done<T> implements TailCallExpressionJava8<T> {
		
		private T result;
		
		@Override
		public TailCallExpressionJava8<T> apply() {
			throw new UnsupportedOperationException("Can't apply a Done object! The result is already available!");
		}

		public Done(T result ) {
			this.result = result;
		}
		
		@Override
		public boolean isCompleted() {
			return true;
		}
		
		@Override
		public T result() {
			return result;
		}
		
		@Override
		public T runRecursiveFunction() {
			return result();
		}
	}

}
