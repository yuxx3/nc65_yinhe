package nc.impl.dm.m4804.rule;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillPackVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 运输单本位币种校验：</br>
 * <b>表体包装信息和货物信息的结算财务组织和表头的物流组织多对应的本位币种相同。</b>
 * 
 * @since 6.0
 * @version 2011-2-17 下午03:06:56
 * @author 高扬
 */
public class CurrencyInfoCheck implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] vos) {
    for (DelivBillAggVO vo : vos) {
      this.checkCurrencyInfo(vo);
    }
  }

  private void checkCurrencyInfo(DelivBillAggVO vo) {
    // 用于存放表体包装信息、货物信息、表体的组织id信息
    Set<String> set = new HashSet<String>();
    DelivBillBVO[] bvos = vo.getDelivBillBVO();
    DelivBillPackVO[] pvos = vo.getDelivBillPackVO();
    if ((bvos != null) && (bvos.length > 0)) {
      for (DelivBillBVO bvo : bvos) {
        if (bvo.getCsettlefinorgid() != null) {
          set.add(bvo.getCsettlefinorgvid());
        }
      }
    }
    if ((pvos != null) && (pvos.length > 0)) {
      for (DelivBillPackVO pvo : pvos) {
        if (pvo.getCsettlefinorgvid() != null) {
          set.add(pvo.getCsettlefinorgvid());
        }
      }
    }
    String headOrg = vo.getParentVO().getPk_org_v();
    set.add(headOrg);
    if (set.size() > 1) {
      // 用于存放表体包装信息、货物信息、表体的组织id信息
      Set<String> currtypeSet = new HashSet<String>();
      String currtype = null;
      for (String str : set) {
        currtype = CurrencyInfo.getLocalCurrtypeByOrgID(str);
        currtypeSet.add(currtype);
        if (currtypeSet.size() > 1) {
          String message =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4014001_0", "04014001-0342")
          /*@res "包装信息、货物信息的结算财务组织和表头的物流组织对应的本位币种应相同，请检查。"*/;
          ExceptionUtils.wrappBusinessException(message);
        }
      }
    }
  }
}
