package nc.impl.report;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.exception.DbException;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
/**
 * 使用JDBC工具类
 * @author 邵文栋
 *
 */
public class DBUtil{
	/**
	 * 构造函数，使用该类时只能使用该类下的静态方法
	 * 该类中只定义静态方法
	 *
	 */
	private DBUtil() {
		
	}
	  /**
     * 获得Connection
     */
	public static Connection getConn() throws SQLException {
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@192.168.10.220:1521:orcl";
//		String user = "nc65";
//		String pwd = "nc65";
//		Connection conn=null;
//		try {
//			 Class.forName(driver);
//			 conn = DriverManager.getConnection(url, user, pwd);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return conn;
		
		PersistenceManager sessionManager=null;
		Connection conn=null;
		try {
			 sessionManager =PersistenceManager. getInstance ();
			 JdbcSession session = sessionManager.getJdbcSession ();
			 //System.out.println(session);
			 conn=session.getConnection();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭会话
		//	sessionManager.release();  
		}
		return conn;
	}
    /**
     * 关闭 Connection
     * @throws SQLException 
     */
	public static void free(Connection conn) {
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
  
	/**
	 * 释放所有资源,一般的查询
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null){
				rs.close();
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						//conn.commit();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
	/**
	 * 释放所有资源
	 * @param rs
	 * @param conn
	 */
	public static void free(ResultSet rs, Connection conn) {
		try {
			if (rs != null){
				rs.close();
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
				if (conn != null)
					try {
						//conn.commit();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
	 }
	/**
	 * 释放所有资源，调用存储过程时使用
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, CallableStatement st, Connection conn) {
		try {
			if (rs != null){
				rs.close();
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						//conn.commit();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	
	/**
	 * 保存aggVo对象
	 * 
	 * @param aggVO
	 * @author 邵文栋
	 * @throws
	 */
	public static String AdjustSaveBill(AggregatedValueObject aggVO, BaseDAO dao) throws BusinessException {
		if (aggVO == null)
			return null;

		// 单据头VO对象
		SuperVO parentVO = (SuperVO) aggVO.getParentVO();
		// 单据表体VO对象
		SuperVO[] childrenVOs = (SuperVO[]) aggVO.getChildrenVO();

		// 表头和表体都不为空时，现删除表体数据再删除表头数据。
			if (parentVO != null) {
			String key = dao.insertVOWithPK((SuperVO) parentVO);
			insertBodys(childrenVOs, key, parentVO.getPKFieldName(), dao);
			return key;
		} else {
			// 表头不为空,表体为空时,只删除表头数据。
			return null;
		}

	}
	
	/**
	 * 删除数据库中指定的单据AggVO对象
	 * 
	 * @param aggVO
	 * @author 邵文栋
	 * @throws
	 * @retrun 1：成功；2：失败
	 */
	public static int deleteBill(AggregatedValueObject aggVO, BaseDAO dao) throws BusinessException {
		if (aggVO == null)
			return 2;

		// 单据头VO对象
		CircularlyAccessibleValueObject parentVO = aggVO.getParentVO();
		// 单据表体VO对象
		CircularlyAccessibleValueObject[] childrenVOs = aggVO.getChildrenVO();

		// 表头和表体都不为空时，现删除表体数据再删除表头数据。
		if (parentVO != null && (childrenVOs != null && childrenVOs.length > 0)) {
			dao.deleteVOArray((SuperVO[]) childrenVOs);
			dao.deleteVO((SuperVO) parentVO);
			return 1;
		} else if (parentVO != null && (childrenVOs == null || childrenVOs.length < 1)) {
			dao.deleteVO((SuperVO) parentVO);
			// 表头不为空,表体为空时,只删除表头数据。
			return 1;
		} else {
			return 2;
		}
	}
	
	
	/**
	 * 保存表体数据
	 * 
	 * @author 田志强
	 * @param superVO
	 * @param key
	 * @param pkFieldName
	 * @param dao
	 */
	private static void insertBodys(SuperVO[] superVO, String key, String pkFieldName, BaseDAO dao)
			throws BusinessException {
		if (superVO != null) {
			for (int i = 0; i < superVO.length; i++) {
				if (pkFieldName != null) {
					if (superVO[i].getParentPKFieldName() != null)
						superVO[i].setAttributeValue(superVO[i].getParentPKFieldName(), key);
					else
						superVO[i].setAttributeValue(pkFieldName, key);
				}
			}
			dao.insertVOArray(superVO);
		}
	}
	

}
