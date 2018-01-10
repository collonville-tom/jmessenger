package org.tc.osgi.bundle.jmessenger.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.tc.osgi.bundle.jmessenger.module.service.LoggerServiceProxy;

/**
 * Classe Abstraite de la structure generique d'un client
 * @author Collonville Thomas
 * @version 0.0.1
 *
 */
public abstract class AbstractClient extends AbstractCommunicationElement {

	/**
	 * InputStream
	 */
	private InputStream ois = null;

	/**
	 * OutputStream
	 */
	private OutputStream oos = null;

	/**
	 * Socket sur laquelle est connecté le client
	 */
	private Socket socket = null;

	/**
	 * Constructeur valué d'un client abstait
	 * @param _threadName String nom du Thread
	 * @param _server AbstractServer le serveur instanciant le client permetant de lui notifier la fermeture eventuelle de ce dernier
	 * @param _socket Socket la socket permettant au client de communiquer
	 * @throws IOException
	 */
	public AbstractClient(final String _threadName, final Socket _socket) throws IOException {
		super(_threadName);
		socket = _socket;
		ois = new ObjectInputStream(socket.getInputStream());
		LoggerServiceProxy.getInstance().getLogger(AbstractClient.class).debug("init ObjectInputStream OK");
		oos = new ObjectOutputStream(socket.getOutputStream());
		LoggerServiceProxy.getInstance().getLogger(AbstractClient.class).debug("init ObjectOutputStream OK");
	}

	/**
	 * @return InputStream
	 */
	public InputStream getOis() {
		return ois;
	}

	/**
	 * @return OutputStream
	 */
	public OutputStream getOos() {
		return oos;
	}

	/**
	 * @return Socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param ois InputStream
	 */
	public void setOis(final InputStream ois) {
		this.ois = ois;
	}

	/**
	 * @param oos OutputStream
	 */
	public void setOos(final OutputStream oos) {
		this.oos = oos;
	}

	/**
	 * @param socket Socket
	 */
	public void setSocket(final Socket socket) {
		this.socket = socket;
	}

}
