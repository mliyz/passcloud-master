package com.passcloud.security.feign;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * The class Custom hystrix concurrency strategy.
 *
 * @author liyuzhang
 */
@Component
public class CustomHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
	/**
	 * Instantiates a new Custom hystrix concurrency strategy.
	 */
//	public CustomHystrixConcurrencyStrategy() {
//		HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
//	}
	
	/**
	 * Wrap callable callable.
	 *
	 * @param <T>      the type parameter
	 * @param callable the callable
	 *
	 * @return the callable
	 */
	@Override
	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		return new HystrixContextWrapper<T>(callable);
	}
	
	/**
	 * The class Hystrix context wrapper.
	 *
	 * @param <V> the type parameter
	 *
	 * @author paascloud.net @gmail.com
	 */
	public static class HystrixContextWrapper<V> implements Callable<V> {
		
		private HystrixRequestContext hystrixRequestContext;
		private Callable<V> delegate;
		
		/**
		 * Instantiates a new Hystrix context wrapper.
		 *
		 * @param delegate the delegate
		 */
		HystrixContextWrapper(Callable<V> delegate) {
			this.hystrixRequestContext = HystrixRequestContext.getContextForCurrentThread();
			this.delegate = delegate;
		}
		
		/**
		 * Call v.
		 *
		 * @return the v
		 *
		 * @throws Exception the exception
		 */
		@Override
		public V call() throws Exception {
			HystrixRequestContext existingState = HystrixRequestContext.getContextForCurrentThread();
			try {
				HystrixRequestContext.setContextOnCurrentThread(this.hystrixRequestContext);
				return this.delegate.call();
			} finally {
				HystrixRequestContext.setContextOnCurrentThread(existingState);
			}
		}
	}
}
