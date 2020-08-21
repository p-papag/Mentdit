package com.example.mentdit.service;

import com.example.mentdit.dto.SubmentditDto;
import com.example.mentdit.exception.MentditException;
import com.example.mentdit.mapper.SubmentditMapper;
import com.example.mentdit.model.Submentdit;
import com.example.mentdit.repository.SubmentditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubmentditService {

    private final SubmentditRepository submentditRepository;
    private final SubmentditMapper submentditMapper;

    @Transactional
    public SubmentditDto save(SubmentditDto submentditDto){
       Submentdit save = submentditRepository.save(submentditMapper.mapDtoToSubmentdit(submentditDto));
       submentditDto.setId(save.getId());
       return submentditDto;
    }

    @Transactional(readOnly = true)
    public List<SubmentditDto> getAll() {
        return submentditRepository.findAll()
                .stream()
                .map(submentditMapper::mapSubmentditToDto)
                .collect(toList());
    }

    public SubmentditDto getSubmentdit(Long id) {
        Submentdit submentdit = submentditRepository.findById(id)
                .orElseThrow(() -> new MentditException("No submentdit found with ID - " + id));
        return submentditMapper.mapSubmentditToDto(submentdit);
    }


}
