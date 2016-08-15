/**
 * 
 */
package nc.ui.mmpac.dmo.view;

import java.util.List;

import javax.swing.Action;
import nc.ui.mm.ref.pbref.PbRefPane;
import nc.ui.mmf.framework.view.BillFormFacade;
import nc.ui.mmpac.dmo.model.DmoBillManageModel;
import nc.ui.mmpac.dmo.scale.ProcedureScleTimeutil;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.itemeditors.UFRefBillItemEditor;
import nc.ui.pubapp.uif2app.actions.AbstractBodyTableExtendAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.util.mmf.framework.base.MMNumberUtil;
import nc.util.mmf.framework.base.MMStringUtil;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.mmpac.dmo.entity.AggDmoVO;
import nc.vo.mmpac.dmo.entity.DmoVO;
import nc.vo.mmpac.dmo.enumeration.DmoStatusEnum;
import nc.vo.pub.lang.UFDouble;

/**
 * @author yuxx
 * @created at 2016-8-8,下午7:33:17
 *
 */
public class DmoBillFormEditor extends BillFormFacade {

    @Override
    public void initUI() {
        super.initUI();
        this.scaleProcess();
        
    }

    @Override
    protected void onAdd() {

        super.onAdd();
        this.setBillItemPropertyForAdd();
        // 特殊情况一些前台控制不可修改项影响新增，需要在这里重新打开，如返工补丁
        this.setBillItemPropertyForBYPUSHCLOSE();
        // 发布了交易类型节点的订单类型不允许编辑
        String trantype = TrantypeFuncUtils.getTrantype(this.getModel().getContext());
        if (MMStringUtil.isNotEmpty(trantype)) {
            // 平台控制不了不可编辑性，特在此增加；
            this.getHeadItem(DmoVO.VTRANTYPEID).setEdit(false);
            this.getHeadItem(DmoVO.VTRANTYPECODE).setEdit(false);
            return;
        }
    }

    @Override
    public void setValue(Object object) {
        super.setValue(object);
        AggDmoVO aggVO = (AggDmoVO) object;
        if (aggVO == null) {
            return;
        }
        DmoVO vo = aggVO.getParentVO();
        String batchCode = vo.getVbatchcode();
        BillItem billItem = this.getHeadItem(DmoVO.VBATCHCODE);
        UIRefPane refPane = (UIRefPane) billItem.getComponent();
        refPane.getUITextField().setText(batchCode);
    }

    @Override
    protected void onEdit() {

        super.onEdit();
        this.setBillItemPropertyForEdit();
        // this.setEditEnableForEdit();
    }

    /***
     * 修改时投放态可以修改的项目：<br>
     * 预计完工日期，备注
     */
    public static final String[] EDITABLEITEMKEYS_PUTSTATE = new String[] {
        /* DmoVO.VBATCHCODE, */DmoVO.TWILLENDTIME, DmoVO.VNOTE, DmoVO.VPRODBATDEF1, DmoVO.VPRODBATDEF2,
        DmoVO.VPRODBATDEF3, DmoVO.VPRODBATDEF4, DmoVO.VPRODBATDEF5, DmoVO.VPRODBATDEF6, DmoVO.VPRODBATDEF7,
        DmoVO.VPRODBATDEF8, DmoVO.VPRODBATDEF9, DmoVO.VPRODBATDEF10, DmoVO.VPRODBATDEF11, DmoVO.VPRODBATDEF12,
        DmoVO.VPRODBATDEF13, DmoVO.VPRODBATDEF14, DmoVO.VPRODBATDEF15, DmoVO.VPRODBATDEF16, DmoVO.VPRODBATDEF17,
        DmoVO.VPRODBATDEF18, DmoVO.VPRODBATDEF19, DmoVO.VPRODBATDEF20, DmoVO.VPRODBATNOTE,DmoVO.VDEF2 // @edit by yuxx at 2016-8-8,下午7:34:07 添加订单类别说明
    };

    /***
     * 变更时 投放态可以修改的项目：<br>
     * 预计完工日期，备注
     */
    public static final String[] REVISEABLEITEMKEYS_PUTSTATE = new String[] {
        DmoVO.TPLANENDTIME, DmoVO.NMMASTNUM, DmoVO.NPLANPUTASTNUM, DmoVO.NMMNUM, DmoVO.NPLANPUTNUM, DmoVO.TWILLENDTIME,
        DmoVO.VNOTE,DmoVO.VDEF2 // @edit by yuxx at 2016-8-8,下午7:34:07 添加订单类别说明
    };

