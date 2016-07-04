package nc.itf.uapbd;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.ui.emm.exceptionhandle.model.exception_config;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.uapbd.orderarearela.Orderrelation;

public interface IOrderarearelaMaintain extends ISmartService{

	 public Orderrelation[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
	 
	 public Orderrelation[] getRelationByTypeAndCode(String type,String code)
	  throws BusinessException,Exception;
}