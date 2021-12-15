package com.example.currencylayer.demo.list;



import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencylayer.demo.CoversionNotFromUSD;
import com.example.currencylayer.demo.list.parser.Conversion;
import com.example.currencylayer.demo.list.parser.JSONParser;

import okhttp3.MediaType;

@RestController
public class Controller {
	@GetMapping("/value")
	public  ResponseEntity<Valuta> example(@RequestParam (name="code",defaultValue="EUR")String param1) {
		JSONParser a=new JSONParser();
		return ResponseEntity.ok(a.getValuefromApi(param1));	
		
	}
	
	@GetMapping(value="/conversion", params= {"src","tgt","amount"})
	public ResponseEntity<CoversionNotFromUSD[]> getvalue(@RequestParam String[] src, @RequestParam String[] tgt,@RequestParam double amount) {
		CoversionNotFromUSD[] conv=new CoversionNotFromUSD[src.length*tgt.length];
		int k=0;
		for(int i=0;i<src.length;i++) {
			for(int j=0;j<tgt.length;j++) {
				 Conversion a=new Conversion(src[i],tgt[j]);
				 conv[k]=new CoversionNotFromUSD();
				 conv[k].setCurrency(a);
				 conv[k].conversion(a, amount);
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
	
	@GetMapping("/history")
	public  Map<String, Object> story() {
		Map<String, Object> json=new HashMap();
		Historical a=new Historical();
		json.put("historical", a);
		return json;
		
		
	}
	


}
