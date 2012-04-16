package ch.supermafia.RMI.object;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ProducerConsumer_I extends Remote
	{
	public void put(Object obj) throws RemoteException;
	public Object get() throws RemoteException;
	}
