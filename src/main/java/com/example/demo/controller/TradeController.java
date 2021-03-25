package com.example.demo.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Trade;
import com.example.demo.service.MyTradeService;

@RestController
@RequestMapping("/trade")
public class TradeController {

	@Autowired
	MyTradeService tradeService;

	@RequestMapping("/allTrades")
	public List getAllTrades() throws Exception {
		return tradeService.getNamesOfLists();
	}

	@RequestMapping("/trades")
	public List getOnlyValidTrades() throws Exception {
		List<Trade> tradelist = tradeService.getNamesOfLists();
		Date sysDate = new SimpleDateFormat("dd/MM/yyyy").parse("25/03/2021");
		for (Trade trade : tradelist) {
			if (trade.getMaturityDate().compareTo(sysDate) < 0) {
				trade.setExpired(true);
			}
		}
		tradelist = tradelist.stream().distinct().filter(t -> t.getMaturityDate().compareTo(sysDate) >= 0)
				.sorted((Trade t1, Trade t2) -> t1.equals(t2) == true ? -1 : -1).filter(t -> !t.isExpired())
				.collect(Collectors.toList());
		/*
		 * Set<Trade> set= new HashSet<>(); set.addAll(tradelist); tradelist= new
		 * ArrayList<Trade>(); tradelist.addAll(set);
		 */
		return tradelist;
	}
}
