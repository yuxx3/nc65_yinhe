/**
 * 
 */
package nc.ui.m5a.billref.action;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.TOBillType;

/**
 * 调入申请拉销售订单
 * @author xx
 * @created at Jun 14, 2016,11:31:12 AM
 *
 */
public class AddFromM5AAction extends AbstractReferenceAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7857043629963696147L;
	private BillForm editor;
	private AbstractAppModel model;
	private TransferViewProcessor processor;

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			AggregatedValueObject[] vos = PfUtilClient.getRetVos();
			if (vos == null) {
				return;
			}
			this.getProcessor().processBillTransfer(vos);
		}
	}

	@Override
	protected boolean isActionEnable() {
		return this.model.getUiState() == UIState.NOT_EDIT;
	}

	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		context.setCurrBilltype(TOBillType.TransIn.getCode());
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		context.setTransTypes(this.getTranstypes());
		context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
		return context;
	}

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
	}

	public TransferViewProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(TransferViewProcessor processor) {
		this.processor = processor;
	}


}
