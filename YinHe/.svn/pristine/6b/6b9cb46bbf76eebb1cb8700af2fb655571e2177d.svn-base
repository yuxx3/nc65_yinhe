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
 * 同步BOM
 * 
 * @author yuxx3
 * @created at 2016年4月14日,下午4:06:12
 * 
 */
public class BomSychAction extends NCAction {
	private static final long serialVersionUID = -3570071797924598035L;

	private IDataSychSrv service;
	private BatchBillTableModel model;

	public BomSychAction() {
		this.setBtnName("同步BOM");
		this.setCode("bomsych");
	}

	@SuppressWarnings("static-access")
	@Override
	public void doAction(ActionEvent e) throws Exception {
		MessageDialog md = new MessageDialog();
		int ret = md.showOkCancelDlg(null, "提示", "是否确认同步？");
		if (ret == 1) {
			try {
				getService().bomSych();
			} catch (Exception error) {
				ShowStatusBarMsgUtil.showErrorMsg("同步异常", "同步异常", getModel()
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
