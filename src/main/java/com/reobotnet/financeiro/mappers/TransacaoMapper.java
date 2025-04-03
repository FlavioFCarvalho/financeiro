package com.reobotnet.financeiro.mappers;

import com.reobotnet.financeiro.dto.TransacaoDTO;
import com.reobotnet.financeiro.entities.Categoria;
import com.reobotnet.financeiro.entities.Transacao;
import com.reobotnet.financeiro.services.CategoriaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TransacaoMapper {

    @Autowired
    private CategoriaService categoriaService;

    @Mapping(source = "categoria.id", target = "categoriaId")
    public abstract TransacaoDTO toDTO(Transacao transacao);

    @Mapping(source = "categoriaId", target = "categoria", qualifiedByName = "mapCategoria")
    public abstract Transacao toEntity(TransacaoDTO transacaoDTO);

    @Named("mapCategoria")
    protected Categoria mapCategoria(Long categoriaId) {
        return categoriaService.buscarPorId(categoriaId);
    }
}