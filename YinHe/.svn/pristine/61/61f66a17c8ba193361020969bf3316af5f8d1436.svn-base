/**
 * 
 */
package nc.ui.comprocess.billref.m25.actions;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.TOBillType;


/**
 * 采购发票拉委托加工入库
 * @author niangzi
 * @created at 2016年4月25日,上午11:24:09
 *
 */
public class AddInvoiceAction extends AbstractReferenceAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5621343152306833927L;
	private BillForm editor;
	private AbstractAppModel model;
	private TransferViewProcessor processor;
	
	
	/**
	 * @create by niangzi at 2016年4月25日,下午2:04:03
	 *
	 */
	public AddInvoiceAction() {
		// TODO 自动生成的构造函数存根
		this.setBtnName("委托加工入库");
		this.setCode("ref47");
	}
	/* （非 Javadoc）
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			AggregatedValueObject[] vos = PfUtilClient.getRetVos();
			if (vos == null) {
				return;
			}
			// 将结果显示到转单界面
			this.getProcessor().processBillTransfer(vos);
		}
	}
	
	private PfButtonClickContext createPfButtonClickContext() {
		// TODO 自动生成的方法存根
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
		context.setCurrBilltype(POBillType.Invoice.getCode());
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
		return context;
	}


	/* （非 Javadoc）
	 * @see nc.ui.uif2.NCAction#isActionEnable()
	 */
	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		return this.model.getUiState() == UIState.NOT_EDIT;
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
