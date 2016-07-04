package nc.impl.dm.m4804.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.dm.m4802.IRewrite4802For4804;
import nc.pubitf.ic.m4c.m4804.IRewrite4CFor4804;
import nc.pubitf.ic.m4c.m4804.Parameter4CFor4804;
import nc.pubitf.ic.m4i.m4804.IRewrite4IFor4804;
import nc.pubitf.ic.m4i.m4804.Parameter4IFor4804;
import nc.pubitf.ic.m4y.m4804.IRewrite4YFor4804;
import nc.pubitf.ic.m4y.m4804.Parameter4YFor4804;
import nc.pubitf.pu.m21.dm.m4804.IOrderWriteBackFor4804;
import nc.pubitf.pu.m21.dm.m4804.IOrderWriteBackParaFor4804;
import nc.pubitf.so.m4331.dm.m4804.IRewrite4331For4804;
import nc.pubitf.so.m4331.dm.m4804.RewritePara4331For4804;
import nc.vo.dm.m4802.entity.DelivapplyBVO;
import nc.vo.dm.m4802.entity.DelivapplyHVO;
import nc.vo.dm.m4802.entity.RewritePara4802For4804;
import nc.vo.dm.m4804.entity.DelivBillAggVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.m4804.entity.RewritePara21For4804;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.ic.m4i.entity.GeneralOutBodyVO;
import nc.vo.ic.m4i.entity.GeneralOutHeadVO;
import nc.vo.ic.m4y.entity.TransOutBodyVO;
import nc.vo.ic.m4y.entity.TransOutHeadVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.DMBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * 新增运输单数量回写上游单据
 * 
 * @since 6.0
 * @version 2010-11-22 下午04:15:57
 * @author yinyxa
 */
public class DelivNumAddOperator implements IRule<DelivBillAggVO> {

	@Override
	public void process(DelivBillAggVO[] vos) {
		ItemKeyMapping mapping = new ItemKeyMapping();
		mapping.setNnumKey(DelivBillBVO.NNUM);
		// mapping.setNnum2Key(DelivBillBVO.NASTNUM);

		mapping.setCsrcidKey(DelivBillBVO.CSRCID);
		mapping.setSrcTSKey(DelivBillBVO.SRCTS);

		mapping.setCsrcbidKey(DelivBillBVO.CSRCBID);
		mapping.setSrcbTSKey(DelivBillBVO.SRCBTS);

		mapping.setVsrctypeKey(DelivBillBVO.VSRCTYPE);
		mapping.setCsrcbbidKey(DelivBillBVO.CPUORDER_BB1ID);
		mapping.setSrcbbTSKey(DelivBillBVO.SRCBBTS);

		BillRewriter tool = new BillRewriter(mapping);
		tool.addSRCHeadClazz(SOBillType.Delivery.getCode(), DeliveryHVO.class);
		tool.addSRCItemClazz(SOBillType.Delivery.getCode(), DeliveryBVO.class);

		tool.addSRCHeadClazz(ICBillType.SaleOut.getCode(), SaleOutHeadVO.class);
		tool.addSRCItemClazz(ICBillType.SaleOut.getCode(), SaleOutBodyVO.class);
		tool.addSRCHeadClazz(ICBillType.TransOut.getCode(),
				TransOutHeadVO.class);
		tool.addSRCItemClazz(ICBillType.TransOut.getCode(),
				TransOutBodyVO.class);
		tool.addSRCHeadClazz(POBillType.Order.getCode(), OrderHeaderVO.class);
		tool.addSRCItemClazz(POBillType.Order.getCode(), OrderItemVO.class);
		tool.addSRCHeadClazz(DMBillType.DelivApply.getCode(),
				DelivapplyHVO.class);
		tool.addSRCItemClazz(DMBillType.DelivApply.getCode(),
				DelivapplyBVO.class);
		// 新增逻辑:回写其它出库单
		tool.addSRCHeadClazz(ICBillType.GeneralOut.getCode(),
				GeneralOutHeadVO.class);
		tool.addSRCItemClazz(ICBillType.GeneralOut.getCode(),
				GeneralOutBodyVO.class);

		Map<String, List<RewritePara>> paraIndex = tool.splitForInsert(vos);
		// 回写发货单参数
		List<RewritePara4331For4804> para4331For4804 = new ArrayList<RewritePara4331For4804>();

		// 回写销售出库单参数
		List<Parameter4CFor4804> para4CFor4804 = new ArrayList<Parameter4CFor4804>();

		// 回写调拨出库单参数
		List<Parameter4YFor4804> para4YFor4804 = new ArrayList<Parameter4YFor4804>();

		// 回写采购订单参数
		List<IOrderWriteBackParaFor4804> para21For4804 = new ArrayList<IOrderWriteBackParaFor4804>();

		// 回写运输申请单参数
		List<RewritePara4802For4804> para4802For4804 = new ArrayList<RewritePara4802For4804>();
		// 回写其它出库单参数
		List<Parameter4IFor4804> para4IFor4804 = new ArrayList<Parameter4IFor4804>();

		this.fillPara4331For4804(para4331For4804,
				paraIndex.get(SOBillType.Delivery.getCode()));
		this.fillPara4CFor4804(para4CFor4804,
				paraIndex.get(ICBillType.SaleOut.getCode()));
		this.fillPara4YFor4804(para4YFor4804,
				paraIndex.get(ICBillType.TransOut.getCode()));
		this.fillPara21For4804(para21For4804,
				paraIndex.get(POBillType.Order.getCode()));
		this.fillPara4802For4804(para4802For4804,
				paraIndex.get(DMBillType.DelivApply.getCode()));
		this.fillPara4IFor4804(para4IFor4804,
				paraIndex.get(ICBillType.GeneralOut.getCode()));
		this.rewrite4331(para4331For4804);
		this.rewrite4C(para4CFor4804);
		this.rewrite4Y(para4YFor4804);
		this.rewrite21(para21For4804);
		this.rewrite4802(para4802For4804);
		this.rewrite4I(para4IFor4804);
	}

