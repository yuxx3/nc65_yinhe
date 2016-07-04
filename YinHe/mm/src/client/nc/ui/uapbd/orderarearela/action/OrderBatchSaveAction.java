/**
 * 
 */
package nc.ui.uapbd.orderarearela.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction;
import nc.vo.uapbd.orderarearela.Orderrelation;

/**
 * @author xx
 * @created at 2016年5月30日,下午3:27:28
 *
 */
public class OrderBatchSaveAction extends BatchSaveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5234842144888418842L;
	@Override
	protected void beforeDoAction() {
		super.beforeDoAction();
		String user = getModel().getContext().getPk_loginUser();
		nc.vo.pub.lang.UFDateTime time=nc.ui.pub.ClientEnvironment.getServerTime();
		Object[] addvos = getModel().getCurrentSaveObject().getAddObjs();
		for (Object obj : addvos) {
			((Orderrelation)obj).setCreator(user);
			((Orderrelation)obj).setCreationtime(time);
		}
		Object[] updatevos = getModel().getCurrentSaveObject().getUpdObjs();
		for (Object obj : updatevos) {
			((Orderrelation)obj).setModifier(user);
			((Orderrelation)obj).setModifiedtime(time);
		}
	}
}
