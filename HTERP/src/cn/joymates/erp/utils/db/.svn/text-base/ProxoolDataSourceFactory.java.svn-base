package cn.joymates.blog.utils.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.logicalcobwebs.proxool.ProxoolDataSource;

/**
 * proxool 、mybaits适配
 * 
 * @author Jacke Hou
 *
 */
public class ProxoolDataSourceFactory implements DataSourceFactory {
    private ProxoolDataSource dataSource;
    
    public ProxoolDataSourceFactory() {
    	this.dataSource = new ProxoolDataSource("MySql5");
    }
    
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setProperties(Properties arg0) {
		
	}
  

}
