/**
 * 
 */
package nc.ui.gdorder.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;

/**
 * @author yuxx3
 * @created at 2016年6月4日,下午1:36:34
 *
 */
public class PawdCheckDialog extends UIDialog {
	// 卡片页面
	private BillCardPanel billCardPanel = null;

	// 对话框宽
	private static final int WIDTH = 280;

	// 对话框高
	private static final int HEIGHT = 150;

	// 输入的密码
	private String password = null;
	
	//字段
	private String key = null;
	
	//密码校验是否通过
	private boolean isok = false;

	public PawdCheckDialog(Container parent) {
		super(parent);
		this.setModal(false);
	}

	/**
	 * 初始化Dialog
	 */
	private void initDialog() {
		// 设置对话框主题
		this.setTitle("请输入编辑密码：");
		// 设置最适合的大小
		this.setSize(new Dimension(PawdCheckDialog.WIDTH, PawdCheckDialog.HEIGHT));
		// 设置对话框位置，正中央
		this.setLocationRelativeTo(getParent());

		UIButton okButton = new UIButton("确定");
		// 添加确定按钮事件
		okButton.addMouseListener(new OKListener());
		UIPanel panel = new UIPanel();
		panel.add(okButton);

		this.add(this.billCardPanel, BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);

		// 设置关闭方式
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * 初始化对话框面板中数据
	 */
	private void initialize() {
		// 初始化为空
		if (null == this.billCardPanel) {
			// 初始化卡片单据模板
			this.billCardPanel = new BillCardPanel();
			// 根据id加载配置的单据模板,配置的单据模板id 在表pub_billtemplet的pk_billtemplet字段中获得,bill_templetcaption ='密码校验'
			this.billCardPanel.loadTemplet("1001D110000000018O2K");
			// 增行和删行按钮
			//this.billCardPanel.setBodyMenu(null);
			// 表体不可多选
			this.billCardPanel.setBodyMultiSelect(false);
			// 回车不可自动增行
			this.billCardPanel.setBodyAutoAddLine(false);
		}
	}

	/**
	 * 传入字段key
	 * @create by yuxx3 at 2016年6月4日,下午2:24:30
	 *
	 * @param key
	 * @return
	 */
	public boolean showDialog(String key) {
		//如果无法获取到key，返回false
		if(key.trim().equals("")||null==key){
			return false;
		}
		initialize();
		initDialog();
		this.showModal();
		return isok;
	}

	/**
	 * 
	 * 确定按钮的监听类
	 * 
	 */
	class OKListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			String pawdNew = billCardPanel.getHeadItem("pawd").toString();
			if (pawdNew.trim().equals("")||null==pawdNew) {
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(billCardPanel, "密码错误，请重新输入!");
				return;
			} else {
				isok = true;
				// 隐藏窗体
				setVisible(false);
				// 关闭窗体
				dispose();
			}
		}

	}
}
