package monitor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IniciaMonitor {
	
	public static void main(String args[]) {
		
		Monitor monitor = Monitor.getInstance();
		monitor.start();
		
	}
}
