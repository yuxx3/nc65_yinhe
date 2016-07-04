package nc.ui.uapbd.refmanage.action;

import java.awt.event.ActionEvent;

import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.batch.BatchDelLineAction;
import nc.vo.util.ManageModeUtil;

/**
 * 自定义删除行按钮
 * @author xx
 * @created at 2016年4月8日,上午11:21:21
 *
 */
public class RefManageDelLineAction extends BatchDelLineAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -656207069340677795L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		if (getBatchBillTable() != null) {
			getBatchBillTable().beforeDelete();
		}
		if (getModel().getUiState() == UIState.EDIT) {
			// 编辑态做标记
			getModel().delLine(-1);
			ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant
					.getDelSuccessInfo(), getModel().getContext());
		} else {
			// 非编辑态直接删除
			int delindex = getModel().getSelectedIndex();
			getModel().delLine(delindex);
		}
		
	}

	@Override
	protected boolean isActionEnable() {
		return true;
	}
	

}
