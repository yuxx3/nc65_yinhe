package nc.impl.dm.m4804.rule;

import java.util.Map;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.dm.m4804.entity.DelivBillPackVO;
import nc.vo.dm.m4804trantype.entity.M4804TranTypeVO;
import nc.vo.dm.m4804trantype.enumeration.FAllocateScopeFlag;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.util.VOFieldLengthChecker;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 运输单的数据检查
 * <ul>
 * <li>数据业务校验</li>
 * <li>极限值处理</li>
 * </ul>
 * 
 * @since 6.0
 * @version 2010-11-19 下午02:50:07
 * @author yinyxa
 */
public class BillDataCheck implements IRule<DelivBillAggVO> {

  private Map<String, M4804TranTypeVO> tranTypeVos;

  /**
   * 初始化参数中传入交易类型vo
   * 
   * @param tranTypeVo
   */
  public BillDataCheck(Map<String, M4804TranTypeVO> tranTypeVo) {
    super();
    this.tranTypeVos = tranTypeVo;
  }

  @Override
  public void process(DelivBillAggVO[] vos) {
    for (DelivBillAggVO bill : vos) {
      this.processBillDataCheck(bill);
    }
    // 极限值校验
    VOFieldLengthChecker.checkVOFieldsLength(vos);
  }

  private void checkBillBody(DelivBillHVO header, DelivBillBVO[] items) {
    try {
      String tranTypeCode = header.getVtrantypecode();
      M4804TranTypeVO typeVO = this.tranTypeVos.get(tranTypeCode);
      if (items == null || items.length == 0) {
        if (FAllocateScopeFlag.INV.equalsValue(typeVO.getFallocatescopeflag())) {
          String message =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4014001_0", "04014001-0328")
          /* @res "当前交易类型要求运费计算后分摊目标为货物行，必须要有货物行信息"*/;
          ExceptionUtils.wrappBusinessException(message);
        }
        return;
      }
      int index = 1;
      for (DelivBillBVO item : items) {
        this.checkBillBodyRow(item, index++);

      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void checkBillBodyRow(DelivBillBVO item, int i) {
    if (item.getNnum() == null) {
      String message =
          NCLangResOnserver.getInstance().getStrByID("4014001_0",
              "04014001-0024", null, new String[] {
                Integer.toString(i)
              })/* 表体第{0}行主数量不能为空 */;
      ExceptionUtils.wrappBusinessException(message);
    }
    // if (item.getCsendaddrdocid() == null) {
    // String message =
    // NCLangResOnserver.getInstance().getStrByID("4014001_0",
    // "04014001-0025", null, new String[] {
    // Integer.toString(i)
    // })/* 表体第{0}行发货地点不能为空 */;
    // ExceptionUtils.wrappBusinessException(message);
    // }
    // if (item.getCreceiveaddrdocid() == null) {
    // String message =
    // NCLangResOnserver.getInstance().getStrByID("4014001_0",
    // "04014001-0026", null, new String[] {
    // Integer.toString(i)
    // })/* 表体第{0}行收货地点不能为空 */;
    // ExceptionUtils.wrappBusinessException(message);
    // }
    String ctakefeeid = item.getCtakefeeid();
    String ccosignid = item.getCcosignid();
    this.checkTakeFeerAndCosigner(ctakefeeid, ccosignid);
  }

  private void checkPackBody(DelivBillHVO header, DelivBillPackVO[] items) {
    try {
      String tranTypeCode = header.getVtrantypecode();
      M4804TranTypeVO typeVO = this.tranTypeVos.get(tranTypeCode);
      if (items == null || items.length == 0) {
        if (FAllocateScopeFlag.PACK.equalsValue(typeVO.getFallocatescopeflag())) {
          String message =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4014001_0", "04014001-0329")
          /* @res "当前交易类型要求运费计算后分摊目标为包装行，必须要有包装行信息"*/;
          ExceptionUtils.wrappBusinessException(message);
        }
        return;
      }
      for (DelivBillPackVO item : items) {
        String ctakefeeid = item.getCtakefeeid();
        String ccosignid = item.getCcosignid();
        this.checkTakeFeerAndCosigner(ctakefeeid, ccosignid);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 校验委托单位和运费承担单位
   * 
   * @param ctakefeeid
   * @param ccosignid
   */
  private void checkTakeFeerAndCosigner(String ctakefeeid, String ccosignid) {
    if (ccosignid != null && ctakefeeid == null) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
              "04014001-0330")/* @res "委托单位非空时必须要有运费承担单位"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    else if (ctakefeeid != null && ccosignid == null) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
              "04014001-0331")/* @res "运费承担单位非空时必须要有委托单位"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
  }

  private void checkTranType(DelivBillAggVO bill) {
    String tranTypeCode = bill.getParentVO().getVtrantypecode();
    M4804TranTypeVO tranType = this.tranTypeVos.get(tranTypeCode);
    Integer allocatescopeflag = tranType.getFallocatescopeflag();

    if (FAllocateScopeFlag.INV.equalsValue(allocatescopeflag)) {
      if (bill.getDelivBillBVO() == null || bill.getDelivBillBVO().length == 0) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
                "04014001-0332")/* @res "交易类型运费分单目标位货物行，请录入货物行信息！"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
    }
    else if (FAllocateScopeFlag.PACK.equalsValue(allocatescopeflag)) {
      if (bill.getDelivBillPackVO() == null
          || bill.getDelivBillPackVO().length == 0) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
                "04014001-0333")/* @res "交易类型运费分单目标位包装行，请录入包装行信息！"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
    }
  }

  private void processBillDataCheck(DelivBillAggVO bill) {
    DelivBillHVO header = bill.getParentVO();
    UFDate dbilldate = header.getDbilldate();
    UFDate ddelivdate = header.getDdelivdate();
    if (dbilldate == null) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
              "04014001-0334")/* @res "制单日期不能为空"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    else if (ddelivdate == null) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
              "04014001-0335")/* @res "运输日期不能为空"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    else if (ddelivdate.beforeDate(dbilldate)) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
              "04014001-0336")/* @res "运输日期不能小于单据日期"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    // else if (header.getTdelivtime() != null
    // && !ddelivdate.isSameDate(header.getTdelivtime().getDate())) {
    // String message = "运输时间的日期和运输日期不等";
    // ExceptionUtils.wrappBusinessException(message);
    // }
    else if (PubAppTool.isNull(header.getCtrantypeid())) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
              "04014001-0337")/* @res "运输类型不能为空"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    // else if (header.getCrouteid() == null) {
    // String message =
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
    // "04014001-0338")/* @res "运输路线不能为空"*/;
    // ExceptionUtils.wrappBusinessException(message);
    // }
    else if (header.getCsendtypeid() == null) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
              "04014001-0339")/* @res "运输方式不能为空"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    this.checkTranType(bill);

    DelivBillBVO[] billItems = bill.getDelivBillBVO();
    this.checkBillBody(header, billItems);
    DelivBillPackVO[] packItems = bill.getDelivBillPackVO();
    this.checkPackBody(header, packItems);
  }

}
