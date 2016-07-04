/**
 * 
 */
package nc.ui.dc.gdorder.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.uapbd.IGdOrderDC;
import nc.itf.uapbd.IGdOrderNM;
import nc.itf.uapbd.IGdorderMaintain;
import nc.ui.ambd.base.service.BatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.uif2.LoginContext;

/**	DC服务
 * @author yuxx
 * @created at 2016��5��30��,����3:06:56
 *
 */
public class GdorderDCService extends BatchAppModelService {

	@Override
	public BatchOperateVO batchSave(BatchOperateVO arg0) throws Exception {
		return getIGdorderSrv().batchSaveDC(arg0);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return getIGdorderSrv().queryByDataVisibilitySettingDC(context, null,false);
	}


	@Override
	public Object[] queryByWhereSql(LoginContext paramLoginContext, String paramString)
			throws Exception {
		return getIGdorderSrv().queryByDataVisibilitySettingDC(paramLoginContext, paramString, false);
	}
	
	
	private IGdorderMaintain  getIGdorderSrv(){
		return (IGdorderMaintain) NCLocator.getInstance().lookup(IGdorderMaintain.class);
	}
}
