package com.example.demo.Service;

import com.example.demo.Model.DAO.MemoDAO;
import com.example.demo.Model.DTO.MemoSaveDTO;
import com.example.demo.Repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public void create(MemoSaveDTO memoSaveDTO) {
        // 입력된 MemoSaveDTO DB에 저장
        memoRepository.save(new MemoDAO(memoSaveDTO));
        log.debug("MemoSaveDTO -> MemoDAO save");
    }
}
