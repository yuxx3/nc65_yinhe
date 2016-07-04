package nc.impl.dm.m4804.rule;

import java.util.HashMap;
import java.util.Map;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.dm.m4804trantype.entity.M4804TranTypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 运输单交易类型校验</br>
 * 运输单的交易类型又是否支持自制的标志。自制的单子不能使用不支持自制的交易类型。
 * 
 * @since 6.0
 * @version 2011-4-20 下午04:01:01
 * @author 高扬
 */
public class TrantypeValidation implements IRule<DelivBillAggVO> {

  private Map<String, M4804TranTypeVO> tranTypeMap =
      new HashMap<String, M4804TranTypeVO>();

  /**
   * 构造子
   * 
   * @param tranTypeMap
   */
  public TrantypeValidation(Map<String, M4804TranTypeVO> tranTypeMap) {
    super();
    this.tranTypeMap = tranTypeMap;
  }

  @Override
  public void process(DelivBillAggVO[] vos) {
    try {
      for (DelivBillAggVO vo : vos) {
        this.checkTranType(vo);
        this.checkBadvfeeflag(vo);
        this.checkbodysettlefinorg(vo);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 
   * @param tranTypeMap
   */
  public void setTranTypeMap(Map<String, M4804TranTypeVO> tranTypeMap) {
    this.tranTypeMap = tranTypeMap;
  }

  private void checkTranType(DelivBillAggVO vo) {
    DelivBillHVO head = vo.getParentVO();
    // 默认为拉单，当表体中有条记录没有来源id时认为是自制
    boolean addManual = false;
    // 项目分单货物行
    DelivBillBVO[] bodys = vo.getDelivBillBVO();
    if (bodys != null && bodys.length > 0) {
      for (DelivBillBVO body : bodys) {
        if (PubAppTool.isNull(body.getCfirstid())) {
          addManual = true;
          break;
        }
      }
    }
    // 包装行信息只能通过自制生成，如果货物行没有信息，只有包装行有的话，单据肯定是自制的
    boolean hasBodyBill =
        vo.getDelivBillBVO() != null && vo.getDelivBillBVO().length > 0;
    boolean hasBodyPack =
        vo.getDelivBillPackVO() != null && vo.getDelivBillPackVO().length > 0;
    if (!hasBodyBill && hasBodyPack) {
      addManual = true;
    }
    // 自制的单据
    if (addManual) {
      String ctrantypeCode = head.getVtrantypecode();
      M4804TranTypeVO tranType = this.tranTypeMap.get(ctrantypeCode);
      // 自制单据时选择的交易类型不支持自制
      if (tranType.getBcanselfmadeflag() != null
          && tranType.getBcanselfmadeflag() == UFBoolean.FALSE) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
                "04014001-0343")/*@res "所选择的交易类型不支持自制！"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
    }
  }

  /**
   * 交易类型为进行应付运费计算的运输单，不允许为代垫费用
   * 
   * @param vo 运输单
   */
  private void checkBadvfeeflag(DelivBillAggVO vo) {
    DelivBillHVO head = vo.getParentVO();
    String ctrantypeCode = head.getVtrantypecode();
    M4804TranTypeVO tranType = this.tranTypeMap.get(ctrantypeCode);
    if (tranType.getBcalculateapfeeflag().booleanValue()
        && head.getBadvfeeflag().booleanValue()) {
      String msg =
          NCLangResOnserver.getInstance().getStrByID("4014001_0",
              "04014001-0104")/*所选交易类型为进行应付运费计算，不允许代垫费用！*/;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  /**
   * 所选交易类型为进行应付运费计算，表体结算财务组织不能为空
   * 
   * @param vo 运输单
   */
  private void checkbodysettlefinorg(DelivBillAggVO vo) {
    DelivBillHVO head = vo.getParentVO();
    String ctrantypeCode = head.getVtrantypecode();
    M4804TranTypeVO tranType = this.tranTypeMap.get(ctrantypeCode);
    if (tranType.getBcalculateapfeeflag().booleanValue()) {
      for (DelivBillBVO bvo : vo.getDelivBillBVO()) {
        String csettlefinorgid = bvo.getCsettlefinorgid();
        if (csettlefinorgid == null) {
          String msg =
              NCLangResOnserver.getInstance().getStrByID("4014001_0",
                  "04014001-0105")/*所选交易类型为进行应付运费计算，表体结算财务组织不能为空！*/;
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
    }
  }
}
