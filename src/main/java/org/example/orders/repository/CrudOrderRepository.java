//package org.example.orders.repository;
//
//import org.example.orders.model.Request;
//import org.example.orders.model.Status;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Transactional(readOnly = true)
//public interface CrudOrderRepository extends JpaRepository<Request, Integer> {
//
//
//    @Query("SELECT o FROM Request o WHERE o.id=:id and o.user.id=:userId")
//    Request get(@Param("id") int id, @Param("userId") int userId);
//
//    @Query("UPDATE Request o SET o.status=:status WHERE o.id=:id")
//    boolean changeStatus(int id, Status status);
//
//    @Query("SELECT o FROM Request o WHERE o.status='SENT' ORDER BY o.registration DESC ")
//    List<Request> getAll();
//
//    @Query("SELECT o FROM Request o WHERE o.user.id=:userId ORDER BY o.registration DESC")
//    List<Request> getAllByUser(int userId);
//
//}
