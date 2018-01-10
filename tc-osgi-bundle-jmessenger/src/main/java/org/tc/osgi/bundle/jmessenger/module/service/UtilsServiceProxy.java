package org.tc.osgi.bundle.jmessenger.module.service;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

import org.tc.osgi.bundle.utils.interf.module.service.IUtilsService;
import org.tc.osgi.bundle.utils.interf.serial.ISerialTool;

/**
 * UtilsService.java.
 *
 * @author Collonville Thomas
 * @version 0.2.2
 * @track SDD_BUNDLE_UTILS_100
 */
public class UtilsServiceProxy implements IUtilsService {

	/**
	 * UtilsServiceProxy instance.
	 */
	private static UtilsServiceProxy instance = null;

	/**
	 * getInstance.
	 * @return UtilsServiceProxy
	 */
	public static UtilsServiceProxy getInstance() {
		if (UtilsServiceProxy.instance == null) {
			UtilsServiceProxy.instance = new UtilsServiceProxy();
		}
		return UtilsServiceProxy.instance;
	}

	/**
	 * IUtilsService service.
	 */
	private IUtilsService service = null;

	/**
	 * UtilsServiceProxy constructor.
	 */
	private UtilsServiceProxy() {

	}

	/**
	 * getService.
	 * @return IUtilsService
	 */
	public IUtilsService getService() {
		return service;
	}

	/**
	 * setService.
	 * @param service IUtilsService
	 */
	public void setService(final IUtilsService service) {
		this.service = service;
	}

	/**
	 * getSerialTool.
	 *
	 * @return SerialTool<Serializable>
	 */
	public <T extends Serializable> ISerialTool<T> getSerialTool() {
		return this.getService().getSerialTool();

	}

	public boolean contains(Annotation[] annots, Class<?> ann) {
		return this.getService().contains(annots, ann);
	}

	/**
	 * list2String.
	 *
	 * @param chaines
	 *            List<String>
	 * @return String
	 */
	public String list2String(final List chaines, final String delimiter) {
		return this.getService().list2String(chaines, delimiter);
	}

	/**
	 * list2String.
	 * @param tab T[]
	 * @param delimiter String
	 * @return <T>
	 */
	public <T> String tab2String(final T[] tab, final String delimiter) {
		return this.getService().tab2String(tab, delimiter);
	}

}
