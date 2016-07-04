/**
 * 
 */
package nc.ui.m5x.billref;

import java.awt.Container;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.querytemplate.TemplateInfo;

/**
 * @author yuxx3
 * @created at 2016��4��19��,����3:40:57
 * 
 */
public class BillRefQuery extends DefaultBillReferQuery {

	/**
	 * @create by yuxx3 at 2016��4��19��,����3:41:08
	 * 
	 * @param c
	 * @param info
	 */
	public BillRefQuery(Container c, TemplateInfo info) {
		super(c, info);
	}

	protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
		super.initQueryConditionDLG(dlgDelegator);
	}
}
