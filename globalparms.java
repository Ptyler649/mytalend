package routines;

public class getglobalparms {
	
public static String getvalue(String inputenv, String inputval, String actenv){

String a_env = inputenv;
String a_val = inputval;
// strip leading and trailing brackets
String a_env_ready = a_env.substring(1,a_env.length()-1);
String a_val_ready = a_val.substring(1,a_val.length()-1);
// replace qoutes
String a_env_ready_q = a_env_ready.replace("\"", "");
String a_val_ready_q = a_val_ready.replace("\"", "");
// split
String[] a_env_list = a_env_ready_q.split(",");
String[] a_val_list = a_val_ready_q.split(",");
// loop through until match
String out = "";
for(int x = 0; x < a_env_list.length; x++){
	if(actenv.equalsIgnoreCase(a_env_list[x])){
		out = a_val_list[x];
		break;
	}
};

return out;
}

}



