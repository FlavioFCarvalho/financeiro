package com.reobotnet.financeiro.services;

import com.reobotnet.financeiro.dtos.CategoriaDTO;
import com.reobotnet.financeiro.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reobotnet.financeiro.entities.Categoria;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarTodas() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(this::toDTO) // Usando o método manual de conversão
                .collect(Collectors.toList());
    }

    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        Categoria categoria = toEntity(categoriaDTO); // Convertendo DTO para entidade
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return toDTO(categoriaSalva); // Convertendo entidade para DTO
    }

    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
        return toDTO(categoria); // Convertendo entidade para DTO
    }

    // Método manual para converter Categoria para CategoriaDTO
    private CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }

    // Método manual para converter CategoriaDTO para Categoria
    private Categoria toEntity(CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null) {
            return null;
        }
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
}