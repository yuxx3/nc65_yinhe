package nc.impl.dm.m4804.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillHVO;
import nc.vo.dm.m4804.entity.DelivBillPackVO;
import nc.vo.dm.m4804trantype.entity.M4804TranTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.dm.route.pub.BillTrafficTask;
import nc.vo.scmf.dm.route.pub.TrafficTaskVO;

import nc.pubitf.scmf.dm.route.IRouteCalculateMileageService;
import nc.pubitf.scmf.dm.route.IRouteValidationService;

import nc.bs.framework.common.NCLocator;

import nc.impl.dm.m4804.rule.util.RouteMessageUtil;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 运输路线相关信息的处理
 * 
 * @since 6.0
 * @version 2010-11-22 下午04:15:09
 * @author yinyxa
 */
public class RouteInfoCalculate implements IRule<DelivBillAggVO> {

  private Map<String, M4804TranTypeVO> tranTypeMap =
      new HashMap<String, M4804TranTypeVO>();

  public Map<String, M4804TranTypeVO> getTranTypeMap() {
    return this.tranTypeMap;
  }

  @Override
  public void process(DelivBillAggVO[] vos) {
    IRouteValidationService routeValidationService =
        NCLocator.getInstance().lookup(IRouteValidationService.class);

    IRouteCalculateMileageService routeCalculateMeleageService =
        NCLocator.getInstance().lookup(IRouteCalculateMileageService.class);
    RouteMessageUtil util;

    try {
      for (DelivBillAggVO vo : vos) {
        // 项目分摊目标，根据该值选择重货物行还是包装行上取信息
        // int flag =
        // this.getTranTypeMap().get(vo.getParentVO().getVtrantypecode())
        // .getFallocatescopeflag().intValue();
        util = new RouteMessageUtil(vo);
        routeValidationService.check(util);
        BillTrafficTask task = routeCalculateMeleageService.calculate(util);
        if (null != task) {
          // 将计算结果返回到表头
          this.fillHeadValue(vo, task);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void setTranTypeMap(Map<String, M4804TranTypeVO> tranTypeMap) {
    this.tranTypeMap = tranTypeMap;
  }

  private void fillHeadValue(DelivBillAggVO vo, BillTrafficTask task) {
    List<TrafficTaskVO> trafficTasks = task.getTrafficTasks();
    TrafficTaskVO trafficTaskVO = null;
    DelivBillHVO head = vo.getParentVO();
    DelivBillBVO[] bodys = vo.getDelivBillBVO();
    head.setNfarestmile(task.getFarthestMileage());
    head.setCfarestaddrdocid(task.getFarthestReceiveAddrId());
    head.setNfatestspecialmile1(task.getFarthestSpecialMileage1());
    head.setNfatestspecialmile2(task.getFarthestSpecialMileage2());
    head.setNfatestspecialmile3(task.getFarthestSpecialMileage3());
    head.setNfatestspecialmile4(task.getFarthestSpecialMileage4());
    head.setCshortestaddrdocid(task.getNearSendAddrId());
    int index = 0;
    if (bodys != null && bodys.length > 0) {
      for (int i = 0; i < bodys.length; i++) {
        trafficTaskVO = trafficTasks.get(index++);
        bodys[i].setNroutemile(trafficTaskVO.getMaileage());
        bodys[i].setNspecialmile1(trafficTaskVO.getSpecialMileage1());
        bodys[i].setNspecialmile2(trafficTaskVO.getSpecialMileage2());
        bodys[i].setNspecialmile3(trafficTaskVO.getSpecialMileage3());
        bodys[i].setNspecialmile4(trafficTaskVO.getSpecialMileage4());
      }
    }
    DelivBillPackVO[] packs = vo.getDelivBillPackVO();
    if (packs != null && packs.length > 0) {
      for (int i = 0; i < packs.length; i++) {
        trafficTaskVO = trafficTasks.get(index++);
        packs[i].setNroutemile(trafficTaskVO.getMaileage());
        packs[i].setNspecialmile1(trafficTaskVO.getSpecialMileage1());
        packs[i].setNspecialmile2(trafficTaskVO.getSpecialMileage2());
        packs[i].setNspecialmile3(trafficTaskVO.getSpecialMileage3());
        packs[i].setNspecialmile4(trafficTaskVO.getSpecialMileage4());
      }
    }

  }
}
