package com.mjc.school.aruka.service;

import com.mjc.school.aruka.dto.TagDto;
import com.mjc.school.aruka.mapper.TagMapper;
import com.mjc.school.aruka.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public Page<TagDto> searchTags(String name, Pageable pageable) {
        return tagRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(tagMapper::toDto);
    }

    public Page<TagDto> getTagsWithPagination(Pageable pageable) {
        return tagRepository.findAll(pageable).map(tagMapper::toDto);
    }

    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream().map(tagMapper::toDto).toList();
    }

    public TagDto getTagById(Long id) {
        return tagMapper.toDto(tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with ID: " + id)));
    }

    public TagDto createTag(TagDto tagDto) {
        return tagMapper.toDto(tagRepository.save(tagMapper.toEntity(tagDto)));
    }

    public TagDto updateTag(Long id, TagDto tagDto) {
        var tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with ID: " + id));

        tag.setName(tagDto.getName());
        return tagMapper.toDto(tagRepository.save(tag));
    }

    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag not found with ID: " + id);
        }
        tagRepository.deleteById(id);
    }
}