package de.kdi;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class TestFactorial {

	@Test
	public void testTailCallFac() throws Exception {
		assertEquals(BigInteger.valueOf(120), new Factorial().fac(BigInteger.valueOf(5), BigInteger.valueOf(1)));
	}
	
	@Test
	public void testTailCallNotOveflows() {
		long currentTimeMillis = System.currentTimeMillis();
		double j = 100;
		for (int i = 0; i < j; i++)
			new Factorial().facTailCall(BigInteger.valueOf(10000), BigInteger.valueOf(1)).runRecursiveFunction();
		System.out.print("Java8: ");
		long x = System.currentTimeMillis() - currentTimeMillis;
		System.out.println(x / j);
	}
	
	@Test
	public void testTailCallJava5() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		double j = 100;
		for (int i = 0; i < j; i++)
			new Factorial().facTailCallJava5(BigInteger.valueOf(10000), BigInteger.valueOf(1)).runRecursiveFunction();
		System.out.print("Java5: ");
		long x = System.currentTimeMillis() - currentTimeMillis;
		System.out.println(x / j);
	}
	
	@Test
	public void testDoneIsReturnedInstandly() throws Exception {
		new Factorial().facTailCallJava5(BigInteger.valueOf(1), BigInteger.valueOf(1)).runRecursiveFunction();
	}
	
}
