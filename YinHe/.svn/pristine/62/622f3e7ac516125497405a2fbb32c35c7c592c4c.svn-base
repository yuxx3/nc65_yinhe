/**
 * 
 */
package nc.ui.uapbd.gdorder.ace.handler;

import nc.ui.uif2.FunNodeClosingHandler;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;

/**
 * @author xx
 * @created at Jun 7, 2016,3:01:32 PM
 *
 */
public class FuncNodeCloseHandler extends FunNodeClosingHandler {
	@Override
	protected void beforeClose() {
		super.beforeClose();
		//关闭节点时清空静态密码集
		GDOrderCompStatus.clearMap();
	}
}
