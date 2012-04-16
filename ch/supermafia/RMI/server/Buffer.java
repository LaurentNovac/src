package ch.supermafia.RMI.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ch.supermafia.RMI.object.ProducerConsumer_I;


public class Buffer extends UnicastRemoteObject implements ProducerConsumer_I
	{
	
	protected Buffer() throws RemoteException
		{
		super();
		}

	@Override
	public void put(Object obj) throws RemoteException
		{
		// TODO Auto-generated method stub
		
		}
	
	@Override
	public Object get() throws RemoteException
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	}
