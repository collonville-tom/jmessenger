package org.tc.osgi.bundle.jmessenger.conf;

import org.tc.osgi.bundle.utils.interf.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

/**
 * AptConfiguration.java.
 * 
 * @author collonville thomas
 * @version 0.0.1
 */
public final class JMessengerPropertyFile extends AbstractPropertyFile {
	/**
	 * String BUNDLE_RACINE.
	 */
	public final static String BUNDLE_RACINE = "tc.osgi.bundle.jmessenger.";

	/**
	 * DefaultConfig conf.
	 */
	private static JMessengerPropertyFile instance = null;

	/**
	 * String EQUINOXLOADERFILE.
	 */
	public static final String JMESSENGER_FILE = "jmessenger";

	/**
	 * getInstance.
	 * 
	 * @return DefaultConfig
	 * @throws EquinoxConfigException
	 * @throws FieldTrackingAssignementException
	 */
	public static JMessengerPropertyFile getInstance() {
		if (JMessengerPropertyFile.instance == null) {
			JMessengerPropertyFile.instance = new JMessengerPropertyFile();
		}
		return JMessengerPropertyFile.instance;
	}

	/**
	 * AptConfiguration constructor.
	 */
	private JMessengerPropertyFile() {
		super(JMessengerPropertyFile.JMESSENGER_FILE, JMessengerPropertyFile.class.getClassLoader());
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getBundleRacine()
	 */
	@Override
	public String getBundleRacine() {
		return JMessengerPropertyFile.BUNDLE_RACINE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getConfFile()
	 */
	@Override
	public String getConfFile() {
		return JMessengerPropertyFile.JMESSENGER_FILE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getXMLFile()
	 */
	@Override
	public String getXMLFile() {
		return JMessengerPropertyFile.getInstance().getConfigDirectory() + getConfFile();
	}

	@Override
	public String getYamlFile() {
		// TODO Auto-generated method stub
		return JMessengerPropertyFile.getInstance().getConfigDirectory() + getConfFile();
	}

}
