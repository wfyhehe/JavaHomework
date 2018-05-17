package com.wfy.work6.nttt.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
	private PrintWriter out;
	private BufferedReader in;
	private ServerSocket server;
	private Socket client;
	private boolean isHost;
	
	/**
	 * Start a server
	 * @param port The port to start the server on
	 */
	public Connection(int port) throws IOException {
		System.out.println("Starting server...");
		server = new ServerSocket(port);
		System.out.println("Waiting for client...");
		client = server.accept();
		System.out.println("Client is connecting...");
		out = new PrintWriter(client.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println("Client is connected");
		isHost = true;
	}
	
	/**
	 * Start a client
	 * @param ip The IP address of the server
	 * @param port The port of the server
	 */
	public Connection(String ip, int port) throws IOException {
		System.out.println("Finding server...");
		client = new Socket(ip, port);
		System.out.println("Connecting to server...");
		out = new PrintWriter(client.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println("Connected to server");
		isHost = false;
	}
	
	/**
	 * Disconnect from server if client or close server if host
	 */
	public void close() throws IOException {
		out.close();
		in.close();
		client.close();
		if(isHost())
			server.close();
	}
	
	/**
	 * @param msg Integer to send
	 */
	public void sendInt(int msg) {
		out.println("INT "+msg);
	}
	
	/**
	 * @param msg Boolean to send
	 */
	public void sendBool(boolean msg) {
		out.println("BOO "+msg);
	}
	
	/**
	 * @param msg String to send
	 */
	public void sendString(String msg) {
		out.println("STR "+msg);
	}
	
	/**
	 * @return Int sent by the other player
	 */
	public int receiveInt() throws IOException {
		String msg = in.readLine();
		if(msg.substring(0, 3).equals("INT"))
			return Integer.parseInt(msg.substring(4));
		else
			throw new IOException("Received wrong type!");
	}
	
	/**
	 * @return Boolean sent by the other player
	 */
	public boolean receiveBool() throws IOException {
		String msg = in.readLine();
		if(msg.substring(0, 3).equals("BOO"))
			return Boolean.parseBoolean(msg.substring(4));
		else
			throw new IOException("Received wrong type!");
	}
	
	/**
	 * @return String sent by the other player
	 */
	public String receiveString() throws IOException {
		String msg = in.readLine();
		if(msg.substring(0, 3).equals("STR"))
			return msg.substring(4);
		else
			throw new IOException("Received wrong type!");
	}
	
	/**
	 * @return True if we are the host
	 */
	public boolean isHost() {
		return isHost;
	}
}
