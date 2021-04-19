package com.riskiq.repository;

import com.riskiq.domain.User;
import com.riskiq.util.RowCountsHelper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * CRUD operations implementation specifically for {@link com.riskiq.domain.User}
 *
 * @author riskiq
 * @created 1/17/18
 */
@Repository
public class UserRepository extends CrudRepository<User, URL> {
    private final RowCountsHelper rowCountsHelper;

    public UserRepository(URL path, RowCountsHelper rowCountsHelper) {
        super(path);
        this.rowCountsHelper = rowCountsHelper;
    }

    @Override
    public long size() {

        long rowCounts = 0;
        try {
            rowCounts = rowCountsHelper.getRowCounts(getPath());
        } catch (IOException | URISyntaxException io) {
            System.out.println(io);
            return rowCounts;
        }
        return rowCounts;
    }
}