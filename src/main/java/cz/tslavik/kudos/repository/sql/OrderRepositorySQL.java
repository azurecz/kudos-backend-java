//package cz.tslavik.Kudosservice.repository.sql;
//
//import cz.csas.users.entity.Users;
//import cz.tslavik.Kudosservice.entity.Kudos;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Transactional(propagation = Propagation.REQUIRED)
//@Repository
//public interface KudosRepository extends PagingAndSortingRepository<Kudos,Integer> {
//
//    Users findByUserNumber(String usernumber);
//
//    @Query("SELECT u FROM Users u JOIN u.userRole ur WHERE " +
//            "(:firstname is null OR u.firstName LIKE %:firstname%) OR " +
//            "(:surname is null OR u.surname LIKE %:surname%) OR " +
//            "(:email is null OR u.email LIKE %:email%) OR " +
//            "(:departmentname is null OR u.departmentName LIKE %:departmentname%) OR " +
//            "(:roleids is null OR ur.id in :roleids)")
//    Page<Users> usersPage(@Param("firstname") String firstname,
//                          @Param("surname") String surname,
//                          @Param("email") String email,
//                          @Param("departmentname") String departmentname,
//                          @Param("roleids") List<Integer> roleids,
//                          Pageable pageable);
//
//    @Query("SELECT u FROM Users u WHERE u.id in :ids")
//    List<Users> findUsersByIDs(List<Integer> ids);
//
//}
