package nc.impl.dm.m4804.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.dm.m4804.entity.DelivBillPackVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 根据表体行上的记录合计到表头的相关字段上
 * 
 * @since 6.0
 * @version 2010-11-19 下午02:39:14
 * @author yinyxa
 */
public class TotalInfoCalculate implements IRule<DelivBillAggVO> {

  private UFDouble zero = new UFDouble(0);

  @Override
  public void process(DelivBillAggVO[] vos) {
    for (DelivBillAggVO bill : vos) {
      this.calculateBillBody(bill);
      this.calculatePackBody(bill);
    }
  }

  private void calculateBillBody(DelivBillAggVO bill) {
    DelivBillBVO[] vos = bill.getDelivBillBVO();
    if (vos == null) {
      return;
    }

    UFDouble ntotalnum = this.zero;
    UFDouble ntotalastnum = this.zero;
    UFDouble ntotalmny = this.zero;
    UFDouble ntotalweight = this.zero;
    UFDouble ntotalvolumn = this.zero;
    UFDouble ntotalsignnum = this.zero;
    UFDouble ntotalsignastnum = this.zero;
    UFDouble ntotalsignweight = this.zero;
    UFDouble ntotalsignvolume = this.zero;

    for (DelivBillBVO vo : vos) {
      if (vo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      UFDouble nnumber = vo.getNnum();
      ntotalnum = MathTool.add(ntotalnum, nnumber);

      UFDouble nastnum = vo.getNastnum();
      ntotalastnum = MathTool.add(ntotalastnum, nastnum);

      UFDouble nmoney = vo.getNmoney();
      ntotalmny = MathTool.add(ntotalmny, nmoney);

      UFDouble nweight = vo.getNweight();
      ntotalweight = MathTool.add(ntotalweight, nweight);

      UFDouble nvolumn = vo.getNvolumn();
      ntotalvolumn = MathTool.add(ntotalvolumn, nvolumn);

      UFDouble nsignnum = vo.getNsignnum();
      ntotalsignnum = MathTool.add(ntotalsignnum, nsignnum);

      UFDouble nsignastnum = vo.getNsignastnum();
      ntotalsignastnum = MathTool.add(ntotalsignastnum, nsignastnum);

      UFDouble nsignweight = vo.getNsignweight();
      ntotalsignweight = MathTool.add(ntotalsignweight, nsignweight);

      UFDouble nsignvolume = vo.getNsignvolume();
      ntotalsignvolume = MathTool.add(ntotalsignvolume, nsignvolume);
    }

    DelivBillHVO head = bill.getParentVO();
    if (head.getStatus() == VOStatus.UNCHANGED) {
      head.setStatus(VOStatus.UPDATED);
    }
    // 总主数量
    head.setNtotalnum(ntotalnum);
    // 总数量
    head.setNtotalastnum(ntotalastnum);
    // 总金额
    head.setNtotalmny(ntotalmny);
    // 总重量
    head.setNtotalweight(ntotalweight);
    // 总体积
    head.setNtotalvolume(ntotalvolumn);
    // 总签收主数量
    head.setNtotalsignnum(ntotalsignnum);
    // 总签收数量
    head.setNtotalsignastnum(ntotalsignastnum);
    // 总签收重量
    head.setNtotalsignweight(ntotalsignweight);
    // 总签收体积
    head.setNtotalsignvolume(ntotalsignvolume);

  }

  private void calculatePackBody(DelivBillAggVO bill) {
    DelivBillPackVO[] vos = bill.getDelivBillPackVO();
    if (vos == null) {
      return;
    }

    UFDouble ntotalpacknum = this.zero;
    UFDouble ntotalpackweight = this.zero;
    UFDouble ntotalpackvolume = this.zero;
    UFDouble ntotalsignpacknum = this.zero;
    UFDouble ntotalsignpackweight = this.zero;
    UFDouble ntotalsignpackvolumn = this.zero;

    for (DelivBillPackVO vo : vos) {
      if (vo.getStatus() == VOStatus.DELETED) {
        continue;
      }

      UFDouble npacknum = vo.getNpacknum();
      ntotalpacknum = MathTool.add(ntotalpacknum, npacknum);

      UFDouble npackweight = vo.getNpackweight();
      ntotalpackweight = MathTool.add(ntotalpackweight, npackweight);

      UFDouble npackvolume = vo.getNpackvolume();
      ntotalpackvolume = MathTool.add(ntotalpackvolume, npackvolume);

      UFDouble nsignpacknum = vo.getNsignpacknum();
      ntotalsignpacknum = MathTool.add(ntotalsignpacknum, nsignpacknum);

      UFDouble nsignpackvolumn = vo.getNsignpackvolumn();
      ntotalsignpackvolumn =
          MathTool.add(ntotalsignpackvolumn, nsignpackvolumn);

      UFDouble nsignpackweight = vo.getNsignpackweight();
      ntotalsignpackweight =
          MathTool.add(ntotalsignpackweight, nsignpackweight);
    }

    DelivBillHVO head = bill.getParentVO();
    if (head.getStatus() == VOStatus.UNCHANGED) {
      head.setStatus(VOStatus.UPDATED);
    }

    // 总包装件数
    head.setNtotalpacknum(ntotalpacknum);
    // 总包装重量
    head.setNtotalpackweight(ntotalpackweight);
    // 总包装体积
    head.setNtotalpackvolume(ntotalpackvolume);
    // 总签收包装件数
    head.setNtotalsignpknum(ntotalsignpacknum);
    // 总签收包装重量
    head.setNtotalsignpkweight(ntotalsignpackweight);
    // 总签收包装体积
    head.setNtotalsignpkvolume(ntotalsignpackvolumn);
  }

}
