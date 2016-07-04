/**
 * 
 */
package nc.ui.uapbd.gdorder.ace.view;

import nc.ui.uif2.editor.BatchBillTable;
import nc.ui.uif2.model.IAppModelDataManagerEx;

/**
 * 编辑页面
 * @author niangzi
 * @created at 2016年5月30日,下午3:40:13
 *
 */
public class GdorderTable extends BatchBillTable {
	private static final long serialVersionUID = -7758357848150647161L;
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
