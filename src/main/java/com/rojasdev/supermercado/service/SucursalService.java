package com.rojasdev.supermercado.service;

import com.rojasdev.supermercado.DTO.SucursalCreate;
import com.rojasdev.supermercado.DTO.SucursalResponse;
import com.rojasdev.supermercado.entity.Sucursal;
import com.rojasdev.supermercado.repository.SucursalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SucursalService {

    private final SucursalRepository repository;

    @Transactional
    public void registrarSucursal(SucursalCreate dto){
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(dto.getNombre());
        sucursal.setCalle(dto.getCalle());
        sucursal.setColonia(dto.getColonia());
        sucursal.setCiudad(dto.getCiudad());
        sucursal.setCp(dto.getCp());

        repository.save(sucursal);
    }

    @Transactional(readOnly = true)
    public List<SucursalResponse> getSucursales(){
        return repository.findAll().stream().map(
                sucursal -> new SucursalResponse(
                        sucursal.getId(),
                        sucursal.getNombre(),
                        sucursal.getCalle()+", col. "+sucursal.getColonia()+
                                ", "+sucursal.getCiudad()+ " : "+sucursal.getCp()
                )
        ).toList();
    }


    public void eliminarSucursal(Long idSucursal){
        if(!repository.existsById(idSucursal)){
            throw new IllegalArgumentException("No existe una sucursal con esta ID");
        }

        repository.deleteById(idSucursal);
    }
}
