/**
 * 
 */
package nc.ui.m5x.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * @author yuxx3
 * @created at 2016��4��19��,����10:52:13
 *
 */
public class RefDlgFor5XFromSale extends SourceRefDlg {
	private static final long serialVersionUID = -5268323918608604962L;
	public RefDlgFor5XFromSale(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}
	 @Override
	public String getRefBillInfoBeanPath() {
	    return "nc/ui/m5x/billref/refinfo.xml";
	}
}
