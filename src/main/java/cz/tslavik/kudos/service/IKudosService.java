package cz.tslavik.kudos.service;

import cz.tslavik.kudos.entity.Kudos;

/**
 * Created by cen80475
 */
public interface IKudosService {

    Kudos getKudosById (String userNumber);

//    User getUserDTOByUserId(Integer userId);
//
//    User getUserDTOByUsernumber(String usernumber);
//
//    LinkedHashSet<User> userLinkedHashSet(List<Integer> userIds);
}
