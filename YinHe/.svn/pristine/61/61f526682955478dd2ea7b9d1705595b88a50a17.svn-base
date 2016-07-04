package nc.bs.uapbd.refmanage.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.bd.cust.refmanage.RefManageVO;
/**
 * 查询BP-暂时没用到
 * @author xx
 * @created at 2016年4月8日,上午11:23:14
 *
 */
public class AceRefmanageBP {

	public RefManageVO[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<RefManageVO>(RefManageVO.class).query(querySheme,
				null);
	}
}
