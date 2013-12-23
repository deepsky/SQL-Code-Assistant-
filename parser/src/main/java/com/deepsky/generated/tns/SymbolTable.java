package com.deepsky.generated.tns;

import java.util.Map;
import java.util.HashMap;

public class SymbolTable {

	public static Integer get(String text){
		return keywords.get(text.toLowerCase());
	}

	static public Map<String, Integer> keywords;
	static {
		keywords = new HashMap<String, Integer>();
		keywords.put("port", 46);
		keywords.put("hs", 58);
		keywords.put("pooled", 56);
		keywords.put("args", 43);
		keywords.put("no", 26);
		keywords.put("failover", 32);
		keywords.put("enable", 22);
		keywords.put("description_list", 15);
		keywords.put("address_list", 37);
		keywords.put("security", 61);
		keywords.put("instance_name", 51);
		keywords.put("service_name", 50);
		keywords.put("type_of_service", 19);
		keywords.put("connect_data", 18);
		keywords.put("dedicated", 54);
		keywords.put("description", 17);
		keywords.put("argv0", 42);
		keywords.put("on", 27);
		keywords.put("false", 31);
		keywords.put("ok", 59);
		keywords.put("key", 48);
		keywords.put("send_buf_size", 35);
		keywords.put("shared", 55);
		keywords.put("load_balance", 29);
		keywords.put("sid", 57);
		keywords.put("sdu", 36);
		keywords.put("protocol", 39);
		keywords.put("host", 45);
		keywords.put("community", 40);
		keywords.put("true", 30);
		keywords.put("ssl_server_cert_dn", 62);
		keywords.put("global_name", 52);
		keywords.put("yes", 25);
		keywords.put("source_route", 24);
		keywords.put("broken", 23);
		keywords.put("presentation", 60);
		keywords.put("address", 38);
		keywords.put("program", 41);
		keywords.put("service", 47);
		keywords.put("off", 28);
		keywords.put("server", 53);
		keywords.put("recv_buf_size", 33);
	}
}
