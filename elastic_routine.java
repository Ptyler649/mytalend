package routines;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class elastic {
	
	public static void main(String[] args){
		elastic.read("localhost:9200", "talendtest", "logs", "JOB", "error_1", false);
	}
    
    public static String create(String esserver, String esindex, int esshards, int esreplicas, boolean esindexflag){
    	elastic obj = new elastic();
    	
    	//curl -XPUT 'http://localhost:9200/mynewindex/' -d '{"settings":{"index":{"number_of_shards":1, "number_of_replicas":1}}}'
    	
    	String command = "curl -XPUT http://"+esserver+"/"+esindex+"/"+" -d {\"settings\":{\"index\":{\"number_of_shards\":"+esshards+",\"number_of_replicas\":"+esreplicas+"}}}";
    	String compare = "curl -XPUT http://localhost:9200/mynewindex/ -d {\"settings\":{\"index\":{\"number_of_shards\":1,\"number_of_replicas\":1}}}";
    	
    	String output = "";
    	
    	if(esindexflag){	
    		output = obj.executeCommand(command);
    	}
    	else{
    		System.out.println(command);
    		System.out.println(compare);
    	};
    	
    	return "logged: "+output;
    }
    
    public static String read(String esserver, String esindex, String estype, String escolumn, String essearch, boolean esindexflag){
     	elastic obj = new elastic();
        
    	// curl -XGET 'http://localhost:9200/talendtest/logs/_search?pretty' -d '{"query":{"constant_score":{"filter":{"term":{"JOB" : "error_1"}}}}}'
    	
     	String command = "curl -XGET http://"+esserver+"/"+esindex+"/"+estype+"/"+"_search?pretty "+"-d {\"query\":{\"constant_score\":{\"filter\":{\"term\":{\""+escolumn+"\""+":\""+essearch+"\"}}}}}";
    	String compare = "curl -XGET http://localhost:9200/talendtest/logs/_search?pretty -d {\"query\":{\"constant_score\":{\"filter\":{\"term\":{\"JOB\":\"error_1\"}}}}}";
    	
    	String output = "";
    	
    	if(esindexflag){	
    		output = obj.executeCommand(command);
    		System.out.println(output);
    	}
    	else{
    		System.out.println(command);
    		System.out.println(compare);
    	};
    	
    	return "logged: "+output;
    	
    }
    
    public static String update(String esserver, String esindex, String estype, String esdocument, boolean esindexflag){
    	elastic obj = new elastic();
    	
    	Date date = new Date();
    	//SimpleDateFormat f = new SimpleDateFormat("EEEE");
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    	String esdatetime = f.format(date);
    	
    	String command = "curl -XPOST http://"+esserver+"/"+esindex+"/"+estype+" -d {\"JOB\":\""+esdocument+"\",\"DATETIME\":\""+esdatetime+"\"}";
    	String compare = "curl -XPOST http://localhost:9200/talendtest/joblogs -d {\"JOB\":\"dw_email_talend\"}";
    	String output = "";
    	
    	if(esindexflag){	
    		output = obj.executeCommand(command);
    	}
    	else{
    		System.out.println(command);
    		System.out.println(compare);
    	};
    	
    	return "logged: "+output;
    	
    }
    
    public static String delete(String esserver, String esindex, boolean esindexflag){
    	
    	elastic obj = new elastic();
    	
    	// curl -XDELETE 'http://localhost:9200/mynewindex?v'
    	
    	String command = "curl -XDELETE http://"+esserver+"/"+esindex+"?v";
    	String compare = "curl -XDELETE http://localhost:9200/mynewindex?v";
    	
    	String output = "";
    	
    	if(esindexflag){	
    		output = obj.executeCommand(command);
    	}
    	else{
    		System.out.println(command);
    		System.out.println(compare);
    	};
    	
    	return "logged: "+output;
    }
    
    private String executeCommand(String command){
    	
    	StringBuffer output = new StringBuffer();
    	
    	Process p;
    	
    	try{
    		p = Runtime.getRuntime().exec(command);
    		p.waitFor();
    		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		String line = "";
    		while((line = reader.readLine())!=null){
    			output.append(line + "\n");
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return output.toString();
    }
    
    
}


