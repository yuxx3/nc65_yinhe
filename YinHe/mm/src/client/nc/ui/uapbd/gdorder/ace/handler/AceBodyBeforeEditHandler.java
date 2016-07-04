/**
 * 
 */
package nc.ui.uapbd.gdorder.ace.handler;

import nc.ui.gdorder.tools.CheckPawd;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;

/**
 * @author niangzi
 * @created at 2016��5��31��,����9:32:50
 * 
 */

//private String a="";
public class AceBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent> {
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent event) {
		// 获取贸易类型
		/*String type = (String) event.getBillCardPanel().getBillModel()
				.getValueAt(event.getRow(), "vdef1");*/
		String type = "1001ZZ10000000017ROV";
		boolean editable = false;
		CheckPawd cpd = new CheckPawd();
		// 合同编号
		if (event.getKey().equals("htcode")) {
			editable = cpd.isOK(type, "htcode");
		}
		// 合同/订单签订日期
		else if (event.getKey().equals("orderdate")) {
			editable = cpd.isOK(type, "orderdate");
		}
		// 合同/订单签订数量
		else if (event.getKey().equals("ordernum")) {
			editable = cpd.isOK(type, "ordernum");
		}
		// 数量
		else if (event.getKey().equals("cust_num")) {
			editable = cpd.isOK(type, "cust_num");
		}
		// 交货日期
		else if (event.getKey().equals("cust_date")) {
			editable = cpd.isOK(type, "cust_date");
		}
		// 性质（计划外/计划内）
		else if (event.getKey().equals("nature")) {
			editable = cpd.isOK(type, "nature");
		}

		// 质量计划下达日期
		else if (event.getKey().equals("plandate")) {
			editable = cpd.isOK(type, "plandate");
		}

		// 硬件预估完成日期
		else if (event.getKey().equals("hardplandate")) {
			editable = cpd.isOK(type, "hardplandate");
		}

		// 硬件实际完成日期
		else if (event.getKey().equals("hardcompdate")) {
			editable = cpd.isOK(type, "hardcompdate");
		}

		// 测试版日期
		else if (event.getKey().equals("testdate")) {
			editable = cpd.isOK(type, "testdate");
		}
		// 正式版日期1
		else if (event.getKey().equals("formaldate1")) {
			editable = cpd.isOK(type, "formaldate1");
		}
		// 正式版日期2
		else if (event.getKey().equals("formaldate2")) {
			editable = cpd.isOK(type, "formaldate2");
		}
		// 正式版日期3
		else if (event.getKey().equals("formaldate3")) {
			editable = cpd.isOK(type, "formaldate3");
		}
		// 到货确认日期
		else if (event.getKey().equals("comfirmdate")) {
			editable = cpd.isOK(type, "comfirmdate");
		}
		// 中期物料到位日期
		else if (event.getKey().equals("moddledate")) {
			editable = cpd.isOK(type, "moddledate");
		}
		// 软件实际下达日期
		else if (event.getKey().equals("softformaldate")) {
			editable = cpd.isOK(type, "softformaldate");
		}
		// 生产尾数
		else if (event.getKey().equals("prosuplus")) {
			editable = cpd.isOK(type, "prosuplus");
		}
		else{
			editable =true;
			return ;
		}
		if(editable){
			//如果可编辑，则把该字段放入map中存储，表明该贸易类型的该字段被编辑过，需要后台更新
			GDOrderCompStatus.addPawd(type, event.getKey());
		}
		event.setReturnValue(editable);
	}

}
