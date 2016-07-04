package nc.ui.uapbd.refmanage.model;

import nc.ui.ambd.base.service.BatchAppModelService;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.uif2.LoginContext;

/**
 * 自定义模型管理器
 * @author xx
 * @created at 2016年4月8日,上午11:12:59
 *
 */
public class RefManageModelManage implements IAppModelDataManagerEx,
		AppEventListener {

	private BatchBillTableModel model;
	private boolean showSeal = false;

	private IExceptionHandler exceptionHandler;
	private String sqlWhere = null;

	@Override
	public void initModel() {
		initModelBySqlWhere(this.sqlWhere);
	}

	@Override
	public void initModelBySqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
		Object[] objs = null;

		LoginContext context = getModel().getContext();
		context.setInitData(null);
		try {
			objs = ((BatchAppModelService) getModel().getService())
					.queryByWhereSql(context, createCondition(sqlWhere));
		} catch (Exception e) {
			getExceptionHandler().handlerExeption(e);
		}
		getModel().initModel(objs);
	}

	private String createCondition(String sqlWhere) {
/*		SqlWhereUtil swu = new SqlWhereUtil(sqlWhere);
		if (!this.showSeal) {
			swu.l().and(" and enablestate = 2").r();
		}
		return swu.getSQLWhere();*/
		return "";
	}

	@Override
	public void refresh() {
		initModelBySqlWhere(this.sqlWhere);
	}

	@Override
	public void handleEvent(AppEvent e) {
		
	}

	@Override
	public void setShowSealDataFlag(boolean showSealDataFlag) {
		this.showSeal = showSealDataFlag;
	}

	public BatchBillTableModel getModel() {
		return model;
	}

	public void setModel(BatchBillTableModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public boolean isShowSeal() {
		return showSeal;
	}

	public void setShowSeal(boolean showSeal) {
		this.showSeal = showSeal;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public IExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(IExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

}
