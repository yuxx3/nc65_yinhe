/**
 * 
 */
package nc.ui.nm.gdorder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uapbd.IGdOrderNM;
import nc.itf.uapbd.IGdorderMaintain;
import nc.ui.ambd.base.service.BatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
import nc.vo.uif2.LoginContext;

/**	内贸服务
 * @author yuxx
 * @created at 2016��5��30��,����3:06:56
 *
 */
public class GdorderNMService extends BatchAppModelService {

	@Override
	public BatchOperateVO batchSave(BatchOperateVO arg0) throws Exception {
		Object[]keys = getTypeKeys("1001ZZ10000000017ROV");
		return getIGdorderSrv().batchSaveNM(arg0,keys);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return getIGdorderSrv().queryByDataVisibilitySettingNM(context, null,false);
	}


	@Override
	public Object[] queryByWhereSql(LoginContext paramLoginContext, String paramString)
			throws Exception {
		return getIGdorderSrv().queryByDataVisibilitySettingNM(paramLoginContext, paramString, false);
	}
	
	
	private IGdorderMaintain  getIGdorderSrv(){
		return (IGdorderMaintain) NCLocator.getInstance().lookup(IGdorderMaintain.class);
	}
	/**
	 * 获取对应贸易类型下的字段
	 * 
	 * @create by xx at Jun 7, 2016,3:32:16 PM
	 * 
	 * @param type
	 * @return
	 */
	private Object[] getTypeKeys(String type) {
		List<String> keys = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : GDOrderCompStatus.pawds
				.entrySet()) {
			if (entry.getKey().equals(type)) {
				String key = (String)entry.getValue();
				keys.add(key);
			}
		}
		return keys.toArray();
	}
}
