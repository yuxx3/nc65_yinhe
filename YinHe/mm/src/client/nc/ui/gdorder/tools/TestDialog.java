/**
 * 
 */
package nc.ui.gdorder.tools;


/**
 * @author yuxx3
 * @created at 2016年6月4日,下午1:43:54
 *
 */
public class TestDialog {

	/**
	 * @create by yuxx3 at 2016年6月4日,下午1:43:54
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		/*PawdCheckDialog dlg = new PawdCheckDialog(null);
		dlg.showDialog("test");*/
		CheckPawd dlg = new CheckPawd();
		boolean isok = dlg.isOK("a", "vbillcode");
		
	}

}
