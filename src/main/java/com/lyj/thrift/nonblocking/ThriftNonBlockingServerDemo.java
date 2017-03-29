package com.lyj.thrift.nonblocking;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.lyj.thrift.HelloWorldService;
import com.lyj.thrift.HelloWorldService.Iface;
import com.lyj.thrift.HelloWorldService.Processor;
import com.lyj.thrift.impl.HelloWorldServiceImpl;

public class ThriftNonBlockingServerDemo {
	
	public  void start() throws TTransportException {
		System.out.println("---------------- TNonblockingServer start ----------------");
		
		Processor<Iface> processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
		
		TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(9090);
		
		TNonblockingServer.Args targs = new TNonblockingServer.Args(tNonblockingServerSocket);
		
		targs.processor(processor);
		targs.transportFactory(new TFastFramedTransport.Factory());
		targs.protocolFactory(new TCompactProtocol.Factory());
		
		TNonblockingServer server = new TNonblockingServer(targs);
	
		server.serve();
	}

	public static void main(String[] args) throws TTransportException {
		new ThriftNonBlockingServerDemo().start();
	}
}
