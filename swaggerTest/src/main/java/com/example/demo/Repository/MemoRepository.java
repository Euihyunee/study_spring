package com.example.demo.Repository;

import com.example.demo.Model.DAO.MemoDAO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemoRepository extends JpaRepository<MemoDAO, Long> {
}
