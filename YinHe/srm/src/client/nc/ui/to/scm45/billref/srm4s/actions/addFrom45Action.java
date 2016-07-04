/**
 * 
 */
package nc.ui.to.scm45.billref.srm4s.actions;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.srmpub.res.billtype.SRMEMBillType;

/**
 * 拉单按钮-库存采购入库
 * @author yuxx3
 * @created at 2016年4月27日,上午9:54:47
 *
 */
public class addFrom45Action extends AbstractReferenceAction {
	private static final long serialVersionUID = -1325738409314127507L;
	
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
			// 将结果显示到转单界面
			getTransferViewProcessor().processBillTransfer(vos);
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
		
		context.setCurrBilltype(SRMEMBillType.EA.getCode());
		/*String vtrantype = TrantypeFuncUtils.getTrantype(getModel().getContext());
		if(vtrantype==null||vtrantype.equals("")){
			context.setCurrBilltype(SRMEMBillType.EA.getCode());
		}else{
			context.setCurrBilltype(vtrantype);
		}*/
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(PfButtonClickContext.NoClassify);
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
