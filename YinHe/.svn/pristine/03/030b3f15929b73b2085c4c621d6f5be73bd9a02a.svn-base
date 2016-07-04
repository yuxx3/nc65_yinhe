package nc.bs.uapbd.orderarearela.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.uapbd.orderarearela.Orderrelation;

public class AceOrderarearelaBP {

	public Orderrelation[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<Orderrelation>(Orderrelation.class).query(querySheme,
				null);
	}
}
