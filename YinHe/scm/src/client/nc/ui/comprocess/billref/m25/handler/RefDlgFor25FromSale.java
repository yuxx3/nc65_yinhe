/**
 * 
 */
package nc.ui.comprocess.billref.m25.handler;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * @author niangzi
 * @created at 2016年4月25日,下午4:40:33
 *
 */
public class RefDlgFor25FromSale extends SourceRefDlg {	
	
	private static final long serialVersionUID = -8875870558730226731L;

	public RefDlgFor25FromSale(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}
	
	@Override
	public String getRefBillInfoBeanPath() {
		// TODO 自动生成的方法存根
		return "nc/ui/comprocess/billref/m25/handler/refinfo.xml";
//		return "nc/ui/m25/maintain/model/refinfo47For25.xml";
				
				
	}
	
}
