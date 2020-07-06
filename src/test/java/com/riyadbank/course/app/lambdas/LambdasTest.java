package com.riyadbank.course.app.lambdas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LambdasTest {
	
	private List<String> objects;
	
	@BeforeEach
	public void setUp() {
		objects = Stream.of("Bottle","Ball","Pencil",
				"Phone","Ball","Wallet",
				"Card","Notebook","Headphones",
				"Phone","Pencil","Ball").collect(Collectors.toList());
	}
	
	/**
	 * Order the objects list by alphabet
	 */
	@Test
	public void orderListByAlphabet() {
		assertEquals(objects.get(0), "Bottle","First expected String doesn't match");
		assertEquals(objects.get(objects.size()-1), "Ball","Last expected String doesn't match");
		
		//objects.stream().sorted(String::compareToIgnoreCase);
		objects.sort(String::compareToIgnoreCase);
		//objects.forEach(System.out::println);
	
		
		assertEquals(objects.get(0), "Ball","First expected String doesn't match");
		assertEquals(objects.get(objects.size()-1), "Wallet","Last expected String doesn't match");
	
	}
	
	/**
	 * Order the list By largest String to the smallest
	 */
	@Test
	public void orderListByLargestStringLength() {
		objects.sort((s1,s2) -> s1.length() >= s2.length() ? -1 : 1 );
		//objects.forEach(System.out::println);		
	}
	
	/**
	 * Filter from objects list, elements which contains "ll" and return them without repeated elements.
	 */
	@Test
	public void filterTest() {
		List<String> newList = objects.stream().filter(s -> s.contains("ll")).distinct()
				.collect(Collectors.toList());
		
		//newList.forEach(System.out::println);
	}
	
	/**
	 * Convert this map from String to a List of hashCodes
	 */
	@Test
	public void filterMap() {
		List<Integer> hashCodeList = objects.stream()
		.map(s -> s.hashCode())
		.collect(Collectors.toList());
		
		hashCodeList.forEach(System.out::println);
	}

}
