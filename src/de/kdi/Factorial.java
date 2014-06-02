package de.kdi;

import java.math.BigInteger;

import de.kdi.tailcalls.TailCallExpression;
import de.kdi.tailcalls.TailCallExpressionJava8;
import de.kdi.tailcalls.TailCallExpressionJava8.Done;

public class Factorial {

	private static final BigInteger ONE = BigInteger.valueOf(1L);

	/**
	 * Simple Factorial method, can't apply tail call optimization due to the keep track of the result of the factorial 
	 * 
	 * @param n
	 * @return
	 */
	public BigInteger factorial(BigInteger n){
		if(n.equals(ONE)){
			return ONE;
		} else {
			return factorial(n.subtract(ONE)).multiply(n);  
		}
	}
	
	/**
	 * Factorial method, applyable for tail call optimization 
	 * 
	 * @param n
	 * @param ret
	 * @return
	 */
	public BigInteger fac(BigInteger n, BigInteger ret){
		if(n.equals(ONE)){
			return ret;
		} else {
			return fac(n.subtract(ONE), ret.multiply(n));
		}
	}
	
	/**
	 * Result using java 5
	 * 
	 * @param n
	 * @param ret
	 * @return
	 */
	public de.kdi.tailcalls.TailCallExpression<BigInteger> facTailCallJava5(BigInteger n, BigInteger ret){
		if(n.equals(ONE)){
			return new de.kdi.tailcalls.TailCallExpression.Done<BigInteger>(ret);
		} else {
			return new de.kdi.tailcalls.TailCallExpression.TailCall<BigInteger>(){
				@Override
				public TailCallExpression<BigInteger> apply() {
					return facTailCallJava5(n.subtract(ONE), ret.multiply(n));
				}
				
			};
		}
		
	}
	
	/**
	 * Result using Java 8
	 * 
	 * @param n
	 * @param ret
	 * @return
	 */
	public TailCallExpressionJava8<BigInteger> facTailCall(BigInteger n, BigInteger ret){
		if(n.equals(ONE)){
			return new Done<BigInteger>(ret);
		} else {
			return () -> facTailCall(n.subtract(ONE), n.multiply(ret));
		}
	}
	
}


