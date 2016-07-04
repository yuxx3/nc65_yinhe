package nc.ui.uapbd.refmanage.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.bd.cust.refmanage.RefManageVO;

public class AceBodyAfterEditHandler implements
		IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// 业务类型字段编辑后事件
		if (e.getKey().equals("busitype")) {
			int selectindex = e.getBillCardPanel().getBillTable()
					.getSelectedRow();
			// 业务类型是开票时，开票延迟罚率可编辑
			e.getBillCardPanel()
					.getBillModel()
					.setCellEditable(selectindex, "billlatepunish",
							e.getValue().equals("1"));
			e.getBillCardPanel()
					.getBillModel()
					.setCellEditable(selectindex, "recoverycom",
							e.getValue().equals("2"));
			e.getBillCardPanel()
					.getBillModel()
					.setCellEditable(selectindex, "recacountlate",
							e.getValue().equals("2"));

			// 设置完编辑性后要清空标准月份天数、延期处罚月数、预警天数、开票延迟率、回收提成率、应收款超期率
			e.getBillCardPanel().getBillModel()
					.setValueAt(null, selectindex, "monthdaynum");
			e.getBillCardPanel().getBillModel()
					.setValueAt(null, selectindex, "punishdelaym");
			e.getBillCardPanel().getBillModel()
					.setValueAt(null, selectindex, "alertdays");
			e.getBillCardPanel().getBillModel()
					.setValueAt(0, selectindex, "billlatepunish");
			e.getBillCardPanel().getBillModel()
					.setValueAt(0, selectindex, "recoverycom");
			e.getBillCardPanel().getBillModel()
					.setValueAt(0, selectindex, "recacountlate");
		}
	}
}
