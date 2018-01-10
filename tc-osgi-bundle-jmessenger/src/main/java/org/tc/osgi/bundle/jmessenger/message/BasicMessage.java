package org.tc.osgi.bundle.jmessenger.message;

import java.io.Serializable;

import org.tc.osgi.bundle.jmessenger.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.utils.interf.pattern.observer.IObserverEvent;

/**
 * BasicMessage est la structure de base pour transporter des donnees entre le client local et le client distant
 *
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class BasicMessage implements Serializable, IObserverEvent {

	/**
	 *
	 */
	private static final long serialVersionUID = -4629543524782512462L;
	/**
	 * Le message sous la forme d'une chaine de caracteres
	 */
	private String message = "";

	/**
	 * Constructeur par defaut
	 */
	public BasicMessage() {
	}

	/**
	 * Constructeur value de la classe Message a partir d'une chaine de caracteres pour la donnees membre "message".
	 * @param message Le message
	 */
	public BasicMessage(final String message) {
		this.message = message;
	}

	/**
	 * Accesseur de la donnees membre message
	 * @return String le message
	 */
	public String getMessage() {
		LoggerServiceProxy.getInstance().getLogger(BasicMessage.class).debug("message : " + message);
		return message;
	}

	/**
	 * Permet de modifier la donnees membre message
	 * @param message String Le message
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * Fourni une reperentation sous forme de chaine de caractere de l'etat courant d'un objet de type Message
	 * @return String representation textuelle du message
	 */
	@Override
	public String toString() {
		return message;
	}

}
