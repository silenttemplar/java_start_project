package Human;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dao_Interface {
	public ResultSet getStudentList() throws SQLException;
}
