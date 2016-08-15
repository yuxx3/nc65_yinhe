/**
 * 
 */
package nc.impl.dm.feecalculate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import nc.bs.dm.tariff.rule.bp.maintain.FillUpDataRule;
import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.pub.formulaparse.FormulaParse;
import nc.impl.pubapp.env.BSContext;
import nc.itf.dm.policy.IBatRangeMaintain;
import nc.itf.dm.policy.IPolicyMaintainApp;
import nc.itf.dm.tariffdef.ITariffdefMaintain;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.md.model.impl.MDEnum;
import nc.pubitf.dm.feecalculate.ICalculateBillView;
import nc.pubitf.dm.formula.IFormulaService;
import nc.pubitf.dm.tariff.ITariffPubManage;
import nc.vo.bd.material.MaterialVO;
import nc.vo.dm.apsettledetail.entity.ApSettleDetailVO;
import nc.vo.dm.enumeration.FallotsetFlag;
import nc.vo.dm.feecalculate.entity.FeeCalculateVO;
import nc.vo.dm.feecalculate.entity.FeeMatCalVO;
import nc.vo.dm.formula.entity.FormularVO;
import nc.vo.dm.m4804.entity.DelivBillBVO;
import nc.vo.dm.policy.entity.BatchRangeBVO;
import nc.vo.dm.policy.entity.BatchRangeVO;
import nc.vo.dm.policy.entity.FeePlcyFeeVO;
import nc.vo.dm.policy.entity.FeePlcyPrcVO;
import nc.vo.dm.policy.entity.FeePlcyVO;
import nc.vo.dm.pub.DMCalculConstant;
import nc.vo.dm.tariff.entity.FeeTariffBatVO;
import nc.vo.dm.tariff.entity.FeeTariffDetailVO;
import nc.vo.dm.tariff.entity.FeeTariffVO;
import nc.vo.dm.tariffdef.entity.FeeTariffDefBVO;
import nc.vo.dm.tariffdef.entity.FeeTariffDefVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.formulaset.FormulaParseFather;
import nc.vo.pub.formulaset.VarryVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * @author yuxx
 * @created at 2016-8-9,上午11:17:45
 *
 */
public class FeeCalculater {

	  /**
	   * key:价格项编码
	   */
	  private Map<String, FeePlcyPrcVO> prcvomap =
	      new HashMap<String, FeePlcyPrcVO>();

	  /**
	   * 非批量分级价格项价格map
	   * key: 价格表维护id+价格项编码
	   */
	  private Map<String, UFDouble> nobatchPrcPrice = null;

	  /**
	   * 批量分级价格项价格map
	   * key：价格表维护id+批量分级子实体id
	   */
	  private Map<String, UFDouble> batchPrcPrice = null;

	  /**
	   * 本位币
	   */
	  private String curr = null;

	  /**
	   * 批量分级范围map
	   */
	  private Map<String, BatchRangeVO> batchRangeMap =
	      new HashMap<String, BatchRangeVO>();

	  /**
	   * 分摊依据map
	   */
	  private Map<Integer, String> allotflagmap = new HashMap<Integer, String>();

	  /**
	   * 运费计算
	   * 
	   * @param vo 运费计算交接单
	   * @return 计算结果明细
	   */
	  public List<ApSettleDetailVO> calculate(FeeCalculateVO vo) {

	    // this.getScale(vo);
	    this.getCurr(vo);
	    // 物流组织
	    String pk_org = vo.getpk_org();
	    // 运输方式
	    String csendtypeid = vo.getcsendtypeid();
	    // 承运商
	    String ccarrierid = vo.getccarrierid();
	    // 策略定义
	    FeePlcyVO feeplcyvo = this.queryFeePlcy(pk_org, csendtypeid, ccarrierid);
	    // 价格表定义
	    FeeTariffDefVO tariffdefvo =
	        this.queryTariffDef(pk_org, csendtypeid, ccarrierid);
	    // 价格表定义维度
	    List<String> fields = this.getCollectFields(tariffdefvo);
	    // 价格表维护
	    FeeTariffVO[] tariffs = this.queryTariff(vo, tariffdefvo);
	    if(tariffs==null){
	    	tariffs = new FeeTariffVO[1];
	    	tariffs[0]= new FeeTariffVO();
	    	FeeTariffDetailVO fvo = new FeeTariffDetailVO();
	    	tariffs[0].setParentVO(fvo);
	    	
	    	tariffs[0].getParentVO().setPk_org(pk_org);
	    	tariffs[0].getParentVO().setCsendtypeid(csendtypeid);
	    	tariffs[0].getParentVO().setCcarrierid(ccarrierid);
	    	tariffs[0].getParentVO().setNprice1(UFDouble.ZERO_DBL);
	    }

	    // 价格表明细map,key:汇总维度+维度值
	    Map<String, FeeTariffVO> tariffmap =
	        this.changeFeeTariffToMap(fields, tariffs);

	    // 创建价格项，费用项映射map
	    this.creatmaps(feeplcyvo);

	    List<ApSettleDetailVO> results =
	        this.calculateFeeMat(vo, feeplcyvo, fields, tariffmap);
	    return results;
	  }

