package cmu.practicum;

import java.util.List;

import org.jgroups.JChannel;
import org.jgroups.blocks.MethodCall;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.blocks.RpcDispatcher;
import org.jgroups.util.RspList;
import org.jgroups.util.Util;

import cmu.practicum.app.VehicleDistance;

public class JgroupsRpc {
    JChannel           channel;
   public static  RpcDispatcher      disp;
   String             props; // set by application

    public static int print(int number) throws Exception {
        return number * 2;
    }

    public void start() throws Exception {
        channel=new JChannel(props);
        disp=new RpcDispatcher(channel, this);
        channel.connect("RpcDispatcherTestGroup");

     
    }
    
    public  <T extends CommonAPI<?>> T callRemote(T appObj) throws Exception {
    	appObj.execute();
    	return appObj;
    }
    
    
    public  <T> RspList<T>  dispatch(ResponseMode responseMode,int timeout,T val, Class<T> valType){
		RspList<T> rsp_list;
	    RequestOptions opts=new RequestOptions(ResponseMode.GET_ALL, timeout);
		try {
			rsp_list=JgroupsRpc.disp.callRemoteMethods(null,
			        "callRemote",
			        new Object[]{val},
			        new Class[]{valType},
			        opts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rsp_list=null;
		}
		return rsp_list;
    }
    
    public static void main(String[] args) throws Exception {
        new JgroupsRpc().start();
    }
}