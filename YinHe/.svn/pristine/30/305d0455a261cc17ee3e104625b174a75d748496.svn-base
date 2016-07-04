/**
 * 
 */
package nc.impl.uapbd;

import java.sql.CallableStatement;
import java.sql.Connection;

import nc.impl.ambd.db.QueryUtil;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.itf.uapbd.IGdOrderNM;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.md.model.MetaDataException;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
import nc.vo.uif2.LoginContext;

/**
 * @author yuxx3
 * @created at 2016年5月31日,上午11:06:34
 *
 */
public class GdorderNMImpl implements IGdOrderNM {

	@Override
	public GDOrderCompStatus[] query(IQueryScheme queryScheme) throws BusinessException {
		return null;
	}
	
	//保存操作
	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
		BatchSaveAction<GDOrderCompStatus> saveAction = new BatchSaveAction<GDOrderCompStatus>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}

	@Override
	public GDOrderCompStatus[] queryByDataVisibilitySetting(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException {
		//执行存储过程
		String sql = "call pkg_report_pro.gd_ordercompstatus("+AppContext.getInstance().getPkUser()+",'b')";
		execute(sql);
		// (不走管控模式的查询)
		//贸易类型（def1）为内贸-b
		StringBuffer whereCondition = new StringBuffer("def1 = 'b'");
		if (paramString != null) {
			whereCondition.append(paramString);
		}
		GDOrderCompStatus[] GDOrderCompStatusCollection = (GDOrderCompStatus[]) QueryUtil
				.queryVOByCond(GDOrderCompStatus.class, whereCondition.toString(),
						" order by ts");
		return GDOrderCompStatusCollection;
	}
	/**
	 * 查询前首先merg into，    执行存储过程
	 * @create by yuxx3 at 2016年5月31日,上午10:22:53
	 *
	 * @param sql
	 * @throws BusinessException
	 */
	private void execute(String sql)throws BusinessException{
		try {
			PersistenceManager sessionManager=PersistenceManager.getInstance ();
			JdbcSession session = sessionManager.getJdbcSession ();
			Connection conn=session.getConnection();
			CallableStatement cstmt;
			cstmt = conn.prepareCall(sql);
			cstmt.execute();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}

}
