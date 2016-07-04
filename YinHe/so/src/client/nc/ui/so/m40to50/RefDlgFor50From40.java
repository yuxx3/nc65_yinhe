/**
 * 
 */
package nc.ui.so.m40to50;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * @author yuxx3
 * @created at 2016年4月19日,上午10:52:13
 *
 */
public class RefDlgFor50From40 extends SourceRefDlg {
	private static final long serialVersionUID = -5268323918608604962L;
	public RefDlgFor50From40(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}
	 @Override
	public String getRefBillInfoBeanPath() {
	    return "nc/ui/so/m40to50/refinfo.xml";
	}
}
