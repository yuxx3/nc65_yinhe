package nc.bs.dm.m4804.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804trantype.entity.M4804TranTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.itf.dm.m4804trantype.IM4804TranTypeService;

import nc.bs.framework.common.NCLocator;

/**
 * 运输单多处需要根据交易类型校验相关信息，该类用于根据聚合vo数组得到其对应的校验类型信息
 * 
 * @since 6.0
 * @version 2012-3-27 下午4:36:05
 * @author 高扬
 */
public class CachTranTypeUitl {

  /**
   * 根据运输单聚合vo数组查询需要需要用到的校验类型信息
   * 
   * @param vos 运输单聚合vo数组
   * @return Map<String, M4804TranTypeVO>
   *         key:运输类型code， value：对应的交易类型信息
   */
  public Map<String, M4804TranTypeVO> getTranTypesByBills(DelivBillAggVO[] vos) {
    Map<String, M4804TranTypeVO> result = null;
    Set<String> ids = new HashSet<String>();
    String pk_group = vos[0].getParentVO().getPk_group();
    for (DelivBillAggVO vo : vos) {
      if (vo.getParentVO().getVtrantypecode() == null) {
        continue;
      }
      ids.add(vo.getParentVO().getVtrantypecode());
    }
    try {
      result =
          NCLocator.getInstance().lookup(IM4804TranTypeService.class)
              .queryTranType(ids.toArray(new String[ids.size()]), pk_group);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;

  }
}
