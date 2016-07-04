/**
 * 
 */
package nc.impl.uapbd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import nc.itf.uapbd.IDataSych;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.vo.pub.BusinessException;


/**
 * @author yuxx3
 * @created at 2016年4月14日,下午4:32:09
 *
 */
public class DataSychImpl implements IDataSych {

	@Override
	public void MateSych() throws BusinessException {
		String sql = "call yh_synchronous_bd_from_plm.synchronous_material()";
		execute(sql);
	}

	@Override
	public void BomSych() throws BusinessException {
		String sql = "call yh_synchronous_bd_from_plm.synchronous_bom()";
		execute(sql);
	}
	
	private void execute(String sql)throws BusinessException{
		try {
			PersistenceManager sessionManager=PersistenceManager.getInstance ();
			JdbcSession session = sessionManager.getJdbcSession ();
			Connection conn=session.getConnection();
			CallableStatement cstmt;
			cstmt = conn.prepareCall(sql);
			cstmt.execute();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}

}
