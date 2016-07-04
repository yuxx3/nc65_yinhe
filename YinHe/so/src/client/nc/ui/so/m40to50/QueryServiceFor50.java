/**
 * 
 */
package nc.ui.so.m40to50;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderFor5X;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.itf.so.m30.self.ISaleOrderMaintainApp;
import nc.itf.so.m4331.IDeliveryMaintain;
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
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 销售发票拉销售发货查询服务
 * @author yuxx3
 * @created at 2016年4月19日,下午1:24:51
 *
 */
public class QueryServiceFor50 implements IRefQueryService {

	@Override
	public Object[] queryByWhereSql(String arg0) throws Exception {
		return null;
	}
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
		DeliveryViewVO[] rets = null;
	    IDeliveryMaintain service = (IDeliveryMaintain)NCLocator.getInstance().lookup(IDeliveryMaintain.class);
	    try
	    {
	      rets = service.queryDeliveryFor4804(queryScheme);
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    return rets;
	}

}
