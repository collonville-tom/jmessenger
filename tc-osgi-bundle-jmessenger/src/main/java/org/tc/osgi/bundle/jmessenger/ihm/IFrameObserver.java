package org.tc.osgi.bundle.jmessenger.ihm;

import org.tc.osgi.bundle.jmessenger.message.BasicMessage;
import org.tc.osgi.bundle.jmessenger.network.AbstractCommunicationElement;
import org.tc.osgi.bundle.utils.interf.pattern.observer.IObserver;

public interface IFrameObserver extends IObserver<AbstractCommunicationElement, BasicMessage> {

}
