# v2v
Distributed Querying API for Vehicular Clouds

Introduction
**************
A middle tier, which provides distributed-querying API, has been developed for the vehicular clouds. The middletier uses Jgroups(www.jgroups.org)  for establishing a multivehicle cluster using ipv4 multicasting.  Once a node joins the cluster it can query other nodes for information using shared classes.

Assumptions
************

1. Assumed that applications along with the middle-tier jars will run in the same JVM.
2.  An IP Address is allocated to the vehicle in the cloud.

Using the middle tier
********************** 
The code for the middle tier has been checked into the github at https://github.com/harikumarks/v2v.
The code can be checked and build using apache maven.
Instructions to do so are as follows
1.	Download and install latest version of maven from Ensure that latest version of maven is installed from maven.apache.org. Instructions on installing maven can be found at maven.apache.org. We used maven version 3.x
2.	 Ensure that “mvn” command line is set in your operating system PATH environment variable and points to <mavenlocation>/bin/mvn
3.	Ensure that latest version of JDK is installed and is available in your operating system PATH environment variable. We used JDK version 8.x
4.	Change to directory where project was checked out and build the project using the command “mvn clean install “.
5.	 The target artifact file can be found at location <projectlocation>/ target/v2v-1.0-SNAPSHOT-jar-with-dependencies.jar

Testing the sample application
********************************

1.	For demonstrating the use the above jar file can be copied to 2 or more nodes. 
2.	Startup the sample app in 2 nodes in processing mode.
<java-home>/bin/java -cp v2v-1.0-SNAPSHOT-jar-with-dependencies.jar -D cmu.practicum.app.SampleApp process
3.	Startup the sample in one node in query mode
<java-home>/bin/java -cp v2v-1.0-SNAPSHOT-jar-with-dependencies.jar -D cmu.practicum.app.SampleApp query

You will start seeing messages such as below in the node where you are running in query mode
-----------------------------------------------------------------
GMS: address=testnode-1-14361, cluster=V2VCloud, physical address=192.168.118.108:10006
-------------------------------------------------------------------
Vehiclename = testnode-1 ,  distance=3
Vehiclename = testnode-2 ,  distance=4




API Usage
************
Joining a cluster
Vehicles need to join the Jgroups cluster before they can query information.  
The code for doing that is as below:
JgroupsRpc jgroupsRpc = JgroupsRpc.getInstance()
jgroupsRpc.start();

Querying the Cluster
*********************
Vehicles can query the cluster for information from the cluster. In-order to query a class representing the application, which can hold information for queried data needs to be deployed across all the nodes in the cluster.  This is nothing but a simple Java  class with some getters for the attributes and an execute method which will populate the attributes at the processing node. The class also needs to extend from the cmu.practicum.CommonAPI class. For example please refer the class cmu.practicum.app.VehicleDistance class.

Example on how to query can be seen in the cmu.practicum.app.SampleApp class.

The following API is used to query the information
public <T> org.jgroups.util.RspList<T> dispatch(org.jgroups.blocks.ResponseMode responseMode, int timeout T val, Class<T> valType)

Parameters:
responseMode - ResponseMode.GET_ALL/ GET_FIRST/GET_MAJORITY

timeout - time to wait

val - the instance of the object
valType - the classname of the object
Returns:
Returns a RspList with objects from different vehicles. See example from SampleApp class for how to parse RspList

For example:
	RspList<VehicleDistance> rsp_list=jrpc.dispatch(ResponseMode.GET_ALL, 5000, new VehicleDistance(), VehicleDistance.class);



References:
************
www.jgroups.org
			






