package nc.itf.report;

import nc.pub.smart.context.SmartContext;
import nc.vo.pub.BusinessException;

/**
 * 报表接口
 * @author 邵文栋
 */
public interface IFreeReport {

	/**
	 * 营收对应发货明细报表
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iInvoiceToAllocationDtl(Object pk_org,Object pk_customer,Object pk_material,
			Object stodoc,Object startdate,Object enddate) throws Exception;
	
	/**
	 * 收款明细对应发票及发货明细
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iGatherToInvoiceAndAllocationDtl(Object startday,Object endday) throws Exception;
	
	/**
	 * 开票延迟罚款明细报表（汇总）
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iDelayPenalty(SmartContext context) throws Exception;
	
	/**
	 * 发货未开票货龄分析报表
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iAllocationUnbilled(SmartContext context) throws Exception;
	
	/**
	 * 质量计划单跟踪一览表
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iQualityTracking(SmartContext context) throws Exception;
	
	
	/**
	 * 销售回收提成额明细报表（汇总）
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iSalesRecoveryommission(Object startday,Object endday) throws Exception;
	
	
	/**
	 * 应收款预警明细报表（汇总）
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iReceivableEarlyWarning(SmartContext context) throws Exception;
	
	/**
	 * 应收款帐龄分柝
	 * @param  SmartContext
	 * @return String
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String iReceivablesAgingAnalysis() throws Exception;
}