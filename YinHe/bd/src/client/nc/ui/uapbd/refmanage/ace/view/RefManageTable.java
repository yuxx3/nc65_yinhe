package nc.ui.uapbd.refmanage.ace.view;

import nc.ui.uif2.editor.BatchBillTable;
import nc.ui.uif2.model.IAppModelDataManagerEx;

/**
 * 编辑面板
 * @author xx
 * @created at 2016年4月8日,上午11:12:39
 *
 */
public class RefManageTable extends BatchBillTable {
	private static final long serialVersionUID = 831909204946731598L;
	
	private IAppModelDataManagerEx modelManager;
	
	@Override
	public void initUI() {
		super.initUI();
		modelManager.initModel();
	}
	
	public IAppModelDataManagerEx getModelManager() {
		return modelManager;
	}
	public void setModelManager(IAppModelDataManagerEx modelManager) {
		this.modelManager = modelManager;
	}	
}
