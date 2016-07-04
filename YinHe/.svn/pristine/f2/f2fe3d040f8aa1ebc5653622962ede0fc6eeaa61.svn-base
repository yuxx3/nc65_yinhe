/**
 * 
 */
package nc.ui.to.scm45.billref.srm4s;

import nc.bs.framework.common.NCLocator;
import nc.itf.ic.m45.self.IPurchaseInQuery;
import nc.pubitf.ic.m45.ent.IPurchaseInQueryForENT;
import nc.pubitf.ic.m45.m24.IPurchaseInQueryFor24;
import nc.pubitf.ic.m45.m25.IPurchaseInQueryFor25;
import nc.pubitf.ic.m45.m4c.IPurchaseInQueryFor4C;
import nc.pubitf.ic.m45.m4k.IPurchaseInQueryFor4K;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @author yuxx3
 * @created at 2016年4月27日,上午10:22:48
 *
 */
public class QueryServiceFor45 implements IRefQueryService {
	@Override
	public Object[] queryByWhereSql(String arg0) throws Exception {
		return null;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
	    PurchaseInVO[] bill = null;
	    IPurchaseInQueryForENT service = (IPurchaseInQueryForENT)NCLocator.getInstance().lookup(IPurchaseInQueryForENT.class);
	    try
	    {
	      bill = service.queryBills(queryScheme);
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    return bill;
	}

}
