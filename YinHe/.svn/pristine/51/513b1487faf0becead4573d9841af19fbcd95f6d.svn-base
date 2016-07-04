/**
 * 
 */
package nc.ui.uapbd.refmanage.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.uapbd.IDataSych;
import nc.itf.uapbd.IDataSychSrv;
import nc.vo.pub.BusinessException;

/**
 * 数据同步服务
 * @author yuxx3
 * @created at 2016年4月14日,下午4:33:20
 *
 */
public class DataSychSrv implements IDataSychSrv {

	@Override
	public void mateSych() throws BusinessException {
		getSrv().MateSych();
	}


	@Override
	public void bomSych() throws BusinessException {
		getSrv().BomSych();
	}
	
	private IDataSych getSrv(){
		return NCLocator.getInstance().lookup(IDataSych.class);
	}
	

}
