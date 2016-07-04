package nc.impl.uapbd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.impl.ambd.db.QueryUtil;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.itf.uapbd.IGdorderMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.md.model.MetaDataException;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.uapbd.gdorder.GDOrderCompStatus;
import nc.vo.uif2.LoginContext;

/**
 * 广电订单实现接口（外贸、内贸、DC、FC）
 * 
 * @author xx
 * @created at Jun 7, 2016,9:50:55 AM
 * 
 */
public class GdorderMaintainImpl implements IGdorderMaintain {

	@Override
	public BatchOperateVO batchSaveWM(BatchOperateVO batchVO,Object[]keys)
			throws BusinessException {
		// BatchSaveAction<GDOrderCompStatus> saveAction = new
		// BatchSaveAction<GDOrderCompStatus>();
		// BatchOperateVO retData = saveAction.batchSave(batchVO);

		Object[] objs =  batchVO.getUpdObjs();
		if(keys.length>0){
			BatchOperateVO retData = updateOrders("1001ZZ10000000017ROU", objs,keys);
		}
		return batchVO;
	}

	/**
	 * 保存
	 * 
	 * @create by xx at Jun 7, 2016,3:28:00 PM
	 * 
	 * @param objs
	 * @return
	 * @throws BusinessException
	 */
	private BatchOperateVO updateOrders(String type, Object[] objs,Object[] keys)
			throws BusinessException {
		// Map<String,Object> map = GDOrderCompStatus.pawds;
		try {
			//List<String> keys = getTypeKeys(type);
			BaseDAO dao = new BaseDAO();
			StringBuilder sql = new StringBuilder(
					"update GD_OrderCompStatus s set");
			for (Object obj : objs) {
				GDOrderCompStatus order = (GDOrderCompStatus)obj;
				for (Object key : keys) {
					String value = (String) order.getAttributeValue((String)key);
					sql.append(" s." + key + "='" + value + "',");
				}
				sql.deleteCharAt(sql.length() - 1);
				sql.append("where s.pk_orderstatus = '"
						+ order.getPk_orderstatus() + "'");
				sql.append(" and s.def1 = '" + type + "'");
				dao.executeUpdate(sql.toString());
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

	/**
	 * 获取对应贸易类型下的字段
	 * 
	 * @create by xx at Jun 7, 2016,3:32:16 PM
	 * 
	 * @param type
	 * @return
	 */
/*	private List<String> getTypeKeys(String type) {
		List<String> keys = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : GDOrderCompStatus.pawds
				.entrySet()) {
			if (entry.getKey().contains(type)) {
				String[] key = entry.getKey().split(type);
				keys.add(key[1]);
			}
		}
		return keys;
	}*/

	@Override
	public GDOrderCompStatus[] queryByDataVisibilitySettingWM(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException {
		// ִ首先调用存储过程，刷入即时数据
		// String sql =
		// "call pkg_report_pro.gd_ordercompstatus("+AppContext.getInstance().getPkUser()+",'a')";
		// execute(sql);
		// 然后查询出数据,a-1001ZZ10000000017ROU
		// String pk = getTypePK("a");
		StringBuffer whereCondition = new StringBuffer(
				"def1 = '1001ZZ10000000017ROU'");
		if (paramString != null) {
			whereCondition.append(paramString);
		}
		GDOrderCompStatus[] GDOrderCompStatusCollection = (GDOrderCompStatus[]) QueryUtil
				.queryVOByCond(GDOrderCompStatus.class,
						whereCondition.toString(), " order by ts");
		return GDOrderCompStatusCollection;

	}

	/**
	 * 执行存储过程
	 * 
	 * @create by yuxx3 at 2016��5��31��,����10:22:53
	 * 
	 * @param sql
	 * @throws BusinessException
	 */
	private void execute(String sql) throws BusinessException {
		try {
			PersistenceManager sessionManager = PersistenceManager
					.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			Connection conn = session.getConnection();
			CallableStatement cstmt;
			cstmt = conn.prepareCall(sql);
			cstmt.execute();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	public BatchOperateVO batchSaveNM(BatchOperateVO batchVO,Object[]keys)
			throws BusinessException {
		/*BatchSaveAction<GDOrderCompStatus> saveAction = new BatchSaveAction<GDOrderCompStatus>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);*/
		Object[] objs = batchVO.getUpdObjs();
		if(keys.length>0){						   
			BatchOperateVO retData = updateOrders("1001ZZ10000000017ROV", objs,keys);
		}
		return batchVO;
	}

	@Override
	public GDOrderCompStatus[] queryByDataVisibilitySettingNM(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException {
		// ִ首先调用存储过程，刷入即时数据
		// String sql =
		// "call pkg_report_pro.gd_ordercompstatus("+AppContext.getInstance().getPkUser()+",'b')";
		// execute(sql);
		// 然后查询出数据,内贸-b-1001ZZ10000000017ROV
		// String pk = getTypePK("b");
		StringBuffer whereCondition = new StringBuffer(
				"def1 = '1001ZZ10000000017ROV'");
		if (paramString != null) {
			whereCondition.append(paramString);
		}
		GDOrderCompStatus[] GDOrderCompStatusCollection = (GDOrderCompStatus[]) QueryUtil
				.queryVOByCond(GDOrderCompStatus.class,
						whereCondition.toString(), " order by ts");
		return GDOrderCompStatusCollection;
	}

	@Override
	public BatchOperateVO batchSaveDC(BatchOperateVO batchVO) {
		BatchSaveAction<GDOrderCompStatus> saveAction = new BatchSaveAction<GDOrderCompStatus>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}

	@Override
	public GDOrderCompStatus[] queryByDataVisibilitySettingDC(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException {
		// ִ首先调用存储过程，刷入即时数据
		// String sql =
		// "call pkg_report_pro.gd_ordercompstatus("+AppContext.getInstance().getPkUser()+",'c')";
		// execute(sql);
		// 然后查询出数据,DC-c-1001ZZ10000000017ROW
		// String pk = getTypePK("c");
		StringBuffer whereCondition = new StringBuffer(
				"def1 = '1001ZZ10000000017ROW'");
		if (paramString != null) {
			whereCondition.append(paramString);
		}
		GDOrderCompStatus[] GDOrderCompStatusCollection = (GDOrderCompStatus[]) QueryUtil
				.queryVOByCond(GDOrderCompStatus.class,
						whereCondition.toString(), " order by ts");
		return GDOrderCompStatusCollection;
	}

	@Override
	public BatchOperateVO batchSaveFC(BatchOperateVO batchVO)
			throws BusinessException {
		BatchSaveAction<GDOrderCompStatus> saveAction = new BatchSaveAction<GDOrderCompStatus>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}

	@Override
	public GDOrderCompStatus[] queryByDataVisibilitySettingFC(
			LoginContext paramLoginContext, String paramString,
			boolean paramBoolean) throws MetaDataException, BusinessException {
		// ִ首先调用存储过程，刷入即时数据
		// String sql =
		// "call pkg_report_pro.gd_ordercompstatus("+AppContext.getInstance().getPkUser()+",'d')";
		// execute(sql);
		// 然后查询出数据,FC-d-1001ZZ10000000017ROX
		// String pk = getTypePK("d");
		StringBuffer whereCondition = new StringBuffer(
				"def1 = '1001ZZ10000000017ROX'");
		if (paramString != null) {
			whereCondition.append(paramString);
		}
		GDOrderCompStatus[] GDOrderCompStatusCollection = (GDOrderCompStatus[]) QueryUtil
				.queryVOByCond(GDOrderCompStatus.class,
						whereCondition.toString(), " order by ts");
		return GDOrderCompStatusCollection;
	}

	/*
	 * private String getTypePK(String type){ String sqlwhere =
	 * "pk_defdoclist = '1001ZZ10000000017RME' and code = '" + type +"'";
	 * DefdocVO doc =
	 * (DefdocVO)QueryUtil.queryVOByCond(DefdocVO.class,sqlwhere,"")[0]; return
	 * doc.getPk_defdoc(); }
	 */
}
