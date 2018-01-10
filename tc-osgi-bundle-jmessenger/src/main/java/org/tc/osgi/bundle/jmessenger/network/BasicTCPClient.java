package org.tc.osgi.bundle.jmessenger.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.tc.osgi.bundle.jmessenger.ihm.ClientFrame;
import org.tc.osgi.bundle.jmessenger.message.BasicMessage;
import org.tc.osgi.bundle.jmessenger.module.service.LoggerServiceProxy;

public class BasicTCPClient extends AbstractClient {

	private static int clientIndex = 0;

	private BasicTCPServer serveur = null;

	public BasicTCPClient(final BasicTCPServer serveur, final Socket _sock) throws IOException {
		super("BasicTCPClient", _sock);
		this.serveur = serveur;
		addObserver(new ClientFrame(this));

	}

	public BasicTCPClient(final InetAddress ip, final int port) throws IOException {
		super("BasicTCPClient" + BasicTCPClient.clientIndex++, new Socket(ip, port));
		LoggerServiceProxy.getInstance().getLogger(BasicTCPClient.class).debug("Construction socket OK");
		addObserver(new ClientFrame(this));

	}

	public InetAddress getInetAddress() {
		return getSocket().getInetAddress();
	}

	public void ReceiveMessage() throws IOException, ClassNotFoundException {
		if (getOos() == null) {
			setOis(new ObjectInputStream(getSocket().getInputStream()));
		}
		if (getOis().available() == 0) {
			BasicMessage message = null;
			message = (BasicMessage) ((ObjectInputStream) getOis()).readObject();
			LoggerServiceProxy.getInstance().getLogger(BasicTCPClient.class).debug("Message receive : " + message.toString());
			this.notifyObservers(message);
		}

	}

	@Override
	public void run() {

		while (isActive()) {
			try {
				ReceiveMessage();
				Thread.yield();
				Thread.sleep(50);
			} catch (final IOException e) {
				LoggerServiceProxy.getInstance().getLogger(BasicTCPClient.class).debug("Connexion lost with " + getInetAddress());
				setActive(false);

			} catch (final ClassNotFoundException e) {
				LoggerServiceProxy.getInstance().getLogger(BasicTCPClient.class).error(e.getMessage());
			} catch (final InterruptedException e) {
				LoggerServiceProxy.getInstance().getLogger(BasicTCPClient.class).error(e.getMessage());
			}
		}
		LoggerServiceProxy.getInstance().getLogger(BasicTCPClient.class).debug("Client Stop");
		try {
			getOis().close();
			getOos().close();
			getSocket().close();
		} catch (final IOException e) {
			LoggerServiceProxy.getInstance().getLogger(BasicTCPClient.class).error(e.getMessage());
		}
	}

	public void SendMessage(final BasicMessage mess) throws IOException {
		if (getOos() == null) {
			setOos(new ObjectOutputStream(getSocket().getOutputStream()));
		}
		((ObjectOutputStream) getOos()).writeObject(mess);
	}

}
