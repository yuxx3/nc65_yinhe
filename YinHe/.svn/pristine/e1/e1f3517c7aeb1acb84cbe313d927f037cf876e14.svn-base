/**
 * 
 */
package nc.ui.m5x.billref.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

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
 * �������������۶���
 * 
 * @author yuxx3
 * @created at 2016��4��19��,����1:33:05
 * 
 */
public class AddDBAction extends AbstractReferenceAction {

	private static final long serialVersionUID = -1175754291751416991L;

	private BillForm editor;
	private AbstractAppModel model;
	private TransferViewProcessor processor;

	public AddDBAction() {
		this.setBtnName("���۶���");
		this.setCode("Ref30AddLine");
		/* this.putValue(Action.SHORT_DESCRIPTION, "�������۶���������"); */
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			AggregatedValueObject[] vos = PfUtilClient.getRetVos();
			if (vos == null) {
				return;
			}
			// �������ʾ��ת������
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
		// ���ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
		context.setCurrBilltype(TOBillType.TransOrder.getCode());
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// ����Ĳ�����ԭ�����õķ����ж����漰��ֻ��������һ����ṹ�����������������¼ӵĲ���
		// ���εĽ������ͼ���
		context.setTransTypes(this.getTranstypes());
		// ��־�ڽ������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ������������ֵ��1����ݽӿڶ��壩��
		// 2������������ã���-1������ݽ������ͷ��飩
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
