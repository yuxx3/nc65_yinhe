/**
 * 
 */
package nc.ui.uapbd.gdorder.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;

/**
 * @author niangzi
 * @created at 2016��5��31��,����9:10:04
 * 
 */
public class GdorderBatchSaveAction extends BatchSaveAction {

	private static final long serialVersionUID = -7137841200363145696L;

	@SuppressWarnings("deprecation")
	@Override
	protected void beforeDoAction() {
		super.beforeDoAction();
		String user = getModel().getContext().getPk_loginUser();
		nc.vo.pub.lang.UFDateTime time = nc.ui.pub.ClientEnvironment
				.getServerTime();
		Object[] updatevos = getModel().getCurrentSaveObject().getUpdObjs();
		for (Object obj : updatevos) {
			((GDOrderCompStatus) obj).setModifier(user);
			((GDOrderCompStatus) obj).setModifiedtime(time);
		}
	}


}
