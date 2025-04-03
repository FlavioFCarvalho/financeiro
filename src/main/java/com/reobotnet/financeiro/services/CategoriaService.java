package com.reobotnet.financeiro.services;

import com.reobotnet.financeiro.entities.Categoria;
import com.reobotnet.financeiro.exceptions.CategoriaNotFoundException;
import com.reobotnet.financeiro.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MessageSource messageSource;

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(
                        messageSource.getMessage("categoria.nao.encontrada", new Object[]{id}, Locale.getDefault())
                ));
    }
}