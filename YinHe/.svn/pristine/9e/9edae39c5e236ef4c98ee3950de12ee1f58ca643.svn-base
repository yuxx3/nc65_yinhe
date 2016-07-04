package nc.impl.uapbd;

import nc.impl.ambd.db.QueryUtil;
import nc.impl.pub.ace.AceOrderarearelaPubServiceImpl;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.uapbd.orderarearela.Orderrelation;
import nc.itf.uapbd.IOrderarearelaMaintain;

public class OrderarearelaMaintainImpl extends AceOrderarearelaPubServiceImpl
		implements IOrderarearelaMaintain {

	@Override
	public Orderrelation[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybasedoc(queryScheme);
	}

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO)
			throws BusinessException {
		BatchSaveAction<Orderrelation> saveAction = new BatchSaveAction<Orderrelation>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}

	@Override
	public Orderrelation[] getRelationByTypeAndCode(String type, String code)
			throws BusinessException, Exception {
		String sqlwhere = "gdcode = '" + code + "' and vdef2 = '" + type + "'";
		return (Orderrelation[]) QueryUtil.queryVOByCond(Orderrelation.class,
				sqlwhere, "");
	}
}