	/**
	 * 功能描述: 回写其它出库单
	 */
	private void rewrite4I(List<Parameter4IFor4804> para4iFor4804) {
		if ((null == para4iFor4804) || (para4iFor4804.size() == 0)) {
			return;
		}
		IRewrite4IFor4804 service = NCLocator.getInstance().lookup(
				IRewrite4IFor4804.class);

		Parameter4IFor4804[] paras = new Parameter4IFor4804[para4iFor4804
				.size()];
		para4iFor4804.toArray(paras);

		try {
			service.rewrite4ITransNumFor4804(paras);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	/**
	 * 功能描述:组装回写其它出库单的参数
	 */
	private void fillPara4IFor4804(List<Parameter4IFor4804> para4iFor4804,
			List<RewritePara> list) {
		if ((list != null) && (list.size() > 0)) {
			for (RewritePara para : list) {
				Parameter4IFor4804 rewirtePara = new Parameter4IFor4804(null,
						para.getCsrcbid());
				rewirtePara.setNtrannum(para.getNnum());
				para4iFor4804.add(rewirtePara);
			}
		}
	}

	private void fillPara21For4804(
			List<IOrderWriteBackParaFor4804> para21For4804,
			List<RewritePara> list) {
		if ((list != null) && (list.size() > 0)) {
			for (RewritePara para : list) {
				RewritePara21For4804 rewirtePara = new RewritePara21For4804(
						para.getCsrcbid(), para.getNnum(), para.getCsrcbbid());
				para21For4804.add(rewirtePara);
			}
		}
	}

	private void fillPara4331For4804(
			List<RewritePara4331For4804> para4331For4804, List<RewritePara> list) {
		if ((list != null) && (list.size() > 0)) {
			for (RewritePara para : list) {
				RewritePara4331For4804 rewirtePara = new RewritePara4331For4804(
						para.getCsrcbid(), para.getNnum());
				para4331For4804.add(rewirtePara);
			}
		}
	}

	private void fillPara4802For4804(
			List<RewritePara4802For4804> para4802For4804, List<RewritePara> list) {
		if ((list != null) && (list.size() > 0)) {
			for (RewritePara para : list) {
				RewritePara4802For4804 rewirtePara = new RewritePara4802For4804(
						para.getCsrcbid(), para.getCsrcid(), para.getNnum(),
						para.getSrcbTS());
				para4802For4804.add(rewirtePara);
			}
		}
	}

	private void fillPara4CFor4804(List<Parameter4CFor4804> para4cFor4804,
			List<RewritePara> list) {
		if ((list != null) && (list.size() > 0)) {
			for (RewritePara para : list) {
				Parameter4CFor4804 rewirtePara = new Parameter4CFor4804(null,
						para.getCsrcbid());
				rewirtePara.setNtrannum(para.getNnum());
				para4cFor4804.add(rewirtePara);
			}
		}
	}

	private void fillPara4YFor4804(List<Parameter4YFor4804> para4yFor4804,
			List<RewritePara> list) {
		if ((list != null) && (list.size() > 0)) {
			for (RewritePara para : list) {
				Parameter4YFor4804 rewirtePara = new Parameter4YFor4804(null,
						para.getCsrcbid());
				rewirtePara.setNtrannum(para.getNnum());
				para4yFor4804.add(rewirtePara);
			}
		}
	}

	/**
	 * 回写采购订单
	 * 
	 * @param para45For4804
	 */
	private void rewrite21(List<IOrderWriteBackParaFor4804> para21For4804) {
		if ((null == para21For4804) || (para21For4804.size() == 0)) {
			return;
		}
		IOrderWriteBackFor4804 service = NCLocator.getInstance().lookup(
				IOrderWriteBackFor4804.class);
		try {
			service.writeBackFor45(para21For4804
					.toArray(new RewritePara21For4804[0]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	/**
	 * 回写出库单
	 * 
	 * @param rewritePara4331For4804
	 */
	private void rewrite4331(List<RewritePara4331For4804> rewritePara4331For4804) {
		if ((null == rewritePara4331For4804)
				|| (rewritePara4331For4804.size() == 0)) {
			return;
		}
		IRewrite4331For4804 service = NCLocator.getInstance().lookup(
				IRewrite4331For4804.class);

		try {
			service.rewriteTransnum(rewritePara4331For4804
					.toArray(new RewritePara4331For4804[0]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	/**
	 * 回写运输申请单
	 * 
	 * @param para4804For4804
	 */
	private void rewrite4802(List<RewritePara4802For4804> para4804For4804) {
		if ((null == para4804For4804) || (para4804For4804.size() == 0)) {
			return;
		}
		IRewrite4802For4804 service = NCLocator.getInstance().lookup(
				IRewrite4802For4804.class);
		try {
			service.rewrite4802For4804(para4804For4804
					.toArray(new RewritePara4802For4804[0]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	/**
	 * 回写销售出库单
	 * 
	 * @param para4cFor4804
	 */
	private void rewrite4C(List<Parameter4CFor4804> para4CFor4804) {
		if ((null == para4CFor4804) || (para4CFor4804.size() == 0)) {
			return;
		}
		IRewrite4CFor4804 service = NCLocator.getInstance().lookup(
				IRewrite4CFor4804.class);

		try {
			service.rewrite4CTransNumFor4804(para4CFor4804
					.toArray(new Parameter4CFor4804[0]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	/**
	 * 回写调拨出库单
	 * 
	 * @param para4yFor4804
	 */
	private void rewrite4Y(List<Parameter4YFor4804> para4YFor4804) {
		if ((null == para4YFor4804) || (para4YFor4804.size() == 0)) {
			return;
		}
		IRewrite4YFor4804 service = NCLocator.getInstance().lookup(
				IRewrite4YFor4804.class);

		try {
			service.rewrite4YTransNumFor4804(para4YFor4804
					.toArray(new Parameter4YFor4804[0]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}
}
