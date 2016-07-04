/**
 * 
 */
package nc.ui.uapbd.gdorder.ace.handler;

import nc.ui.gdorder.tools.CheckPawd;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

/**
 * 公用的表体编辑前事件处理部分
 * 
 * @author yuxx3
 * @created at 2016年6月6日,下午2:55:55
 * 
 */
public class PubBodyBeforeEditHandler {

	public void deal(CardBodyBeforeEditEvent event) {
		// 获取贸易类型
		/*
		 * String type = (String) event.getBillCardPanel().getBillModel()
		 * .getValueAt(event.getRow(), "vdef1");
		 */
		String type = "1001ZZ10000000017ROU";
		boolean editable = false;
		CheckPawd cpd = new CheckPawd();
		// 合同编号
		if (event.getKey().equals("htcode")) {
			editable = cpd.isOK(type, "htcode");
		}
		// 合同/订单签订日期
		if (event.getKey().equals("orderdate")) {
			editable = cpd.isOK(type, "orderdate");
		}
		// 合同/订单签订数量
		if (event.getKey().equals("ordernum")) {
			editable = cpd.isOK(type, "ordernum");
		}
		// 数量
		if (event.getKey().equals("cust_num")) {
			editable = cpd.isOK(type, "cust_num");
		}
		// 交货日期
		if (event.getKey().equals("cust_date")) {
			editable = cpd.isOK(type, "cust_date");
		}
		// 性质（计划外/计划内）
		if (event.getKey().equals("nature")) {
			editable = cpd.isOK(type, "nature");
		}

		// 质量计划下达日期
		if (event.getKey().equals("plandate")) {
			editable = cpd.isOK(type, "plandate");
		}

		// 硬件预估完成日期
		if (event.getKey().equals("hardplandate")) {
			editable = cpd.isOK(type, "hardplandate");
		}

		// 硬件实际完成日期
		if (event.getKey().equals("hardcompdate")) {
			editable = cpd.isOK(type, "hardcompdate");
		}

		// 测试版日期
		if (event.getKey().equals("testdate")) {
			editable = cpd.isOK(type, "testdate");
		}
		// 正式版日期1
		if (event.getKey().equals("formaldate1")) {
			editable = cpd.isOK(type, "formaldate1");
		}
		// 正式版日期2
		if (event.getKey().equals("formaldate2")) {
			editable = cpd.isOK(type, "formaldate2");
		}
		// 正式版日期3
		if (event.getKey().equals("formaldate3")) {
			editable = cpd.isOK(type, "formaldate3");
		}
		// 到货确认日期
		if (event.getKey().equals("comfirmdate")) {
			editable = cpd.isOK(type, "comfirmdate");
		}
		// 中期物料到位日期
		if (event.getKey().equals("moddledate")) {
			editable = cpd.isOK(type, "moddledate");
		}
		// 软件实际下达日期
		if (event.getKey().equals("softformaldate")) {
			editable = cpd.isOK(type, "softformaldate");
		}
		// 生产尾数
		if (event.getKey().equals("prosuplus")) {
			editable = cpd.isOK(type, "prosuplus");
		}
		event.setReturnValue(editable);
	}

}