    /***
     * 特殊情况一些前台控制不可修改项影响新增，需要在这里重新打开，如返工补丁
     */
    public static final String[] ADDOPENBLEITEMKEYSBYPUSHCLOSE = new String[] {
        DmoVO.VTRANTYPEID, DmoVO.CMATERIALVID, DmoVO.NMMNUM, DmoVO.NMMASTNUM
    };

    /***
     * 修改审核态可以修改的项目：<br>
     * 预计完工日期，备注
     */
    public static final String[] EDITABLEITEMKEYS_AUDITSTATE = new String[] {
        DmoVO.NMMASTNUM, DmoVO.NPLANPUTASTNUM, DmoVO.NMMNUM, DmoVO.NPLANPUTNUM, DmoVO.TPLANENDTIME,
        DmoVO.TPLANSTARTTIME, DmoVO.VBATCHCODE, DmoVO.TWILLENDTIME, DmoVO.VNOTE, DmoVO.VPRODBATDEF1,
        DmoVO.VPRODBATDEF2, DmoVO.VPRODBATDEF3, DmoVO.VPRODBATDEF4, DmoVO.VPRODBATDEF5, DmoVO.VPRODBATDEF6,
        DmoVO.VPRODBATDEF7, DmoVO.VPRODBATDEF8, DmoVO.VPRODBATDEF9, DmoVO.VPRODBATDEF10, DmoVO.VPRODBATDEF11,
        DmoVO.VPRODBATDEF12, DmoVO.VPRODBATDEF13, DmoVO.VPRODBATDEF14, DmoVO.VPRODBATDEF15, DmoVO.VPRODBATDEF16,
        DmoVO.VPRODBATDEF17, DmoVO.VPRODBATDEF18, DmoVO.VPRODBATDEF19, DmoVO.VPRODBATDEF20, DmoVO.VPRODBATNOTE,
        DmoVO.VDEF2 // @edit by yuxx at 2016-8-8,下午7:34:07 添加订单类别说明
    };

    /***
     * 变更审核态可以修改的项目：<br>
     * 预计完工日期，备注
     */
    public static final String[] REVISEABLEITEMKEYS_AUDITSTATE = new String[] {
        DmoVO.TPLANSTARTTIME, DmoVO.TPLANENDTIME, DmoVO.NMMASTNUM, DmoVO.NPLANPUTASTNUM, DmoVO.NMMNUM,
        DmoVO.NPLANPUTNUM, DmoVO.VBATCHCODE, DmoVO.TWILLENDTIME, DmoVO.VNOTE, DmoVO.VPRODBATDEF1, DmoVO.VPRODBATDEF2,
        DmoVO.VPRODBATDEF3, DmoVO.VPRODBATDEF4, DmoVO.VPRODBATDEF5, DmoVO.VPRODBATDEF6, DmoVO.VPRODBATDEF7,
        DmoVO.VPRODBATDEF8, DmoVO.VPRODBATDEF9, DmoVO.VPRODBATDEF10, DmoVO.VPRODBATDEF11, DmoVO.VPRODBATDEF12,
        DmoVO.VPRODBATDEF13, DmoVO.VPRODBATDEF14, DmoVO.VPRODBATDEF15, DmoVO.VPRODBATDEF16, DmoVO.VPRODBATDEF17,
        DmoVO.VPRODBATDEF18, DmoVO.VPRODBATDEF19, DmoVO.VPRODBATDEF20, DmoVO.VPRODBATNOTE,
        DmoVO.VDEF2 // @edit by yuxx at 2016-8-8,下午7:34:07 添加订单类别说明
    };

