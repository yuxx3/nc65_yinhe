package nc.ui.uapbd.refmanage.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.uapbd.IRefmanageMaintain;
import nc.ui.ambd.base.service.BatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.uif2.LoginContext;

/**
 * 参照维护服务
 * @author xx
 * @created at 2016年4月8日,上午11:21:40
 *
 */
public class RefManageService extends BatchAppModelService {

	@Override
	public BatchOperateVO batchSave(BatchOperateVO arg0) throws Exception {
		return getIRefManageSrv().batchSave(arg0);
	}

	
	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return getIRefManageSrv().queryByDataVisibilitySetting(context, null,false);
	}


	@Override
	public Object[] queryByWhereSql(LoginContext paramLoginContext, String paramString)
			throws Exception {
		return getIRefManageSrv().queryByDataVisibilitySetting(paramLoginContext, paramString, false);
	}
	
	private IRefmanageMaintain getIRefManageSrv(){
		return (IRefmanageMaintain) NCLocator.getInstance().lookup(IRefmanageMaintain.class);
	}

}
