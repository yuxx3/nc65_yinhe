package nc.itf.uapbd;

import nc.md.model.MetaDataException;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
import nc.vo.uif2.LoginContext;

/**
 * 广电订单接口（外贸、内贸、DC、FC）
 * @author xx
 * @created at Jun 7, 2016,9:50:18 AM
 *
 */
public interface IGdorderMaintain {
	
	/**
	 * 外贸保存
	 * @create by xx at Jun 7, 2016,9:49:11 AM
	 *
	 * @param paramBatchOperateVO
	 * @return
	 * @throws BusinessException
	 */
	public abstract BatchOperateVO batchSaveWM(
			BatchOperateVO paramBatchOperateVO,Object[] keys) throws BusinessException;
	
	/**
	 * 外贸查询
	 * @create by xx at Jun 7, 2016,9:49:16 AM
	 *
	 * @param paramLoginContext
	 * @param paramString
	 * @param paramBoolean
	 * @return
	 * @throws MetaDataException
	 * @throws BusinessException
	 */
	public abstract GDOrderCompStatus[] queryByDataVisibilitySettingWM(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;
	/**
	 * 内贸保存
	 * @create by xx at Jun 7, 2016,9:49:20 AM
	 *
	 * @param paramBatchOperateVO
	 * @return
	 * @throws BusinessException
	 */
	public abstract BatchOperateVO batchSaveNM(
			BatchOperateVO paramBatchOperateVO,Object[]keys) throws BusinessException;
	
	/**
	 * 内贸查询
	 * @create by xx at Jun 7, 2016,9:49:24 AM
	 *
	 * @param paramLoginContext
	 * @param paramString
	 * @param paramBoolean
	 * @return
	 * @throws MetaDataException
	 * @throws BusinessException
	 */
	public abstract GDOrderCompStatus[] queryByDataVisibilitySettingNM(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;
	/**
	 * DC保存
	 * @create by xx at Jun 7, 2016,9:49:29 AM
	 *
	 * @param paramBatchOperateVO
	 * @return
	 * @throws BusinessException
	 */
	public abstract BatchOperateVO batchSaveDC(
			BatchOperateVO paramBatchOperateVO) throws BusinessException;
	
	/**
	 * DC查询
	 * @create by xx at Jun 7, 2016,9:49:33 AM
	 *
	 * @param paramLoginContext
	 * @param paramString
	 * @param paramBoolean
	 * @return
	 * @throws MetaDataException
	 * @throws BusinessException
	 */
	public abstract GDOrderCompStatus[] queryByDataVisibilitySettingDC(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;
	/**
	 * FC保存
	 * @create by xx at Jun 7, 2016,9:49:37 AM
	 *
	 * @param paramBatchOperateVO
	 * @return
	 * @throws BusinessException
	 */
	public abstract BatchOperateVO batchSaveFC(
			BatchOperateVO paramBatchOperateVO) throws BusinessException;
	
	/**
	 * FC查询
	 * @create by xx at Jun 7, 2016,9:49:42 AM
	 *
	 * @param paramLoginContext
	 * @param paramString
	 * @param paramBoolean
	 * @return
	 * @throws MetaDataException
	 * @throws BusinessException
	 */
	public abstract GDOrderCompStatus[] queryByDataVisibilitySettingFC(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException;

}