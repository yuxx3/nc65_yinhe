package nc.ui.fasten.workflow.freereport;

import java.awt.Container;

import javax.swing.Action;

import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.pub.smart.data.IRowData;
import nc.pub.smart.tracedata.ITraceDataOperator;
import nc.pub.smart.tracedata.TraceDataInterface;
import nc.pub.smart.tracedata.TraceDataParam;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.sm.power.FuncRegisterCacheAccessor;
import nc.vo.sm.funcreg.FuncRegisterVO;
import com.ufida.report.free.userdef.DefaultMenu;
import com.ufida.report.free.userdef.IMenuActionInfo;

/**
 * 报表联查单据的实现类
 * 
 * @author: 
 */

public class ReportTraceDataOperator implements ITraceDataOperator,TraceDataInterface {
	// 功能编码
	public final static String DEST_FUNC_CODE = "50080002";
	// 根据流程生产订单号查询
	public final static String PK_HEAD = "vbillcode";

	@Override
	public Action[] ctreateExtensionActions() {
		return null; // new Action[]{new MyAction()};
	}

	@Override
	public ITraceDataOperator[] provideTraceDataOperator() {
		return new ITraceDataOperator[] { new ReportTraceDataOperator() };
	}

	@Override
	public void traceData(Container container, TraceDataParam param) {

		// 获得选中表体行的PK
		final IRowData rowData = param.getRowData();
		String selItem0 = (String) rowData.getData(PK_HEAD);
		final String selItem = (selItem0 == null) ? "" : selItem0;
		// JOptionPane.showMessageDialog(container, "单据PK：" + selItem);

		// 获得invoker参数
		// ToftPanelAdaptor adaptor = getModel().getContext().getEntranceUI();

		// 获得功能VO参数
		FuncRegisterVO vo = FuncRegisterCacheAccessor.getInstance()
				.getFuncRegisterVOByFunCode(DEST_FUNC_CODE);

		// 构造传递数据参数
		FuncletInitData fid = new FuncletInitData();
		fid.setInitType(ILinkType.LINK_TYPE_QUERY);
		ILinkQueryData data = new ILinkQueryData() {
			public String getBillID() {
				return selItem;
			}

			public String getBillType() {
				return null;
			}

			public String getPkOrg() {
				return null;
			}

			public Object getUserObject() {
				return rowData;
			}
		};
		fid.setInitData(data);

		// 弹出目标单据
		FuncletWindowLauncher.openFuncNodeInTabbedPane(container, vo, fid,
				null, false);

	}

	@Override
	public IMenuActionInfo getMenuItemInfo() {
		return new DefaultMenu("Linkquery", "联查单据");
	}

}