    /***
     * 新增时不可以修改的项目：<br>
     */
    public static final String[] EDITABLEITEMKEYS_ADD = new String[] {
        DmoVO.NNUM, DmoVO.NASTNUM, DmoVO.CMATERIALID, DmoVO.VPACKBOMVER, DmoVO.VBOMVERSION, DmoVO.VRTVERSION,
        DmoVO.TACTENDTIME, DmoVO.FBILLSTATUS, DmoVO.TACTSTARTTIME, DmoVO.NWRASTNUM, DmoVO.NWRNUM, DmoVO.NINASTNUM,
        DmoVO.NINNUM, DmoVO.NREWORKDMOASTNUM, DmoVO.NREWORKDMONUM, DmoVO.NDISCARDASTNUM, DmoVO.NDISCARDDMOASTNUM,
        DmoVO.NDISCARDDMONUM, DmoVO.NDISCARDNUM, DmoVO.NZCGASTNUM, DmoVO.NZCGNUM, DmoVO.NZDBASTNUM, DmoVO.NZDBNUM,
        DmoVO.NZWWASTNUM, DmoVO.NZWWNUM, DmoVO.VFIRSTBID, DmoVO.VFIRSTCODE, DmoVO.VFIRSTID, DmoVO.VFIRSTMOCODE,
        DmoVO.VFIRSTMOID, DmoVO.VFIRSTROWNO, DmoVO.VFIRSTTRANTYPECODE, DmoVO.VFIRSTTRANTYPEID, DmoVO.VFIRSTTYPE,
        DmoVO.VSRCBID, DmoVO.VSRCCODE, DmoVO.VSRCID, DmoVO.VSRCROWNO, DmoVO.VSRCTRANTYPECODE, DmoVO.VSRCTRANTYPEID,
        DmoVO.VSRCTYPE, DmoVO.VORIGMOCODE, DmoVO.VORIGMOID, DmoVO.VPARENTMOBID, DmoVO.VPARENTMOID, DmoVO.VPARENTMOCODE,
        DmoVO.VPARENTMOROWNO, DmoVO.VPARENTMOTYPE, DmoVO.VBILLTYPE, DmoVO.DDEMANDTIME, DmoVO.DPLANSUPPLYTIME,
        DmoVO.PK_PLANORG, DmoVO.PK_DEMANDORG, DmoVO.PK_DEMANDORG_V, DmoVO.VERSION, DmoVO.CECNID,
        DmoVO.VDEF2 // @edit by yuxx at 2016-8-8,下午7:34:07 添加订单类别说明
    };

    /***
     * 计划态可以修改的项目:<br>
     * 业务员，生产部门，销售订单号，订单类型，辅单位，计划开工日期，计划开始时间，计划完工日期，<br>
     * 计划结束时间，数量，计划投入数量，预计完工日期，，批次号，是否倒冲[PDM中没有该字段啊！！2010-1-29 10:00:25]，<br>
     * 是否加急，BOM版本，工艺路线版本，计划工厂<br>
     * 优先级，客户，资料，备注，自由项
     */
    public static final String[] EDITABLEITEMKEYS_PLANSATE = new String[] {
        DmoVO.NMMNUM, DmoVO.VCHANGERATE, DmoVO.CPLANNERID, DmoVO.CDEPTVID, DmoVO.CASTUNITID, DmoVO.TPLANSTARTTIME,
        DmoVO.TPLANENDTIME, DmoVO.NMMASTNUM, DmoVO.TWILLENDTIME, DmoVO.VBATCHCODE, DmoVO.NPLANPUTASTNUM,
        DmoVO.NPLANPUTNUM, DmoVO.CBOMVERSIONID, DmoVO.CRTVERSIONID, DmoVO.CCUSTOMERID, DmoVO.CFFILEID,
        DmoVO.NSCRAPFACTOR, DmoVO.CPACKBOMVID, DmoVO.CPDMOPROCEDURENO,
        /* DmoVO.VDOCADDRESS, */DmoVO.VNOTE, DmoVO.VFREE1, DmoVO.VFREE2, DmoVO.VFREE3, DmoVO.VFREE4, DmoVO.VFREE5,
        DmoVO.VFREE6, DmoVO.VFREE7, DmoVO.VFREE8, DmoVO.VFREE9, DmoVO.VFREE10,
        /* DmoVO.CPLANFACTORYVID, */DmoVO.CPROJECTID, DmoVO.CPRODUCTORID, DmoVO.CVENDORID, DmoVO.VDEF1, DmoVO.VDEF2,
        DmoVO.VDEF3, DmoVO.VDEF4, DmoVO.VDEF5, DmoVO.VDEF6, DmoVO.VDEF7, DmoVO.VDEF8, DmoVO.VDEF9, DmoVO.VDEF10,
        DmoVO.VDEF11, DmoVO.VDEF12, DmoVO.VDEF13, DmoVO.VDEF14, DmoVO.VDEF15, DmoVO.VDEF16, DmoVO.VDEF17, DmoVO.VDEF18,
        DmoVO.VDEF19, DmoVO.VDEF20, DmoVO.BURGENT, DmoVO.VPRODBATDEF1, DmoVO.VPRODBATDEF2, DmoVO.VPRODBATDEF3,
        DmoVO.VPRODBATDEF4, DmoVO.VPRODBATDEF5, DmoVO.VPRODBATDEF6, DmoVO.VPRODBATDEF7, DmoVO.VPRODBATDEF8,
        DmoVO.VPRODBATDEF9, DmoVO.VPRODBATDEF10, DmoVO.VPRODBATDEF11, DmoVO.VPRODBATDEF12, DmoVO.VPRODBATDEF13,
        DmoVO.VPRODBATDEF14, DmoVO.VPRODBATDEF15, DmoVO.VPRODBATDEF16, DmoVO.VPRODBATDEF17, DmoVO.VPRODBATDEF18,
        DmoVO.VPRODBATDEF19, DmoVO.VPRODBATDEF20, DmoVO.VPRODBATNOTE, DmoVO.CFFILEID,
        DmoVO.VDEF2 // @edit by yuxx at 2016-8-8,下午7:34:07 添加订单类别说明
    };

