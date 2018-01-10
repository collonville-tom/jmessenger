package org.tc.osgi.bundle.jmessenger.network;

import org.tc.osgi.bundle.jmessenger.ihm.IFrameObserver;
import org.tc.osgi.bundle.jmessenger.message.BasicMessage;
import org.tc.osgi.bundle.utils.interf.pattern.observer.AbstractSubject;

/**
 * Classe permettant de definir le comportement abstrait d'un element en reseau de type Runnable
 * et ainsi simplifier le comportement Threading
 *
 * @author Collonville Thomas
 * @version 0.0.1
 */
public abstract class AbstractCommunicationElement extends AbstractSubject<IFrameObserver, BasicMessage> implements Runnable {

	/**
	 * boolean active.
	 */
	private boolean active = true;

	private String threadName = null;
	private Thread threadOwner = null;

	/**
	 * Constructeur par defaut
	 */
	public AbstractCommunicationElement(final String threadName) {
		super();
		this.threadName = threadName;
	}

	/**
	 * isActive.
	 * @return boolean
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * setActive.
	 * @param active boolean
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setThreadOwner(final Thread _t) {
		_t.setName(threadName);
		threadOwner = _t;
	}

	/**
	 * Fourni une reperentation sous forme de chaine de caractere de l'etat courant d'un objet de type Communication
	 * Est etabli sur la base de l'activité potentiel du thread
	 * @return String
	 */
	@Override
	public String toString() {
		final StringBuffer buff = new StringBuffer(threadOwner.getName());
		buff.append("Thread is active : ");
		buff.append(active);
		buff.append(", is alive : ");
		buff.append(threadOwner.isAlive());
		return buff.toString();
	}

}
