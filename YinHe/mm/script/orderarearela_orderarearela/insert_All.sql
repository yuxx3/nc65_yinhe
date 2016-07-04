INSERT INTO sm_funcregister (ts, funtype, isbuttonpower, fun_name, funcode, isenable, iscauserusable, fun_desc, class_name, fun_property, isfunctype, orgtypecode, cfunid, pk_group, help_name, mdid, dr, own_module, parent_id ) VALUES ('2016-05-26 14:42:28', 0, 'N', '字段区域设置', '50081002', 'Y', null, null, 'nc.ui.pubapp.uif2app.ToftPanelAdaptorEx', 0, 'N', 'GLOBLE00000000000000', '0001ZZ1000000000FY2P', '~', null, '~', 0, '5008', '1001D110000000016QSG' );
INSERT INTO sm_paramregister (ts, paramname, pk_param, parentid, paramvalue, dr ) VALUES ('2016-05-26 14:42:30', 'BeanConfigFilePath', '0001ZZ1000000000FY2Q', '0001ZZ1000000000FY2P', 'nc/ui/uapbd/orderarearela/ace/view/Orderarearela_config.xml', 0 );
INSERT INTO sm_menuitemreg (ts, nodeorder, pk_menu, menuitemname, iconpath, resid, pk_menuitem, ismenutype, menudes, funcode, menuitemcode, dr ) VALUES ('2016-05-26 14:42:31', null, '1001D110000000016QSI', '字段区域设置', null, 'D50081002', '0001ZZ1000000000FY2R', 'N', null, '50081002', '50081002', 0 );
INSERT INTO pub_billtemplet (ts, BILL_TEMPLETCAPTION, BILL_TEMPLETNAME, NODECODE, PK_BILLTEMPLET, PK_BILLTYPECODE, PK_CORP, METADATACLASS, MODULECODE, LAYER ) VALUES ('2016-05-26 14:43:11', '广电订单字段所属区域', 'SYSTEM', '50081002', '0001ZZ1000000000FY2S', '50081002', '@@@@', 'uapbd.orderrelation', '5008', 0 );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'pk_rela', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY2T', 1, 'N', 'N', 0, 1, 1, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.pk_rela', 'pk_rela', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'pk_group', 'N', 1, 'Y', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY2U', 1, 'N', 'N', 1, 2, 2, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.pk_group', 'pk_group', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'pk_org', 'N', 1, 'Y', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY2V', 1, 'N', 'N', 1, 3, 3, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.pk_org', 'pk_org', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'gdcode', 'N', 1, 'Y', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY2W', 1, 'N', 'N', 1, 4, 4, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.gdcode', 'gdcode', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def0', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY2X', 1, 'N', 'N', 0, 5, 5, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def0', 'def0', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def1', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY2Y', 1, 'N', 'N', 0, 6, 6, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def1', 'def1', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def2', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY2Z', 1, 'N', 'N', 0, 7, 7, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def2', 'def2', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def3', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY30', 1, 'N', 'N', 0, 8, 8, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def3', 'def3', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def4', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY31', 1, 'N', 'N', 0, 9, 9, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def4', 'def4', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def5', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY32', 1, 'N', 'N', 0, 10, 10, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def5', 'def5', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def6', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY33', 1, 'N', 'N', 0, 11, 11, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def6', 'def6', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def7', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY34', 1, 'N', 'N', 0, 12, 12, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def7', 'def7', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def8', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY35', 1, 'N', 'N', 0, 13, 13, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def8', 'def8', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'def9', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY36', 1, 'N', 'N', 0, 14, 14, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.def9', 'def9', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'creator', 'N', 1, 'Y', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY37', 1, 'N', 'N', 1, 15, 15, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.creator', 'creator', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'creationtime', 'N', 1, 'Y', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY38', 1, 'N', 'N', 1, 16, 16, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.creationtime', 'creationtime', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'modifier', 'N', 1, 'Y', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY39', 1, 'N', 'N', 1, 17, 17, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.modifier', 'modifier', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'modifiedtime', 'N', 1, 'Y', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY3A', 1, 'N', 'N', 1, 18, 18, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.modifiedtime', 'modifiedtime', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-05-26 14:43:11', 1, -1, 1, -1, -1, 'ts', 'N', 1, 'N', 0, 'N', 0, '0001ZZ1000000000FY2S', '0001ZZ1000000000FY3B', 1, 'N', 'N', 0, 19, 19, 'orderrelation', '广电订单字段所属区域', 0, 'N', 1, 1, 1, 100, 'uapbd.orderrelation.ts', 'ts', 'N', 'N' );
INSERT INTO pub_billtemplet_t (ts, position, resid, tabindex, vdef2, vdef1, pk_billtemplet_t, metadatapath, vdef3, tabcode, pk_billtemplet, pos, metadataclass, basetab, mixindex, tabname, pk_layout ) VALUES ('2016-05-26 14:43:14', null, null, 0, null, null, '0001ZZ1000000000FY3C', 'orderrelation', null, 'orderrelation', '0001ZZ1000000000FY2S', 1, 'uapbd.orderrelation', null, null, '广电订单字段所属区域', '~' );
INSERT INTO pub_systemplate_base (ts, nodekey, funnode, layer, moduleid, templateid, pk_systemplate, devorg, pk_country, pk_industry, tempstyle, dr ) VALUES ('2016-05-26 14:43:14', 'bt', '50081002', 0, '5008', '0001ZZ1000000000FY2S', '0001ZZ1000000000FY3D', '00001', '~', '~', 0, 0 );
