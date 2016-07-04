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
 * @created at 2016年6月2日,下午1:53:27
 *
 */
public interface IGdOrderFC {
	//批量保存
	public abstract BatchOperateVO batchSave(
			BatchOperateVO paramBatchOperateVO) throws BusinessException;
	
	//查询(不走管控模式)
	public abstract GDOrderCompStatus[] queryByDataVisibilitySetting(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;

	
	 public GDOrderCompStatus[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
}
