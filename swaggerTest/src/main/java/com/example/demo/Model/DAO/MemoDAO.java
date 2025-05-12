package com.example.demo.Model.DAO;

import com.example.demo.Model.BaseEntity;
import com.example.demo.Model.DTO.MemoSaveDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(name = "GEN_MEMO",
    sequenceName = "SEQ_MEMO", initialValue = 1, allocationSize = 1)
@Table(name = "memo")
@Entity
public class MemoDAO extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_MEMO")
    @Id
    private long id;
    private String content;
    private String writer;

    public MemoDAO(String content) {
        this.content = content;
    }

    public MemoDAO() {
    }

    public MemoDAO(MemoSaveDTO memoSaveDTO) {
        this.content = memoSaveDTO.getContent();
        this.writer = memoSaveDTO.getWriter();
    }
}
