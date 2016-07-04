/**
 * 
 */
package nc.itf.uapbd;

import nc.vo.pub.BusinessException;

/**
 * 数据同步服务
 * @author yuxx3
 * @created at 2016年4月14日,下午4:34:26
 *
 */
public interface IDataSychSrv {
	/**
	 * 同步物料
	 * @create by yuxx3 at 2016年4月14日,下午4:35:52
	 *
	 * @throws BusinessException
	 */
	public void mateSych()throws BusinessException;
	/**
	 * 同步BOM
	 * @create by yuxx3 at 2016年4月14日,下午4:36:15
	 *
	 * @throws BusinessException
	 */
	public void bomSych()throws BusinessException;

}
