/**
 * 
 */
package nc.ui.dm.m4804.editor.util;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.dm.m4804.entity.DelivBillPackVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @author yuxx
 * @created at 2016-8-6,下午2:27:21
 * 
 */
public class M4804LinkageUtil {

	private BillCardPanel billCardPanel;

	private int flag;

	/**
	 * 运输单表体合计字段联动处理类构造函数
	 * 
	 * @param billCardPanel
	 * @param flag
	 *            货物页签flag=0 包装页签flag=1
	 */
	public M4804LinkageUtil(BillCardPanel billCardPanel, int flag) {
		super();
		this.billCardPanel = billCardPanel;
		this.setFlag(flag);
	}

	public BillCardPanel getBillCardPanel() {
		return this.billCardPanel;
	}

	public int getFlag() {
		return this.flag;
	}

	public void linkage() {
		if (this.flag == 0) {
			this.linkageBill();
		} else if (this.flag == 1) {
			this.linkagePack();
		}

	}

	public void setBillCardPanel(BillCardPanel billCardPanel) {
		this.billCardPanel = billCardPanel;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	private void linkageBill() {
		CardPanelValueUtils util = new CardPanelValueUtils(this.billCardPanel);
		// 总行号
		int rowCount = util.getRowCount();
		if (rowCount > 0) {
			// 总数量
			UFDouble ntotalastnum = UFDouble.ZERO_DBL;
			// 总主数量
			UFDouble ntotalnum = UFDouble.ZERO_DBL;
			// 总重量
			UFDouble ntotalweight = UFDouble.ZERO_DBL;
			// 总体积
			UFDouble ntotalvolume = UFDouble.ZERO_DBL;
			// 总金额
			UFDouble ntotalmny = UFDouble.ZERO_DBL;
			
			// @edit by yuxx at 2016-8-15,上午9:47:02 总件数、重量、体积（vdef15,vdef16,vdef17）
			UFDouble vdef15 = UFDouble.ZERO_DBL;
			UFDouble vdef16 = UFDouble.ZERO_DBL;
			UFDouble vdef17 = UFDouble.ZERO_DBL;
			//end edit
			
			// 数量
			UFDouble nastnum = UFDouble.ZERO_DBL;
			// 主数量
			UFDouble nnum = UFDouble.ZERO_DBL;
			// 重量
			UFDouble nweight = UFDouble.ZERO_DBL;
			// 体积
			UFDouble nvolume = UFDouble.ZERO_DBL;
			// 金额
			UFDouble nmny = UFDouble.ZERO_DBL;
			
		    // @edit by yuxx at 2016-8-15,上午9:50:25 表体件数、重量、体积
			UFDouble vbdef18 = UFDouble.ZERO_DBL;
			UFDouble vbdef19 = UFDouble.ZERO_DBL;
			UFDouble vbdef20 = UFDouble.ZERO_DBL;
			// end edit
			for (int i = 0; i < rowCount; i++) {
				nastnum = util.getBodyUFDoubleValue(i, DelivBillBVO.NASTNUM);
				nastnum = nastnum == null ? UFDouble.ZERO_DBL : nastnum;
				nnum = util.getBodyUFDoubleValue(i, DelivBillBVO.NNUM);
				nnum = nnum == null ? UFDouble.ZERO_DBL : nnum;
				nweight = util.getBodyUFDoubleValue(i, DelivBillBVO.NWEIGHT);
				nweight = nweight == null ? UFDouble.ZERO_DBL : nweight;
				nvolume = util.getBodyUFDoubleValue(i, DelivBillBVO.NVOLUMN);
				nvolume = nvolume == null ? UFDouble.ZERO_DBL : nvolume;
				nmny = util.getBodyUFDoubleValue(i, DelivBillBVO.NMONEY);
				nmny = nmny == null ? UFDouble.ZERO_DBL : nmny;
				
				// @edit by yuxx at 2016-8-15,上午10:16:33 取表体的件数、重量，体积（vbdef18,vbdef19,vbdef20）
				vbdef18 = util.getBodyUFDoubleValue(i, DelivBillBVO.VBDEF18);
				vbdef18 = vbdef18==null ? UFDouble.ZERO_DBL:vbdef18;
				vbdef19 = util.getBodyUFDoubleValue(i, DelivBillBVO.VBDEF19);
				vbdef19 = vbdef19==null ? UFDouble.ZERO_DBL:vbdef19;
				vbdef20 = util.getBodyUFDoubleValue(i, DelivBillBVO.VBDEF20);
				vbdef20 = vbdef20==null ? UFDouble.ZERO_DBL:vbdef20;
				
				ntotalastnum = ntotalastnum.add(nastnum);
				ntotalnum = ntotalnum.add(nnum);
				ntotalweight = ntotalweight.add(nweight);
				ntotalvolume = ntotalvolume.add(nvolume);
				ntotalmny = ntotalmny.add(nmny);
				
				// @edit by yuxx at 2016-8-15,上午10:20:06 合计件数、重量、体积
				vdef15 = vdef15.add(vbdef18);
				vdef16 = vdef16.add(vbdef19);
				vdef17 = vdef17.add(vbdef20);
			}

			util.setHeadTailValue(DelivBillHVO.NTOTALASTNUM, ntotalastnum);
			util.setHeadTailValue(DelivBillHVO.NTOTALNUM, ntotalnum);
			util.setHeadTailValue(DelivBillHVO.NTOTALWEIGHT, ntotalweight);
			util.setHeadTailValue(DelivBillHVO.NTOTALVOLUME, ntotalvolume);
			util.setHeadTailValue(DelivBillHVO.NTOTALMNY, ntotalmny);
			// @edit by yuxx at 2016-8-6,下午2:31:08
			util.setHeadTailValue(DelivBillHVO.VDEF15, vdef15);
			util.setHeadTailValue(DelivBillHVO.VDEF16, vdef16);
			util.setHeadTailValue(DelivBillHVO.VDEF17, vdef17);
			
			// @edit by yuxx at 2016-8-15,上午10:27:16 后台执行前台vbillcode上的编辑后公式，
			//         计算折扣，今后折扣公式变化一定需要在vbillcode的编辑公式中更改，否则无效
			String[] formula=this.billCardPanel.getHeadItem("vbillcode").getEditFormulas();
			this.billCardPanel.execHeadFormulas(formula);
			//this.calDef();
		} else {
			util.setHeadTailValue(DelivBillHVO.NTOTALASTNUM, null);
			util.setHeadTailValue(DelivBillHVO.NTOTALNUM, null);
			util.setHeadTailValue(DelivBillHVO.NTOTALWEIGHT, null);
			util.setHeadTailValue(DelivBillHVO.NTOTALVOLUME, null);
			util.setHeadTailValue(DelivBillHVO.NTOTALMNY, null);
		}
	}

	private void linkagePack() {
		CardPanelValueUtils util = new CardPanelValueUtils(this.billCardPanel);
		// 总行号
		int rowCount = util.getRowCount();
		if (rowCount > 0) {
			// 总包装件数
			UFDouble ntotalpacknum = UFDouble.ZERO_DBL;
			// 总包装重量
			UFDouble ntotalpackweight = UFDouble.ZERO_DBL;
			// 总包装体积
			UFDouble ntotalpackvolume = UFDouble.ZERO_DBL;

			// 包装件数
			UFDouble npacknum = UFDouble.ZERO_DBL;
			// 包装重量
			UFDouble npackweight = UFDouble.ZERO_DBL;
			// 包装体积
			UFDouble npackvolume = UFDouble.ZERO_DBL;
			for (int i = 0; i < rowCount; i++) {
				npacknum = util.getBodyUFDoubleValue(i,
						DelivBillPackVO.NPACKNUM);
				npacknum = npacknum == null ? UFDouble.ZERO_DBL : npacknum;
				npackweight = util.getBodyUFDoubleValue(i,
						DelivBillPackVO.NPACKWEIGHT);
				npackweight = npackweight == null ? UFDouble.ZERO_DBL
						: npackweight;
				npackvolume = util.getBodyUFDoubleValue(i,
						DelivBillPackVO.NPACKVOLUME);
				npackvolume = npackvolume == null ? UFDouble.ZERO_DBL
						: npackvolume;

				ntotalpacknum = ntotalpacknum.add(npacknum);
				ntotalpackweight = ntotalpackweight.add(npackweight);
				ntotalpackvolume = ntotalpackvolume.add(npackvolume);
			}

			util.setHeadTailValue(DelivBillHVO.NTOTALPACKNUM, ntotalpacknum);
			util.setHeadTailValue(DelivBillHVO.NTOTALPACKWEIGHT,
					ntotalpackweight);
			util.setHeadTailValue(DelivBillHVO.NTOTALPACKVOLUME,
					ntotalpackvolume);
			// @edit by yuxx at 2016-8-6,下午2:31:08
			//this.calDef();
		} else {
			util.setHeadTailValue(DelivBillHVO.NTOTALPACKNUM, null);
			util.setHeadTailValue(DelivBillHVO.NTOTALPACKWEIGHT, null);
			util.setHeadTailValue(DelivBillHVO.NTOTALPACKVOLUME, null);
		}
	}
	/**
	 * 联动合计vdef15,16,17
	 * @create by yuxx at 2016-8-6,下午2:30:18
	 *
	 */
/*	private void calDef() {
		CardPanelValueUtils util = new CardPanelValueUtils(this.billCardPanel);
		UFDouble totaldef18 = (UFDouble) this.billCardPanel.getTotalTableModel().getValueAt(
				0, 11);
		UFDouble totaldef19 = (UFDouble) this.billCardPanel.getTotalTableModel().getValueAt(
				0, 12);
		UFDouble totaldef20 = (UFDouble) this.billCardPanel.getTotalTableModel().getValueAt(
				0, 13);
		util.setHeadTailValue(DelivBillHVO.VDEF15, totaldef18);// 件数
		util.setHeadTailValue(DelivBillHVO.VDEF16, totaldef19);// 重量
		util.setHeadTailValue(DelivBillHVO.VDEF17, totaldef20);// 体积
		*//**
		 * 后台执行前台vbillcode上的编辑后公式，计算折扣，今后折扣公式变化一定需要在vbillcode的编辑公式中更改，否则无效
		 *//*
		String[] formula=this.billCardPanel.getHeadItem("vbillcode").getEditFormulas();
		this.billCardPanel.execHeadFormulas(formula);
	}*/
}
