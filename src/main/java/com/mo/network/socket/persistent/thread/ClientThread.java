package com.mo.network.socket.persistent.thread;


import com.mo.network.socket.persistent.manager.ClientManager;
import com.mo.network.socket.persistent.utils.SocketUtil;

import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread{

	private Socket clientSocket;
	private boolean isConnect;

	public ClientThread(Socket socket) {
		clientSocket = socket;
		isConnect = true;
	}
	
	@Override
	public void run() {
		while (isConnect) {
			byte[] data = null;
			try {
				data = SocketUtil.readData(clientSocket);
				if (data != null) {
					process(data);
				}
			} catch (IOException e) {
				// 连接意外关闭
				e.printStackTrace();
				isConnect = false;
				System.out.println("Out!");
				ClientManager.removeClient(clientSocket);
			}
		}
	}

	private void process(byte[] data) {
		String dataString = new String(data);
		System.out.println(dataString);
		try {
			SocketUtil.sendData(clientSocket, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Out!");
		}
	}
	
}
