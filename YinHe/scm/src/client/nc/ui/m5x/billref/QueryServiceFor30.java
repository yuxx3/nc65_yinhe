/**
 * 
 */
package nc.ui.m5x.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderFor5X;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.itf.so.m30.self.ISaleOrderMaintainApp;
import nc.pubitf.so.m30.api.ISaleOrderVO;
import nc.pubitf.so.m30.pu.m21.ISaleOrderFor21;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.pubitf.so.m30.to.m5a.ISaleOrderFor5A;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * �������������۶�����ѯ����
 * @author yuxx3
 * @created at 2016��4��19��,����1:24:51
 *
 */
public class QueryServiceFor30 implements IRefQueryService {

	@Override
	public Object[] queryByWhereSql(String arg0) throws Exception {
		return null;
	}
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
		SaleOrderVO[] rets = null;
	    ISaleOrderFor5X service = (ISaleOrderFor5X)NCLocator.getInstance().lookup(ISaleOrderFor5X.class);
	    try
	    {
	      rets = service.querySaleOrderFor5X(queryScheme);
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    return rets;
	}

}
