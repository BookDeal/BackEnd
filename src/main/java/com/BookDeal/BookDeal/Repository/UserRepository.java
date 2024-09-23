package com.BookDeal.BookDeal.Repository;

import com.BookDeal.BookDeal.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
