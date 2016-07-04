package nc.bs.dm.m4804;

import java.util.Map;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.dm.m4804trantype.entity.M4804TranTypeVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.res.billtype.DMBillType;
import nc.vo.scmpub.rule.TrafficOrgEnableCheckRule;

import nc.bs.dm.m4804.pub.CachTranTypeUitl;
import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.bs.scmpub.rule.DeleteTempDataBeforeRule;

import nc.impl.dm.m4804.rule.BillDataCheck;
import nc.impl.dm.m4804.rule.BillDataFill;
import nc.impl.dm.m4804.rule.BillWeightCheck;
import nc.impl.dm.m4804.rule.CheckUnique;
import nc.impl.dm.m4804.rule.CurrencyInfoCheck;
import nc.impl.dm.m4804.rule.DelivNumAddOperator;
import nc.impl.dm.m4804.rule.RouteInfoCalculate;
import nc.impl.dm.m4804.rule.SetAddAuditInfoRule;
import nc.impl.dm.m4804.rule.TotalInfoCalculate;
import nc.impl.dm.m4804.rule.TrantypeValidation;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;

public class DelivBillInsertBP {

  public DelivBillAggVO[] insert(DelivBillAggVO[] bills) {

    // 新增前规则
    this.processBeforeRule(bills);
    BillInsert<DelivBillAggVO> billinsert = new BillInsert<DelivBillAggVO>();
    DelivBillAggVO[] ret = billinsert.insert(bills);
    // 新增后规则
    this.processAfterRule(ret);
    return ret;
  }

  private void insertBillCode(DelivBillAggVO[] bills) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(DMBillType.DelivBill.getCode(),
            DelivBillHVO.VBILLCODE, DelivBillHVO.PK_GROUP, DelivBillHVO.PK_ORG,
            DelivBillHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.createBillCode(bills);
  }

  private void processAfterRule(DelivBillAggVO[] bills) {
    // 单据号唯一性校验
    IRule<DelivBillAggVO> rule = new CheckUnique();
    rule.process(bills);
  }

  private void processBeforeRule(DelivBillAggVO[] bills) {
    Map<String, M4804TranTypeVO> tranTypeVos =
        new CachTranTypeUitl().getTranTypesByBills(bills);

    // 生成单据号
    this.insertBillCode(bills);

    // 根据表体合计表头字段
    IRule<DelivBillAggVO> rule = new TotalInfoCalculate();
    rule.process(bills);

    // 填充数据
    rule = new BillDataFill();
    rule.process(bills);
    // 数据检查
    rule = new BillDataCheck(tranTypeVos);
    rule.process(bills);
    // 运输单交易类型校验
    rule = new TrantypeValidation(tranTypeVos);
    rule.process(bills);
    // 超重量检查
    //rule = new BillWeightCheck(tranTypeVos);
    //rule.process(bills);

    // 超体积检查
    //rule = new BillVolumeCheck(tranTypeVos);
    //rule.process(bills);

    // 运输路线相关信息的处理
    rule = new RouteInfoCalculate();
    rule.process(bills);

    // 本位币种，表体包装信息和货物信息的结算财务组织和表头的物流组织多对应的本位币种相同。
    rule = new CurrencyInfoCheck();
    rule.process(bills);

    // 设置新增时的审计信息：创建人、制单人、制单时间
    rule = new SetAddAuditInfoRule();
    rule.process(bills);

    // 新增运输单数量回写上游单据
    rule = new DelivNumAddOperator();
    rule.process(bills);

    // 主组织停用检查规则
    rule = new TrafficOrgEnableCheckRule<DelivBillAggVO>();
    rule.process(bills);

    // 保存删除暂存数据
    IRule<AbstractBill> rule1 = new DeleteTempDataBeforeRule();
    rule1.process(bills);

  }

}
