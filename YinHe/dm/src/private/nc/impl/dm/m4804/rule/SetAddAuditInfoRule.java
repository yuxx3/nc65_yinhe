package nc.impl.dm.m4804.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.AuditInfoUtils;

/**
 * 设置新增时的审计信息：创建人、制单人、制单时间
 * 
 * @since 6.0
 * @version 2011-3-8 下午06:33:07
 * @author 高扬
 */
public class SetAddAuditInfoRule implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] vos) {
    AuditInfoUtils.setAddAuditInfo(vos);
    ISuperVO vo = null;
    for (IBill bill : vos) {
      vo = bill.getParent();
      // 制单人
      vo.setAttributeValue(DelivBillHVO.BILLMAKER, AppContext.getInstance()
          .getPkUser());
      // 制单日期
      vo.setAttributeValue(DelivBillHVO.DMAKEDATE, AppContext.getInstance()
          .getBusiDate());
    }
  }
}
