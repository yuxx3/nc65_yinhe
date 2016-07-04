package nc.impl.pub.ace;
import nc.bs.uapbd.orderarearela.ace.bp.AceOrderarearelaBP;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.uapbd.orderarearela.Orderrelation;
import nc.vo.uif2.LoginContext;

public abstract class AceOrderarearelaPubServiceImpl extends SmartServiceImpl {
	public Orderrelation[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		return new AceOrderarearelaBP().queryByQueryScheme(querySheme);
	}
}