package nc.impl.dm.m4804.rule.util;

import java.util.ArrayList;
import java.util.List;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.DelivBillPackVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmf.dm.route.pub.IRoute;
import nc.vo.scmf.dm.route.pub.TrafficTaskVO;

/**
 * 运输路线校验服务：为校验服务提供获取参数的接口
 * 
 * @since 6.0
 * @version 2011-6-9 下午03:59:46
 * @author 高扬
 */
public class RouteMessageUtil implements IRoute {
  private DelivBillAggVO delivBillvo;

  // private int flag;

  public RouteMessageUtil(DelivBillAggVO vo) {
    super();
    this.delivBillvo = vo;
    // this.flag = flag;
  }

  @Override
  public List<TrafficTaskVO> getAddrDoc() throws BusinessException {
    if (this.delivBillvo == null) {
      return null;
    }
    List<TrafficTaskVO> result = new ArrayList<TrafficTaskVO>();
    if ((this.delivBillvo.getChildrenVO() != null)
        && (this.delivBillvo.getChildrenVO().length > 0)) {
      result.addAll(this.getAddDocFromBody(this.delivBillvo.getDelivBillBVO()));
    }
    if ((this.delivBillvo.getDelivBillPackVO() != null)
        && (this.delivBillvo.getDelivBillPackVO().length > 0)) {
      result.addAll(this.getAddDocFromPack(this.delivBillvo
          .getDelivBillPackVO()));
    }
    return result;
  }

  @Override
  public String getRouteId() {
    if (this.delivBillvo == null) {
      return null;
    }
    return this.delivBillvo.getParentVO().getCrouteid();
  }

  private List<TrafficTaskVO> getAddDocFromBody(DelivBillBVO[] childrenVO) {
    List<TrafficTaskVO> ref = new ArrayList<TrafficTaskVO>();
    TrafficTaskVO trafficTask = null;
    for (DelivBillBVO vo : childrenVO) {
      trafficTask = new TrafficTaskVO();
      trafficTask.setSendAddrId(vo.getCsendaddrdocid());
      trafficTask.setReceiveAddrId(vo.getCreceiveaddrdocid());
      ref.add(trafficTask);
    }
    return ref;
  }

  private List<TrafficTaskVO> getAddDocFromPack(DelivBillPackVO[] delivBillBVO) {
    List<TrafficTaskVO> ref = new ArrayList<TrafficTaskVO>();
    TrafficTaskVO trafficTask = null;
    for (DelivBillPackVO vo : delivBillBVO) {
      trafficTask = new TrafficTaskVO();
      trafficTask.setSendAddrId(vo.getCsendaddrdocid());
      trafficTask.setReceiveAddrId(vo.getCreceiveaddrdocid());
      ref.add(trafficTask);
    }
    return ref;
  }
}