    /***
     * 计划态产能分流后可以修改的项目:<br>
     * 业务员，生产部门，工作中心，销售订单号，订单类型，计划开工日期，计划开始时间，计划完工日期，<br>
     * 计划结束时间，数量，计划投入数量，预计完工日期，班次，班组，批次号，是否倒冲[PDM中没有该字段啊！！2010-1-29 10:00:25]，<br>
     * 是否加急，计划工厂<br>
     * 优先级，客户，资料，备注，自由项
     */
    public static final String[] EDITABLEITEMKEYS_PLANSATEANDTURN = new String[] {
        DmoVO.TPLANSTARTTIME, DmoVO.TPLANENDTIME, DmoVO.NMMASTNUM, DmoVO.TWILLENDTIME, DmoVO.VBATCHCODE,
        DmoVO.NPLANPUTASTNUM, DmoVO.NMMNUM, DmoVO.NPLANPUTASTNUM, DmoVO.NPLANPUTNUM, DmoVO.CCUSTOMERID, DmoVO.CDEPTVID,
        DmoVO.BURGENT, DmoVO.VPRODBATDEF1, DmoVO.VPRODBATDEF2, DmoVO.VPRODBATDEF3, DmoVO.VPRODBATDEF4,
        DmoVO.VPRODBATDEF5, DmoVO.VPRODBATDEF6, DmoVO.VPRODBATDEF7, DmoVO.VPRODBATDEF8, DmoVO.VPRODBATDEF9,
        DmoVO.VPRODBATDEF10, DmoVO.VPRODBATDEF11, DmoVO.VPRODBATDEF12, DmoVO.VPRODBATDEF13, DmoVO.VPRODBATDEF14,
        DmoVO.VPRODBATDEF15, DmoVO.VPRODBATDEF16, DmoVO.VPRODBATDEF17, DmoVO.VPRODBATDEF18, DmoVO.VPRODBATDEF19,
        DmoVO.VPRODBATDEF20, DmoVO.VPRODBATNOTE, 
        DmoVO.VNOTE,DmoVO.VDEF2 // @edit by yuxx at 2016-8-8,下午7:34:07 添加订单类别说明
    };

    /***
     * 设置生产订单新增时的BillItem的一些属性不可编辑属性
     */
    private void setBillItemPropertyForAdd() {

        if (!MMValueCheck.isEmpty(this.billCardPanel.getHeadItem(DmoVO.PK_ORG).getValueObject())) {
            for (String itemKey : DmoBillFormEditor.EDITABLEITEMKEYS_ADD) {
                this.getBillCardPanel().getHeadItem(itemKey).setEdit(false);
            }
            this.getBillCardPanel().getHeadItem(DmoVO.CMATERIALVID).setEdit(true);
          
        }
    }

    /***
     * 特殊情况一些前台控制不可修改项影响新增，需要在这里重新打开，如返工补丁
     */
    private void setBillItemPropertyForBYPUSHCLOSE() {

        if (!MMValueCheck.isEmpty(this.billCardPanel.getHeadItem(DmoVO.PK_ORG).getValueObject())) {
            for (String itemKey : DmoBillFormEditor.ADDOPENBLEITEMKEYSBYPUSHCLOSE) {
                this.getBillCardPanel().getHeadItem(itemKey).setEdit(true);
            }
        }
    }

    /***
     * 设置生产订单新增时的BillItem的一些属性不可编辑属性
     */
    private void setBillItemPropertyForEdit() {

        DmoVO vo = this.getSelectedDmoVO();
        if (DmoStatusEnum.FREE.equalsValue(vo.getFbillstatus())) {
            this.setBillItemPropertyForPlanState(true, vo);
        }
        else if (DmoStatusEnum.AUDIT.equalsValue(vo.getFbillstatus())) {
            this.setBillItemPropertyForAuditState(vo);
            // this.bodyActionEnableControl();
        }
        else if (DmoStatusEnum.PUT.equalsValue(vo.getFbillstatus())) {
            this.setBillItemPropertyForPlanState(false, vo);
            // this.bodyActionEnableControl();
        }
    }

