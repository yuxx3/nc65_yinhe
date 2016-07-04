/**
 * 
 */
package nc.itf.uapbd;

import nc.md.model.MetaDataException;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
import nc.vo.uif2.LoginContext;

/**
 * 内贸接口
 * @author yuxx3
 * @created at 2016��5��31��,����11:05:35
 *
 */
public interface IGdOrderNM {
	//保存
	public abstract BatchOperateVO batchSave(
			BatchOperateVO paramBatchOperateVO) throws BusinessException;
	
	//平台的查询
	public abstract GDOrderCompStatus[] queryByDataVisibilitySetting(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;

	 //查询
	 public GDOrderCompStatus[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
}
