package nc.impl.pub.ace;
import nc.bs.uapbd.refmanage.ace.bp.AceRefmanageBP;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.bd.cust.refmanage.RefManageVO;
import nc.vo.uif2.LoginContext;

/**
 * 按查询模板查询-暂时没用到
 * @author xx
 * @created at 2016年4月8日,上午11:22:48
 *
 */
public abstract class AceRefmanagePubServiceImpl extends SmartServiceImpl {
	public RefManageVO[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		return new AceRefmanageBP().queryByQueryScheme(querySheme);
	}
}