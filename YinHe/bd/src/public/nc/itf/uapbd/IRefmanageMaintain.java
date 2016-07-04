package nc.itf.uapbd;

import nc.md.model.MetaDataException;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.cust.refmanage.RefManageVO;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.uif2.LoginContext;

/**
 * 批量保存和查询接口
 * 
 * @author xx
 * 
 */
public interface IRefmanageMaintain {

	/**
	 * 批量保存
	 * 
	 * @param paramBatchOperateVO
	 * @return
	 * @throws BusinessException
	 */
	public abstract BatchOperateVO batchSave(
			BatchOperateVO paramBatchOperateVO) throws BusinessException;

	/**
	 * 查询(不走管控模式)
	 * @param paramLoginContext
	 * @param paramString
	 * @param paramBoolean
	 * @return
	 * @throws MetaDataException
	 * @throws BusinessException
	 */
	public abstract RefManageVO[] queryByDataVisibilitySetting(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;

	public RefManageVO[] query(IQueryScheme queryScheme)
			throws BusinessException, Exception;
}