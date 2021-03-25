package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.model.Trade;
import com.example.demo.model.Trades;

@Service
public class MyTradeService {

	private static MyTradeService instance;

	Trades trades;

	private MyTradeService() throws Exception {
		loadListOfStockListNames();
	}

	public static MyTradeService getInstance() throws Exception {
		if (instance == null)
			instance = new MyTradeService();

		return instance;
	}

	public List getNamesOfLists() {
		return trades.getTrades();
	}

	private void loadListOfStockListNames() throws Exception {
		trades = new Trades();
		List<Trade> list = new ArrayList<>();
		List<String> streamString = new ArrayList<>();
		Path path = Paths.get(getClass().getClassLoader().getResource("tradingFile.txt").toURI());

		try (Stream<String> stream = Files.lines(path)) {
			streamString = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Object obj : streamString) {
			String[] myarray = obj.toString().split(";");
			Trade trade = new Trade();
			trade.setTradeId(myarray[0]);
			trade.setVersion(Integer.parseInt(myarray[1]));
			trade.setCounterPartyId(myarray[2]);
			trade.setBookingId(myarray[3]);
			trade.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse(myarray[5]));
			trade.setMaturityDate(new SimpleDateFormat("dd/MM/yyyy").parse(myarray[4]));
			trade.setExpired(myarray[6].equals("Y") ? true : false);
			list.add(trade);
		}
		trades.setTrades(list);

	}

}
