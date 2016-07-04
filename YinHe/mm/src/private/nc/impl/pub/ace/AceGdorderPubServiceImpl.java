package nc.impl.pub.ace;
import nc.bs.uapbd.gdorder.ace.bp.AceGdorderBP;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
import nc.vo.uif2.LoginContext;

public abstract class AceGdorderPubServiceImpl extends SmartServiceImpl {
	public GDOrderCompStatus[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		return new AceGdorderBP().queryByQueryScheme(querySheme);
	}
}