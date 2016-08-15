/**
 * 
 */
package nc.impl.to.report.transorder;

import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;

import nc.pubitf.to.report.transorder.ITransOrderQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.report.ReportQueryConUtil;
import nc.vo.to.report.pub.TOReportConProcessUtils;
import nc.vo.to.report.pub.TOReportFieldConst;

/**
 * @author yuxx
 * @created at 2016-7-26,下午3:32:38
 * 
 */
public class TransOrderQueryImpl implements ITransOrderQuery {

	private Map<String, String> getFieldMapping() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(TOReportFieldConst.PK_ORG, "to_bill");
		map.put(TOReportFieldConst.FSTATUSFLAG, "to_bill");
		map.put(TOReportFieldConst.DBILLDATE, "to_bill");
		map.put(TOReportFieldConst.CINSTORDOCID, "to_bill_b");
		map.put(TOReportFieldConst.CINDEPTID, "to_bill_b");
		map.put(TOReportFieldConst.CINPSNID, "to_bill");
		map.put(TOReportFieldConst.COUTSTORDOCID, "to_bill_b");
		map.put(TOReportFieldConst.CTOUTSTORDOCID, "to_bill_b");
		map.put(TOReportFieldConst.COUTDEPTID, "to_bill");
		map.put(TOReportFieldConst.COUTPSNID, "to_bill");

		map.put(TOReportFieldConst.CORIGCURRENCYID, "to_bill");
		map.put(TOReportFieldConst.CINSTOCKORGID, "to_bill");
		map.put(TOReportFieldConst.CTOUTSTOCKORGID, "to_bill");
		map.put(TOReportFieldConst.FMODEFLAG, "to_bill");
		map.put(TOReportFieldConst.CTRANTYPEID, "to_bill");

		map.put(TOReportFieldConst.VBILLCODE, "to_bill");
		map.put(TOReportFieldConst.CINVENTORYID, "to_bill_b");
		map.put(TOReportFieldConst.BILLMAKER, "to_bill");
		map.put(TOReportFieldConst.DMAKEDATE, "to_bill");
		map.put(TOReportFieldConst.APPROVER, "to_bill");
		map.put(TOReportFieldConst.TAUDITTIME, "to_bill");
		map.put(TOReportFieldConst.CASSCUSTID, "to_bill_b");
		
