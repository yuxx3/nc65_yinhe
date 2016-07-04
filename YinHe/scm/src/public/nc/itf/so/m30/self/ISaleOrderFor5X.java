/**
 * 
 */
package nc.itf.so.m30.self;

import nc.bs.bank_cvp.compile.registry.BussinessMethods;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单到调拨单拉单查询接口
 * 
 * @author yuxx3
 * @created at 2016年4月27日,下午3:37:14
 * 
 */
public interface ISaleOrderFor5X {

	public SaleOrderVO[] querySaleOrderFor5X(IQueryScheme queryscheme)
			throws BusinessException;

}
