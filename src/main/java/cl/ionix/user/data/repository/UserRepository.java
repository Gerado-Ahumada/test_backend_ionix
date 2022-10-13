package cl.ionix.user.data.repository;

import cl.ionix.user.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;





@Transactional
@Repository
  public interface UserRepository extends JpaRepository <UserEntity, Long>{
  UserEntity findByEmail(String email);
  UserEntity findByName(String name);
  @Modifying
  @Query("delete from UserEntity u where u.email like ?1")
  void deleteUserByEmail(String email);
  @Modifying
  @Query("update UserEntity u set u.name = ?1, u.username = ?2, u.phone = ?3 where u.email like ?4")
  void setUserInfoByEmail(String name, String username, Integer phone, String email);









  }