		// @edit by yuxx at 2016-7-26,下午3:39:56 添加质量计划号
		map.put(TOReportFieldConst.VBDEF1, "to_bill_b");
		return map;
	}

	private String getResultSql(ConditionVO[] convos) {
		TOReportConProcessUtils process = new TOReportConProcessUtils(convos,
				this.getFieldMapping(), null);
		SqlBuilder sql = new SqlBuilder();
		sql.append(this.getSelectSql());
		sql.append(" ,fmodeflag,fstatusflag,fbuysellflag,nnum,nastnum,noutnum,ninnum,");
		sql.append(" isnull(to_bill_b.noutnum,0)-isnull(to_bill_b.ninnum,0)-isnull(to_bill_b.nwaylossnum,0) nastonwaynum,");
		sql.append(" nwaylossnum,nreturnnum,nsendnum,niosettlenum,");

		// old代码
		// sql.append(" case when to_bill.fioonwayownerflag='1' ");
		// sql.append(" then isnull(to_bill_b.ninnum,0)-isnull(abs(to_bill_b.niosettlenum),0) ");
		// sql.append(" else isnull(to_bill_b.ninnum,0)+isnull(to_bill_b.nwaylossnum,0)-isnull(abs(to_bill_b.niosettlenum),0) end iosettlenum, ");

		// 交易类型若为反向结算，则减去绝对值 panfengc
		sql.append("case when trantype.breverseflag='Y' and to_bill.fioonwayownerflag='1'");
		sql.append(" then isnull(to_bill_b.ninnum,0)-isnull(abs(to_bill_b.niosettlenum),0) ");
		sql.append(" when trantype.breverseflag='Y'");
		sql.append(" then isnull(to_bill_b.ninnum,0)+isnull(to_bill_b.nwaylossnum,0)-isnull(abs(to_bill_b.niosettlenum),0) ");

		sql.append("when to_bill.fioonwayownerflag='1' ");
		sql.append(" then isnull(to_bill_b.ninnum,0)-isnull(to_bill_b.niosettlenum,0) ");
		sql.append(" else isnull(to_bill_b.ninnum,0)+isnull(to_bill_b.nwaylossnum,0)-isnull(to_bill_b.niosettlenum,0) end iosettlenum, ");

		sql.append(" niosettlemny,notsettlenum, ");
		sql.append(" case when to_bill.fotonwayownerflag='1' ");
		sql.append(" then isnull(to_bill_b.ninnum,0)-isnull(abs(to_bill_b.notsettlenum),0) ");
		sql.append(" else isnull(to_bill_b.ninnum,0)+isnull(to_bill_b.nwaylossnum,0)-isnull(abs(to_bill_b.notsettlenum),0) end otsettlenum, ");
		sql.append(" notsettlemny,nqtorigtaxnetprc,naddpricerate,norigtaxmny");
		sql.append(" from to_bill inner join to_bill_b on to_bill.cbillid = to_bill_b.cbillid ");
		sql.append(" left join to_m5xtrantype trantype on to_bill.ctrantypeid=trantype.ctrantypeid ");
		String joinsql = process.getInnerSql();
		if (joinsql != null && joinsql.length() > 0) {
			sql.append(joinsql);
		}
		sql.append(" where to_bill.dr=0 and to_bill_b.dr=0 ");
		String wheresql = process.getWhereSql();
		if (wheresql != null && wheresql.length() > 0) {
			sql.append(wheresql);
		}
		return sql.toString();
	}

	private String getSelectSql() {
		SqlBuilder sql = new SqlBuilder();
		sql.append(" select to_bill.vbillcode,to_bill.dbilldate,vfirsttype,to_bill.ctrantypeid,");
		sql.append(" ccurrencyid,vnote,to_bill.pk_org,pk_org_v,");
		sql.append(" coutstordocid,coutspaceid,coutdeptvid,coutpsnid,");
		sql.append(" cinstockorgvid,cinstordocid,cinspaceid,cindeptvid,");
		sql.append(" cinpsnid,ctoutstockorgvid,ctoutstordocid,ctoutspaceid,");
		sql.append(" ctoutdeptvid,ctoutpsnid,crowno,cinventoryvid,cunitid,");
		sql.append(" castunitid,vchangerate,vbatchcode,");
		sql.append(" creceivecustid,creceiveaddrid,creceiveareaid,creceivesiteid,");
		sql.append(" csendtypeid,cproductorid,cvendorid,cprojectid,casscustid,vfree1,");
		sql.append(" vfree2,vfree3,vfree4,vfree5,vfree6,vfree7,vfree8,");
		sql.append(" vfree9,vfree10,dplanoutdate,dplanarrivedate,boutendflag,");
		sql.append(" bsendendflag,billmaker,dmakedate,approver,taudittime,");
		// 增加特征码
		sql.append("cffileid,");
		sql.append(" to_bill.cincountryid, to_bill.ctoutcountryid, to_bill.coutcountryid, ");
		sql.append("to_bill.ctaxcountryid, to_bill.btriatradeflag, to_bill.ctradewordid,");
		sql.append(" to_bill_b.ctaxcodeid, to_bill_b.ncaltaxmny, to_bill_b.corigcountryid, ");
		// @edit by yuxx at 2016-7-26,下午3:41:25 添加质量计划号
		sql.append("to_bill_b.vbdef1,");
		sql.append(" to_bill_b.corigareaid, to_bill_b.cdesticountryid, to_bill_b.cdestiareaid,corigcurrencyid ");

		return sql.toString();
	}

	@Override
	public String query5XData(IContext context) throws BusinessException {
		ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);

		if (qryconutil.isNull()) {
			SqlBuilder sql = new SqlBuilder();
			sql.append(this.getSelectSql());
			sql.append(",'' fmodeflag,'' fstatusflag, to_bill.fbuysellflag, 0 nnum,0 nastnum,0 noutnum,0 ninnum,0 nastonwaynum,");
			sql.append(" 0 nwaylossnum,0 nreturnnum,0 nsendnum,0 niosettlenum,");
			sql.append(" 0 iosettlenum,0 niosettlemny,0 notsettlenum,0 otsettlenum,");
			sql.append(" 0 notsettlemny,0 nqtorigtaxnetprc,0 naddpricerate,0 norigtaxmny");
			sql.append(" from to_bill,to_bill_b where to_bill.cbillid = to_bill_b.cbillid ");
			sql.append(" and to_bill.dr=0 and to_bill_b.dr=0 ");
			sql.append(" and to_bill.dbilldate", AppContext.getInstance()
					.getBusiDate().toString());
			sql.append(" and to_bill_b.dbilldate", AppContext.getInstance()
					.getBusiDate().toString());
			sql.append(" and to_bill.pk_org", AppContext.getInstance()
					.getPkGroup());
			sql.append(" and to_bill_b.pk_org", AppContext.getInstance()
					.getPkGroup());
			return sql.toString();

		}
		String result = null;
		try {
			ConditionVO[] convos = (ConditionVO[]) qryconutil.getUserObject();

			result = this.getResultSql(convos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return result;
	}

}
