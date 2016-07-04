/**
 * 
 */
package nc.ui.so.m40to50;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.vo.querytemplate.TemplateInfo;

/**
 * @author yuxx3
 * @created at 2016年4月19日,下午3:40:57
 *
 */
public class BillRefQuery extends DefaultBillReferQuery {

	/**
	 * @create by yuxx3 at 2016年4月19日,下午3:41:08
	 *
	 * @param c
	 * @param info
	 */
	public BillRefQuery(Container c, TemplateInfo info) {
		super(c, info);
	}
	  @Override
	  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
		  super.initQueryConditionDLG(dlgDelegator);
	  }
}
