package org.tc.osgi.bundle.jmessenger.network;

import java.io.IOException;
import java.net.Socket;

import org.tc.osgi.bundle.jmessenger.ihm.ServeurFrame;
import org.tc.osgi.bundle.jmessenger.message.BasicMessage;
import org.tc.osgi.bundle.jmessenger.module.service.LoggerServiceProxy;

public class BasicTCPServer extends AbstractServer {

	public BasicTCPServer(final int port, final ServeurFrame frame) throws IOException {
		super(port);
		addObserver(frame);
	}

	@Override
	public void run() {

		while (isActive()) {
			try {

				LoggerServiceProxy.getInstance().getLogger(BasicTCPServer.class).debug("Serveur is running...");
				final Socket sock = getServerSocket().accept();
				LoggerServiceProxy.getInstance().getLogger(BasicTCPServer.class).debug("Connexion establish with" + sock.getInetAddress());

				final BasicTCPClient client = new BasicTCPClient(this, sock);
				final Thread tclient = new Thread(client);
				client.setThreadOwner(tclient);

				getClientList().add(client);
				LoggerServiceProxy.getInstance().getLogger(BasicTCPServer.class).debug("Launching specific client");
				tclient.start();
				this.notifyObservers(new BasicMessage("Client open: " + getClientList().size()));
				Thread.yield();

			} catch (final IOException e) {
				setActive(false);
				LoggerServiceProxy.getInstance().getLogger(BasicTCPServer.class).error(e.getMessage());
			} catch (final NullPointerException e) {
				LoggerServiceProxy.getInstance().getLogger(BasicTCPServer.class).error(e.getMessage());
			}
		}
		try {
			getServerSocket().close();
		} catch (final IOException e) {
			LoggerServiceProxy.getInstance().getLogger(BasicTCPServer.class).error(e.getMessage());
		}
	}

}
