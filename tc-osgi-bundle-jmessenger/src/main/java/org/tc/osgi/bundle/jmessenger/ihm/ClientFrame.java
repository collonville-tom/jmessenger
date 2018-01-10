package org.tc.osgi.bundle.jmessenger.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.tc.osgi.bundle.jmessenger.message.BasicMessage;
import org.tc.osgi.bundle.jmessenger.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.jmessenger.network.AbstractCommunicationElement;
import org.tc.osgi.bundle.jmessenger.network.BasicTCPClient;

public class ClientFrame extends JFrame implements IFrameObserver {

	private static final long serialVersionUID = 4915721008950391375L;
	private final BasicTCPClient comClient;
	private final JTextArea receiveText = new JTextArea();
	private final JTextArea sendText = new JTextArea();

	public ClientFrame(final BasicTCPClient comClient) {
		super("connexion with " + comClient.getInetAddress().toString());
		this.comClient = comClient;
		LoggerServiceProxy.getInstance().getLogger(ClientFrame.class).debug("Construction client frame");
		initFrame();

	}

	private void initFrame() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(final WindowEvent e) {

			}

			@Override
			public void windowClosing(final WindowEvent e) {
				comClient.setActive(false);

			}

			@Override
			public void windowDeactivated(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(final WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		final JPanel panneau = new JPanel();
		panneau.setLayout(new BorderLayout());
		panneau.setSize(450, 350);

		final JScrollPane ppane = new JScrollPane(panneau);

		// receiveText.setColumns(40);
		receiveText.setEditable(false);
		final JScrollPane spane = new JScrollPane(receiveText);
		spane.setPreferredSize(new Dimension(400, 250));
		panneau.add(spane, BorderLayout.NORTH);

		final JButton bouton = new JButton("Send");
		bouton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					final String texttoSend = sendText.getText();
					sendText.setText("");
					comClient.SendMessage(new BasicMessage(texttoSend));
					final StringBuffer buff = new StringBuffer(receiveText.getText());
					buff.append("\n Toi ->");
					buff.append(texttoSend);
					receiveText.setText(buff.toString());

				} catch (final IOException e1) {
					LoggerServiceProxy.getInstance().getLogger(ClientFrame.class).error(e1.getMessage());
				}

			}
		});

		final JScrollPane spane2 = new JScrollPane(sendText);
		spane2.setPreferredSize(new Dimension(400, 50));
		panneau.add(spane2, BorderLayout.CENTER);
		panneau.add(bouton, BorderLayout.SOUTH);
		setContentPane(ppane);
		this.setSize(500, 400);
		setResizable(false);
		setVisible(true);
		LoggerServiceProxy.getInstance().getLogger(ClientFrame.class).debug("client lancé");

	}

	@Override
	public void update(AbstractCommunicationElement _subject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AbstractCommunicationElement _subject, BasicMessage _event) {
		if (_event instanceof BasicMessage) {
			final StringBuffer buff = new StringBuffer(ClientFrame.this.receiveText.getText());
			buff.append("\n Elle/Lui->");
			buff.append(((BasicMessage) _event).getMessage());
			ClientFrame.this.receiveText.setText(buff.toString());
		}

	}

}
