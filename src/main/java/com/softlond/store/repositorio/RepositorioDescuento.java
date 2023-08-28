package com.softlond.store.repositorio;

import com.softlond.store.repositorio.entidades.DescuentoDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDescuento extends CrudRepository<DescuentoDAO,Long> {
}
