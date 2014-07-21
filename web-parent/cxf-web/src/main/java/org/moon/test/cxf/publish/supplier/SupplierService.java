package org.moon.test.cxf.publish.supplier;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "SupplierService",targetNamespace = "http://www.moon.org/ws/supplier", name = "SupplierService")
public interface SupplierService {

	@WebMethod
	public boolean saveOrUpdate(@WebParam(name = "suppliers") List<Supplier> suppliers);
}
