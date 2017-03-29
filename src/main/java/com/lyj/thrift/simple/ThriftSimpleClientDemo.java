package com.lyj.thrift.simple;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.lyj.thrift.HelloWorldService;

/**
 * D:\Users\KY\workspace\thrift-examples\src\main\resources>
 * thrift-0.10.0.exe -r -gen java
 * D:\Users\KY\workspace\thrift-examples\src\main\java\com\lyj\thrift\HelloWorld.thrift
 * 
 * @author Jack
 *
 */
public class ThriftSimpleClientDemo {

	public static void main(String[] args) throws TException {
		TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 9090, 5000));
		TProtocol protocol = new TBinaryProtocol(transport);

		HelloWorldService.Client client = new HelloWorldService.Client(protocol);
		// Opens the transport for reading/writing.
		transport.open();

		for (int i = 0; i < 10000; i++) {
			String sayHello = client.sayHello("Haha--" + System.currentTimeMillis());
			System.out.println(sayHello);
		}

		// Closes the transport.
//		transport.close();
	}

}
