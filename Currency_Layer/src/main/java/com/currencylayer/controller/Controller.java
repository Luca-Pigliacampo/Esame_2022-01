package com.currencylayer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currencylayer.Conversion;

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

}
