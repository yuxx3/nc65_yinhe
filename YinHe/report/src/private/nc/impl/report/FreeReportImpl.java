package nc.impl.report;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.itf.report.IFreeReport;
import nc.pub.smart.context.SmartContext;
import nc.vo.pub.BusinessException;
import nc.vo.pub.report.BaseQueryCondition.BaseQueryCondition;
import nc.vo.pubapp.AppContext;

import com.ufida.report.anareport.FreeReportContextKey;

public class FreeReportImpl implements IFreeReport {
		
	/**
	 * 发票对应发货明细报表
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@Override
	public String iInvoiceToAllocationDtl(Object pk_org,Object pk_customer,Object pk_material,
			Object stodoc,Object startdate,Object enddate) throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "call pkg_report_pro.invoice_correspond_allocation(?,?,?,?,?,?,?)"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, pk_customer);//客户
		cstmt.setObject(2, pk_org);//组织
		cstmt.setObject(3, startdate);//开票起始日期
		cstmt.setObject(4, enddate);//开票结束日期
		cstmt.setObject(5, pk_material);//物料
		cstmt.setObject(6, stodoc);//仓库
		cstmt.setObject(7, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from v_invoice_to_allocation";
	}

	/**
	 * 收款明细对应发票及发货明细
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@Override
	public String iGatherToInvoiceAndAllocationDtl(Object startday,Object endday) throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "call pkg_report_pro.gather_correspond_invoice(?,?,?)"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, startday);//开票起始日期
		cstmt.setObject(2, endday);//开票结束日期
		cstmt.setObject(3, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from v_gather_to_invoiceallocation";
	}
	
	/**
	 * 开票延迟罚款明细报表（汇总）
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@Override
	public String iDelayPenalty(SmartContext context) throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "call pkg_report_pro.invoice_delay_penalty(?)"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from report_invoice_delay_penalty";
	}

	/**
	 * 发货未开票货龄分析报表
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@Override
	public String iAllocationUnbilled(SmartContext context) throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "call pkg_report_pro.unbilledmaterialageanalysis(?)"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from v_unbilledmatageanalysis";
	}
	
	/**
	 * 质量计划单跟踪一览表
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@Override
	public String iQualityTracking(SmartContext context) throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "call pkg_report_pro.quality_plan_tracking(?)"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from v_quality_plan_tracking";
	}
	
	/**
	 * 销售回收提成额明细报表（汇总）
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@Override
	public String iSalesRecoveryommission(Object startday,Object endday) throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "call pkg_report_pro.gather_correspond_invoice(?,?,?)"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, startday);//开票起始日期
		cstmt.setObject(2, endday);//开票结束日期
		cstmt.setObject(3, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from v_gather_to_invoiceallocation";
	}
	
	/**
	 * 应收款预警明细报表（汇总）
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@Override
	public String iReceivableEarlyWarning(SmartContext context) throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "call pkg_report_pro.receivable_earlywaring_dtl(?)"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from receivable_earlywaring_dtl";
	}
	
	/**
	 * 应收款帐龄分柝
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String iReceivablesAgingAnalysis() throws Exception {
		Connection conn = DBUtil.getConn();
		//获得报表界面查询条件
		//BaseQueryCondition query_cond=(BaseQueryCondition)context.getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		//HashMap<String,Object> cond_map=(HashMap<String,Object>)query_cond.getUserObject();
		String query = "{call pkg_report_pro.yingshouzlanalysis(?)}"; // 此处调用前面建立的存储过程！
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setObject(1, AppContext.getInstance().getPkUser());//当前登录人
		cstmt.execute();
		//返回查询sql
		return "select * from report_yingshou_zl_analyse";
	}
}
