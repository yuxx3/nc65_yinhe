/**
 * 
 */
package nc.ui.gdorder.tools;

import javax.swing.JOptionPane;

import nc.bs.framework.common.NCLocator;
import nc.itf.bd.defdoc.IDefdocQryService;
import nc.itf.uapbd.IOrderarearelaMaintain;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.pub.BusinessException;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
import nc.vo.uapbd.orderarearela.Orderrelation;

/**
 * 密码校验
 * 
 * @author yuxx3
 * @created at 2016年6月4日,下午3:29:50
 * 
 */
public class CheckPawd {

	/**
	 * 对应字段的密码校验 首先判断是否存在静态密码值， 如果存在再校验密码是否正确， 正确则不需要弹框，否则弹框输入密码校验
	 * 
	 * @create by yuxx3 at 2016年6月4日,下午3:30:44
	 * 
	 * @param 贸易类型
	 * @param 字段
	 * @return
	 */
	public boolean isOK(String type, String key) {
		try {
			// 获取字段所属区域
			Orderrelation rela = getRelationService().getRelationByTypeAndCode(
					type, key)[0];
			// 获取对应区域的助记码-密码
			DefdocVO defqy = getDefDocService().queryDefdocByPk(
					new String[] { rela.vdef1 })[0];
			String pawd = defqy.getMnecode();
			// 首先判断该区域密码是否存在静态变量值
			//Map<String,Object> map = GDOrderCompStatus.pawds;
			String stpawd = (String) GDOrderCompStatus.getPawd(type+rela.vdef1);
			if (null != stpawd && stpawd != "") {
				// 静态密码存在时，校验该密码是否正确
				if (pawd.equals(stpawd)) {
					// 正确则不必再次输入密码，直接返回可编辑
					return true;
				}
			}
			// 密码不存在或密码校验不通过时，需要弹框输入密码
			String str = JOptionPane.showInputDialog("请输入编辑密码：");
			// 输入信息非空校验
			if(null == str){
				//直接把对话框关掉，不输入内容，此时只需要返回false即可
				return false;
			}
			else if (str.trim().equals("")) {
				//这种情况为输入空，或空格
				JOptionPane.showMessageDialog(null, "输入密码不允许为空！");
				return false;
			}
			if (!str.equals(pawd)) {
				JOptionPane.showMessageDialog(null, "密码错误！");
				return false;
			}
			//密码正确,将密码存入静态密码集中，key-贸易类型+区域，value-密码值
			GDOrderCompStatus.addPawd(type+rela.vdef1, str);
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 获取查询字段所属区域的服务
	 * 
	 * @create by yuxx3 at 2016年6月4日,下午3:52:40
	 * 
	 * @return
	 */
	private IOrderarearelaMaintain getRelationService() {
		return (IOrderarearelaMaintain) NCLocator.getInstance().lookup(
				IOrderarearelaMaintain.class);
	}

	/**
	 * 获取自定档案服务
	 * 
	 * @create by yuxx3 at 2016年6月4日,下午4:17:52
	 * 
	 * @return
	 */
	private IDefdocQryService getDefDocService() {
		return (IDefdocQryService) NCLocator.getInstance().lookup(
				IDefdocQryService.class);
	}
}
