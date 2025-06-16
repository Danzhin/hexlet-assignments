package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    public AuthorDTO create(AuthorCreateDTO dto) {
        var author = mapper.map(dto);
        repository.save(author);
        return mapper.map(author);
    }

    public List<AuthorDTO> readAll() {
        var authors = repository.findAll();
        return authors.stream().map(mapper::map).toList();
    }

    public AuthorDTO readById(Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        return mapper.map(author);
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO dto) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        mapper.update(dto, author);
        repository.save(author);
        return mapper.map(author);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
