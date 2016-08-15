/**
 * 
 */
package nc.ui.dm.m4804.handler;

import nc.ui.dm.m4804.editor.util.M4804LinkageUtil;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * @author yuxx
 * @created at 2016-8-5,下午5:03:18
 *
 */
public class NumberEditHandler {

	  /**
	   * 主数量编辑后事件
	   * 
	   * @param e
	   */
	  public void afterEdit(CardBodyAfterEditEvent e) {
	    BillCardPanel panel = e.getBillCardPanel();
	    CardPanelValueUtils util = new CardPanelValueUtils(panel);
	    UFDouble nnum = util.getBodyUFDoubleValue(e.getRow(), DelivBillBVO.NNUM);
	    if (nnum == null) {
	      util.setBodyValue(null, e.getRow(), DelivBillBVO.NMONEY);
	      util.setBodyValue(null, e.getRow(), DelivBillBVO.NASTNUM);
	    }
	    else if (nnum.compareTo(UFDouble.ZERO_DBL) <= 0) {
	      util.setBodyValue(null, e.getRow(), DelivBillBVO.NNUM);
	      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
	          "4014001_0", "04014001-0072")/*主数量必须大于0*/);
	    }
	    else {
	      // 调用“数量单价金额公共算法”
	      this.setNPM(e);
	    }
	    // 联动表头合计字段
	    M4804LinkageUtil linkageutil = new M4804LinkageUtil(panel, 0);

	    linkageutil.linkage();
	  }

	  /**
	   * 主数量编辑前事件
	   * 
	   * @param e
	   */
	  public void beforeEdit(CardBodyBeforeEditEvent e) {
	    BillCardPanel panel = e.getBillCardPanel();
	    CardPanelValueUtils util = new CardPanelValueUtils(panel);
	    // 物料为空，不可编辑
	    String inventory =
	        ValueUtils.getString(util.getBodyValue(e.getRow(),
	            DelivBillBVO.CINVENTORYVID, null));
	    if (PubAppTool.isNull(inventory)) {
	      MessageDialog.showHintDlg(
	          e.getBillCardPanel(),
	          null,
	          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
	              "04014001-0225")/*@res "请先选择物料。"*/);
	      e.setReturnValue(Boolean.valueOf(false));
	      return;
	    }
	  }

	  /**
	   * 根据单位(辅单位)设置数量、单价、金额
	   * 
	   * @param e
	   */
	  private void setNPM(CardBodyAfterEditEvent e) {
	    BillCardPanel panel = e.getBillCardPanel();
	    NumPriceMnyUtil editUtil = new NumPriceMnyUtil(panel);
	    editUtil.calculateNumPriceMny(e.getRow(), e.getKey());
	  }
	}
