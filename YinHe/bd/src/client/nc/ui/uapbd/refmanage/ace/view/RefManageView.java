package nc.ui.uapbd.refmanage.ace.view;

import nc.ui.pubapp.uif2app.view.OrgPanel;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.ui.uif2.model.BatchBillTableModel;

/**
 * 组织界面-暂时用不到
 * @author xx
 * @created at 2016年4月8日,上午11:20:55
 *
 */
public class RefManageView extends OrgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2190407207662784030L;

	@Override
	public void initUI() {
		super.initUI();
		this.getDataManager().initModel();
	}
	
	
}
