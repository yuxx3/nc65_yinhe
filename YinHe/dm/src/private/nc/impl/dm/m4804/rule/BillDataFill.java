package nc.impl.dm.m4804.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.dm.m4804.entity.DelivBillPackVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class BillDataFill implements IRule<DelivBillAggVO> {

  @Override
  public void process(DelivBillAggVO[] vos) {
    for (DelivBillAggVO bill : vos) {
      DelivBillHVO hvo = bill.getParentVO();
      // 补全表头
      this.fillHVO(hvo);
      // 补全表体
      if ((bill.getDelivBillBVO() != null)
          && (bill.getDelivBillBVO().length > 0)) {
        this.fillBody(bill.getDelivBillBVO(), hvo);
      }
      if ((null != bill.getDelivBillPackVO())
          && (bill.getDelivBillPackVO().length > 0)) {
        this.fillPackBody(bill.getDelivBillPackVO(), hvo);
      }
    }
  }

  private void fillBody(DelivBillBVO[] delivBillPackVO, DelivBillHVO hvo) {
    for (DelivBillBVO bvo : delivBillPackVO) {
      if (PubAppTool.isNull(bvo.getPk_group())) {
        bvo.setPk_group(hvo.getPk_group());
      }
      if (bvo.getDbilldate() == null) {
        bvo.setDbilldate(hvo.getDbilldate());
      }
      if (PubAppTool.isNull(bvo.getPk_org())) {
        bvo.setPk_org(hvo.getPk_org());
      }
    }
  }

  private void fillHVO(DelivBillHVO hvo) {
    if (hvo.getPk_group() == null) {
      hvo.setPk_group(BSContext.getInstance().getGroupID());
    }
    // 为运输单表头填充组织最新版本号
    if (hvo.getPk_org_v() == null) {
      String pk_org_v = null;
      pk_org_v = OrgUnitPubService.getNewVIDByOrgID(hvo.getPk_org());
      hvo.setPk_org_v(pk_org_v);
    }
  }

  private void fillPackBody(DelivBillPackVO[] childrenVO, DelivBillHVO hvo) {
    for (DelivBillPackVO bvo : childrenVO) {
      if (PubAppTool.isNull(bvo.getPk_group())) {
        bvo.setPk_group(hvo.getPk_group());
      }
      if (PubAppTool.isNull(bvo.getPk_org())) {
        bvo.setPk_org(hvo.getPk_org());
      }
    }
  }

}