    @Override
    protected void processBillData(nc.ui.pub.bill.BillData data) {
        /*
         * BillItem hslItem = data.getHeadItem(DmoVO.VCHANGERATE);
         * MetaDataEditPropertyAdpter metaDataProperty = new MetaDataEditPropertyAdpter(hslItem.getMetaDataProperty());
         * metaDataProperty.setDatatype(IBillItem.FRACTION);
         * hslItem.setMetaDataProperty(metaDataProperty);
         */
        // 控制模板中生产批次号参照
        PbRefPane refpane = new PbRefPane();
        BillItem billItem = data.getHeadItem(DmoVO.VBATCHCODE);
        UFRefBillItemEditor editor = new UFRefBillItemEditor(billItem);
        editor.setComponent(refpane);
        refpane.setAutoCheck(false);
        refpane.setAutoscrolls(false);
        refpane.revalidate();
        refpane.setFormat(false);
        billItem.setComponent(refpane);
        billItem.setItemEditor(editor);
    }

    /**
     * 表体按钮控制编辑性
     */
    private void bodyActionEnableControl() {
        List<Action> actionList = this.getBodyLineActions();
        if (MMValueCheck.isEmpty(actionList)) {
            return;
        }
        if (actionList.size() == 10) {
            actionList.remove(8);// 移除空格按钮
            actionList.remove(6);// 移除空格按钮
        }
        AbstractBodyTableExtendAction[] actions = actionList.toArray(new AbstractBodyTableExtendAction[0]);
        for (AbstractBodyTableExtendAction action : actions) {
            action.setEnabled(false);
        }
    }

    /**
     * @param vo
     */
    private void setBillItemPropertyForAuditState(DmoVO vo) {
        this.billCardPanel.setEnabled(false);
        DmoBillManageModel model = (DmoBillManageModel) this.getModel();
        if (model.isAdjust()) {
            for (String itemKey : DmoBillFormEditor.REVISEABLEITEMKEYS_AUDITSTATE) {

                this.billCardPanel.getHeadItem(itemKey).setEnabled(true);
            }
        }
        else {
            for (String itemKey : DmoBillFormEditor.EDITABLEITEMKEYS_AUDITSTATE) {

                this.billCardPanel.getHeadItem(itemKey).setEnabled(true);
            }
        }
        this.getBillCardPanel().getBillModel("pk_procedure").setEnabled(true);
        // this.billCardPanel.getBodyPanel().setEnabled(isEditable());
    }

    /***
     * 设置计划态和投放态生产订单修改时的BillItem的一些属性
     * 
     * @param isPlan
     *            :boolean
     */
    private void setBillItemPropertyForPlanState(boolean isPlan, DmoVO vo) {
        if (isPlan) {
            this.billCardPanel.setEnabled(false);
            UFDouble divNum = MMNumberUtil.add(vo.getNzwwnum(), vo.getNzcgnum());
            if (MMNumberUtil.isGtZero(divNum)) {
                for (String itemKey : DmoBillFormEditor.EDITABLEITEMKEYS_PLANSATEANDTURN) {
                    this.billCardPanel.getHeadItem(itemKey).setEnabled(true);
                }
            }
            else {
                for (String itemKey : DmoBillFormEditor.EDITABLEITEMKEYS_PLANSATE) {
                    this.billCardPanel.getHeadItem(itemKey).setEnabled(true);

                }
            }
            this.getBillCardPanel().getBillModel("pk_procedure").setEnabled(true);
        }
        else {
            this.billCardPanel.setEnabled(false);

            DmoBillManageModel model = (DmoBillManageModel) this.getModel();
            if (model.isAdjust()) {
                for (String itemKey : DmoBillFormEditor.REVISEABLEITEMKEYS_PUTSTATE) {

                    this.billCardPanel.getHeadItem(itemKey).setEnabled(true);
                }
            }
            else {
                for (String itemKey : DmoBillFormEditor.EDITABLEITEMKEYS_PUTSTATE) {

                    this.billCardPanel.getHeadItem(itemKey).setEnabled(true);
                }
            }
            this.getBillCardPanel().getBillModel("pk_procedure").setEnabled(true);
        }
    }

    private DmoVO getSelectedDmoVO() {
        Object obj = this.getModel().getSelectedData();
        DmoVO vo = null;
        if (obj instanceof AggDmoVO) {
            AggDmoVO aggvo = (AggDmoVO) obj;
            vo = aggvo.getParentVO();
        }
        else {
            vo = (DmoVO) obj;
        }
        return vo;

    }

    /**
     * 精度处理
     */
    private void scaleProcess() {
        new ProcedureScleTimeutil().setCardScale(this);
    }
}

