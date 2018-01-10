package org.tc.osgi.bundle.jmessenger.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.jmessenger.message.BasicMessage;
import org.tc.osgi.bundle.jmessenger.module.activator.JMessengerActivator;
import org.tc.osgi.bundle.jmessenger.module.service.GuiUtilsServiceProxy;
import org.tc.osgi.bundle.jmessenger.network.AbstractCommunicationElement;
import org.tc.osgi.bundle.jmessenger.network.BasicTCPClient;
import org.tc.osgi.bundle.jmessenger.network.BasicTCPServer;
import org.tc.osgi.bundle.utils.interf.module.exception.TcOsgiException;

public class ServeurFrame extends JFrame implements IFrameObserver {

	/**
	 * long serialVersionUID.
	 */
	private static final long serialVersionUID = -4751723344409781434L;
	private final JTextArea consoleText = new JTextArea();
	private final JTextField ip = new JTextField("host");
	private final JTextField port = new JTextField("port");
	private BasicTCPServer server = null;
	private final JTextField serverport = new JTextField("serverport");
	private final JTextArea statusText = new JTextArea();

	public ServeurFrame(final BundleContext context) {
		super();
		initFrame(context);
	}

	private void initFrame(final BundleContext context) {

		try {
			addWindowListener(GuiUtilsServiceProxy.getInstance().getBundleClosingWindowsAdapter(context, JMessengerActivator.AUTO_BUNDLE_NAME));
		} catch (TcOsgiException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		final JPanel panneau = new JPanel();
		panneau.setLayout(new FlowLayout());
		panneau.setSize(450, 350);

		final JScrollPane ppane = new JScrollPane(panneau);
		ppane.setAutoscrolls(false);

		statusText.setEditable(false);
		final JScrollPane spane = new JScrollPane(statusText);
		spane.setPreferredSize(new Dimension(400, 200));
		panneau.add(spane, BorderLayout.WEST);

		final JButton bouton = new JButton("CreateClient");
		bouton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				BasicTCPClient client = null;
				try {
					client = new BasicTCPClient(InetAddress.getByName(ip.getText()), Integer.parseInt(port.getText()));
					final Thread tClient = new Thread(client);
					client.setThreadOwner(tClient);
					tClient.start();
				} catch (final NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (final UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (final IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		final JButton boutonServer = new JButton("LaunchServer");
		boutonServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					server = new BasicTCPServer(Integer.parseInt(serverport.getText()), ServeurFrame.this);
					ServeurFrame.this.setTitle("connexion possible on " + InetAddress.getLocalHost().getHostName() + ":"
						+ Integer.toString(server.getServerSocket().getLocalPort()));
					final Thread tServer = new Thread(server);
					server.setThreadOwner(tServer);
					tServer.start();
				} catch (final NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (final UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (final IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		consoleText.setEditable(false);
		final JScrollPane spane2 = new JScrollPane(consoleText);
		spane2.setPreferredSize(new Dimension(400, 200));
		panneau.add(spane2);

		panneau.add(bouton);
		panneau.add(boutonServer);
		ip.setColumns(20);
		port.setColumns(20);
		serverport.setColumns(20);
		panneau.add(ip);
		panneau.add(port);
		panneau.add(serverport);
		setContentPane(panneau);
		this.setSize(500, 550);
		setResizable(true);
		setVisible(true);

	}

	@Override
	public void update(AbstractCommunicationElement _subject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AbstractCommunicationElement _subject, BasicMessage _event) {
		System.out.println(_event.toString());

	}

}
