package com.lyj.thrift.nonblocking;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

import com.lyj.thrift.HelloWorldService;

public class ThriftNonBlockingClassDemo {
	
	public static void main(String[] args) throws TException {
		
		TFramedTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 9090, 5000));
		
		TCompactProtocol protocol = new TCompactProtocol(transport);
		
		HelloWorldService.Client client = new HelloWorldService.Client(protocol);
		
		transport.open();
		
		for (int i = 0; i < 10000; i++) {
			String sayHello = client.sayHello("Haha--" + System.currentTimeMillis());
			System.out.println(sayHello);
		}
		
		transport.close();
	}

}
