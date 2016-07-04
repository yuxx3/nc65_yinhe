/**
 * 
 */
package nc.ui.uapbd.refmanage.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction;
import nc.ui.uif2.UIState;
import nc.vo.bd.cust.refmanage.RefManageVO;

/**
 * @author xx
 * @created at 2016年4月8日,上午11:28:58
 * 
 */
public class RefManageSaveAction extends BatchSaveAction {
	
	private static final long serialVersionUID = -2139634900301272610L;

	@Override
	protected void beforeDoAction() {
		super.beforeDoAction();
		// @edit by xx at
		// 2016年4月8日,下午1:02:14--编辑态不按回车直接点击保存会导致界面数据未刷新到model中，取不到更新数据
		//getModel().setUiState(UIState.NOT_EDIT);
		String user = getModel().getContext().getPk_loginUser();
		nc.vo.pub.lang.UFDateTime time2=nc.ui.pub.ClientEnvironment.getServerTime();
		Object[] addvos = getModel().getCurrentSaveObject().getAddObjs();
		for (Object obj : addvos) {
			((RefManageVO) obj).setCreator(user);
			((RefManageVO) obj).setCreationtime(time2);
		}
		Object[] updatevos = getModel().getCurrentSaveObject().getUpdObjs();
		for (Object obj : updatevos) {
			((RefManageVO) obj).setModifier(user);
			((RefManageVO) obj).setModifiedtime(time2);
		}
	}
}
