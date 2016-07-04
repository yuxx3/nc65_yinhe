package nc.vo.bd.cust.refmanage;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2016-3-31
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class RefManageVO extends SuperVO {
	
/**
*参照维护主键
*/
public String pk_refmanage;
/**
*编码
*/
public String code;
/**
*名称
*/
public String name;
/**
*集团
*/
public String pk_group;
/**
*机构
*/
public String pk_org;
/**
*机构版本
*/
public String pk_org_v;
/**
*参数所属业务类型
*/
public String busitype;
/**
*标准月份天数 
*/
public Integer monthdaynum;
/**
*处罚延期月数
*/
public Integer punishdelaym;
/**
*预警天数
*/
public Integer alertdays;
/**
*开票延迟处罚率
*/
public UFDouble billlatepunish;
/**
*回收提成率
*/
public UFDouble recoverycom;
/**
*应收款超期处罚率
*/
public UFDouble recacountlate;
/**
*自定义项0
*/
public String def0;
/**
*自定义项1
*/
public String def1;
/**
*自定义项2
*/
public String def2;
/**
*自定义项3
*/
public String def3;
/**
*自定义项4
*/
public String def4;
/**
*自定义项5
*/
public String def5;
/**
*自定义项6
*/
public String def6;
/**
*自定义项7
*/
public String def7;
/**
*自定义项8
*/
public String def8;
/**
*自定义项9
*/
public String def9;
/**
*创建人
*/
public String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*最后修改人
*/
public String modifier;
/**
*最后修改时间
*/
public UFDateTime modifiedtime;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_refmanage的Getter方法.属性名：参照维护主键
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getPk_refmanage() {
return this.pk_refmanage;
} 

/**
* 属性pk_refmanage的Setter方法.属性名：参照维护主键
* 创建日期:2016-3-31
* @param newPk_refmanage java.lang.String
*/
public void setPk_refmanage ( String pk_refmanage) {
this.pk_refmanage=pk_refmanage;
} 
 
/**
* 属性 code的Getter方法.属性名：编码
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* 属性code的Setter方法.属性名：编码
* 创建日期:2016-3-31
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* 属性 name的Getter方法.属性名：名称
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* 属性name的Setter方法.属性名：名称
* 创建日期:2016-3-31
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2016-3-31
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2016-3-31
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：机构
*  创建日期:2016-3-31
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：机构
* 创建日期:2016-3-31
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：机构版本
*  创建日期:2016-3-31
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：机构版本
* 创建日期:2016-3-31
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 busitype的Getter方法.属性名：参数所属业务类型
*  创建日期:2016-3-31
* @return nc.vo.bd.cust.refmanage.BusiTypeEnum
*/
public String getBusitype() {
return this.busitype;
} 

/**
* 属性busitype的Setter方法.属性名：参数所属业务类型
* 创建日期:2016-3-31
* @param newBusitype nc.vo.bd.cust.refmanage.BusiTypeEnum
*/
public void setBusitype ( String busitype) {
this.busitype=busitype;
} 
 
/**
* 属性 monthdaynum的Getter方法.属性名：标准月份天数 
*  创建日期:2016-3-31
* @return java.lang.Integer
*/
public Integer getMonthdaynum() {
return this.monthdaynum;
} 

/**
* 属性monthdaynum的Setter方法.属性名：标准月份天数 
* 创建日期:2016-3-31
* @param newMonthdaynum java.lang.Integer
*/
public void setMonthdaynum ( Integer monthdaynum) {
this.monthdaynum=monthdaynum;
} 
 
/**
* 属性 punishdelaym的Getter方法.属性名：处罚延期月数
*  创建日期:2016-3-31
* @return java.lang.Integer
*/
public Integer getPunishdelaym() {
return this.punishdelaym;
} 

/**
* 属性punishdelaym的Setter方法.属性名：处罚延期月数
* 创建日期:2016-3-31
* @param newPunishdelaym java.lang.Integer
*/
public void setPunishdelaym ( Integer punishdelaym) {
this.punishdelaym=punishdelaym;
} 
 
/**
* 属性 alertdays的Getter方法.属性名：预警天数
*  创建日期:2016-3-31
* @return java.lang.Integer
*/
public Integer getAlertdays() {
return this.alertdays;
} 

/**
* 属性alertdays的Setter方法.属性名：预警天数
* 创建日期:2016-3-31
* @param newAlertdays java.lang.Integer
*/
public void setAlertdays ( Integer alertdays) {
this.alertdays=alertdays;
} 
 
/**
* 属性 billlatepunish的Getter方法.属性名：开票延迟处罚率
*  创建日期:2016-3-31
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getBilllatepunish() {
return this.billlatepunish;
} 

/**
* 属性billlatepunish的Setter方法.属性名：开票延迟处罚率
* 创建日期:2016-3-31
* @param newBilllatepunish nc.vo.pub.lang.UFDouble
*/
public void setBilllatepunish ( UFDouble billlatepunish) {
this.billlatepunish=billlatepunish;
} 
 
/**
* 属性 recoverycom的Getter方法.属性名：回收提成率
*  创建日期:2016-3-31
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getRecoverycom() {
return this.recoverycom;
} 

/**
* 属性recoverycom的Setter方法.属性名：回收提成率
* 创建日期:2016-3-31
* @param newRecoverycom nc.vo.pub.lang.UFDouble
*/
public void setRecoverycom ( UFDouble recoverycom) {
this.recoverycom=recoverycom;
} 
 
/**
* 属性 recacountlate的Getter方法.属性名：应收款超期处罚率
*  创建日期:2016-3-31
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getRecacountlate() {
return this.recacountlate;
} 

/**
* 属性recacountlate的Setter方法.属性名：应收款超期处罚率
* 创建日期:2016-3-31
* @param newRecacountlate nc.vo.pub.lang.UFDouble
*/
public void setRecacountlate ( UFDouble recacountlate) {
this.recacountlate=recacountlate;
} 
 
/**
* 属性 def0的Getter方法.属性名：自定义项0
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef0() {
return this.def0;
} 

/**
* 属性def0的Setter方法.属性名：自定义项0
* 创建日期:2016-3-31
* @param newDef0 java.lang.String
*/
public void setDef0 ( String def0) {
this.def0=def0;
} 
 
/**
* 属性 def1的Getter方法.属性名：自定义项1
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef1() {
return this.def1;
} 

/**
* 属性def1的Setter方法.属性名：自定义项1
* 创建日期:2016-3-31
* @param newDef1 java.lang.String
*/
public void setDef1 ( String def1) {
this.def1=def1;
} 
 
/**
* 属性 def2的Getter方法.属性名：自定义项2
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef2() {
return this.def2;
} 

/**
* 属性def2的Setter方法.属性名：自定义项2
* 创建日期:2016-3-31
* @param newDef2 java.lang.String
*/
public void setDef2 ( String def2) {
this.def2=def2;
} 
 
/**
* 属性 def3的Getter方法.属性名：自定义项3
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef3() {
return this.def3;
} 

/**
* 属性def3的Setter方法.属性名：自定义项3
* 创建日期:2016-3-31
* @param newDef3 java.lang.String
*/
public void setDef3 ( String def3) {
this.def3=def3;
} 
 
/**
* 属性 def4的Getter方法.属性名：自定义项4
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef4() {
return this.def4;
} 

/**
* 属性def4的Setter方法.属性名：自定义项4
* 创建日期:2016-3-31
* @param newDef4 java.lang.String
*/
public void setDef4 ( String def4) {
this.def4=def4;
} 
 
/**
* 属性 def5的Getter方法.属性名：自定义项5
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef5() {
return this.def5;
} 

/**
* 属性def5的Setter方法.属性名：自定义项5
* 创建日期:2016-3-31
* @param newDef5 java.lang.String
*/
public void setDef5 ( String def5) {
this.def5=def5;
} 
 
/**
* 属性 def6的Getter方法.属性名：自定义项6
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef6() {
return this.def6;
} 

/**
* 属性def6的Setter方法.属性名：自定义项6
* 创建日期:2016-3-31
* @param newDef6 java.lang.String
*/
public void setDef6 ( String def6) {
this.def6=def6;
} 
 
/**
* 属性 def7的Getter方法.属性名：自定义项7
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef7() {
return this.def7;
} 

/**
* 属性def7的Setter方法.属性名：自定义项7
* 创建日期:2016-3-31
* @param newDef7 java.lang.String
*/
public void setDef7 ( String def7) {
this.def7=def7;
} 
 
/**
* 属性 def8的Getter方法.属性名：自定义项8
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef8() {
return this.def8;
} 

/**
* 属性def8的Setter方法.属性名：自定义项8
* 创建日期:2016-3-31
* @param newDef8 java.lang.String
*/
public void setDef8 ( String def8) {
this.def8=def8;
} 
 
/**
* 属性 def9的Getter方法.属性名：自定义项9
*  创建日期:2016-3-31
* @return java.lang.String
*/
public String getDef9() {
return this.def9;
} 

/**
* 属性def9的Setter方法.属性名：自定义项9
* 创建日期:2016-3-31
* @param newDef9 java.lang.String
*/
public void setDef9 ( String def9) {
this.def9=def9;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2016-3-31
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2016-3-31
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2016-3-31
* @return nc.vo.pub.lang.UFDate
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2016-3-31
* @param newCreationtime nc.vo.pub.lang.UFDate
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：最后修改人
*  创建日期:2016-3-31
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：最后修改人
* 创建日期:2016-3-31
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：最后修改时间
*  创建日期:2016-3-31
* @return nc.vo.pub.lang.UFDate
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：最后修改时间
* 创建日期:2016-3-31
* @param newModifiedtime nc.vo.pub.lang.UFDate
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2016-3-31
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2016-3-31
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("uapbd.RefManageVO");
    }
   }
    