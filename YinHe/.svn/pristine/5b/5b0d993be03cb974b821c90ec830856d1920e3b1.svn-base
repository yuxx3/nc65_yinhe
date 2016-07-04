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
 * @author yuxx3
 * @created at 2016.6.2,1:53:00
 *
 */
public interface IGdOrderDC {
	//批量保存
	public abstract BatchOperateVO batchSave(
			BatchOperateVO paramBatchOperateVO) throws BusinessException;
	
	//平台查询
	public abstract GDOrderCompStatus[] queryByDataVisibilitySetting(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;

	//查询
	 public GDOrderCompStatus[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
}
