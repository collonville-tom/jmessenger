package org.tc.osgi.bundle.jmessenger.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.tc.osgi.bundle.jmessenger.module.service.LoggerServiceProxy;

/**
 * Classe Abstraite de la structure générique d'un serveur
 * @author Collonville Thomas
 * @version 0.0.1
 *
 */
public abstract class AbstractServer extends AbstractCommunicationElement {

	/**
	 * List<AbstractClient> clientList.
	 */
	private List<AbstractClient> clientList = null;

	/**
	 * Port d'ecoute du serveur de socket
	 */
	private Integer port = null;

	/**
	 * Server de Socket permettant d'accepter plusieurs clients
	 */
	private ServerSocket serverSocket = null;

	/**
	 * Constructeur valué d'un serveur, celui ci ecoutera le port definie passé en parametre
	 * @param port Integer le port d'ecoute du serveur
	 * @throws IOException
	 */
	public AbstractServer(final Integer port) throws IOException {
		super("AbstractServer");
		this.port = port;
		serverSocket = new ServerSocket(this.port);
		LoggerServiceProxy.getInstance().getLogger(AbstractServer.class).debug("building server " + this.port);
	}

	/**
	 * getClientList.
	 * @return List<AbstractClient>
	 */
	public List<AbstractClient> getClientList() {
		if (clientList == null) {
			clientList = new ArrayList<AbstractClient>();
		}
		return clientList;
	}

	/**
	 * getPort.
	 * @return Integer
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * getServerSocket.
	 * @return ServerSocket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * setPort.
	 * @param port Integer
	 */
	public void setPort(final Integer port) {
		this.port = port;
	}

	/**
	 * setServerSocket.
	 * @param serverSocket ServerSocket
	 */
	public void setServerSocket(final ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

}
