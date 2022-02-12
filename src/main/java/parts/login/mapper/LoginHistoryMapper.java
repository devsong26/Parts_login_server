package parts.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import parts.login.domain.LoginHistory;

@Mapper
public interface LoginHistoryMapper {

    void save(LoginHistory loginHistory);
}
