package nc.impl.dm.m4804.rule;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.scmpub.res.billtype.DMBillType;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 单据号重复性校验
 * 
 * @since 6.0
 * @version 2010-11-23 下午04:10:01
 * @author yinyxa
 */
public class CheckBillCodeRule implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] bills) {
    BillCodeInfo info =
    		BillCodeInfoBuilder.buildBillCodeInfo(DMBillType.DelivBill.getCode(),
            DelivBillHVO.VBILLCODE, DelivBillHVO.PK_GROUP, DelivBillHVO.PK_ORG,
            DelivBillHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.checkUnique(bills);
  }

}
