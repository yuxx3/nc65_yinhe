/**
 * 
 */
package nc.impl.so.m30.self;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.itf.so.m30.self.ISaleOrderFor5X;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.comparator.RowNoComparator;

/**
 * @author yuxx3
 * @created at 2016年4月27日,下午3:41:23
 * 
 */
public class SaleOrderFor5XImpl implements ISaleOrderFor5X {
	@Override
	public SaleOrderVO[] querySaleOrderFor5X(IQueryScheme queryScheme)
			throws BusinessException {
		String ordersql = createOrderSql(queryScheme);
		SaleOrderVO[] bills = null;
		try {
			bills = querySaleOrderVOForSource(queryScheme, ordersql);
			
			/*if ((bills != null) && (bills.length > 0)) {
				QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
				String dest_pk_org = qsp.getQueryCondition("dest_pk_org")
						.getValues()[0];

				for (SaleOrderVO bill : bills) {
					bill.getParentVO().setDest_pk_org(dest_pk_org);
				}
			}*/
			return bills;
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
		}
		return bills;
	}

	private String createOrderSql(IQueryScheme queryScheme) {
		SqlBuilder order = new SqlBuilder();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		order.append(" order by ");
		String tableName = processor.getTableAliasOfAttribute(
				SaleOrderHVO.class, "vbillcode");

		order.append(tableName);
		order.append(".");
		order.append("vbillcode");
		order.append(",");
		tableName = processor.getTableAliasOfAttribute(SaleOrderBVO.class,
				"crowno");

		order.append(tableName);
		order.append(".");
		order.append("crowno");
		return order.toString();
	}

	private SaleOrderVO[] querySaleOrderVOForSource(IQueryScheme scheme,
			String order) {
		SchemeViewQuery query = new SchemeViewQuery(SaleOrderViewVO.class);

		SaleOrderViewVO[] views = (SaleOrderViewVO[]) query
				.query(scheme, order);
		if (ArrayUtils.isEmpty(views)) {
			return null;
		}
		for (SaleOrderViewVO view : views) {
			SaleOrderHVO headvo = view.getHead();
			SaleOrderBVO bodyvo = view.getBody();
			headvo.setPk_group(bodyvo.getPk_group());
			headvo.setPk_org(bodyvo.getPk_org());
			headvo.setDbilldate(bodyvo.getDbilldate());
			headvo.setNdiscountrate(bodyvo.getNdiscountrate());
		}
		SaleOrderVO[] queryVos = (SaleOrderVO[]) new CombineViewToAggUtil(
				SaleOrderVO.class, SaleOrderHVO.class, SaleOrderBVO.class)
				.combineViewToAgg(views, "csaleorderid");

		for (SaleOrderVO vo : queryVos) {
			RowNoComparator c = new RowNoComparator("crowno");
			Arrays.sort(vo.getChildrenVO(), c);
		}
		return queryVos;
	}

}
