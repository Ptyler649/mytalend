//Load Server
String actenv = (String)globalMap.get("tSystem_1_OUTPUT");
//MQ_QUEUE_MGR
String a_env = input_row.env;
String a_val = input_row.value;
// GetValue
String mq_queue_mgr = getglobalparms.getvalue(a_env,a_val,actenv);
System.out.println("mgr: "+mq_queue_mgr);

//MQ_CHANNEL
String b_env = input_row.env1;
String b_val = input_row.value1;
// GetValue
String mq_channel = getglobalparms.getvalue(b_env,b_val,actenv);
System.out.println("channel: "+mq_channel);

