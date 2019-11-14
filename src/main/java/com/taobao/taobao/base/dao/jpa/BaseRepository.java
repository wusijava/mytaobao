package com.taobao.taobao.base.dao.jpa;

import com.taobao.taobao.entity.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author lvlu
 * @date 2019-01-19 11:19
 **/
@NoRepositoryBean
public interface BaseRepository<T extends Identifiable<ID>, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
