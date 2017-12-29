package com.mili.calculatorg;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
/**
* Calculator logic
*/
@Service
public class Calculator {
	@Cacheable("sum")
	int sum(int a, int b) {
		return a + b;
}
}