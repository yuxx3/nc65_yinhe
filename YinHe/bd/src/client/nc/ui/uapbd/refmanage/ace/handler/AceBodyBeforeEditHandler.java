/**
 * 
 */
package nc.ui.uapbd.refmanage.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

/**
 * 表体字段编辑前事件，主要处理开票延迟率，回收提成率，应收款超期率
 * 
 * @author yuxx3
 * @created at 2016年4月13日,上午9:12:09
 * 
 */
public class AceBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		int selectindex = e.getBillCardPanel().getBillTable().getSelectedRow();
		Object busitype = e.getBillCardPanel().getBillModel()
				.getValueAt(selectindex, "busitype");
		if (busitype != null) {
			if (e.getKey().equals("billlatepunish")) {
				if (!busitype.equals("开票")) {
					e.setReturnValue(false);
				}
			} else if (e.getKey().equals("recoverycom")
					|| e.getKey().equals("recacountlate")) {
				if (!busitype.equals("收款")) {
					e.setReturnValue(false);
				}
			}
		}
		e.setReturnValue(true);
	}
}
