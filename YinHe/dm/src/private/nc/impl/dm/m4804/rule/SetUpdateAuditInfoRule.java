package nc.impl.dm.m4804.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.pubapp.util.AuditInfoUtils;

/**
 * 设置修改时的审计信息：创建人、制单人、制单时间
 * 
 * @since 6.0
 * @version 2011-3-8 下午06:33:07
 * @author 高扬
 */
public class SetUpdateAuditInfoRule implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] vos) {
    AuditInfoUtils.setUpdateAuditInfo(vos);
  }

}
