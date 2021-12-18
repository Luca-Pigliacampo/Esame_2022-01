package com.currencylayer.controller;

import java.time.LocalDate;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currencylayer.Conversion;
import com.currencylayer.Pair;
import com.currencylayer.parse.JSONParser;

@RestController
public class Controller {

	@GetMapping(value="/conversion", params= {"src","tgt","amount"})
	public ResponseEntity<Conversion[]> getvalue(@RequestParam String[] src, @RequestParam String[] tgt,@RequestParam double amount) {
		Conversion[] conv=new Conversion[src.length*tgt.length];
		int k=0;
		
		for(int i=0;i<src.length;i++) {
			for(int j=0;j<tgt.length;j++) {

				
				conv[k]=new Conversion();
				conv[k].conversion(src[i], tgt[j], amount);
				k=k+1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return ResponseEntity.ok(conv);	

	}
	@GetMapping (value="/liveExchange", params={"src"})
	public ResponseEntity<Pair> getLivePair (@RequestParam String src){
		JSONParser pairObj = new JSONParser();
		Pair pair=pairObj.getPairfromApi(src, LocalDate.now());
		
		return ResponseEntity.ok(pair);
		
	}

}
