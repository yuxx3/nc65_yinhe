package nc.impl.dm.m4804.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.dm.enumeration.FstatusFlag;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 检查单据状态
 * 
 * @since 6.0
 * @version 2010-11-22 下午03:21:15
 * @author yinyxa
 */
public class CheckStateRuleForEdit implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] vos) {
    for (DelivBillAggVO vo : vos) {
      DelivBillHVO hvo = vo.getParentVO();
      int flag = hvo.getFstatusflag().intValue();
      // 修改检查
      if (ValueUtils.getInt(FstatusFlag.FREE.value()) != flag) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4014001_0", "04014001-0341")/*@res "单据为非自由态，不可编辑。"*/);
      }
    }
  }
}
