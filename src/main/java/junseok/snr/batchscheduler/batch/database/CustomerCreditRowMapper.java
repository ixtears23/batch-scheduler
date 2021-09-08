package junseok.snr.batchscheduler.batch.database;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCreditRowMapper implements RowMapper<CustomerCredit> {

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String CREDIT_COLUMN = "credit";

    @Override
    public CustomerCredit mapRow(ResultSet rs, int rowNum) throws SQLException {
        final CustomerCredit customerCredit = new CustomerCredit();
        customerCredit.setId(rs.getInt(ID_COLUMN));
        customerCredit.setName(rs.getString(NAME_COLUMN));
        customerCredit.setCredit(rs.getBigDecimal(CREDIT_COLUMN));
        return customerCredit;
    }

}
