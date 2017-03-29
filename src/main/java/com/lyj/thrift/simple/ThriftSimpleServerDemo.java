package com.lyj.thrift.simple;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;

import com.lyj.thrift.HelloWorldService;
import com.lyj.thrift.HelloWorldService.Iface;
import com.lyj.thrift.HelloWorldService.Processor;
import com.lyj.thrift.impl.HelloWorldServiceImpl;

public class ThriftSimpleServerDemo {
	
	public void start() throws Exception {
		System.out.println("----------- Starting thrift siomple server -----------");
		
		Processor<Iface> processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
		
		//Creates just a port listening server socket
		TServerSocket tServerSocket = new TServerSocket(9090);
		
		//TFramedTransport is a buffered TTransport that ensures a fully read message every time by preceding messages with a 4-byte frame size.
		TFramedTransport.Factory transportFactory = new TFramedTransport.Factory();
		
		//Binary protocol implementation for thrift.
		Factory factory = new TBinaryProtocol.Factory();
		//TServer is a Generic interface for a Thrift server
		TServer.Args args = new TServer.Args(tServerSocket);
		args.protocolFactory(factory);
		args.transportFactory(transportFactory);
		args.processor(processor);
		
        TServer server = new TSimpleServer(args);
        //The run method fires up the server and gets things going.
        server.serve();
		System.out.println("----------- thrift siomple server is stop -----------");
	}

	public static void main(String[] args) throws Exception {
		new ThriftSimpleServerDemo().start();
	}
}
