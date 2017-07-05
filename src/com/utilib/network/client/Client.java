package com.utilib.network.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import com.utilib.util.LogLevel;
import com.utilib.util.Logger;

public class Client
{
	private SocketChannel		_sockChannel;
	private InetSocketAddress	_sockAddress;
	private boolean				_error;
	private Logger				_logger	= Logger.getInstance();
	
	public Client()
	{
	}
	
	public Client(final String hostname, final int port)
	{
		_logger.log(LogLevel.FATAL, "Connecting to the server...");
		
		// If fails to connect to the server socket, return error state.
		try
		{
			connect(hostname, port);
		}
		catch (IOException e)
		{
			_logger.log(LogLevel.FATAL, "Failed to connect!");
			_error = true;
			
			e.printStackTrace();
			return;
		}
		_logger.log(LogLevel.FATAL, "Connected succesfully!");
	}
	
	/**
	 * Connects to the server socket with the Ip and port provided.
	 * 
	 * @param hostname
	 * @param port
	 * @throws IOException
	 */
	public void connect(final String hostname, final int port) throws IOException
	{
		_sockAddress = new InetSocketAddress(hostname, port);
		_sockChannel = SocketChannel.open(_sockAddress);
	}
	
	/**
	 * Starts the client session.
	 */
	public void startSession()
	{
		// If an error was found, don't start the session.
		if (_error)
			return;
		
		// Input from keyboard
		Scanner scanner = new Scanner(System.in);
		
		// While the user doesn't write ~quit, continue
		while (true)
		{
			// Store the input of the user
			String msg = scanner.nextLine();
			byte[] message = msg.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(message);
			
			try
			{
				// Send the message to the server socket.
				_sockChannel.write(buffer);
				_logger.log(LogLevel.NORMAL, "Sending: " + msg);
				
				buffer.clear();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			// If the user message is ~quit, break the loop
			if (msg.equals("~quit"))
				break;
		}
		
		// Close the input stream and the socket connection.
		scanner.close();
		close();
	}
	
	/**
	 * Closes the socket of the client.
	 */
	public void close()
	{
		try
		{
			_sockChannel.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		// Initializes the client and connect him to the Ip and port specified.
		Client client = new Client("localhost", 5588);
		client.startSession();	// start the client session.
	}
}