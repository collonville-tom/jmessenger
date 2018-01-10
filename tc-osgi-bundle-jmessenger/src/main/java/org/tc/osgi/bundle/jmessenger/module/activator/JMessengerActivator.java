package org.tc.osgi.bundle.jmessenger.module.activator;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.gui.utils.interf.module.service.IGuiUtilsService;
import org.tc.osgi.bundle.jmessenger.conf.JMessengerPropertyFile;
import org.tc.osgi.bundle.jmessenger.ihm.ServeurFrame;
import org.tc.osgi.bundle.jmessenger.module.service.BundleUtilsServiceProxy;
import org.tc.osgi.bundle.jmessenger.module.service.GuiUtilsServiceProxy;
import org.tc.osgi.bundle.jmessenger.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.jmessenger.module.service.PropertyServiceProxy;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.interf.module.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IPropertyUtilsService;
import org.tc.osgi.bundle.utils.interf.module.utils.AbstractTcOsgiActivator;
import org.tc.osgi.bundle.utils.interf.module.utils.TcOsgiProxy;

/**
 * Activator.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class JMessengerActivator extends AbstractTcOsgiActivator {

	private TcOsgiProxy<ILoggerUtilsService> iLoggerUtilsService;
	private TcOsgiProxy<IPropertyUtilsService> iPropertyUtilsService;
	private TcOsgiProxy<IGuiUtilsService> iGuiUtilsService;

	private String guiUtilsDependencyBundleName;

	public String getGuiUtilsDependencyBundleName() throws FieldTrackingAssignementException {
		if (guiUtilsDependencyBundleName == null) {
			PropertyServiceProxy.getInstance().getXMLPropertyFile(JMessengerPropertyFile.getInstance().getXMLFile()).fieldTraking(this,
				"guiUtilsDependencyBundleName");
		}
		return guiUtilsDependencyBundleName;
	}

	public void setGuiUtilsDependencyBundleName(String guiUtilsDependencyBundleName) {
		this.guiUtilsDependencyBundleName = guiUtilsDependencyBundleName;
	}

	/**
	 * String AUTO_BUNDLE_NAME.
	 */
	public static final String AUTO_BUNDLE_NAME = "tc-osgi-bundle-jmessenger";

	@Override
	protected void checkInitBundleUtilsService(BundleContext context) throws TcOsgiException {
		throw new TcOsgiException("checkInitBundleUtilsService not implemented");

	}

	@Override
	protected void initProxys(BundleContext context) throws TcOsgiException {

		BundleUtilsServiceProxy.getInstance().setService(this.getIBundleUtilsService().getInstance());

		this.iLoggerUtilsService = new TcOsgiProxy<ILoggerUtilsService>(context, ILoggerUtilsService.class);
		LoggerServiceProxy.getInstance().setService(this.iLoggerUtilsService.getInstance());

		this.waitSpringBundle(1000);

		this.iGuiUtilsService = new TcOsgiProxy<IGuiUtilsService>(context, IGuiUtilsService.class);
		GuiUtilsServiceProxy.getInstance().setService(this.iGuiUtilsService.getInstance());

	}

	@Override
	protected void initServices(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void detachProxys(BundleContext context) throws TcOsgiException {
		this.iGuiUtilsService.close();
		this.iPropertyUtilsService.close();
		this.iLoggerUtilsService.close();

	}

	@Override
	protected void detachServices(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeStart(BundleContext context) throws TcOsgiException {
		// Cas specifique d'init du proxy des properties avant initproxy dedié
		// pour permettre l'initialisation des valeurs des bundles en
		// dependances.
		this.iPropertyUtilsService = new TcOsgiProxy<IPropertyUtilsService>(context, IPropertyUtilsService.class);
		PropertyServiceProxy.getInstance().setService(this.iPropertyUtilsService.getInstance());
		this.getIBundleUtilsService().getInstance().getBundleStarter().processOnBundle(context, this.getGuiUtilsDependencyBundleName());

	}

	@Override
	protected void beforeStop(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterStart(BundleContext context) throws TcOsgiException {
		ServeurFrame server = new ServeurFrame(context);

	}

	@Override
	protected void afterStop(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

}
