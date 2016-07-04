package nc.ui.fc.gdorder.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
/**
  batch addLine or insLine action autogen
*/
public class GdorderAddLineAction extends BatchAddLineAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setDefaultData(Object obj) {
		super.setDefaultData(obj);
		GDOrderCompStatus singleDocVO = (GDOrderCompStatus) obj;
		singleDocVO.setPk_group(this.getModel().getContext().getPk_group());
		singleDocVO.setPk_org(this.getModel().getContext().getPk_org());
	}

}