/**
 * 
 */
package nc.ui.uapbd.refmanage.action;

import java.awt.event.ActionEvent;

import nc.itf.uapbd.IDataSychSrv;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BatchBillTableModel;

/**
 * 同步物料
 * 
 * @author yuxx3
 * @created at 2016年4月14日,下午3:55:13
 * 
 */
public class MateiSychAction extends NCAction {
	private static final long serialVersionUID = -7551483542338790814L;
	private IDataSychSrv service;
	private BatchBillTableModel model;

	public MateiSychAction() {
		this.setBtnName("同步物料");
		this.setCode("datasych");
	}

	@SuppressWarnings("static-access")
	@Override
	public void doAction(ActionEvent e) throws Exception {
		MessageDialog md = new MessageDialog();
		int ret = md.showOkCancelDlg(null, "提示", "是否确认同步？");
		if (ret == 1) {
			try {
				getService().mateSych();
				ShowStatusBarMsgUtil.showStatusBarMsg("同步成功！", getModel()
						.getContext());
			} catch (Exception error) {
				ShowStatusBarMsgUtil.showErrorMsg("同步异常！", "同步异常！", getModel()
						.getContext());
			}
		}
	}

	public IDataSychSrv getService() {
		return service;
	}

	public void setService(IDataSychSrv service) {
		this.service = service;
	}

	public BatchBillTableModel getModel() {
		return model;
	}

	public void setModel(BatchBillTableModel model) {
		this.model = model;
	}

}
