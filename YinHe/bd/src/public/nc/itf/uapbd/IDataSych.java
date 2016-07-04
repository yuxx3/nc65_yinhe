/**
 * 
 */
package nc.itf.uapbd;

import nc.vo.pub.BusinessException;

/**
 * 数据同步-同步物料、同步BOM
 * @author yuxx3
 * @created at 2016年4月14日,下午4:29:55
 *
 */
public interface IDataSych {
	/**
	 * 同步物料
	 * @create by yuxx3 at 2016年4月14日,下午4:31:03
	 *
	 * @throws BusinessException
	 */
	public void MateSych()throws BusinessException;
	/**
	 * 同步BOM
	 * @create by yuxx3 at 2016年4月14日,下午4:31:29
	 *
	 * @throws BusinessException
	 */
	public void BomSych()throws BusinessException;

}
