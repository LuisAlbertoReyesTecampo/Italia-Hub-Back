package italia.hub.api.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepositorySupport {


	private DataSource dataSource;

	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);

	}

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
