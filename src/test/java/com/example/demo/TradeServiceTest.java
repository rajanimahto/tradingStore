package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.demo.service.MyTradeService;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

	@InjectMocks
	MyTradeService tradeService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {
		tradeService.getInstance();
		tradeService.getNamesOfLists();
	}
	
	
}
