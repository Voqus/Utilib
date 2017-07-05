package com.utilib.network.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.utilib.util.LogLevel;
import com.utilib.util.Logger;

public class Server
{
	private ServerSocketChannel	_socketChannel;
	private Selector			_selector;
	private InetSocketAddress	_sockAddress;
	private ServiceHandler		_socketThread;
	private Logger				_logger	= Logger.getInstance();
	private int					_port;
	private boolean				_error;
	
	public Server()
	{
		_sockAddress = null;
		_port = 0;
	}
	
	public Server(final String hostname, final int port)
	{
		_logger.log(LogLevel.FATAL, "Binding...");
		
		// If fails to bind the socket, return error state.
		try
		{
			bind(hostname, port);
		}
		catch (IOException e)
		{
			_logger.log(LogLevel.FATAL, "Binding failed!");
			_error = true;
			
			e.printStackTrace();
			return;
		}
		
		_logger.log(LogLevel.FATAL, "Binded successfully!");
	}
	
	/**
	 * Binds the server channel with the Ip and port provided.
	 * 
	 * @param hostname
	 * @param port
	 * @throws IOException
	 */
	public void bind(final String hostname, final int port) throws IOException
	{
		if (_error)
			return;
		
		_port = port;
		
		_selector = Selector.open();
		_socketChannel = ServerSocketChannel.open();
		
		_sockAddress = new InetSocketAddress(hostname, port);
		_socketChannel.bind(_sockAddress);
		
		_socketChannel.configureBlocking(false);
	}
	
	/**
	 * @return true/false whether the server startup was successful or not.
	 */
	public boolean start()
	{
		if (_error)
			return false;
		
		_logger.log(LogLevel.FATAL, "Starting the server");
		
		// Initialize the thread that handles the server behavior and start it.
		_socketThread = new ServiceHandler(_socketChannel, _selector);
		_socketThread.start();
		
		_logger.log(LogLevel.FATAL, "Started the server\n");
		
		return true;
	}
	
	/**
	 * @return true/false whether the server stopped successfully or not.
	 */
	public boolean stop()
	{
		_logger.log(LogLevel.FATAL, "Stopping the server");
		
		try
		{
			// Interrupt the thread.
			_socketThread.interrupt();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * @return the port of the server channel that's running at.
	 */
	public int getPort()
	{
		return _port;
	}
	
	public static void main(String[] args)
	{
		// Initializes the server with Ip localhost and port 5588
		Server server = new Server("localhost", 5588);
		server.start(); // start the server
	}
}

class ServiceHandler extends Thread
{
	private ServerSocketChannel	_socket;
	private Selector			_selector;
	private Logger				_logger	= Logger.getInstance();
	
	public ServiceHandler(final ServerSocketChannel socket, final Selector selector)
	{
		_socket = socket;
		_selector = selector;
	}
	
	@Override
	public void run()
	{
		_logger.log(LogLevel.FATAL, "Waiting for clients...");
		
		int ops = _socket.validOps();
		
		try
		{
			_socket.register(_selector, ops, null);
		}
		catch (ClosedChannelException e1)
		{
			e1.printStackTrace();
		}
		
		// While the thread is not interrupted.
		while (!isInterrupted())
		{
			{
				try
				{
					// Select the keys for the IO operations.
					_selector.select();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
				// Store the keys and iterate through them.
				Set<SelectionKey> keys = _selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				
				// While the iterator has more elements.
				while (iterator.hasNext())
				{
					SelectionKey key = iterator.next();
					
					// If the key is ready to accept a new client connection.
					if (key.isAcceptable())
					{
						try
						{
							SocketChannel client = _socket.accept();
							client.configureBlocking(false);
							client.register(_selector, SelectionKey.OP_READ);
							
							_logger.log(LogLevel.NORMAL, "Connection accepted from: " + client.getLocalAddress() + "\n");
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					// If the key is ready for reading.
					else if (key.isReadable())
					{
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(256);
						
						try
						{
							client.read(buffer);
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						
						// Print the message received to the screen.
						String result = new String(buffer.array()).trim();
						_logger.log(LogLevel.NORMAL, "Received: " + result);
						
						// If the message is ~quit, terminate the socket connection.
						if (result.equals("~quit"))
						{
							try
							{
								client.close();
								System.out.println("Client closed.");
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}
						}
					}
					
					// Remove the key collection.
					iterator.remove();
				}
			}
		}
		_logger.log(LogLevel.FATAL, "Server stopped.");
	}
	
}