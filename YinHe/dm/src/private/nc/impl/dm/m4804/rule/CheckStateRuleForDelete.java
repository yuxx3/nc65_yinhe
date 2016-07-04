package nc.impl.dm.m4804.rule;

import nc.vo.dm.enumeration.FstatusFlag;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 检查单据状态
 * 
 * @since 6.0
 * @version 2010-11-22 下午03:21:15
 * @author yinyxa
 */
public class CheckStateRuleForDelete implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] vos) {
    try {
      for (DelivBillAggVO vo : vos) {
        DelivBillHVO hvo = vo.getParentVO();
        int flag = hvo.getFstatusflag().intValue();
        // 修改检查
        if (ValueUtils.getInt(FstatusFlag.FREE.value()) != flag) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4014001_0", "04014001-0340")
          /*@res "单据为非自由态，不可删除。"*/);
        }
        // 校验运输单是否存在下游任务单，因为是调用批删除，这里逐条调用校验，
        if (hvo.getCdelivbill_hid() != null) {
          UFBoolean bmissionbillflag = hvo.getBmissionbillflag();
          boolean result = false;
          if (bmissionbillflag != null
              && bmissionbillflag.equals(UFBoolean.TRUE)) {
            result = true;
          }
          if (result) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4014001_0", "04014001-0080")
            /*@res "单据存在下游任务单，不可删除。"*/);
          }
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
