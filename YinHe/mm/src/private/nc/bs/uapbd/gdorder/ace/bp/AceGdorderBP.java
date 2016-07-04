package nc.bs.uapbd.gdorder.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;

public class AceGdorderBP {

	public GDOrderCompStatus[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<GDOrderCompStatus>(GDOrderCompStatus.class).query(querySheme,
				null);
	}
}
