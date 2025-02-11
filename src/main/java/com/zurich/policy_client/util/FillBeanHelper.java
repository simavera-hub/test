package com.zurich.policy_client.util;


import org.springframework.stereotype.Component;

/*import com.zurich.bts.timetracking.api.dto.UserBeanResponse;
import com.zurich.bts.timetracking.api.dto.UserProjectBeanDTO;
import com.zurich.bts.timetracking.entity.Provider;
import com.zurich.bts.timetracking.entity.Team;
import com.zurich.bts.timetracking.entity.User;
import com.zurich.utils.Utils;
import com.zurich.bts.timetracking.repository.IProviderRepository;
import com.zurich.bts.timetracking.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
*/
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FillBeanHelper {
 /*  @Autowired
    private IProviderRepository providerRepository;
    public UserBeanResponse fillUserBean(Optional<User> findById) {
        if(findById==null || findById.isEmpty()){
            return new UserBeanResponse();
        }else{
            return new UserBeanResponse(findById.get().getId(), findById.get().getProviderId(), 
            findById.get().getGadId(), findById.get().getType(), findById.get().getName(), findById.get().getEmail(),
             findById.get().getAreaId(), findById.get().getActive(), findById.get().getStartDate(), findById.get().getEndDate());
        }
    }
*/
}