	  private void creatmaps(FeePlcyVO feeplcyvo) {
	    this.createPrcVOMap(feeplcyvo);
	    this.createAllotflagMap();
	    this.createBatchLevelMap(feeplcyvo);
	  }

	  private void getCurr(FeeCalculateVO vo) {
	    String curr = null;
	    for (ICalculateBillView view : vo.getViews()) {
	      String pk_org = view.getcsettlefinorgid();
	      String orgCurr = OrgUnitPubService.getOrgCurr(pk_org);
	      if (curr != null && !curr.equals(orgCurr)) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0018")/*@res "货物行结算财务组织本币不同，无法进行运费计算！"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      if (curr == null) {
	        curr = OrgUnitPubService.getOrgCurr(pk_org);
	      }
	    }
	    if (curr != null) {
	      this.curr = curr;
	    }
	  }

	  /**
	   * 询策略定义
	   * 
	   * @param pk_org 物流组织
	   * @param csendtypeid 运输方式
	   * @param ccarrierid 承运商
	   * @return 策略定义
	   */
	  private FeePlcyVO queryFeePlcy(String pk_org, String csendtypeid,
	      String ccarrierid) {
	    FeePlcyVO feeplcyvo = null;
	    IPolicyMaintainApp policyservice =
	        NCLocator.getInstance().lookup(IPolicyMaintainApp.class);
	    try {
	      feeplcyvo = policyservice.queryFeePlcyVO(pk_org, csendtypeid, ccarrierid);
	      if (feeplcyvo == null) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0019")/*@res "计算失败！未匹配到策略定义，请检查！"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      else if (feeplcyvo.getFeePlcyFeeVO().length == 0) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0020")/*@res "计算失败！策略定义未定义费用项，请检查！"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    return feeplcyvo;
	  }

	  /**
	   * 创建价格项map
	   * 
	   * @param feeplcyvo 策略定义
	   */
	  private void createPrcVOMap(FeePlcyVO feeplcyvo) {
	    for (FeePlcyPrcVO prcvo : feeplcyvo.getFeePlcyPrcVO()) {
	      if (prcvo.getBquotedflag().booleanValue()) {
	        this.prcvomap.put(prcvo.getVitemcode(), prcvo);
	      }
	    }
	  }

	  /**
	   * 创建批量分级范围map
	   * 
	   * @param feeplcyvo 策略定义
	   */
	  private void createBatchLevelMap(FeePlcyVO feeplcyvo) {
	    List<String> batlevelids = new ArrayList<String>();
	    for (FeePlcyPrcVO prcvo : feeplcyvo.getFeePlcyPrcVO()) {
	      if (prcvo.getBbatprcflag().booleanValue()) {
	        batlevelids.add(prcvo.getCbatlevelid());
	      }
	    }
	    if (batlevelids.size() > 0) {
	      BatchRangeVO[] batchrangevos = null;
	      IBatRangeMaintain batservice =
	          NCLocator.getInstance().lookup(IBatRangeMaintain.class);
	      try {
	        batchrangevos =
	            batservice.queryBatrange(batlevelids.toArray(new String[batlevelids
	                .size()]));
	      }
	      catch (BusinessException e) {
	        ExceptionUtils.wrappException(e);
	      }
	      if (batchrangevos == null || batchrangevos.length == 0) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0021")/*@res "计算失败！未查到批量分级价格项的批量分级范围，请检查！"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      else {
	        for (BatchRangeVO batchRangeVO : batchrangevos) {
	          this.batchRangeMap.put(batchRangeVO.getPrimaryKey(), batchRangeVO);
	        }
	      }
	    }
	  }

	  /**
	   * 询价格表定义
	   * 
	   * @param pk_org 物流组织
	   * @param csendtypeid 运输方式
	   * @param ccarrierid 承运商
	   * @return 价格表定义
	   */
	  private FeeTariffDefVO queryTariffDef(String pk_org, String csendtypeid,
	      String ccarrierid) {
	    FeeTariffDefVO tariffdefvo = null;
	    ITariffdefMaintain defservice =
	        NCLocator.getInstance().lookup(ITariffdefMaintain.class);
	    try {
	      tariffdefvo =
	          defservice.queryFeeTariffDefVO(pk_org, csendtypeid, ccarrierid);
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    if (tariffdefvo == null) {
//	      String msg =
//	          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
//	              "04014002-0022")/*@res "计算失败！未匹配到价格表定义，请检查！"*/;
//	      ExceptionUtils.wrappBusinessException(msg);
	    }
	    return tariffdefvo;
	  }

	  /**
	   * 获取价格表定义维度
	   * 
	   * @param tariffdefvo 价格表定义
	   * @return 汇总维度
	   */
	  private List<String> getCollectFields(FeeTariffDefVO tariffdefvo) {
	    List<String> fields = new ArrayList<String>();
	    FeeTariffDefBVO[] bvos = tariffdefvo.getChildrenVO();
	    for (FeeTariffDefBVO bvo : bvos) {
	      if (!bvo.getBbshowflag().booleanValue()
	          && !bvo.getBhshowflag().booleanValue()) {
	        continue;
	      }
	      String fdataitemflag = bvo.getFdataitemflag();
	      if (!fdataitemflag.startsWith("vnote")
	          && !fdataitemflag.startsWith("vbnote")) {
	        fields.add(fdataitemflag);
	      }
	    }
	    return fields;
	  }

	  /**
	   * 根据 定义id和运输日期查询价格表明细数据
	   * 
	   * @param vo
	   * @param tariffdefvo
	   * @return 应付运费价格表维护
	   */

	  private FeeTariffVO[] queryTariff(FeeCalculateVO vo,
	      FeeTariffDefVO tariffdefvo) {
	    FeeTariffVO[] tariffs = null;
	    ITariffPubManage tariffservice =
	        NCLocator.getInstance().lookup(ITariffPubManage.class);
	    try {
	      String pk_tariffdef = tariffdefvo.getParentVO().getPk_tariffdef();

	      UFDate ddelivdate = vo.getddelivdate();
	      tariffs = tariffservice.queryFeeTariffVOBySql(pk_tariffdef, ddelivdate);
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    if (tariffs == null || tariffs.length == 0) {
	    	
	      /*String msg =
	          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	              "04014002-0023")@res "计算失败！未查询到价格表明细数据，请检查！";
	      ExceptionUtils.wrappBusinessException(msg);*/
	    }
	    return tariffs;
	  }

	  /**
	   * 将价格表明细数据转化为map
	   * 
	   * @param fields 价格表定义维度
	   * @param tariffs 价格表明细数据
	   * @return map
	   */
	  private Map<String, FeeTariffVO> changeFeeTariffToMap(List<String> fields,
	      FeeTariffVO[] tariffs) {
	    Map<String, FeeTariffVO> tariffmap = new HashMap<String, FeeTariffVO>();
	    for (FeeTariffVO tariff : tariffs) {
	      StringBuilder sb = new StringBuilder();
	      String str = "";
	      for (String field : fields) {
	        /*如果field为基本分类字段(cmarbasclassid)进行另外赋值*/
	        if (field.equals("cmarbasclassid")) {
	          str = field + ":" + tariff.getParentVO().getMaterialinnercode() + ",";
	        }
	        else {
	          if (field.equals("dbegindate") || field.equals("denddate")) {
	            continue;
	          }
	          sb.append(field);
	          sb.append(":");
	          sb.append(tariff.getParentVO().getAttributeValue(field));
	          sb.append(",");
	        }
	      }
	      /*放置到最后*/
	      sb.append(str);
	      if (sb.length() > 0) {
	        sb.deleteCharAt(sb.length() - 1);
	      }
	      String key = sb.toString();
	      if (tariffmap.containsKey(key)) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0024")/*@res "计算失败！存在相同维度的价格表维护数据，请检查！"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      else {
	        tariffmap.put(key, tariff);
	      }
	    }
	    return tariffmap;
	  }

	  /**
	   * 计算费用项
	   * 
	   * @param vo 运费计算交接单
	   * @param feeplcyvo 策略定义
	   * @param fields 价格表定义的维度
	   * @param tariffmap 价格表明细map
	   * @return 计算结果明细
	   */
	  private List<ApSettleDetailVO> calculateFeeMat(FeeCalculateVO vo,
	      FeePlcyVO feeplcyvo, List<String> fields,
	      Map<String, FeeTariffVO> tariffmap) {
	    // 从内存中取价格缓存
	    this.getMapPackFromContext();
	    // 公式解析器
	    FormulaParseFather parse = new FormulaParse();
	    List<ApSettleDetailVO> results = new ArrayList<ApSettleDetailVO>();
	    List<FeeMatCalVO> calvos = this.classfiyBillViews(vo, fields, feeplcyvo);
	    for (int i = 0; i < calvos.size(); i++) {
	      FeeMatCalVO feematcalvo = calvos.get(i);
	      String feematcalvo_key = feematcalvo.getKey();
	      FeeTariffVO tariffVO = tariffmap.get(feematcalvo_key);
	      // 如果维度中有物料分类，没有当前级次没有匹配到，需要匹配上级物料分类
	      if (tariffVO == null) {
	        if (feematcalvo_key.contains("cmarbasclassid")) {
	          for (int k = 1; k < feematcalvo_key.length() / 4; k++) {
	            feematcalvo_key =
	                feematcalvo_key.substring(0, feematcalvo_key.length() - 4);
	            // if (feematcalvo_key.length() == 0) {
	            // 如果key中已经不包含cmarbasclassid，说明已经查询过最上级分类了
	            if (!feematcalvo_key.contains("cmarbasclassid")) {
	              break;
	            }
	            tariffVO = tariffmap.get(feematcalvo_key);
	            if (tariffVO != null) {
	              feematcalvo.setKey(feematcalvo_key);
	              break;
	            }
	          }
	        }
//	        if (tariffVO == null) {
//	          String msg =
//	              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
//	                  "4014002_0", "04014002-0025")/*@res "计算失败！未匹配到价格表价格，无法进行运费计算！"*/;
//	          ExceptionUtils.wrappBusinessException(msg);
//	        }
	      }
	      results.addAll(this.calFeeItem(i, parse, feematcalvo, tariffVO));
	    }
	    // 将价格缓存到内存中
	    this.setMapPackToContext();
	    return results;
	  }

	  private void getMapPackFromContext() {
	    BSContext context = BSContext.getInstance();
	    Object sessionobject = context.getSession(DMCalculConstant.DMPRCPRICEMAP);
	    if (sessionobject != null && sessionobject instanceof PriceMapPack) {
	      PriceMapPack mappack = (PriceMapPack) sessionobject;
	      if (mappack.getNobatchPrcPrice() != null) {
	        this.nobatchPrcPrice = mappack.getNobatchPrcPrice();
	      }
	      else {
	        this.nobatchPrcPrice = new HashMap<String, UFDouble>();
	      }
	      if (mappack.getBatchPrcPrice() != null) {
	        this.batchPrcPrice = mappack.getBatchPrcPrice();
	      }
	      else {
	        this.batchPrcPrice = new HashMap<String, UFDouble>();
	      }
	    }
	    else {
	      this.nobatchPrcPrice = new HashMap<String, UFDouble>();
	      this.batchPrcPrice = new HashMap<String, UFDouble>();
	    }
	  }

	  private void setMapPackToContext() {
	    BSContext context = BSContext.getInstance();
	    PriceMapPack mappack = new PriceMapPack();
	    mappack.setBatchPrcPrice(this.batchPrcPrice);
	    mappack.setNobatchPrcPrice(this.nobatchPrcPrice);
	    context.setSession(DMCalculConstant.DMPRCPRICEMAP, mappack);
	  }

	  /**
	   * 根据汇总维度对views进行分类，构建FeeMatCalVO
	   * 
	   * @param vo 运费计算交接单
	   * @param fields 汇总维度
	   * @param feeplcyvo 策略定义
	   * @return 同一纬度的货物行
	   */
	  private List<FeeMatCalVO> classfiyBillViews(FeeCalculateVO vo,
	      List<String> fields, FeePlcyVO feeplcyvo) {
	    List<FeeMatCalVO> vos = new ArrayList<FeeMatCalVO>();
	    MapList<String, ICalculateBillView> maplist =
	        new MapList<String, ICalculateBillView>();
	    // 物料
	    String[] marBasClassKeys = new String[] {
	      MaterialVO.PK_MARBASCLASS
	    };
	    // String[] cmaterialid = null;
	    StringBuilder sb = new StringBuilder();
	    String str = "";
	    FillUpDataRule rule = new FillUpDataRule();
	    // 将查询物料放在了for循环中，导致sql数量过大 wangjlk 2014-8-25
	    // for (ICalculateBillView view : vo.getViews()) {
	    // cmaterialid = new String[] {
	    // view.getcmaterialid()
	    // };
	    List<String> cmaterialids = new ArrayList<String>();
	    for (ICalculateBillView view : vo.getViews()) {
	      cmaterialids.add(view.getcmaterialid());
	    }
	    Map<String, MaterialVO> marMap =
	        MaterialPubService.queryMaterialBaseInfo(
	            cmaterialids.toArray(new String[cmaterialids.size()]),
	            marBasClassKeys);
	    Set<String> cmarbasclassidset = new HashSet<String>();
	    // 物料id与对应物料基本分类id map；MaterialVO 是一个较大对象（对比String），
	    // 在后面循环ibillview时不需要再使用对这个对象的引用，这样垃圾回收时可以回收这部分内存占用
	    Map<String, String> maridclassmap = new HashMap<String, String>();
	    for (Entry<String, MaterialVO> entry : marMap.entrySet()) {
	      String cmarbasclassid = entry.getValue().getPk_marbasclass();
	      cmarbasclassidset.add(cmarbasclassid);
	      maridclassmap.put(entry.getKey(), cmarbasclassid);
	    }
	    // 物料基本分类id 与 innercode 映射map
	    Map<String, String> idandinnercodemap =
	        rule.queryInnercodeByMarbasclassids(cmarbasclassidset
	            .toArray(new String[cmarbasclassidset.size()]));

	    for (ICalculateBillView view : vo.getViews()) {
	      // MaterialVO materialVO = marMap.get(view.getcmaterialid());
	      // 获取物料基本分类id
	      String cmarbasclassid = maridclassmap.get(view.getcmaterialid());
	      for (String field : fields) {
	        /*如果field为基本分类字段(cmarbasclassid)进行另外赋值*/
	        if (field.equals("cmarbasclassid")) {
	          str = field + ":" + idandinnercodemap.get(cmarbasclassid) + ",";
	        }
	        else {
	          if (field.equals("dbegindate") || field.equals("denddate")) {
	            continue;
	          }
	          sb.append(field);
	          sb.append(":");
	          sb.append(this.getvaluebykey(ICalculateBillView.class.getName(),
	              field, view));
	          sb.append(",");
	        }
	      }
	      // 把innercode拼到最后，方便查找上级
	      sb.append(str);
	      if (sb.length() > 0) {
	        sb.deleteCharAt(sb.length() - 1);
	      }
	      maplist.put(sb.toString(), view);
	      sb.delete(0, sb.length());
	    }
	    // map的key： 汇总维度+维度值， 与价格表明细map的key相同
	    // 在计算时可以直接用key，取到对应的价格表明细
	    for (Entry<String, List<ICalculateBillView>> entry : maplist.entrySet()) {
	      String key = entry.getKey();
	      List<ICalculateBillView> views = entry.getValue();
	      FeeMatCalVO calvo =
	          new FeeMatCalVO(key, views.toArray(new ICalculateBillView[views
	              .size()]), feeplcyvo);
	      vos.add(calvo);
	    }
	    return vos;
	  }

	  private Object getvaluebykey(String classname, String key, Object obj) {
	    Method m;
	    Object value = null;
	    try {
	      if (classname.equals(ICalculateBillView.class.getName())) {
	        m = ICalculateBillView.class.getMethod("get" + key);
	      }
	      else {
	        m = FeeMatCalVO.class.getMethod("get" + key);
	      }

	      value = m.invoke(obj, new Object[] {});
	    }
	    catch (SecurityException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    catch (NoSuchMethodException e) {
	      String message =
	          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014001_0",
	              "04014001-0379", null, new String[] {
	                key
	              })/*@res "运费计算失败，费用项公式存在非法字段{0}*/;
	      ExceptionUtils.wrappBusinessException(message);
	    }
	    catch (IllegalArgumentException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    catch (IllegalAccessException e) {
	      // 按规范抛出异常(EJB边界请用marsh方法)
	      ExceptionUtils.wrappException(e);
	    }
	    catch (InvocationTargetException e) {
	      // 按规范抛出异常(EJB边界请用marsh方法)
	      ExceptionUtils.wrappException(e);
	    }
	    catch (Exception e) {
	      ExceptionUtils.wrappException(e);
	    }
	    return value;
	  }

	  /**
	   * 分摊依据与对应字段映射
	   * 暂时先这样，以后将枚举改为string
	   * 
	   */
	  private void createAllotflagMap() {
	    this.allotflagmap.put(FallotsetFlag.NNUM.getIntegerValue(), "nnum");
	    this.allotflagmap.put(FallotsetFlag.NASTNUM.getIntegerValue(), "nastnum");
	    this.allotflagmap.put(FallotsetFlag.NVOLUME.getIntegerValue(), "nvolumn");
	    this.allotflagmap.put(FallotsetFlag.NWEIGHT.getIntegerValue(), "nweight");
	    this.allotflagmap.put(FallotsetFlag.NSIGNNUM.getIntegerValue(), "nsignnum");
	    this.allotflagmap.put(FallotsetFlag.NSIGNASTNUM.getIntegerValue(),
	        "nsignastnum");
	    this.allotflagmap.put(FallotsetFlag.NSIGNVOLUME.getIntegerValue(),
	        "nsignvolume");
	    this.allotflagmap.put(FallotsetFlag.NSIGNWEIGHT.getIntegerValue(),
	        "nsignweight");
	    this.allotflagmap.put(FallotsetFlag.NVALUE.getIntegerValue(), "nmoney");
	  }

	  /**
	   * 计算费用项并分摊到明细行
	   * 
	   * @param n 分组序号
	   * @param parse 公式解析器
	   * @param calvo 同一纬度的货物行
	   * @param tariffvo 价格表维护数据
	   * @return 计算结果明细
	   */
	  private List<ApSettleDetailVO> calFeeItem(int n, FormulaParseFather parse,
	      FeeMatCalVO calvo, FeeTariffVO tariffvo) {
	    List<ApSettleDetailVO> results = new ArrayList<ApSettleDetailVO>();
	    FeePlcyVO feeplcyvo = calvo.getFeeplcyvo();
	    for (FeePlcyFeeVO feevo : feeplcyvo.getFeePlcyFeeVO()) {
	      String vfeeformcode = feevo.getVfeeformcode();
	      parse.setExpress(vfeeformcode);
	      VarryVO varryVO = parse.getVarry();
	      String[] itemcodes = varryVO.getVarry();
	      MapList<String, UFDouble> maplist =
	          this.calItemValue(parse, calvo, tariffvo, itemcodes);
	      // 解析价格项批量依据公式时会设置公式到解析器中，所以这里重新设置一下。
	      parse.setExpress(vfeeformcode);
	      parse.setDataSArray(maplist.toMap());
	      UFDouble value = UFDouble.ZERO_DBL;
	      if (parse.getValueAsObject() instanceof Integer) {
	        value = new UFDouble(((Integer) parse.getValueAsObject()).intValue());
	      }
	      else if (parse.getValueAsObject() instanceof UFDouble) {
	        value = (UFDouble) parse.getValueAsObject();
	      }
	      else {
	        String msg =
	            NCLangResOnserver.getInstance().getStrByID("4014002_0",
	                "04014002-0043", null, new String[] {
	                  feevo.getVfeeformname()
	                })/*计算失败！费用项公式:{0}无法计算出结果，请检查！*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      value = ScaleUtils.getScaleUtilAtBS().adjustMnyScale(value, this.curr);

	      AllotFeePackVO packvo = new AllotFeePackVO();
	      packvo.setAllotflag(feevo.getFallotsetflag());
	      packvo.setFeeitemid(feevo.getCfeeitemid());
	      packvo.setNfeemny(value);
	      // 分组序号 用于最后的运算结果：运费明细 需要持久化的数据。作用是用于 结算单修改费用项金额重算时进行分摊需要。
	      packvo.setNum(n);
	      packvo.setViews(calvo.getViews());
	      results.addAll(this.allotFeeToDetails(packvo));
	    }
	    return results;
	  }

	  /**
	   * 计算费用项公式各参数对应值
	   * 
	   * @param parse 公式解析器
	   * @param novdefvo 计算VO
	   * @param tariffvo 价格表明细
	   * @param itemcodes 参数编码
	   * @return key:公式参数，value:公式参数值
	   */
	  private MapList<String, UFDouble> calItemValue(FormulaParseFather parse,
	      FeeMatCalVO novdefvo, FeeTariffVO tariffvo, String[] itemcodes) {
	    MapList<String, UFDouble> maplist = new MapList<String, UFDouble>();
	    IFormulaService service =
	        NCLocator.getInstance().lookup(IFormulaService.class);
	    FormularVO[] formularVOs = null;
	    try {
	      formularVOs = service.queryAllDMFormular();
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    for (String itemcode : itemcodes) {
	      UFDouble price = null;
	      // 如果费用项公式包含价格项，需要取到价格项对应价格
	      if (itemcode.startsWith("nprice")) {
	        FeePlcyPrcVO prcvo = this.prcvomap.get(itemcode);
	        if (prcvo == null) {
	          String msg =
	              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
	                  "4014002_0", "04014002-0026")/*@res "计算失败！未匹配到被引用的价格项，请检查！"*/;
	          ExceptionUtils.wrappBusinessException(msg);
	        }
	        // 价格项如果是批量分级，需要先通过批量分级依据公式计算出批量依据值
	        // 匹配对应的批量分级范围取价
	        else if (prcvo.getBbatprcflag().booleanValue()) {
	          String vbatformulacode = prcvo.getVbatformulacode();
	          UFDouble batchvalue =
	              this.getPrcBatchValue(parse, novdefvo, vbatformulacode,
	                  formularVOs);
	          String cbatlevelid = prcvo.getCbatlevelid();
	          String cbatlevelbid =
	              this.getbatlevel(prcvo, batchvalue, cbatlevelid);
	          price = this.getBatchPrcPrice(cbatlevelbid, tariffvo);
	        }
	        else {
	          price = this.getNoBatchPrcPrice(itemcode, tariffvo);
	        }
	        if (price == null) {
	          String msg =
	              NCLangResOnserver.getInstance().getStrByID("4014002_0",
	                  "04014002-0044", null, new String[] {
	                    itemcode.replace("nprice", "")
	                  })/*计算失败！价格项{0}的对应价格为空!*/;
	          ExceptionUtils.wrappBusinessException(msg);
	        }
	      }
	      else {
	        price =
	            (UFDouble) this.getvaluebykey(FeeMatCalVO.class.getName(),
	                itemcode, novdefvo);
	        if (price == null && formularVOs != null) {
	          for (FormularVO formularVO : formularVOs) {
	            if (formularVO.getVcode().equals(itemcode)) {
	              String msg =
	                  NCLangResOnserver.getInstance().getStrByID("4014002_0",
	                      "04014002-0045", null, new String[] {
	                        formularVO.getVname()
	                      })/*计算失败！{0}为空!*/;
	              ExceptionUtils.wrappBusinessException(msg);
	            }
	          }
	        }
	      }

	      maplist.put(itemcode, price);
	    }
	    return maplist;
	  }

	  /**
	   * 计算价格项批量依据公式值
	   * 
	   * @param parse 公式解析器
	   * @param novdefvo 计算VO
	   * @param vbatformulacode 价格项批量依据公式
	   * @param formularVOs 函数公式
	   * @return 批量依据公式值
	   */
	  private UFDouble getPrcBatchValue(FormulaParseFather parse,
	      FeeMatCalVO novdefvo, String vbatformulacode, FormularVO[] formularVOs) {
	    parse.setExpress(vbatformulacode);
	    VarryVO varry = parse.getVarry();
	    String[] prcitemcodes = varry.getVarry();
	    MapList<String, UFDouble> valuelist = new MapList<String, UFDouble>();
	    for (String code : prcitemcodes) {
	      UFDouble codevalue =
	          (UFDouble) this.getvaluebykey(FeeMatCalVO.class.getName(), code,
	              novdefvo);
	      if (codevalue == null) {
	        for (FormularVO formularVO : formularVOs) {
	          if (formularVO.getVcode().equals(code)) {
	            String msg =
	                NCLangResOnserver.getInstance().getStrByID("4014002_0",
	                    "04014002-0045", null, new String[] {
	                      formularVO.getVname()
	                    })/*计算失败！{0}为空!*/;
	            ExceptionUtils.wrappBusinessException(msg);
	          }
	        }
	      }
	      valuelist.put(code, codevalue);
	    }
	    parse.setDataSArray(valuelist.toMap());
	    Object result = parse.getValueAsObject();
	    UFDouble batchvalue = null;
	    // 返回的结果 有可能为Integer，其他绝大多数情况都为UFDouble
	    if (result instanceof Integer) {
	      batchvalue = new UFDouble(((Integer) result).intValue());
	    }
	    else if (result instanceof UFDouble) {
	      batchvalue = (UFDouble) result;
	    }
	    else {
	      String msg =
	          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	              "04014002-0027")/*@res "计算失败！批量分级公式计算出错，请检查！"*/;
	      ExceptionUtils.wrappBusinessException(msg);
	    }
	    return batchvalue;
	  }

	  /**
	   * 根据批量依据值获取所在批量分级子表ID
	   * 
	   * @param prcvo 价格项vo
	   * @param batchvalue 批量依据值
	   * @param cbatlevelid 批量分级主表ID
	   * @return 批量分级子表ID
	   */
	  private String getbatlevel(FeePlcyPrcVO prcvo, UFDouble batchvalue,
	      String cbatlevelid) {
	    BatchRangeVO batchrangevo = this.batchRangeMap.get(cbatlevelid);
	    String cbatlevelbid = null;
	    for (BatchRangeBVO bvo : batchrangevo.getChildrenVO()) {
	      UFDouble nrangefrom = bvo.getNrangefrom();
	      UFDouble nrangeto = bvo.getNrangeto();
	      if (batchvalue.compareTo(nrangefrom) >= 0
	          && (nrangeto == null || batchvalue.compareTo(nrangeto) < 0)) {
	        cbatlevelbid = bvo.getPk_batrange_b();
	        break;
	      }
	    }
	    if (cbatlevelbid == null) {
	      String msg =
	          NCLangResOnserver.getInstance().getStrByID("4014002_0",
	              "04014002-0046", null, new String[] {
	                prcvo.getVshowname()
	              })/*计算失败！匹配{0}批量分级范围出错！*/;
	      ExceptionUtils.wrappBusinessException(msg);
	    }
	    return cbatlevelbid;
	  }

	  /**
	   * 获取非批量分级的价格项金额
	   * 
	   * @param itemcode 价格项编码
	   * @param tariffvo 价格表维护
	   * @return 价格项金额
	   */
	  private UFDouble getNoBatchPrcPrice(String itemcode, FeeTariffVO tariffvo) {
	    UFDouble price =
	        this.nobatchPrcPrice.get(tariffvo.getPrimaryKey() + itemcode);
	    if (price == null) {
	      price = (UFDouble) tariffvo.getParentVO().getAttributeValue(itemcode);
	      this.nobatchPrcPrice.put(tariffvo.getPrimaryKey() + itemcode, price);
	    }
	    return price;
	  }

	  /**
	   * 获取批量分级的价格项金额
	   * 
	   * @param cbatlevelbid 批量分级ID
	   * @param tariffvo 价格表维护
	   * @return 价格项金额
	   */
	  private UFDouble getBatchPrcPrice(String cbatlevelbid, FeeTariffVO tariffvo) {
	    UFDouble price =
	        this.batchPrcPrice.get(tariffvo.getPrimaryKey() + cbatlevelbid);
	    if (price == null) {
	      for (FeeTariffBatVO batvo : tariffvo.getChildrenVO()) {
	        if (batvo.getCbatlevelbid().equals(cbatlevelbid)) {
	          price = batvo.getNprice();
	          break;
	        }
	      }
	      this.batchPrcPrice.put(tariffvo.getPrimaryKey() + cbatlevelbid, price);
	    }
	    return price;
	  }

	  /**
	   * 将费用项金额分摊生成运费明细
	   * 
	   * @param packvo 运费分摊参数类
	   * @return 运费明细
	   */
	  private List<ApSettleDetailVO> allotFeeToDetails(AllotFeePackVO packvo) {
	    String field = this.chooseAllotFlag(packvo);
	    ICalculateBillView[] views = packvo.getViews();
	    // 总分摊依据值
	    UFDouble ncalculmny = UFDouble.ZERO_DBL;
	    Map<String, UFDouble> culmnymap = new HashMap<String, UFDouble>();
	    for (ICalculateBillView view : views) {
	      UFDouble culmny =
	          (UFDouble) this.getvaluebykey(ICalculateBillView.class.getName(),
	              field, view);
	      if (culmny == null) {
	        Integer allotflag = packvo.getAllotflag();
	        FallotsetFlag fallotsetFlag =
	            MDEnum.valueOf(FallotsetFlag.class, allotflag);
	        String msg =
	            NCLangResOnserver.getInstance().getStrByID("4014002_0",
	                "04014002-0047", null, new String[] {
	                  fallotsetFlag.getName()
	                })/*计算失败！分摊依据：{0}为空*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      culmnymap.put(view.getcbill_bid(), culmny);
	      ncalculmny = MathTool.add(ncalculmny, culmny);
	    }
	    if (ncalculmny.equals(UFDouble.ZERO_DBL)) {
	      FallotsetFlag allot =
	          MDEnum.valueOf(FallotsetFlag.class, packvo.getAllotflag());
	      String msg =
	          NCLangResOnserver.getInstance().getStrByID("4014002_0",
	              "04014002-0048", null, new String[] {
	                allot.getName()
	              })/*计算失败！分摊依据：{0}总值为0，请检查！*/;
	      ExceptionUtils.wrappBusinessException(msg);
	    }
	    Map<String, UFDouble> feemap = this.allotfee(packvo, ncalculmny, culmnymap);
	    List<ApSettleDetailVO> results =
	        this.createDetailVOs(packvo, culmnymap, feemap);

	    return results;
	  }

	  /**
	   * 获取分摊依据字段
	   * 
	   * @param packvo 运费分摊参数类
	   * @return 分摊依据字段
	   */
	  private String chooseAllotFlag(AllotFeePackVO packvo) {
	    Integer allotflag = packvo.getAllotflag();
	    if (FallotsetFlag.MAXWV.equalsValue(allotflag)) {
	      UFDouble volumn = packvo.getnvolumn();
	      UFDouble weight = packvo.getnweight();
	      UFDouble nquotiety = packvo.getViews()[0].getnexchangerate();
	      if (weight == null) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0028")/*@res "计算失败！体积为空，无法计算MAX(重量，体积*折算点)"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      else if (volumn == null) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0028")/*@res "计算失败！体积为空，无法计算MAX(重量，体积*折算点)"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      else if (nquotiety == null) {
	        String msg =
	            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4014002_0",
	                "04014002-0029")/*@res "未询到重量体积系数！"*/;
	        ExceptionUtils.wrappBusinessException(msg);
	      }
	      else if (weight.compareTo(volumn.multiply(nquotiety)) > 0) {
	        return DelivBillBVO.NWEIGHT;
	      }
	      return DelivBillBVO.NVOLUMN;
	    }
	    return this.allotflagmap.get(allotflag);
	  }

	  /**
	   * 运费分摊
	   * 
	   * @param packvo 运费分摊参数类
	   * @param ncalculmny 总分摊依据值
	   * @param culmnymap 分摊依据值map
	   * @return 运费明细map
	   */
	  private Map<String, UFDouble> allotfee(AllotFeePackVO packvo,
	      UFDouble ncalculmny, Map<String, UFDouble> culmnymap) {
	    ICalculateBillView[] views = packvo.getViews();
	    UFDouble nfeemny = packvo.getNfeemny();
	    // 已分摊金额 处理倒挤
	    UFDouble ntotalMny = UFDouble.ZERO_DBL;
	    UFDouble nnewMny;
	    Map<String, UFDouble> feemap = new HashMap<String, UFDouble>();
	    for (ICalculateBillView view : views) {
	      // 分摊依据值
	      UFDouble culmny = culmnymap.get(view.getcbill_bid());
	      nnewMny = nfeemny.multiply(culmny).div(ncalculmny);
	      nnewMny =
	          ScaleUtils.getScaleUtilAtBS().adjustMnyScale(nnewMny, this.curr);
	      ntotalMny = ntotalMny.add(nnewMny);
	      feemap.put(view.getcbill_bid(), nnewMny);
	    }
	    // 如果已经分摊的合计金额不等于差异金额，金额倒挤
	    if (ntotalMny.compareTo(nfeemny) != 0) {
	      nfeemny = nfeemny.sub(ntotalMny);
	      // 从最后一个明细开始查找，倒挤到最后一个分摊依据值不等于0的明细上
	      for (int i = views.length - 1; i >= 0; i--) {
	        ICalculateBillView view = views[i];
	        if (culmnymap.get(view.getcbill_bid()).compareTo(UFDouble.ZERO_DBL) > 0) {
	          UFDouble feemny = feemap.get(view.getcbill_bid());
	          feemny = MathTool.add(feemny, nfeemny);
	          feemap.put(view.getcbill_bid(), feemny);
	          break;
	        }
	      }
	    }
	    return feemap;
	  }

	  /**
	   * 根据分摊运费创建运费明细
	   * 
	   * @param packvo 运费分摊参数类
	   * @param culmnymap 分摊依据值map
	   * @param feemap 运费明细map
	   * @return results
	   */
	  private List<ApSettleDetailVO> createDetailVOs(AllotFeePackVO packvo,
	      Map<String, UFDouble> culmnymap, Map<String, UFDouble> feemap) {
	    List<ApSettleDetailVO> results = new ArrayList<ApSettleDetailVO>();
	    ICalculateBillView[] views = packvo.getViews();
	    for (ICalculateBillView view : views) {
	      // 分摊依据值
	      UFDouble culmny = culmnymap.get(view.getcbill_bid());
	      // 运费金额
	      UFDouble feemny = feemap.get(view.getcbill_bid());
	      ApSettleDetailVO vo = new ApSettleDetailVO();
	      vo.setVbillcode(view.getvdelivbillcode());
	      vo.setCfeeitemid(packvo.getFeeitemid());
	      vo.setNfeemny(feemny);
	      vo.setFallotflag(packvo.getAllotflag());
	      // 记录分摊依据值，在 结算单修改费用项金额重算时，不需要在取策略定义再取去分摊依据然后计算分摊依据值
	      vo.setVallotvalue(culmny.toString());
	      vo.setIgroupingno(Integer.valueOf(packvo.getNum()));
	      vo.setCbillareaid(view.getcbillhid());
	      // 存储计算金额，是为了 结算单修改费用项金额 重算在各组间分摊时使用， 用最开始从运输单计算出的金额最标准
	      // 因为如果直接用金额，可能因为修改后 又处理过尾差，所以比例和最初不同。
	      vo.setNcalculatemny(feemny);
	      for (String key : DMCalculConstant.SAMEFIELDS) {
	        Object value =
	            this.getvaluebykey(ICalculateBillView.class.getName(), key, view);
	        vo.setAttributeValue(key, value);
	      }
	      results.add(vo);
	    }
	    return results;
	  }
	}
