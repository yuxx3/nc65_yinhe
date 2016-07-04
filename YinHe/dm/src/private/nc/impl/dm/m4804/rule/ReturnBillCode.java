package nc.impl.dm.m4804.rule;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.scmpub.res.billtype.DMBillType;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 运输单删除回退单据号
 * 
 * @since 6.0
 * @version 2011-11-3 上午11:17:23
 * @author 高扬
 */
public class ReturnBillCode implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] vos) {
    BillCodeInfo info =
    		BillCodeInfoBuilder.buildBillCodeInfo(DMBillType.DelivBill.getCode(),
            DelivBillHVO.VBILLCODE, DelivBillHVO.PK_GROUP, DelivBillHVO.PK_ORG,
            DelivBillHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.returnBillCode(vos);
  }
}
